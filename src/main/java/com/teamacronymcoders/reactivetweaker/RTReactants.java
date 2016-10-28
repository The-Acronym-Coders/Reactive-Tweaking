package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.utils.BaseMapAddition;
import com.teamacronymcoders.reactivetweaker.actions.BaseMultiMapAddition;
import erogenousbeef.bigreactors.api.data.OreDictToReactantMapping;
import erogenousbeef.bigreactors.api.data.ReactantData;
import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.HashMap;
import java.util.Map;

import static com.teamacronymcoders.reactivetweaker.RTHelper.*;

@ZenClass("mod.reactivetweaker.Reactants")
public class RTReactants {

    @ZenMethod
    public static void registerReactant(String key, boolean fuel, @Optional int color) {
        Map<String, ReactantData> data = new HashMap<>();
        data.put(key, new ReactantData(key, fuel ? ReactantData.ReactantType.Fuel : ReactantData.ReactantType.Waste, color));
        MineTweakerAPI.apply(new BaseMapAddition<String, ReactantData>("Reactants", reactants, data) {
            @Override
            protected String getRecipeInfo(Map.Entry<String, ReactantData> recipe) {
                return recipe.getKey();
            }
        });
    }

    @ZenMethod
    public static void registerSolid(IIngredient ing, String reactantName, @Optional int reactantQty) {
        if(reactantQty == 0) {
            reactantQty = 1000;
        }
        Map<String, SourceProductMapping> data = new HashMap<>();
        data.put(reactantName, new OreDictToReactantMapping((String) ing.getInternal(), reactantName, reactantQty));
        MineTweakerAPI.apply(new BaseMultiMapAddition("Solids", solidToReactant, reactantToSolid, data) {
            @Override
            protected String getRecipeInfo(Map.Entry recipe) {
                return recipe.getKey() + "";
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
