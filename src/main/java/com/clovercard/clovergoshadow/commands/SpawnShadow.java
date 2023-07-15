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
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;

public class SpawnShadow {
    public SpawnShadow(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("clovergoshadow")
                        .then(
                                Commands.literal("spawnshadow")
                                        .then(
                                                Commands.argument("specs", StringArgumentType.greedyString())
                                                        .executes(cmd -> spawnShadowPokemon(cmd.getSource(), StringArgumentType.getString(cmd, "specs")))
                                        )
                        )
        );
    }
    public int spawnShadowPokemon(CommandSource src, String specs) {
        if(!(src.getEntity() instanceof ServerPlayerEntity)) return 1;
        IFormattableTextComponent errorMsg;
        IFormattableTextComponent errorMsg2;
        IFormattableTextComponent errorMsg3;
        IFormattableTextComponent successMsg;
        ServerPlayerEntity player = (ServerPlayerEntity) src.getEntity();
        PokemonSpecification spec = PokemonSpecificationProxy.create(specs);
        if(spec == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg = new TranslationTextComponent("clovergoshadow.spawnshadow.error1");
                errorMsg.setStyle(errorMsg.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg, Util.NIL_UUID);
            }
            else {
                errorMsg = new StringTextComponent("Unable to create specs.");
                errorMsg.setStyle(errorMsg.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg, Util.NIL_UUID);
            }
            return 1;
        }
        Pokemon pokemon = PokemonFactory.create(spec);
        if(pokemon == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg2 = new TranslationTextComponent("clovergoshadow.spawnshadow.error2");
                errorMsg2.setStyle(errorMsg2.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg2, Util.NIL_UUID);
            }
            else {
                errorMsg2 = new StringTextComponent("Unable to find the pokemon specified.");
                errorMsg2.setStyle(errorMsg2.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg2, Util.NIL_UUID);
            }
            return 1;
        }
        RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(ribbon == null) {
            if(Config.CONFIG.isUseTranslatables()) {
                errorMsg3 = new TranslationTextComponent("clovergoshadow.spawnshadow.error3");
                errorMsg3.setStyle(errorMsg3.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg3, Util.NIL_UUID);
            }
            else {
                errorMsg3 = new StringTextComponent("Unable to find the Shadow Type ribbon! Was it removed?");
                errorMsg3.setStyle(errorMsg3.getStyle().applyFormat(TextFormatting.RED));
                player.sendMessage(errorMsg3, Util.NIL_UUID);
            }
            return 1;
        }
        pokemon.addRibbon(ribbon);
        pokemon.getOrSpawnPixelmon(player.getCommandSenderWorld(), player.getX(), player.getY() + 1, player.getZ());
        if(Config.CONFIG.isUseTranslatables()) {
            successMsg = new TranslationTextComponent("clovergoshadow.spawnshadow.success");
            successMsg.setStyle(successMsg.getStyle().applyFormat(TextFormatting.GREEN));
            player.sendMessage(successMsg, Util.NIL_UUID);
        }
        else {
            successMsg = new StringTextComponent("Spawned shadow pokemon near you!");
            successMsg.setStyle(successMsg.getStyle().applyFormat(TextFormatting.GREEN));
            player.sendMessage(successMsg, Util.NIL_UUID);
        }
        return 0;
    }
}
