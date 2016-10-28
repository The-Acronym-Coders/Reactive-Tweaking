package com.teamacronymcoders.reactivetweaker.commands;

import com.blamejared.mtlib.commands.CommandLogger;
import com.teamacronymcoders.reactivetweaker.RTHelper;
import erogenousbeef.bigreactors.common.BigReactors;

import java.util.Collection;

/**
 * Created by Jared.
 */
public class CommandLoggerReactions extends CommandLogger {

    @Override
    public Collection<? extends String> getList() {
        return RTHelper.reactions.keySet();
    }

    @Override
    public String getName() {
        return BigReactors.NAME + " Reactions";
    }
}
