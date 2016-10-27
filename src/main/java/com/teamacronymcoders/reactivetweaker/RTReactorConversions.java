package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.ReactorConversions")
public class RTReactorConversions {
    @ZenMethod
    public static void register(String sourceName, String productName, @Optional float reactivity, @Optional float fissionRate) {
        if(reactivity == 0) {
            reactivity = 1.05F;
        }
        if(fissionRate == 0) {
            fissionRate = 0.01F;
        }
        ReactorConversions.register(sourceName, productName, reactivity, fissionRate);
    }

}
