package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.utils.BaseUndoable;
import erogenousbeef.bigreactors.api.registry.ReactorConversions;
import minetweaker.MineTweakerAPI;
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
        float finalReactivity = reactivity;
        float finalFissionRate = fissionRate;
        MineTweakerAPI.apply(new BaseUndoable("Reactor Conversions") {
            @Override
            public void apply() {
                RTHelper.register(sourceName, productName, finalReactivity, finalFissionRate);
            }

            @Override
            public void undo() {
                RTHelper.unregister(sourceName);
            }
        });
    }

}
