package com.clovercard.clovergoshadow.helpers;

import com.clovercard.clovergoshadow.config.Config;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.pokemon.species.Stats;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class ItemHelper {
    public static ItemStack makeSpawner() {
        ItemStack itemStack = new ItemStack(PixelmonItems.poke_flute);
        int attempts = 5;
        Species rand = PixelmonSpecies.getRandomSpecies(true, true, false);
        while(Config.CONFIG.getShadowBlackList().contains(rand.getName())) {
            if(attempts <= 0) return null;
            rand = PixelmonSpecies.getRandomSpecies(true, true, false);
            attempts--;
        }
        List<Stats> forms = rand.getForms(false);
        Stats form = forms.get((int) (Math.random()*forms.size()));
        if(!Config.CONFIG.getShadowFormWhiteList().contains(form.getName())) form = rand.getDefaultForm();
        String formName = form.getName();
        if(rand.getDefaultForm().is(form)) formName = "Default";
        IFormattableTextComponent itemName;
        if(Config.CONFIG.isUseTranslatables()) itemName = new TranslationTextComponent("clovergoshadow.spawner", formName, rand.getName());
        else {
            itemName = new StringTextComponent("Shadow " + formName + " " + rand.getName() + " Wishing Piece");
        }
        itemStack.setHoverName(itemName);
        itemStack.getOrCreateTag().putBoolean("clovergoshadowwishingpiece", true);
        itemStack.getOrCreateTag().putString("clovergoshadowspecies", rand.getName());
        itemStack.getOrCreateTag().putString("clovergoshadowform", form.getName());
        return itemStack;
    }
    public static ItemStack makeSpawnerLegendary() {
        ItemStack itemStack = new ItemStack(PixelmonItems.poke_flute);
        int attempts = 5;
        Species rand = PixelmonSpecies.getRandomLegendary();
        while(Config.CONFIG.getShadowBlackList().contains(rand.getName())) {
            if(attempts <= 0) return null;
            rand = PixelmonSpecies.getRandomLegendary();
            attempts--;
        }
        List<Stats> forms = rand.getForms(false);
        Stats form = forms.get((int) (Math.random()*forms.size()));
        if(!Config.CONFIG.getShadowFormWhiteList().contains(form.getName())) form = rand.getDefaultForm();
        String formName = form.getName();
        if(rand.getDefaultForm().is(form)) formName = "Default";
        IFormattableTextComponent itemName;
        if(Config.CONFIG.isUseTranslatables()) itemName = new TranslationTextComponent("clovergoshadow.spawner", formName, rand.getName());
        else {
            itemName = new StringTextComponent("Shadow " + formName + " " + rand.getName() + " Wishing Piece");
        }
        itemStack.setHoverName(itemName);
        itemStack.getOrCreateTag().putBoolean("clovergoshadowwishingpiece", true);
        itemStack.getOrCreateTag().putString("clovergoshadowspecies", rand.getName());
        itemStack.getOrCreateTag().putString("clovergoshadowform", form.getName());
        return itemStack;
    }
    public static ItemStack makeLegendSpawner() {
        ItemStack itemStack = new ItemStack(PixelmonItems.poke_flute);
        int attempts = 5;
        Species rand = PixelmonSpecies.getRandomLegendary();
        while(Config.CONFIG.getShadowBlackList().contains(rand.getName())) {
            if(attempts <= 0) return null;
            rand = PixelmonSpecies.getRandomLegendary();
            attempts--;
        }
        List<Stats> forms = rand.getForms(false);
        Stats form = forms.get((int) (Math.random()*forms.size()));
        if(!Config.CONFIG.getShadowFormWhiteList().contains(form.getName())) form = rand.getDefaultForm();
        String formName = form.getName();
        if(rand.getDefaultForm().is(form)) formName = "Default";
        IFormattableTextComponent itemName;
        if(Config.CONFIG.isUseTranslatables()) itemName = new TranslationTextComponent("clovergoshadow.spawner", formName, rand.getName());
        else {
            itemName = new StringTextComponent("Shadow " + formName + " " + rand.getName() + " Wishing Piece");
        }
        itemStack.setHoverName(itemName);
        itemStack.getOrCreateTag().putBoolean("clovergoshadowwishingpiece", true);
        itemStack.getOrCreateTag().putString("clovergoshadowspecies", rand.getName());
        itemStack.getOrCreateTag().putString("clovergoshadowform", form.getName());
        return itemStack;
    }
}
