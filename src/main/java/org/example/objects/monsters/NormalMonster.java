package org.example.objects.monsters;


public class NormalMonster extends Monster {
    public NormalMonster(String name, int hp, int baseAttack, int baseDefense,
                         int baseFocusPoints, int focusPoints, int focusPointsPerTurn,
                         int skillDamageVariance, String monsterSkill, String bossSkill, int expReward){

        super(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                focusPointsPerTurn, skillDamageVariance, monsterSkill, bossSkill, expReward);
    }
}
