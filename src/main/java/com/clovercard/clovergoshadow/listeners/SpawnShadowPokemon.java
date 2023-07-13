package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.pokemon.species.aggression.Aggression;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.npcs.trainers.SpawnActionNPCTrainer;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

public class SpawnShadowPokemon {
    @SubscribeEvent
    public void onSpawn(SpawnEvent event) {
        if(event.action instanceof SpawnActionPokemon) {
            SpawnActionPokemon action = (SpawnActionPokemon) event.action;
            //Determine whether pokemon is Shadow Type
            if(Math.random() < Config.CONFIG.getShadowSpawnPercent()/100) {
                Pokemon shadow = action.pokemon;
                if(Config.CONFIG.getShadowBlackList().contains(shadow.getSpecies().getName())) return;
                shadow.getOrCreatePixelmon().setAggression(Aggression.AGGRESSIVE);
                RibbonType ribbon = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
                if(ribbon == null) return;
                if(!(event.action.spawnLocation.cause instanceof ServerPlayerEntity)) return;
                ServerPlayerEntity player = (ServerPlayerEntity) event.action.spawnLocation.cause;
                shadow.addRibbon(ribbon);
                IFormattableTextComponent msg;
                if(Config.CONFIG.isUseTranslatables()) {
                    msg = new TranslationTextComponent("clovergoshadow.spawn", shadow.getTranslatedName());
                }
                else msg = new StringTextComponent("A shadow " + shadow.getTranslatedName().getString() + " has spawned near you!");
                msg.setStyle(msg.getStyle().applyFormat(TextFormatting.LIGHT_PURPLE));
                player.sendMessage(msg, Util.NIL_UUID);
            }
        }
        if(event.action instanceof SpawnActionNPCTrainer) {
            SpawnActionNPCTrainer action = (SpawnActionNPCTrainer) event.action;
            if(Math.random() < Config.CONFIG.getShadowTrainerPercent()/100) {
                NPCTrainer trainer = action.getOrCreateEntity();
                trainer.getPersistentData().putBoolean("isshadowtrainer", true);
                ArrayList<Pokemon> team = (ArrayList<Pokemon>) trainer.getPokemonStorage().getTeam();
                team.forEach(pkm -> pkm.setNickname(new StringTextComponent("Shadow " + pkm.getTranslatedName().getString())));
                IFormattableTextComponent msg;
                if(Config.CONFIG.isUseTranslatables()) msg = new TranslationTextComponent("clovergoshadow.spawntrainer");
                else msg = new StringTextComponent("A suspicious looking trainer spawned near you!");
                msg.setStyle(msg.getStyle().applyFormat(TextFormatting.LIGHT_PURPLE));
                if(event.action.spawnLocation.cause instanceof ServerPlayerEntity) {
                    ServerPlayerEntity player = (ServerPlayerEntity) event.action.spawnLocation.cause;
                    player.sendMessage(msg, Util.NIL_UUID);
                }
            }
        }
    }
}
