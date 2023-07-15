package com.clovercard.clovergoshadow.statuses;


import com.clovercard.clovergoshadow.config.Config;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.StatusBase;
import com.pixelmonmod.pixelmon.battles.status.StatusType;

public class ShadowBoost extends StatusBase {
    public ShadowBoost() { super(StatusType.None); }
    @Override
    public int[] modifyStats(final PixelmonWrapper user, final int[] stats) {
        stats[BattleStatsType.ATTACK.getStatIndex()] *= Config.CONFIG.getShadowBoostAttack();
        stats[BattleStatsType.SPECIAL_ATTACK.getStatIndex()] *= Config.CONFIG.getShadowBoostSpecialAttack();
        stats[BattleStatsType.DEFENSE.getStatIndex()] *= Config.CONFIG.getShadowBoostDefense();
        stats[BattleStatsType.SPECIAL_DEFENSE.getStatIndex()] *= Config.CONFIG.getShadowBoostSpecialDefense();
        stats[BattleStatsType.SPEED.getStatIndex()] *= Config.CONFIG.getShadowBoostSpeed();
        return stats;
    }
}
