package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.raids.StartRaidEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RaidDenStart {
    @SubscribeEvent
    public void onRaid(StartRaidEvent event) {
        if(event.getDen().getPersistentData().contains("clovergoshadowden")) {
            String specName = event.getDen().getPersistentData().getString("clovergoshadowden");
            if(event.getRaidPixelmon().getPokemon().getSpecies().getName().equals(specName)) {
                RibbonType shadow = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
                if (shadow != null) {
                    event.getRaidPixelmon().getPokemon().addRibbon(shadow);
                    event.getDen().getPersistentData().remove("clovergoshadowden");
                }
            }
            else event.getDen().getPersistentData().remove("clovergoshadowden");
        }
    }
}
