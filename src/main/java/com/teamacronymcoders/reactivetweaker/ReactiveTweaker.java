package com.teamacronymcoders.reactivetweaker;

import minetweaker.MineTweakerAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.teamacronymcoders.reactivetweaker.ReactiveTweaker.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDS)
public class ReactiveTweaker {
    public final static String MODID = "reactivetweaker";
    public final static String NAME = "Reactive Tweaker";
    public final static String VERSION = "@VERSION@";
    public final static String DEPENDS = "required-after:MineTweaker3;required-after:bigreactors;";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MineTweakerAPI.registerClass(RTReactants.class);
        MineTweakerAPI.registerClass(RTReactorConversions.class);
        MineTweakerAPI.registerClass(RTReactorInterior.class);
    }
}
