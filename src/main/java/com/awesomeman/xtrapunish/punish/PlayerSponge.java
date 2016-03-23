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

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.type.GridInventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.awesomeman.xtrapunish.api.punish.Punishment;
import com.awesomeman.xtrapunish.util.AffectedBlocks;

public class PlayerSponge implements Punishment {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Optional<Player> optional = args.<Player>getOne("player");
        if(!optional.isPresent()) {
            src.sendMessage(Text.of(TextColors.RED, "Player argument not specified! Correct usage: /punish sponge <player>"));
            return CommandResult.empty();
        }
        Player player = optional.get();
        ItemStack sponge = ItemStack.builder().itemType(ItemTypes.SPONGE).build();
        
        Inventory inv  = player.getInventory().query(GridInventory.class);
        Inventory hotbar = player.getInventory().query(Hotbar.class);
        for(Inventory slot : inv.slots()) {
            slot.set(sponge);
        }
        for(Inventory slot : hotbar.slots()) {
            slot.set(sponge);
        }
        return CommandResult.success();
    }

    @Override
    public String permission() {
        return "xtrapunish.sponge";
    }

    @Override
    public Text description() {
        return Text.of("Sets everything in a player's inventory to sponge.");
    }

    @Override
    public Text helpDescription() {
        return Text.of(TextColors.GREEN, "/punish sponge <player> - ", TextColors.GOLD, "Replaces all items in a player's inventory with sponges.");
    }

    @Override
    public Optional<CommandElement> arguments() {
        return Optional.of(GenericArguments.optional(GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))));
    }

    @Override
    public String[] command() {
        return new String[] { "sponge" };
    }

    @Override
    public Optional<List<AffectedBlocks>> affectedBlocks() {
        return Optional.empty();
    }
}
