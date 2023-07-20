package com.clovercard.clovergoshadow.listeners;

import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.enums.RibbonEnum;
import com.clovercard.clovergoshadow.helpers.ExpHelper;
import com.clovercard.clovergoshadow.helpers.RibbonHelper;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.type.RibbonType;
import com.pixelmonmod.pixelmon.api.pokemon.stats.Moveset;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class CaptureListeners {
    @SubscribeEvent
    public void onCapture(CaptureEvent.SuccessfulCapture event) {
        RibbonType shadow = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(shadow == null) return;
        if(RibbonHelper.hasRibbon(event.getPokemon().getPokemon(), shadow)) {
            Pokemon pokemon = event.getPokemon().getPokemon();
            pokemon.setLevel(0);
            Ability ability = pokemon.getMoveset().getAbility();
            pokemon.rerollMoveset();
            Moveset moves = pokemon.getMoveset();
            moves.setAbility(ability);
            List<ImmutableAttack> eggMoves = new ArrayList<>(pokemon.getForm().getMoves().getEggMoves());
            if(!eggMoves.isEmpty()) {
                for(int i = 0; i < 4; i++) {
                    if(moves.attacks[i] == null) {
                        if(eggMoves.isEmpty()) break;
                        int eggMove = (int) Math.floor(Math.random()*eggMoves.size());
                        moves.set(i, new Attack(eggMoves.get(eggMove)));
                        eggMoves.remove(eggMove);
                    }
                }
            }
            ExpHelper.addExpToPlayer(event.getPlayer(), Config.CONFIG.getExpShadowPokemonCapture());
        }
    }

    @SubscribeEvent
    public void onRaidCapture(CaptureEvent.SuccessfulRaidCapture event) {
        RibbonType shadow = RibbonHelper.getRibbonTypeIfExists(RibbonEnum.SHADOW_RIBBON.getRibbonId());
        if(shadow == null) return;
        if(event.getRaidPokemon() == null) return;
        if(RibbonHelper.hasRibbon(event.getRaidPokemon(), shadow)) {
            Pokemon pokemon = event.getRaidPokemon();
            pokemon.setLevel(0);
            Ability ability = pokemon.getMoveset().getAbility();
            pokemon.rerollMoveset();
            Moveset moves = pokemon.getMoveset();
            moves.setAbility(ability);
            List<ImmutableAttack> eggMoves = new ArrayList<>(pokemon.getForm().getMoves().getEggMoves());
            if(!eggMoves.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    if (moves.attacks[i] == null) {
                        if(eggMoves.isEmpty()) break;
                        int eggMove = (int) Math.floor(Math.random() * eggMoves.size());
                        moves.set(i, new Attack(eggMoves.get(eggMove)));
                        eggMoves.remove(eggMove);
                    }
                }
            }
            if(event.getPlayer() == null) return;
            ExpHelper.addExpToPlayer(event.getPlayer(), Config.CONFIG.getExpShadowPokemonRaidCapture());
        }
    }
}
