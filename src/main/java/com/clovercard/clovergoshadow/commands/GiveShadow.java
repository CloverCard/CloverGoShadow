package com.clovercard.clovergoshadow.commands;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.api.pokemon.PokemonSpecification;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class GiveShadow {
    public GiveShadow(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("clovergoshadowadmin")
                        .then(
                                Commands.literal("giveshadow")
                                        .then(
                                                Commands.argument("player", StringArgumentType.string())
                                                        .then(
                                                                Commands.argument("specs", StringArgumentType.greedyString())
                                                                        .executes(cmd -> giveShadow(cmd.getSource(), StringArgumentType.getString(cmd, "player"), StringArgumentType.getString(cmd, "specs")))
                                                        )
                                        )
                        )
        );
    }
    public int giveShadow(CommandSource src, String target, String specs) {
        if(!(src.getEntity() instanceof ServerPlayerEntity)) return 1;
        IFormattableTextComponent errorMsg;
        IFormattableTextComponent errorMsg2;
        IFormattableTextComponent errorMsg3;
        IFormattableTextComponent errorMsg4;
        IFormattableTextComponent successMsgR;
        IFormattableTextComponent successMsgG;
        ServerPlayerEntity player = (ServerPlayerEntity) src.getEntity();
        ServerPlayerEntity receiver = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(target);
        if(receiver == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg = new TranslationTextComponent("clovergoshadow.giveshadow.error1");
                errorMsg.setStyle(errorMsg.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg, Util.NIL_UUID);
            }
            else {
                errorMsg = new StringTextComponent("Unable to find the player provided!");
                errorMsg.setStyle(errorMsg.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg, Util.NIL_UUID);
            }
            return 1;
        }
        PokemonSpecification spec = PokemonSpecificationProxy.create(specs);
        if(spec == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg2 = new TranslationTextComponent("clovergoshadow.giveshadow.error2");
                errorMsg2.setStyle(errorMsg2.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg2, Util.NIL_UUID);
            }
            else {
                errorMsg2 = new StringTextComponent("Unable to create specs.");
                errorMsg2.setStyle(errorMsg2.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg2, Util.NIL_UUID);
            }
            return 1;
        }
        Pokemon pokemon = PokemonFactory.create(spec);
        if(pokemon == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg3 = new TranslationTextComponent("clovergoshadow.giveshadow.error3");
                errorMsg3.setStyle(errorMsg3.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg3, Util.NIL_UUID);
            }
            else {
                errorMsg3 = new StringTextComponent("Unable to find the pokemon specified.");
                errorMsg3.setStyle(errorMsg3.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg3, Util.NIL_UUID);
            }
            return 1;
        }
        RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(ribbon == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg4 = new TranslationTextComponent("clovergoshadow.giveshadow.error4");
                errorMsg4.setStyle(errorMsg4.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg4, Util.NIL_UUID);
            }
            else {
                errorMsg4 = new StringTextComponent("Unable to find the Shadow Type ribbon! Was it removed?");
                errorMsg4.setStyle(errorMsg4.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg4, Util.NIL_UUID);
            }
            return 1;
        }
        pokemon.addRibbon(ribbon);
        StorageProxy.getParty(receiver).add(pokemon);
        if(Config.CONFIG.isUseTranslatables()) {
            successMsgR = new TranslationTextComponent("clovergoshadow.giveshadow.successreceiver", pokemon.getSpecies().getTranslatedName());
            successMsgG = new TranslationTextComponent("clovergoshadow.giveshadow.successgiver", receiver.getName().getString(), pokemon.getSpecies().getTranslatedName());
            successMsgR.setStyle(successMsgR.getStyle().applyFormat(TextFormatting.GREEN));
            successMsgG.setStyle(successMsgG.getStyle().applyFormat(TextFormatting.GREEN));
            receiver.sendMessage(successMsgR, Util.NIL_UUID);
            player.sendMessage(successMsgG, Util.NIL_UUID);
        }
        else {
            successMsgR = new StringTextComponent("You received a shadow " + pokemon.getSpecies().getName() + "!");
            successMsgG = new StringTextComponent("You sent " + receiver.getName().getString() + " a shadow " + pokemon.getSpecies().getName() + "!");
            successMsgR.setStyle(successMsgR.getStyle().applyFormat(TextFormatting.GREEN));
            successMsgG.setStyle(successMsgG.getStyle().applyFormat(TextFormatting.GREEN));
            receiver.sendMessage(successMsgR, Util.NIL_UUID);
            player.sendMessage(successMsgG, Util.NIL_UUID);
        }
        return 0;
    }
}
