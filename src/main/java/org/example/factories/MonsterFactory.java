package org.example.factories;

import org.example.interfaces.IFactoryMonster;
import org.example.objects.monsters.Monster;
import org.example.objects.monsters.NormalMonster;

public class MonsterFactory implements IFactoryMonster {
    @Override
    public NormalMonster createMonster(String name, int hp, int baseAttack, int baseDefense,
                                 int baseFocusPoints, int focusPoints, int focusPointsPerTurn,
                                 int skillDamageVarianceBound, int skillDamageVarianceOrigin, String monsterSkill,
                                 String bossSkill, int expReward){

        return new NormalMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                monsterSkill, bossSkill, expReward);
    }
}
