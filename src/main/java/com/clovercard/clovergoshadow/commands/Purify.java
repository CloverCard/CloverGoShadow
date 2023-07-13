package com.clovercard.clovergoshadow.commands;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.ExpHelper;
import com.clovercard.clovergoshadow.helpers.ItemHelper;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.util.text.*;

public class Purify {
    public Purify(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("clovergoshadow")
                        .then(Commands.literal("purify")
                                .then(Commands.argument("slot", IntegerArgumentType.integer(1, 6))
                                        .executes(cmd -> purifyPokemon(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "slot")))))
        );
    }
    private int purifyPokemon(CommandSource cmd, int slot) {
        if(!(cmd.getEntity() instanceof ServerPlayerEntity)) return 1;

        IFormattableTextComponent errorMsg;
        IFormattableTextComponent errorMsg2;
        IFormattableTextComponent errorMsg3;
        IFormattableTextComponent successMsg;

        if(Config.CONFIG.isUseTranslatables()) {
            errorMsg = new TranslationTextComponent("clovergoshadow.purify.error1");
            errorMsg2 = new TranslationTextComponent("clovergoshadow.purify.error2");
            errorMsg3 = new TranslationTextComponent("clovergoshadow.purify.error3");
            successMsg = new TranslationTextComponent("clovergoshadow.purify.success");
        }
        else {
            errorMsg = new StringTextComponent("Could not find the ribbons and/or pokemon needed for this command!");
            errorMsg2 = new StringTextComponent("This Pokemon is not a Shadow Pokemon, so it cannot be purified!");
            errorMsg3 = new StringTextComponent("Your pokemon needs to level 50 to purify.");
            successMsg = new StringTextComponent("Your pokemon has been purified!");
        }

        errorMsg.setStyle(errorMsg.getStyle().applyFormat(TextFormatting.RED));
        errorMsg2.setStyle(errorMsg2.getStyle().applyFormat(TextFormatting.RED));
        errorMsg3.setStyle(errorMsg3.getStyle().applyFormat(TextFormatting.RED));
        successMsg.setStyle(successMsg.getStyle().applyFormat(TextFormatting.GREEN));

        ServerPlayerEntity player = (ServerPlayerEntity) cmd.getEntity();
        PlayerPartyStorage storage = StorageProxy.getParty(player);
        Pokemon pkm = storage.get(slot-1);
        RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        RibbonType ribbon2 = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
        if(pkm == null || ribbon == null || ribbon2 == null) {
            player.sendMessage(errorMsg, Util.NIL_UUID);
            return 1;
        }
        //Make sure pokemon is at least level 50
        if(pkm.getPokemonLevel() < 50) {
            player.sendMessage(errorMsg3, Util.NIL_UUID);
            return 1;
        }
        //Remove Shadow Ribbon
        if(!RibbonHelper.removeRibbonIfExists(pkm, ribbon)) {
            player.sendMessage(errorMsg2, Util.NIL_UUID);
            return 1;
        }
        //Clear out duplicate ribbons
        while(RibbonHelper.removeRibbonIfExists(pkm, ribbon));
        player.sendMessage(successMsg, Util.NIL_UUID);
        int[] ivs = pkm.getIVs().getArray();
        for(int i = 0; i < 3; i++) {
           ivs[(int) Math.floor(Math.random()*6)] = 31;
        }
        if(!RibbonHelper.hasRibbon(pkm, ribbon2)) pkm.addRibbon(ribbon2);
        pkm.getIVs().fillFromArray(ivs);

        if(Math.random() < Config.CONFIG.getShadowRaidPiece()/100) {
            ItemStack piece = ItemHelper.makeSpawner();
            if(piece != null) player.inventory.add(piece);
        }
        ExpHelper.addExpToPlayer(player, Config.CONFIG.getExpPurifyShadowPokemon());
        return 0;
    }
}
