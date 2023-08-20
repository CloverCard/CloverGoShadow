package com.clovercard.clovergoshadow.helpers;

import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.statuses.ShadowBoost;
import com.pixelmonmod.api.registry.RegistryValue;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.StatusBase;

import java.util.List;

public class RibbonHelper {
    public static boolean hasRibbon(Pokemon pokemon, RibbonType type) {
        List<Ribbon> ribbons = pokemon.getRibbons();
        if(ribbons.isEmpty()) return false;
        for(Ribbon ribbon: ribbons) {
            RegistryValue<RibbonType> reg = ribbon.getType();
            if(reg == null) continue;
            if(!reg.getValue().isPresent()) continue;
            if(reg.getValue().get().equals(type)) return true;
        }
        return false;
    }
    public static RibbonType getRibbonTypeIfExists(String ribbonName) {
        RegistryValue<RibbonType> reg = RibbonRegistry.getRibbon(ribbonName);
        if(reg == null) return null;
        if(!reg.getValue().isPresent()) return null;
        return reg.getValue().get();
    }
    public static boolean isWearingShadowRibbon(Pokemon pokemon) {
        //Check if wearing a ribbon
        Ribbon ribbon = pokemon.getDisplayedRibbon();
        if (ribbon == null) return false;

        //Check if ribbon is Shadow Ribbon
        RegistryValue<RibbonType> regType = ribbon.getType();
        RegistryValue<RibbonType> regType2 = RibbonRegistry.getRibbon(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if (regType == null || regType2 == null) return false;
        if (!regType.getValue().isPresent() || !regType2.getValue().isPresent()) return false;
        return regType.getValue().get().equals(regType2.getValue().get());
    }

    public static boolean isWearingPurifiedRibbon(Pokemon pokemon) {
        //Check if wearing a ribbon
        Ribbon ribbon = pokemon.getDisplayedRibbon();
        if (ribbon == null) return false;

        //Check if ribbon is Shadow Ribbon
        RegistryValue<RibbonType> regType = ribbon.getType();
        RegistryValue<RibbonType> regType2 = RibbonRegistry.getRibbon(RibbonEnum.PURIFIED_RIBBON.getRibbonId());
        if (regType == null || regType2 == null) return false;
        if (!regType.getValue().isPresent() || !regType2.getValue().isPresent()) return false;
        return regType.getValue().get().equals(regType2.getValue().get());
    }
    public static boolean removeRibbonIfExists(Pokemon pokemon, RibbonType type) {
        List<Ribbon> ribbons = pokemon.getRibbons();
        if(ribbons.isEmpty()) return false;
        for(Ribbon ribbon: ribbons) {
            RegistryValue<RibbonType> reg = ribbon.getType();
            if(reg == null) continue;
            if(!reg.getValue().isPresent()) continue;
            if(reg.getValue().get().equals(type)) {
                if(RibbonHelper.isHoldingRibbon(pokemon, type)) pokemon.setDisplayedRibbon(null);
                pokemon.removeRibbon(ribbon);
                return true;
            }
        }
        return false;
    }

    public static boolean isHoldingRibbon(Pokemon pokemon, RibbonType type) {
        Ribbon ribbon = pokemon.getDisplayedRibbon();
        if(ribbon == null) return false;
        RegistryValue<RibbonType> reg = ribbon.getType();
        if(reg == null) return false;
        if(!reg.getValue().isPresent()) return false;
        return reg.getValue().get().equals(type);
    }

    public static boolean hasShadowBoost(PixelmonWrapper pkm) {
        for(StatusBase sts: pkm.getStatuses()) {
            if(sts instanceof ShadowBoost) return true;
        }
        return false;
    }
}
