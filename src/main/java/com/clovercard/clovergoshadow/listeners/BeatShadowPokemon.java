package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.ExpHelper;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.events.raids.EndRaidEvent;
import com.pixelmonmod.pixelmon.api.events.raids.RaidDropsEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.battles.controller.participants.RaidPixelmonParticipant;
import com.pixelmonmod.pixelmon.battles.raids.RaidData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class BeatShadowPokemon {
    @SubscribeEvent
    public void onBeat(BeatWildPixelmonEvent event) {
        ServerPlayerEntity player = event.player;
        Pokemon pokemon = event.wpp.allPokemon[0].pokemon;
        RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(ribbon == null) return;
        if(RibbonHelper.hasRibbon(pokemon, ribbon)) {
            ExpHelper.addExpToPlayer(player, Config.CONFIG.getExpShadowWildPokemonVictory());
        }
    }
    @SubscribeEvent
    public void onRaidBeat(EndRaidEvent event) {
        if(event.didRaidersWin()) {
            RaidPixelmonParticipant raid = event.getRaidParticipant();
            Pokemon pokemon = raid.allPokemon[0].pokemon;
            RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
            if(ribbon == null) return;
            if(RibbonHelper.hasRibbon(pokemon, ribbon)) {
                List<RaidData.RaidPlayer> players = event.getRaid().getPlayers();
                for(RaidData.RaidPlayer player: players) {
                    ServerPlayerEntity entity = player.playerEntity;
                    ExpHelper.addExpToPlayer(entity, Config.CONFIG.getExpShadowRaidPokemonVictory());
                    if(entity != null) entity.addTag("clovergoshadowcatch");
                }
            }
        }
    }
    @SubscribeEvent
    public void onRaidDrops(RaidDropsEvent event) {
        if(event.getPlayer().playerEntity != null) {
            ServerPlayerEntity player = event.getPlayer().playerEntity;
            if(player.getTags().contains("clovergoshadowcatch")) {
                player.removeTag("clovergoshadowcatch");
                RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
                if(ribbon == null) return;
                event.getPossibleCatch().addRibbon(ribbon);
            }
        }
    }
}
