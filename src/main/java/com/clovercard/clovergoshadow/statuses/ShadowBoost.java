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
        stats[BattleStatsType.HP.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostHp();
        stats[BattleStatsType.ATTACK.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostAttack();
        stats[BattleStatsType.SPECIAL_ATTACK.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostSpecialAttack();
        stats[BattleStatsType.DEFENSE.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostDefense();
        stats[BattleStatsType.SPECIAL_DEFENSE.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostSpecialDefense();
        stats[BattleStatsType.SPEED.getBattleStatIndex()] *= Config.CONFIG.getShadowBoostSpeed();
        return stats;
    }
}
