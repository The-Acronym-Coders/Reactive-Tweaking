package com.teamacronymcoders.reactivetweaker;

import com.blamejared.mtlib.utils.BaseUndoable;
import erogenousbeef.bigreactors.api.registry.ReactorInterior;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.reactivetweaker.ReactorInterior")
public class RTReactorInterior {

    @ZenMethod
    public static void registerBlock(String oreDictName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        ReactorInterior.registerBlock(oreDictName, absorption, heatEfficiency, moderation, heatConductivity);
        MineTweakerAPI.apply(new BaseUndoable("Reactor Interior") {
            @Override
            public void apply() {
                RTHelper.registerInteriourBlock(oreDictName, absorption, heatEfficiency, moderation, heatConductivity);
            }

            @Override
            public void undo() {
                RTHelper.removeInteriourBlock(oreDictName);
            }
        });
    }

    @ZenMethod
    public static void registerFluid(String fluidName, float absorption, float heatEfficiency, float moderation, float heatConductivity) {
        MineTweakerAPI.apply(new BaseUndoable("Reactor Interior") {
            @Override
            public void apply() {
                RTHelper.registerInteriourFluid(fluidName, absorption, heatEfficiency, moderation, heatConductivity);
            }

            @Override
            public void undo() {
                RTHelper.removeInteriourFluid(fluidName);
            }
        });
    }
}
