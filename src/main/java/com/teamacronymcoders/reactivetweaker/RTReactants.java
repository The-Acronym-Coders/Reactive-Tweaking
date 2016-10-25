package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.Reactants;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mod.reactivetweaker.Reactants")
public class RTReactants {
    @ZenMethod
    public static void registerReactants(String name, boolean fuel) {
        Reactants.registerReactant(name, fuel);
    }

    @ZenMethod
    public static void registerReactant(String name, int type, int color) {
        Reactants.registerReactant(name, type, color);
    }

    @ZenMethod
    public static void registerSolid(ItemStack itemStack, String reactantName) {
        Reactants.registerSolid(itemStack, reactantName);
    }

    @ZenMethod
    public static void registerSolid(ItemStack itemStack, String reactantName, int reactantQty) {
        Reactants.registerSolid(itemStack, reactantName, reactantQty);
    }

    @ZenMethod
    public static void registerSolid(String oreDictName, String reactantName, int reactantAmount) {
        Reactants.registerSolid(oreDictName, reactantName, reactantAmount);
    }

    @ZenMethod
    public static void registerSolid(String oreDictName, String reactantName) {
        Reactants.registerSolid(oreDictName, reactantName);
    }

    @ZenMethod
    public static void registerFluid(FluidStack fluidStack, String reactantName) {
        Reactants.registerFluid(fluidStack, reactantName);
    }

    @ZenMethod
    public static void registerFluid(Fluid fluid, String reactantName) {
        Reactants.registerFluid(fluid, reactantName);
    }
}
