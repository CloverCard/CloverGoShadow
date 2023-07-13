package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.api.registry.RegistryValue;
import com.pixelmonmod.pixelmon.api.events.PokemonSendOutEvent;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SendOutPokemon {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onSendOut(PokemonSendOutEvent event) {
        if(event.getPokemon().getDisplayedRibbon() != null) {
            if(Config.CONFIG.isUseTranslatables()) return;
            Ribbon ribbon = event.getPokemon().getDisplayedRibbon();
            RibbonType shadow = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
            RibbonType purified = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
            if(shadow == null || purified == null) return;
            RegistryValue<RibbonType> reg = ribbon.getType();
            if(!reg.getValue().isPresent()) return;
            RibbonType equipped = reg.getValueUnsafe();
            if(equipped.equals(shadow)) ribbon.getRibbonData().setPrefix(new StringTextComponent("Shadow "));
            if(equipped.equals(purified)) ribbon.getRibbonData().setPrefix(new StringTextComponent("Purified "));
        }
    }
}
