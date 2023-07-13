package com.clovercard.clovergoshadow.helpers;

import com.clovercard.clovergoshadow.config.Config;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.util.Util;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class ExpHelper {
    /*
    Level System
    lvl 0 -> 1 = 100
    lvl 10 -> 11 = 200
    lvl 20 -> 21 = 300
    lvl 30 -> 31 = 400
    lvl 40 -> 41 = 500
    lvl 50 -> 0 = 600
    */
    public static int getLevelExp(int level){
        return (level*10) + 100;
    }

    public static void addExpToPlayer(ServerPlayerEntity player, int amount) {
        if(player == null) return;
        ServerScoreboard scoreboard = ServerLifecycleHooks.getCurrentServer().getScoreboard();
        ScoreObjective levelObj = scoreboard.getObjective("clshadowlevels");
        ScoreObjective expObj = scoreboard.getObjective("clshadowexp");

        if (levelObj == null) {
            scoreboard.addObjective("clshadowlevels", ScoreCriteria.DUMMY, new StringTextComponent("researchlevel"), ScoreCriteria.DUMMY.getDefaultRenderType());
            levelObj = scoreboard.getObjective("clshadowlevels");
        }
        if (expObj == null) {
            scoreboard.addObjective("clshadowexp", ScoreCriteria.DUMMY, new StringTextComponent("researchexp"), ScoreCriteria.DUMMY.getDefaultRenderType());
            expObj = scoreboard.getObjective("clshadowexp");
        }
        if(levelObj == null || expObj == null) {
            return;
        }
        Score plLevel = scoreboard.getOrCreatePlayerScore(player.getName().getString(), levelObj);
        Score plExp = scoreboard.getOrCreatePlayerScore(player.getName().getString(), expObj);
        int required = getLevelExp(plLevel.getScore());
        plExp.setScore(plExp.getScore() + amount);
        IFormattableTextComponent msg;
        if(Config.CONFIG.isUseTranslatables()) {
            msg = new TranslationTextComponent("clovergoshadow.expgain", amount);
        }
        else msg = new StringTextComponent("Your research level has gained " + amount + " exp!");
        player.sendMessage(msg, Util.NIL_UUID);
        while(plExp.getScore() > required) {
            plExp.setScore(plExp.getScore() - required);
            if(plLevel.getScore() < 50) {
                plLevel.setScore(plLevel.getScore() + 1);
                if(plLevel.getScore() != 0 && plLevel.getScore() % 10 == 0) {
                    ItemStack spawner = ItemHelper.makeLegendSpawner();
                    if(spawner != null) {
                        ServerPlayerEntity pl = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(player.getUUID());
                        if (pl != null) pl.inventory.add(spawner);
                    }
                }
            }
            else {
                plLevel.setScore(0);
                ItemStack spawner = ItemHelper.makeLegendSpawner();
                if(spawner != null) {
                    ServerPlayerEntity pl = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(player.getUUID());
                    if (pl != null) pl.inventory.add(spawner);
                }
            }
            required = getLevelExp(plLevel.getScore());
            IFormattableTextComponent levelMsg;
            if(Config.CONFIG.isUseTranslatables()) {
                levelMsg = new TranslationTextComponent("clovergoshadow.levelgain", plLevel.getScore());
            }
            else levelMsg = new StringTextComponent("Your research level has levelled up to " + plLevel.getScore() + "!");
            player.sendMessage(levelMsg, Util.NIL_UUID);
        }
    }
}
