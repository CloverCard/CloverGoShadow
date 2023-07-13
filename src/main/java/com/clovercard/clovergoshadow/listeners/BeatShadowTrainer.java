package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.ExpHelper;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.BeatTrainerEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class BeatShadowTrainer {
    @SubscribeEvent
    public void onBeatTrainer(BeatTrainerEvent event) {
        if(!event.trainer.getPersistentData().contains("isshadowtrainer")) return;
        List<Pokemon> team = event.trainer.getPokemonStorage().getTeam();
        Pokemon shadow = team.get((int) (Math.random()*team.size()));
        Pokemon clone = PokemonFactory.copy(shadow);
        if(RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId()) == null) return;
        clone.addRibbon(RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId()));
        clone.setNickname(clone.getTranslatedName());
        clone.getOrSpawnPixelmon(event.trainer.level, event.trainer.getX(), event.trainer.getY(), event.trainer.getZ(), 0, 0);
        ExpHelper.addExpToPlayer(event.player, Config.CONFIG.getExpTrainerVictory());
    }
}
