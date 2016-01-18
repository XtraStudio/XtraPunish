package com.awesomeman.xtrapunish.punish;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

/**
 * Continuously broadcasts a message to the entire server until /punish broadcaststop is ran.
 */
public class BroadcastOverlord implements CommandExecutor {
    
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        return CommandResult.success();
    }
}