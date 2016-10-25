package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.TurbineCoil;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.TurbineCoil")
public class RTTurbineCoil {
    @ZenMethod
    public static void registerBlock(String oreDictName, float efficiency, float bonus, float extractionRate) {
        TurbineCoil.registerBlock(oreDictName, efficiency, bonus, extractionRate);
    }
}
