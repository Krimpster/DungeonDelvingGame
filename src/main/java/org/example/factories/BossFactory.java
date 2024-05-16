package org.example.factories;

import org.example.interfaces.IFactoryMonster;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.Monster;

public class BossFactory implements IFactoryMonster {
    @Override
    public Monster createMonster(String name, int hp, int baseAttack, int baseDefense,
                                 int baseFocusPoints, int focusPoints, int focusPointsPerTurn,
                                 int skillDamageVarianceBound, int skillDamageVarianceOrigin, String monsterSkill,
                                 String bossSkill, int expReward){
        return new BossMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                monsterSkill, bossSkill, expReward);
    }
}
