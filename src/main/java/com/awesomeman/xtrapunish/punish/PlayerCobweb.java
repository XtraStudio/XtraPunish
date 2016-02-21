/*
The MIT License (MIT)

Copyright (c) 2016 12AwesomeMan34 / 12AwsomeMan34

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.awesomeman.xtrapunish.punish;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.awesomeman.xtrapunish.XtraPunish;
import com.awesomeman.xtrapunish.api.punish.Punishment;

public class PlayerCobweb implements Punishment {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Optional<Player> optional = args.<Player>getOne("player");
        if(!optional.isPresent()) {
            src.sendMessage(Text.of(TextColors.RED, "Player argument not specified! Correct usage: /punish cobweb <player>"));
            return CommandResult.empty();
        }
        Player player = optional.get();
        Location<World> loc = player.getLocation();
        
        // Immediately around the player
        Location<World> loc1 = loc.getRelative(Direction.NORTH);
        Location<World> loc2 = loc.getRelative(Direction.EAST);
        Location<World> loc3 = loc.getRelative(Direction.SOUTH);
        Location<World> loc4 = loc.getRelative(Direction.WEST);
        // Corners
        Location<World> loc5 = loc1.getRelative(Direction.WEST);
        Location<World> loc6 = loc1.getRelative(Direction.EAST);
        Location<World> loc7 = loc3.getRelative(Direction.WEST);
        Location<World> loc8 = loc3.getRelative(Direction.EAST);
        // Tops
        Location<World> loc_1 = loc.getRelative(Direction.UP);
        Location<World> loc1_1 = loc1.getRelative(Direction.UP);
        Location<World> loc1_2 = loc2.getRelative(Direction.UP);
        Location<World> loc1_3 = loc3.getRelative(Direction.UP);
        Location<World> loc1_4 = loc4.getRelative(Direction.UP);
        Location<World> loc1_5 = loc5.getRelative(Direction.UP);
        Location<World> loc1_6 = loc6.getRelative(Direction.UP);
        Location<World> loc1_7 = loc7.getRelative(Direction.UP);
        Location<World> loc1_8 = loc8.getRelative(Direction.UP);
        
        List<Location<World>> locs = new ArrayList<>();
        locs.add(loc);
        locs.add(loc1);
        locs.add(loc2);
        locs.add(loc3);
        locs.add(loc4);
        locs.add(loc5);
        locs.add(loc6);
        locs.add(loc7);
        locs.add(loc8);
        locs.add(loc_1);
        locs.add(loc1_1);
        locs.add(loc1_2);
        locs.add(loc1_3);
        locs.add(loc1_4);
        locs.add(loc1_5);
        locs.add(loc1_6);
        locs.add(loc1_7);
        locs.add(loc1_8);
        
        List<BlockState> states = new ArrayList<>();
        
        for(Location<World> location : locs) {
            states.add(location.getBlock());
            location.setBlockType(BlockTypes.WEB);
        }
        // We made it to the end. Happy face. Now to track the player
        XtraPunish.instance.cobwebManager.addPlayer(player, locs, states);
        
        src.sendMessage(Text.of(TextColors.GREEN, "Success! ", TextColors.GOLD, "Player " + player.getName() + " will be engulfed in cobwebs!"));
        
        return CommandResult.success();
    }

    @Override
    public String permission() {
        return "xtrapunish.cobweb";
    }

    @Override
    public Text description() {
        return Text.of("Spawns cobwebs around a player. When the player exists the cobwebs, they disappear!");
    }

    @Override
    public Text helpDescription() {
        return Text.of(TextColors.GREEN, "/punish cobweb <player> - ", TextColors.GOLD, "Spawns cobweb around the player. The cobweb will disappear when the player exits the cobwebs.");
    }

    @Override
    public Optional<CommandElement> arguments() {
        return Optional.of(GenericArguments.optional(GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))));
    }

    @Override
    public String command() {
        return "cobweb";
    }
}
