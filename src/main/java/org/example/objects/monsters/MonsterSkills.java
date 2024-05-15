package org.example.objects.monsters;

import org.example.interfaces.ICommand;
import org.example.interfaces.ISkillCommand;

import java.util.HashMap;
import java.util.Random;

public class MonsterSkills {
    private int skillCost;
    private Random random = new Random();
    HashMap<String, ISkillCommand> monsterSkills = new HashMap<>();
    public MonsterSkills(Monster monster){
        monsterSkills.put("Rush", () -> rushSkill(monster));
        monsterSkills.put("Skewer", () -> skewerSkill(monster));
        monsterSkills.put("Heal", () -> healSkill(monster));
        monsterSkills.put("Bash", () -> bashSkill(monster));
        monsterSkills.put("Slice", () -> sliceSkill(monster));
        monsterSkills.put("Gore", () -> goreSkill(monster));
    }

    public double rushSkill(Monster monster){
        skillCost = 20;
        System.out.println(monster.getName() + " uses Rush.\n");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return monster.getAttack() / (2.0 * random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }

    public double skewerSkill(Monster monster){
        skillCost = 60;
        System.out.println(monster.getName() + " uses Skewer.\n");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return monster.getAttack() / (3.5 * random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }

    public double healSkill(Monster monster){
        skillCost = 100;
        System.out.println(monster.getName() + " healed itself, how much you do not know...");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return (monster.getAttack() * 80) * (random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }

    public double bashSkill(Monster monster){
        skillCost = 100;
        System.out.println(monster.getName() + " uses Bash.\n");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return monster.getAttack() / (6.0 * random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }

    public double sliceSkill(Monster monster){
        skillCost = 50;
        System.out.println(monster.getName() + " uses Slice.\n");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return monster.getAttack() / (3.0 * random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }
    public double goreSkill(Monster monster){
        skillCost = 200;
        System.out.println(monster.getName() + " uses Gore.\n");
        monster.setFocusPoints(monster.getFocusPoints() - skillCost);
        return monster.getAttack() / (8.0 * random.nextDouble(monster.getSkillDamageVariance()) / 100);
    }

    public HashMap<String, ISkillCommand> getMonsterSkills() {
        return monsterSkills;
    }
}
