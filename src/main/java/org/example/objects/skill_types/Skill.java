package org.example.objects.skill_types;

public class Skill {
    private String skillName;
    private int skillCost;
    private double damageMultiplier;

    public Skill(String skillName, int skillCost, double damageMultiplier){
        this.skillName = skillName;
        this.skillCost = skillCost;
        this.damageMultiplier = damageMultiplier;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
}
