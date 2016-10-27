package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.Reactants;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IngredientItem;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.oredict.IngredientOreDict;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mod.reactivetweaker.Reactants")
public class RTReactants {

    @ZenMethod
    public static void registerReactants(String name, boolean fuel, @Optional int color) {
        Reactants.registerReactant(name, (fuel ? 0 : 1), color);
    }

    @ZenMethod
    public static void registerSolid(IIngredient ing, String reactantName, @Optional int reactantQty) {
        if(reactantQty == 0) {
            reactantQty = 1000;
        }
        if(ing instanceof IngredientOreDict) {
            IngredientOreDict ore = (IngredientOreDict) ing;
            Reactants.registerSolid((String) ore.getInternal(), reactantName, reactantQty);
        } else if(ing instanceof IngredientItem) {
            IngredientItem item = (IngredientItem) ing;
            Reactants.registerSolid((ItemStack) item.getInternal(), reactantName, reactantQty);
        }
    }

    @ZenMethod
    public static void registerFluid(ILiquidStack stack, String reactantName) {
        Reactants.registerFluid((FluidStack) stack.getDefinition().asStack(stack.getAmount()).getInternal(), reactantName);
    }

}
