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

    @Override
    public String getDetails(){
        return "Monster: " + name + " | Health: " + hp + " | Attack value: " + attack + " | Defense value: " + defense +
                " | Focus Points: " + focusPoints + " | Focus Point regen: " + focusPointsPerTurn +
                " | Skill Damage range: " + skillDamageVarianceOrigin + " - " + skillDamageVarianceBound +
                " | First Skill name: " + monsterSkill + " | Second Skill name: " + bossSkill +
                " | EXP reward: " + expReward;
    }
}
