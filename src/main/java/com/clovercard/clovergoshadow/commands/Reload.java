package com.clovercard.clovergoshadow.commands;

import com.clovercard.clovergoshadow.config.Config;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class Reload {
    public Reload(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("clovergoshadow")
                        .then(
                                Commands.literal("reload").requires((src) -> CommandHelper.hasPermission(src, 2, "clovergoshadow.command.reload"))
                                        .executes(cmd -> reloadConfig(cmd.getSource()))
                        )
        );
    }
    private int reloadConfig(CommandSource src) {
        if(Config.load()) {
            IFormattableTextComponent msg = new TranslationTextComponent("clovergoshadow.reloadedconfig");
            if(src.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) src.getEntity();
                msg.setStyle(msg.getStyle().applyFormat(TextFormatting.GREEN));
                player.sendMessage(msg, Util.NIL_UUID);
            }
            else {
                System.out.println("Reloaded Config!");
            }
        }
        else {
            IFormattableTextComponent msg = new TranslationTextComponent("clovergoshadow.reloadedconfigfailed");
            if(src.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) src.getEntity();
                msg.setStyle(msg.getStyle().applyFormat(TextFormatting.GREEN));
                player.sendMessage(msg, Util.NIL_UUID);
            }
            else {
                System.out.println("Failed To Reload Config!");
            }
        }
        return 1;
    }
}
