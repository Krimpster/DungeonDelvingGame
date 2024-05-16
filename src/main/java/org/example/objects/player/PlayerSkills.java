package org.example.objects.player;

import org.example.interfaces.IBasicInterface;
import org.example.interfaces.IPlayerSkillCommand;
import org.example.objects.skill_types.PlayerSkill;
import org.example.objects.skill_types.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayerSkills {
    private Random random = new Random();
    private PlayerCharacter playerCharacter;
    private String input;
    private ArrayList<PlayerSkill> skillValues = new ArrayList<>();
    private ArrayList<String> basicSkillList = new ArrayList<>();
    private HashMap<String, IBasicInterface> basicSkillCommandHashMap = new HashMap<>();
    private ArrayList<String> fighterSkillList = new ArrayList<>();
    private ArrayList<String> mageSkillList = new ArrayList<>();
    private ArrayList<String> rogueSkillList = new ArrayList<>();
    private HashMap<String, IPlayerSkillCommand> professionSkillCommandHashMap = new HashMap<>();
    public PlayerSkills(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
        basicSkillList.add("Attack the monster (a)");
        basicSkillList.add("Defend against the next blow (d)");

        fighterSkillList.add("Rending Flash (r)");
        fighterSkillList.add("Invigour (i)");
        fighterSkillList.add("Hammerblow (h)");

        mageSkillList.add("Firebolt (f)");
        mageSkillList.add("Thunderclap (t)");
        mageSkillList.add("Ice Lance (l)");

        rogueSkillList.add("Sneak Attack (s)");
        rogueSkillList.add("Throwing Knife (t)");
        rogueSkillList.add("Vital Strike (v)");
        rogueSkillList.add("Pilfer Enemy (p)");
    }

    public PlayerSkills(PlayerCharacter playerCharacter, String input){
        this.playerCharacter = playerCharacter;
        this.input = input;

        basicSkillCommandHashMap.put("a", () -> basicAttack(playerCharacter));
        basicSkillCommandHashMap.put("d", () -> basicDefend(playerCharacter));

        skillValues.add(new PlayerSkill("Rending Flash","r", 15, 1.5, "fighter"));
        skillValues.add(new PlayerSkill("Invigour","i", 60, 2.1, "fighter"));
        skillValues.add(new PlayerSkill("Hammerblow", "h", 45, 4.0, "fighter"));
        skillValues.add(new PlayerSkill("Firebolt", "f", 50, 1.5, "mage"));
        skillValues.add(new PlayerSkill("Thunderclap", "t", 125, 3.5, "mage"));
        skillValues.add(new PlayerSkill("Ice Lance", "l", 200, 5.0, "mage"));
        skillValues.add(new PlayerSkill("Sneak Attack", "s", 30, 2.3, "rogue"));
        skillValues.add(new PlayerSkill("Throwing Knife", "k", 55, 3.0, "rogue"));
        skillValues.add(new PlayerSkill("Vital Strike", "v", 100, 5.0, "rogue"));
        skillValues.add(new PlayerSkill("Pilfer Enemy", "p", 200, 1.1, "rogue"));

        professionSkillCommandHashMap.put("r", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("i", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("h", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));

        professionSkillCommandHashMap.put("f", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("t", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("l", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));

        professionSkillCommandHashMap.put("s", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("k", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("v", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
        professionSkillCommandHashMap.put("p", (playerCharacter1, input1) -> playerSkill(playerCharacter, input));
    }

    public double basicAttack(PlayerCharacter playerCharacter){
        System.out.println("You attack the monster!");
        return playerCharacter.getAttack() * 1.2;
    }

    public double basicDefend(PlayerCharacter playerCharacter){
        System.out.println("You brace yourself for the next blow");
        return playerCharacter.getDefense() * 4;
    }

    public double playerSkill(PlayerCharacter playerCharacter, String input){
        for (PlayerSkill ps : skillValues){
            if (ps.getSkillAbbreviation().equals(input)){
                int skillCost = ps.getSkillCost();
                if(playerCharacter.getFocusPoints() >= skillCost) {
                    System.out.println("You use " + ps.getSkillName() + ". (" + skillCost + ")\n");
                    playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
                    return playerCharacter.getAttack() * ps.getDamageMultiplier() * getRandom(playerCharacter);
                }
            }
        }
        System.out.println("The skill fizzles due to insufficient focus...");
        return 0;
    }

    private double getRandom(PlayerCharacter playerCharacter){
        return random.nextDouble(playerCharacter.getSkillDamageVarianceOrigin(),
                playerCharacter.getSkillDamageVarianceBound()) / 100;
    }


    public ArrayList<String> getBasicSkillList() {
        return basicSkillList;
    }

    public HashMap<String, IBasicInterface> getBasicSkillCommandHashMap() {
        return basicSkillCommandHashMap;
    }

    public ArrayList<String> getFighterSkillList() {
        return fighterSkillList;
    }

    public ArrayList<String> getMageSkillList() {
        return mageSkillList;
    }

    public ArrayList<String> getRogueSkillList() {
        return rogueSkillList;
    }

    public HashMap<String, IPlayerSkillCommand> getProfessionSkillCommandHashMap() {
        return professionSkillCommandHashMap;
    }
}
