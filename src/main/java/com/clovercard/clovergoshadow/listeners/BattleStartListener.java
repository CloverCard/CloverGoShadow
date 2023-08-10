package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.clovercard.clovergoshadow.statuses.ShadowBoost;
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.TrainerParticipant;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BattleStartListener {
    @SubscribeEvent
    public void onBattleStart(BattleStartedEvent.Post event) {
        RibbonType ribbonType = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(ribbonType == null) return;
        for(BattleParticipant bp: event.getTeamOne()) {
            for(PixelmonWrapper pw: bp.getTeamPokemon()) {
                if(pw == null) continue;
                if(pw.pokemon.getFormattedNickname().getString().contains("Shadow") && bp instanceof TrainerParticipant) {
                    pw.pokemon.addRibbon(ribbonType);
                }
                if(RibbonHelper.hasRibbon(pw.pokemon, ribbonType)) {
                    if(pw.addStatus(new ShadowBoost(), pw)) System.out.println("Added shadow boost to " + pw.pokemon.getSpecies().getName());
                }
            }
        }
        for(BattleParticipant bp: event.getTeamTwo()) {
            for(PixelmonWrapper pw: bp.getTeamPokemon()) {
                if(pw == null) continue;
                if(pw.pokemon.getFormattedNickname().getString().contains("Shadow") && bp instanceof TrainerParticipant) {
                    pw.pokemon.addRibbon(ribbonType);
                }
                if(RibbonHelper.hasRibbon(pw.pokemon, ribbonType)) {
                    pw.addStatus(new ShadowBoost(), pw);
                }
            }
        }
    }
}
