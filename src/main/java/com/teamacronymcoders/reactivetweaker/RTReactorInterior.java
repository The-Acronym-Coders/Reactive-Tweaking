package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.ReactorInterior;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.ReactorInterior")
public class RTReactorInterior {
    @ZenMethod
    public static void registerBlock(String oreDictName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        ReactorInterior.registerBlock(oreDictName, absorption, heatEfficiency, moderation, heatConductivity);
    }

    @ZenMethod
    public static void registerFluid(String fluidName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        ReactorInterior.registerFluid(fluidName, absorption, heatEfficiency, moderation, heatConductivity);
    }
}
