package org.example.objects.monsters;


public class BossMonster extends Monster {
    public BossMonster(String name, int hp, int baseAttack, int baseDefense,
                       int baseFocusPoints, int focusPoints, int focusPointsPerTurn,
                       int skillDamageVarianceBound, int skillDamageVarianceOrigin, String monsterSkill,
                       String bossSkill, int expReward){

        super(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                monsterSkill, bossSkill, expReward);
    }

    @Override
    public double basicAttack() {
        System.out.println(getName() + " attacks you!");
        return getAttack() * 1.2;
    }
}
