package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.clovercard.clovergoshadow.statuses.ShadowBoost;
import com.pixelmonmod.pixelmon.api.events.battles.ApplyBonusStatsEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.RaidPixelmonParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import com.pixelmonmod.pixelmon.battles.status.StatusBase;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class ApplyBoostsOnBattleStart {
    @SubscribeEvent
    public void onTest(ApplyBonusStatsEvent event) {
        //Handles Wild Pokemon and Adds Ribbons if Needed to NPC Trainers
        for (BattleParticipant participant : event.getBattleController().participants) {
            if (!((participant instanceof WildPixelmonParticipant) || (participant instanceof RaidPixelmonParticipant)) ) continue;
            for (PixelmonWrapper pkm : participant.controlledPokemon) {
                RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
                if (ribbon == null) return;
                if (!RibbonHelper.hasRibbon(pkm.pokemon, ribbon)) continue;
                //Prevent multiple Shadow Boosts on one pokemon
                boolean foundBoost = false;
                for (StatusBase sts : pkm.getStatuses()) {
                    if (sts instanceof ShadowBoost) {
                        foundBoost = true;
                        break;
                    }
                }
                //Add boost if it's not already added
                if (!foundBoost) pkm.addStatus(new ShadowBoost(), pkm);
            }
        }


        //Applies Shadow Boost to non-wild pokemon.
        if (event.getPokemon().getPixelmonWrapper().isPresent()) {
            PixelmonWrapper pkm = event.getPokemon().getPixelmonWrapper().get();
            ArrayList<PixelmonWrapper> team = pkm.getTeamPokemon();
            RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
            if (ribbon == null) return;
            //Add Shadow Boost to all members on the team.
            for (PixelmonWrapper member : team) {
                if (!RibbonHelper.hasRibbon(member.pokemon, ribbon)) continue;
                //Prevent multiple Shadow Boosts on one pokemon
                boolean foundBoost = false;
                for (StatusBase sts : member.getStatuses()) {
                    if (sts instanceof ShadowBoost) {
                        foundBoost = true;
                        break;
                    }
                }
                //Add boost if it's not already added
                if (!foundBoost) member.addStatus(new ShadowBoost(), member);
            }
            for(PixelmonWrapper pkm2: pkm.getOpponentPokemon()) {
                if(pkm2.pokemon.getOwnerTrainer() != null) {
                    boolean foundBoost = false;
                    ArrayList<StatusBase> toRemove = new ArrayList<>();
                    for (StatusBase sts : pkm2.getStatuses()) {
                        if (sts instanceof ShadowBoost) {
                            if(pkm2.getNickname().startsWith("Shadow")) foundBoost = true;
                            else toRemove.add(sts);
                            break;
                        }
                    }
                    if(!foundBoost && pkm2.getNickname().startsWith("Shadow")) pkm2.addStatus(new ShadowBoost(), pkm2);
                    if(!toRemove.isEmpty()) toRemove.forEach(pkm2::removeStatus);
                }
            }
        }
    }
}
