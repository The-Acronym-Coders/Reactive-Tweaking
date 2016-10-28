package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.helpers.ReflectionHelper;
import erogenousbeef.bigreactors.api.data.*;
import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import erogenousbeef.bigreactors.api.registry.ReactorInterior;
import erogenousbeef.bigreactors.api.registry.TurbineCoil;
import erogenousbeef.bigreactors.common.BRLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RTHelper {

    public static Map<String, ReactantData> reactants = null;
    public static Map<String, OreDictToReactantMapping> solidToReactant = null;
    public static Map<String, FluidToReactantMapping> fluidToReactant = null;
    public static Map<String, List<SourceProductMapping>> reactantToSolid = null;
    public static Map<String, List<SourceProductMapping>> reactantToFluid = null;

    public static Map<String, ReactorReaction> reactions = null;

    public static Map<String, ReactorInteriorData> reactorModeratorBlocks = null;
    public static Map<String, ReactorInteriorData> reactorModeratorFluids = null;

    private static Map<String, CoilPartData> blocks = null;


    static {
        try {
            reactants = ReflectionHelper.getStaticObject(Reactants.class, "_reactants");
            solidToReactant = ReflectionHelper.getStaticObject(Reactants.class, "_solidToReactant");
            fluidToReactant = ReflectionHelper.getStaticObject(Reactants.class, "_fluidToReactant");
            reactantToSolid = ReflectionHelper.getStaticObject(Reactants.class, "_reactantToSolid");
            reactantToFluid = ReflectionHelper.getStaticObject(Reactants.class, "_reactantToFluid");

            reactions = ReflectionHelper.getStaticObject(ReactorConversions.class, "_reactions");

            reactorModeratorBlocks = ReflectionHelper.getStaticObject(ReactorInterior.class, "_reactorModeratorBlocks");
            reactorModeratorFluids = ReflectionHelper.getStaticObject(ReactorInterior.class, "_reactorModeratorFluids");

            blocks = ReflectionHelper.getStaticObject(TurbineCoil.class, "_blocks");

        } catch(Exception e) {
            System.out.println("Unable to access API fields.");
        }
    }

    private RTHelper() {

    }

    public static CoilPartData removeBlock(String oreDictName) {
        return blocks.remove(oreDictName);
    }

    public static CoilPartData registerBlock(String oreDictName, float efficiency, float bonus, float extractionRate) {
        if(blocks.containsKey(oreDictName)) {
            CoilPartData data = blocks.get(oreDictName);
            BRLog.warning("Overriding existing coil part data for oredict name <%s>, original values: eff %.2f / bonus %.2f, new values: eff %.2f / bonus %.2f", oreDictName, data.efficiency, data.bonus, efficiency, bonus);
            data.efficiency = efficiency;
            data.bonus = bonus;
            return data;
        } else {
            return blocks.put(oreDictName, new CoilPartData(efficiency, bonus, extractionRate));
        }

    }

    public static ReactorInteriorData removeInteriourBlock(String oreDictName) {
        return reactorModeratorBlocks.remove(oreDictName);
    }

    public static ReactorInteriorData removeInteriourFluid(String oreDictName) {
        return reactorModeratorFluids.remove(oreDictName);
    }

    public static ReactorInteriorData registerInteriourBlock(String oreDictName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        if(reactorModeratorBlocks.containsKey(oreDictName)) {
            BRLog.warning("Overriding existing radiation moderator block data for oredict name <%s>", oreDictName);
            ReactorInteriorData data = reactorModeratorBlocks.get(oreDictName);
            data.absorption = absorption;
            data.heatEfficiency = heatEfficiency;
            data.moderation = moderation;
            return data;
        } else {
            return reactorModeratorBlocks.put(oreDictName, new ReactorInteriorData(absorption, heatEfficiency, moderation, heatConductivity));
        }

    }

    public static ReactorInteriorData registerInteriourFluid(String fluidName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        if(reactorModeratorFluids.containsKey(fluidName)) {
            BRLog.warning("Overriding existing radiation moderator fluid data for fluid name <%s>", fluidName);
            ReactorInteriorData data = reactorModeratorFluids.get(fluidName);
            data.absorption = absorption;
            data.heatEfficiency = heatEfficiency;
            data.moderation = moderation;
            return data;
        } else {
            return reactorModeratorFluids.put(fluidName, new ReactorInteriorData(absorption, heatEfficiency, moderation, heatConductivity));
        }

    }

    public static ReactorReaction register(String sourceName, String productName, float reactivity, float fissionRate) {
        if(reactions.containsKey(sourceName)) {
            BRLog.warning("Overwriting %s => %s reaction mapping! Someone may be fiddling with Extreme Reactors game data!", sourceName, productName);
        }

        ReactorReaction mapping = new ReactorReaction(sourceName, productName, reactivity, fissionRate);
        return reactions.put(sourceName, mapping);
    }

    public static ReactorReaction unregister(String sourceName) {
        return reactions.remove(sourceName);
    }


    public static ReactantData registerReactant(String name, int type, int color) {
        if(type >= 0 && type < ReactantData.TYPES.length) {
            if(reactants.containsKey(name)) {
                BRLog.warning("Overwriting data for reactant %s - someone may be altering BR game data or have duplicate reactant names!", name);
            }

            ReactantData data = new ReactantData(name, ReactantData.TYPES[type], color);
            return reactants.put(name, data);
        } else {
            throw new IllegalArgumentException("Unsupported type; value may only be 0 or 1");
        }
    }

    public static ReactantData removeReactant(String name) {
        return reactants.remove(name);
    }

    public static OreDictToReactantMapping removeSolid(String key) {
        reactantToSolid.remove(key);
        return solidToReactant.remove(key);
    }

//    public static FluidToReactantMapping removeFluid(String key) {
//        reactantToFluid.remove(key);
//        return fluidToReactant.remove(key);
//    }

    public static SourceProductMapping registerSolid(String oreDictName, String reactantName, int reactantAmount) {
        if(!reactants.containsKey(reactantName)) {
            throw new IllegalArgumentException("Unknown reactantName " + reactantName);
        } else {
            OreDictToReactantMapping mapping = new OreDictToReactantMapping(oreDictName, reactantName, reactantAmount);
            OreDictToReactantMapping old = solidToReactant.put(mapping.getSource(), mapping);
            SourceProductMapping reverseMapping = mapping.getReverse();
            mapReactant(reverseMapping.getSource(), reverseMapping, reactantToSolid);
            return old;
        }
    }

//    public static FluidToReactantMapping registerFluid(FluidStack fluidStack, String reactantName) {
//        if(!reactants.containsKey(reactantName)) {
//            throw new IllegalArgumentException("Unknown reactantName " + reactantName);
//        } else {
//            FluidToReactantMapping mapping = new FluidToReactantMapping(fluidStack, reactantName);
//            SourceProductMapping reverseMapping = mapping.getReverse();
//            mapReactant(reverseMapping.getSource(), reverseMapping, reactantToFluid);
//            return fluidToReactant.put(mapping.getSource(), mapping);
//
//        }
//    }

    private static void mapReactant(String reactantName, SourceProductMapping mapping, Map<String, List<SourceProductMapping>> map) {
        List<SourceProductMapping> list;
        if(map.containsKey(reactantName) && map.get(reactantName) != null) {
            list = map.get(reactantName);
            list.add(mapping);
        } else {
            list = new ArrayList<>();
            map.put(reactantName, list);
        }
        list.add(mapping);
    }
}
