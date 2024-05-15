package org.example.objects.player;

import org.example.interfaces.ICommand;
import org.example.interfaces.ISkillCommand;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayerSkills {
    private Random random = new Random();
    private PlayerCharacter playerCharacter;
    private int skillCost;
    private ArrayList<String> basicSkillList = new ArrayList<>();
    private HashMap<String, ISkillCommand> basicSkillCommandHashMap = new HashMap<>();
    private ArrayList<String> fighterSkillList = new ArrayList<>();
    private ArrayList<String> mageSkillList = new ArrayList<>();
    private ArrayList<String> rogueSkillList = new ArrayList<>();
    private HashMap<String, ISkillCommand> professionSkillCommandHashMap = new HashMap<>();

    public PlayerSkills(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
        basicSkillList.add("Attack the monster (a)");
        basicSkillList.add("Defend against the next blow (d)");

        basicSkillCommandHashMap.put("a", () -> basicAttack(playerCharacter));
        basicSkillCommandHashMap.put("d", () -> basicDefend(playerCharacter));

        fighterSkillList.add("Rending Flash (r)");
        fighterSkillList.add("Invigour (i)");
        fighterSkillList.add("Hammerblow (h)");

        professionSkillCommandHashMap.put("r", () -> fighterSkill1(playerCharacter));
        professionSkillCommandHashMap.put("i", () -> fighterSkill2(playerCharacter));
        professionSkillCommandHashMap.put("h", () -> fighterSkill3(playerCharacter));

        mageSkillList.add("Firebolt (f)");
        mageSkillList.add("Thunderclap (t)");
        mageSkillList.add("Ice Lance (i)");

        professionSkillCommandHashMap.put("f", () -> mageSkill1(playerCharacter));
        professionSkillCommandHashMap.put("t", () -> mageSkill2(playerCharacter));
        professionSkillCommandHashMap.put("i", () -> mageSkill3(playerCharacter));

        rogueSkillList.add("Sneak Attack (s)");
        rogueSkillList.add("Throwing Knife (t)");
        rogueSkillList.add("Vital Strike (v)");
        rogueSkillList.add("Pilfer Enemy (p)");

        professionSkillCommandHashMap.put("s", () -> rogueSkill1(playerCharacter));
        professionSkillCommandHashMap.put("t", () -> rogueSkill2(playerCharacter));
        professionSkillCommandHashMap.put("v", () -> rogueSkill3(playerCharacter));
        professionSkillCommandHashMap.put("p", () -> rogueSkill4(playerCharacter));
    }

    public double basicAttack(PlayerCharacter playerCharacter){
        System.out.println("You attack the monster!");
        return playerCharacter.getAttack() / 1.2;
    }

    public double basicDefend(PlayerCharacter playerCharacter){
        System.out.println("You brace yourself for the next blow");
        return playerCharacter.getDefense() * 4;
    }

    public double fighterSkill1(PlayerCharacter playerCharacter){
        skillCost = 20;
        if(playerCharacter.getPlayerClass().equals("fighter") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Rending Flash. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double fighterSkill2(PlayerCharacter playerCharacter){
        skillCost = 60;
        if(playerCharacter.getPlayerClass().equals("fighter") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Invigour. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / 1.5;
        }
        return 0;
    }

    public double fighterSkill3(PlayerCharacter playerCharacter){
        skillCost = 45;
            if(playerCharacter.getPlayerClass().equals("fighter") &&
                    playerCharacter.getFocusPoints() >= skillCost) {
                System.out.println("You use HammerBlow. (" + skillCost + ")\n");
                playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
                return playerCharacter.getAttack() / (2.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
            }
        return 0;
    }

    public double mageSkill1(PlayerCharacter playerCharacter){
        skillCost = 50;
        if(playerCharacter.getPlayerClass().equals("mage") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Firebolt. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (2.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double mageSkill2(PlayerCharacter playerCharacter){
        skillCost = 75;
        if(playerCharacter.getPlayerClass().equals("mage") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Thunderclap. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (3.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double mageSkill3(PlayerCharacter playerCharacter){
        skillCost = 200;
        if(playerCharacter.getPlayerClass().equals("mage") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Ice Lance. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double rogueSkill1(PlayerCharacter playerCharacter){
        skillCost = 30;
        if(playerCharacter.getPlayerClass().equals("rogue") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Sneak Attack. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (2.3 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double rogueSkill2(PlayerCharacter playerCharacter){
        skillCost = 55;
        if(playerCharacter.getPlayerClass().equals("rogue") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Throwing Knife. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (3.0 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double rogueSkill3(PlayerCharacter playerCharacter){
        skillCost = 100;
        if(playerCharacter.getPlayerClass().equals("rogue") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Vital Strike. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }

    public double rogueSkill4(PlayerCharacter playerCharacter){
        skillCost = 200;
        if(playerCharacter.getPlayerClass().equals("rogue") &&
                playerCharacter.getFocusPoints() >= skillCost) {
            System.out.println("You use Pilfer. (" + skillCost + ")\n");
            playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
            return playerCharacter.getAttack() / (0.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
        }
        return 0;
    }


    public ArrayList<String> getBasicSkillList() {
        return basicSkillList;
    }

    public HashMap<String, ISkillCommand> getBasicSkillCommandHashMap() {
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

    public HashMap<String, ISkillCommand> getProfessionSkillCommandHashMap() {
        return professionSkillCommandHashMap;
    }
}
