package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.utils.BaseUndoable;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mod.reactivetweaker.Reactants")
public class RTReactants {

    @ZenMethod
    public static void registerReactant(String key, boolean fuel, @Optional int color) {
        MineTweakerAPI.apply(new BaseUndoable("Reactants") {
            @Override
            public void apply() {
                RTHelper.registerReactant(key, fuel ? 0 : 1, color);
            }

            @Override
            public void undo() {
                RTHelper.removeReactant(key);
            }
        });
    }

    @ZenMethod
    public static void registerSolid(IIngredient ing, String reactantName, @Optional int reactantQty) {
        if(reactantQty == 0) {
            reactantQty = 1000;
        }
        int finalReactantQty = reactantQty;
        MineTweakerAPI.apply(new BaseUndoable("Solids") {

            @Override
            public void apply() {
                RTHelper.registerSolid((String) ing.getInternal(), reactantName, finalReactantQty);
            }

            @Override
            public void undo() {
                RTHelper.removeSolid((String) ing.getInternal());
            }
        });
    }

    //not implemented in ER yet
//    @ZenMethod
//    public static void registerFluid(ILiquidStack stack, String reactantName) {
//        MineTweakerAPI.apply(new BaseUndoable("Fluids") {
//            @Override
//            public void apply() {
//                RTHelper.registerFluid(InputHelper.toFluid(stack), reactantName);
//            }
//
//            @Override
//            public void undo() {
//                RTHelper.removeFluid(reactantName);
//            }
//        });
//    }

}
