/**
 * This file is part of XtraPunish, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 - 2016 XtraStudio <https://github.com/XtraStudio>
 * Copyright (c) Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.awesomeman.xtrapunish.punish;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.awesomeman.xtrapunish.api.punish.Punishment;
import com.awesomeman.xtrapunish.manager.Managers;

/**
 * Stops that annoying broadcast!
 */
public class BroadcastStop implements Punishment {
    
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(Managers.broadcastManager.cancelBroadcast()) {
            src.sendMessage(Text.of(TextColors.RED, "Broadcast is not running!"));
        } else {
            src.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.GOLD, "Broadcast successfully stopped."));
        }
        return CommandResult.success();
    }

    @Override
    public String permission() {
        return "xtrapunish.broadcast.stop";
    }

    @Override
    public Text description() {
        return Text.of("Stops that annoying broadcast!");
    }

    @Override
    public Text helpDescription() {
        return Text.of(TextColors.GREEN, "/punish stop-broadcast - ", TextColors.GOLD, "Stops the broadcast.");
    }

    @Override
    public Optional<CommandElement> arguments() {
        return Optional.empty();
    }

    @Override
    public String[] command() {
        return new String[] { "stop-broadcast" };
    }
}
