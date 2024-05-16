package org.example.objects.skill_types;

import org.example.objects.player.PlayerSkills;

public class PlayerSkill extends Skill{
    private String skillAbbreviation;
    private String playerClass;
    public PlayerSkill(String skillName, String skillAbbreviation, int skillCost, double damageMultiplier, String playerClass){
        super(skillName, skillCost, damageMultiplier);
        this.skillAbbreviation = skillAbbreviation;
        this.playerClass = playerClass;
    }

    public String getSkillAbbreviation() {
        return skillAbbreviation;
    }

    public String getPlayerClass() {
        return playerClass;
    }
}
