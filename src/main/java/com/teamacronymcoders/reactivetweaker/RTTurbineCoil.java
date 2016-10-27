package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.utils.BaseUndoable;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.TurbineCoil")
public class RTTurbineCoil {
    @ZenMethod
    public static void registerBlock(String oreDictName, float efficiency, float bonus, float extractionRate) {
        MineTweakerAPI.apply(new BaseUndoable("Turbine Coil") {
            @Override
            public void apply() {
                RTHelper.registerBlock(oreDictName, efficiency, bonus, extractionRate);
            }

            @Override
            public void undo() {
                RTHelper.removeBlock(oreDictName);
            }
        });
    }
}
