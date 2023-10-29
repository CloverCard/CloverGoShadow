package com.clovercard.clovergoshadow.commands;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.helpers.ExpHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ModifyStats {
    public ModifyStats(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("clovergoshadow")
                        .then(
                                Commands.literal("addexp").requires((src) -> CommandHelper.hasPermission(src, 2, "clovergoshadow.command.addexp"))
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(Commands.argument("exp", IntegerArgumentType.integer(1))
                                                                .executes(cmd -> addExp(cmd.getSource(), EntityArgument.getPlayer(cmd, "player"), IntegerArgumentType.getInteger(cmd, "exp")))
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("addlevel").requires((src) -> CommandHelper.hasPermission(src, 2, "clovergoshadow.command.addlevel"))
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(Commands.argument("level", IntegerArgumentType.integer(1, 50))
                                                                .executes(cmd -> addLevel(cmd.getSource(), EntityArgument.getPlayer(cmd, "player"), IntegerArgumentType.getInteger(cmd, "level")))
                                                )
                                        )
                        )
                        .then(
                                Commands.literal("setlevel").requires((src) -> CommandHelper.hasPermission(src, 2, "clovergoshadow.command.setlevel"))
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(Commands.argument("level", IntegerArgumentType.integer(0, 50))
                                                                .executes(cmd -> setLevel(cmd.getSource(), EntityArgument.getPlayer(cmd, "player"), IntegerArgumentType.getInteger(cmd, "level")))
                                                        )
                                        )
                        )
                        .then(
                                Commands.literal("setexp").requires((src) -> CommandHelper.hasPermission(src, 2, "clovergoshadow.command.setexp"))
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(Commands.argument("exp", IntegerArgumentType.integer(0))
                                                                .executes(cmd -> setExp(cmd.getSource(), EntityArgument.getPlayer(cmd, "player"), IntegerArgumentType.getInteger(cmd, "exp")))
                                                        )
                                        )
                        )
        );
    }
    private int addExp(CommandSource src, ServerPlayerEntity target, int amount) {
        ExpHelper.addExpToPlayer(target, amount);
        return 1;
    }
    private int addLevel(CommandSource src, ServerPlayerEntity target, int amount) {
        ExpHelper.addLevelToPlayer(target, amount);
        return 1;
    }
    private int setExp(CommandSource src, ServerPlayerEntity target, int amount) {
        ExpHelper.setExpForPlayer(target, amount);
        return 1;
    }
    private int setLevel(CommandSource src, ServerPlayerEntity target, int amount) {
        ExpHelper.setLevelForPlayer(target, amount);
        return 1;
    }
}
