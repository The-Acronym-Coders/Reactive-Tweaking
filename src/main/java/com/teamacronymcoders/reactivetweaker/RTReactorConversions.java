package com.teamacronymcoders.reactivetweaker;

import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.ReactorConversions")
public class RTReactorConversions {
    @ZenMethod
    public static void register(String sourceName, String productName) {
        ReactorConversions.register(sourceName, productName);
    }

    @ZenMethod
    public static void register(String sourceName, String productName, float reactivity, float fissionRate) {
        ReactorConversions.register(sourceName, productName, reactivity, fissionRate);
    }
}
