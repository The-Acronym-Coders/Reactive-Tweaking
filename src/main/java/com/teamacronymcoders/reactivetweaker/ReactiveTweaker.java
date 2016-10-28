package com.teamacronymcoders.reactivetweaker;

import com.teamacronymcoders.reactivetweaker.commands.CommandLoggerFluids;
import com.teamacronymcoders.reactivetweaker.commands.CommandLoggerReactants;
import com.teamacronymcoders.reactivetweaker.commands.CommandLoggerReactions;
import com.teamacronymcoders.reactivetweaker.commands.CommandLoggerSolids;
import minetweaker.MineTweakerAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
        MineTweakerAPI.registerClass(RTTurbineCoil.class);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        if(MineTweakerAPI.server != null) {
            MineTweakerAPI.server.addMineTweakerCommand("erFluids", new String[]{"/minetweaker erFluids", "    Outputs a list of all Extreme Reactors fluid names in the game to the minetweaker log"}, new CommandLoggerFluids());
            MineTweakerAPI.server.addMineTweakerCommand("erReactants", new String[]{"/minetweaker erReactants", "    Outputs a list of all Extreme Reactors reactants names in the game to the minetweaker log"}, new CommandLoggerReactants());
            MineTweakerAPI.server.addMineTweakerCommand("erReactions", new String[]{"/minetweaker erReactions", "    Outputs a list of all Extreme Reactors reactions names in the game to the minetweaker log"}, new CommandLoggerReactions());
            MineTweakerAPI.server.addMineTweakerCommand("erSolids", new String[]{"/minetweaker erSolids", "    Outputs a list of all Extreme Reactors solids names in the game to the minetweaker log"}, new CommandLoggerSolids());
        }
    }

}
