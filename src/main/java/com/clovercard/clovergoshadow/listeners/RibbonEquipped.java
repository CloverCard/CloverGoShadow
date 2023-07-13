package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.api.registry.RegistryValue;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.event.RibbonEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RibbonEquipped {
    @SubscribeEvent
    public void ribbonEquipped(RibbonEvent.SetDisplayedRibbon event) {
        if(Config.CONFIG.isUseTranslatables()) return;
        RibbonType shadow = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        RibbonType purified = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
        if(shadow == null || purified == null) return;
        if(event.getRibbon() == null) return;
        RegistryValue<RibbonType> reg = event.getRibbon().getType();
        if(!reg.getValue().isPresent()) return;
        RibbonType equipped = reg.getValueUnsafe();
        if(equipped.equals(shadow)) event.getRibbon().getRibbonData().setPrefix(new StringTextComponent("Shadow "));
        if(equipped.equals(purified)) event.getRibbon().getRibbonData().setPrefix(new StringTextComponent("Purified "));
    }
}
