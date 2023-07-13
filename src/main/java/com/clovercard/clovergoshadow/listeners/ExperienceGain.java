package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ExperienceGain {
    @SubscribeEvent
    public void onExpGain(ExperienceGainEvent event) {
        //Check if Pokemon has Shadow Ribbon.
        RibbonType type = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        RibbonType type2 = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
        if(type == null && type2 == null) return;
        if(RibbonHelper.hasRibbon(event.pokemon.getPokemon(), type)) {
            //Manage Shadow Pokemon Experience Gain
            float mult = Config.CONFIG.getShadowExpGainMultiplier();
            int exp = (int) (event.getExperience() * mult);
            event.setExperience(exp);
        }
        else if(RibbonHelper.hasRibbon(event.pokemon.getPokemon(), type2)) {
            //Manage Purified Pokemon Experience Gain
            float mult = Config.CONFIG.getPurifiedExpGainMultiplier();
            int exp = (int) (event.getExperience() * mult);
            event.setExperience(exp);
        }
    }
}
