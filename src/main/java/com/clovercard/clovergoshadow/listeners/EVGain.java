package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.pokemon.EVsGainedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EVGain {
    @SubscribeEvent
    public void onEV(EVsGainedEvent event) {
        //Check if Shadow Ribbon and Purified Ribbon Exist
        RibbonType type = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        RibbonType type2 = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
        if(type == null || type2 == null) return;

        //Check if Pokemon has Shadow or Purified Ribbon and modify yields if so.
        if(RibbonHelper.hasRibbon(event.pokemon, type)) {
            float mult = Config.CONFIG.getShadowEvGainMultiplier();
            //Reduce EV Yields
            int hp = (int) (event.evYields.getYield(BattleStatsType.HP) * mult);
            int atk = (int) (event.evYields.getYield(BattleStatsType.ATTACK) * mult);
            int def = (int) (event.evYields.getYield(BattleStatsType.DEFENSE) * mult);
            int spatk = (int) (event.evYields.getYield(BattleStatsType.SPECIAL_ATTACK) * mult);
            int spdef = (int) (event.evYields.getYield(BattleStatsType.SPECIAL_DEFENSE) * mult);
            int spe = (int) (event.evYields.getYield(BattleStatsType.SPEED) * mult);

            event.evYields.addToYield(BattleStatsType.HP, hp);
            event.evYields.addToYield(BattleStatsType.ATTACK, atk);
            event.evYields.addToYield(BattleStatsType.DEFENSE, def);
            event.evYields.addToYield(BattleStatsType.SPECIAL_ATTACK, spatk);
            event.evYields.addToYield(BattleStatsType.SPECIAL_DEFENSE, spdef);
            event.evYields.addToYield(BattleStatsType.SPEED, spe);
        }
        else if(RibbonHelper.hasRibbon(event.pokemon, type2)) {
            float mult = Config.CONFIG.getPurifiedEvGainMultiplier();
            //Reduce EV Yields
            int hp = (int) (event.evYields.getYield(BattleStatsType.HP) * mult);
            int atk = (int) (event.evYields.getYield(BattleStatsType.ATTACK) * mult);
            int def = (int) (event.evYields.getYield(BattleStatsType.DEFENSE) * mult);
            int spatk = (int) (event.evYields.getYield(BattleStatsType.SPECIAL_ATTACK) * mult);
            int spdef = (int) (event.evYields.getYield(BattleStatsType.SPECIAL_DEFENSE) * mult);
            int spe = (int) (event.evYields.getYield(BattleStatsType.SPEED) * mult);

            event.evYields.addToYield(BattleStatsType.HP, hp);
            event.evYields.addToYield(BattleStatsType.ATTACK, atk);
            event.evYields.addToYield(BattleStatsType.DEFENSE, def);
            event.evYields.addToYield(BattleStatsType.SPECIAL_ATTACK, spatk);
            event.evYields.addToYield(BattleStatsType.SPECIAL_DEFENSE, spdef);
            event.evYields.addToYield(BattleStatsType.SPEED, spe);
        }
    }
}
