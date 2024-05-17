package org.example.objects.monsters;

import org.example.interfaces.IMonsterSkillInterface;
import org.example.objects.skill_types.Skill;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MonsterSkills {
    private Random random = new Random();
    private ArrayList<String> skillNames = new ArrayList<>();
    private ArrayList<Skill> skillValues = new ArrayList<>();
    private HashMap<String, IMonsterSkillInterface> monsterSkills = new HashMap<>();
    private String skillName;

    public MonsterSkills(){
        skillNames.add("Rush");
        skillNames.add("Skewer");
        skillNames.add("Heal");
        skillNames.add("Bash");
        skillNames.add("Slice");
        skillNames.add("Gore");
    }

    public MonsterSkills(Monster monster, String skillName){
        this.skillName = skillName;
        skillValues.add(new Skill("Rush", 20, 2.0));
        skillValues.add(new Skill("Skewer", 60, 2.6));
        skillValues.add(new Skill("Heal", 100, 9.0));
        skillValues.add(new Skill("Bash", 75, 3.0));
        skillValues.add(new Skill("Slice", 50, 2.4));
        skillValues.add(new Skill("Gore", 200, 4.3));


        monsterSkills.put("Rush", (monster1, string) -> monsterSkill(monster, skillName));
        monsterSkills.put("Skewer", (monster1, string) -> monsterSkill(monster, skillName));
        monsterSkills.put("Heal", (monster1, string) -> monsterSkill(monster, skillName));
        monsterSkills.put("Bash", (monster1, string) -> monsterSkill(monster, skillName));
        monsterSkills.put("Slice", (monster1, string) -> monsterSkill(monster, skillName));
        monsterSkills.put("Gore", (monster1, string) -> monsterSkill(monster, skillName));
    }

    public double monsterSkill(Monster monster, String skillName){
        for (Skill s : skillValues){
            if (s.getSkillName().equals(skillName)){
                int skillCost = s.getSkillCost();
                if(monster.getFocusPoints() >= skillCost) {
                    System.out.println("The monster uses " + s.getSkillName() + ".");
                    monster.setFocusPoints(monster.getFocusPoints() - skillCost);
                    return monster.getAttack() * s.getDamageMultiplier() * getRandom(monster);
                }
            }
        }
        System.out.println("The skill fizzles due to insufficient focus...\n");
        return 0;
    }

    private double getRandom(Monster monster){
        return random.nextDouble(monster.getSkillDamageVarianceOrigin(), monster.getSkillDamageVarianceBound()) / 100;
    }
    public HashMap<String, IMonsterSkillInterface> getMonsterSkills() {
        return monsterSkills;
    }

    public ArrayList<String> getSkillNames() {
        return skillNames;
    }
}
