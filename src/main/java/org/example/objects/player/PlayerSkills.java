package org.example.objects.player;

import org.example.interfaces.ICommand;
import org.example.objects.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayerSkills {
    private Random random = new Random();
    private int skillCost;
    private ArrayList<String> fighterSkillList = new ArrayList<>();
    private HashMap<String, ICommand> fighterSkillCommandHashMap = new HashMap<>();

    private ArrayList<String> mageSkillList = new ArrayList<>();
    private HashMap<String, ICommand> mageSkillCommandHashMap = new HashMap<>();

    private ArrayList<String> rogueSkillList = new ArrayList<>();
    private HashMap<String, ICommand> rogueSkillCommandHashMap = new HashMap<>();

    public PlayerSkills(PlayerCharacter playerCharacter){
        fighterSkillList.add("Rending Flash (rend)");
        fighterSkillList.add("Invigour (inv)");
        fighterSkillList.add("Hammerblow (ham)");

        fighterSkillCommandHashMap.put("rend", () -> fighterSkill1(playerCharacter));
        fighterSkillCommandHashMap.put("inv", () -> fighterSkill2(playerCharacter));
        fighterSkillCommandHashMap.put("ham", () -> fighterSkill3(playerCharacter));

        mageSkillList.add("Firebolt (fire)");
        mageSkillList.add("Thunderclap (thund)");
        mageSkillList.add("Ice Lance (ice)");

        mageSkillCommandHashMap.put("fire", () -> mageSkill1(playerCharacter));
        mageSkillCommandHashMap.put("thind", () -> mageSkill2(playerCharacter));
        mageSkillCommandHashMap.put("ice", () -> mageSkill3(playerCharacter));

        rogueSkillList.add("Sneak Attack (sneak)");
        rogueSkillList.add("Throwing Knife (throw)");
        rogueSkillList.add("Vital Strike (vital)");
        rogueSkillList.add("Pilfer Enemy (pilf)");

        rogueSkillCommandHashMap.put("sneak", () -> rogueSkill1(playerCharacter));
        rogueSkillCommandHashMap.put("throw", () -> rogueSkill2(playerCharacter));
        rogueSkillCommandHashMap.put("vital", () -> rogueSkill3(playerCharacter));
        rogueSkillCommandHashMap.put("pilf", () -> rogueSkill4(playerCharacter));
    }

    public double fighterSkill1(PlayerCharacter playerCharacter){
        skillCost = 20;
        System.out.println("You use Rending Flash. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public void fighterSkill2(PlayerCharacter playerCharacter){
        skillCost = 60;
        System.out.println("You use Invigour. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        int healAmount = playerCharacter.getAttack() / 2;
        playerCharacter.setHp(playerCharacter.getHp() + healAmount);
        System.out.println("You healed yourself for " + healAmount + " HP.\n");
    }

    public double fighterSkill3(PlayerCharacter playerCharacter){
        skillCost = 45;
        System.out.println("You use HammerBlow. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (2.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double mageSkill1(PlayerCharacter playerCharacter){
        skillCost = 50;
        System.out.println("You use Firebolt. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (2.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double mageSkill2(PlayerCharacter playerCharacter){
        skillCost = 75;
        System.out.println("You use Thunderclap. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (3.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double mageSkill3(PlayerCharacter playerCharacter){
        skillCost = 200;
        System.out.println("You use Ice Lance. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double rogueSkill1(PlayerCharacter playerCharacter){
        skillCost = 30;
        System.out.println("You use Sneak Attack. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (2.3 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double rogueSkill2(PlayerCharacter playerCharacter){
        skillCost = 55;
        System.out.println("You use Throwing Knife. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (3.0 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double rogueSkill3(PlayerCharacter playerCharacter){
        skillCost = 100;
        System.out.println("You use Vital Strike. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }

    public double rogueSkill4(PlayerCharacter playerCharacter){
        skillCost = 200;
        System.out.println("You use Pilfer. (" + skillCost + ")\n");
        playerCharacter.setFocusPoints(playerCharacter.getFocusPoints() - skillCost);
        return playerCharacter.getAttack() / (0.5 * random.nextDouble(playerCharacter.getSkillDamageVariance()) / 100);
    }


    public ArrayList<String> getFighterSkillList() {
        return fighterSkillList;
    }

    public HashMap<String, ICommand> getFighterSkillCommandHashMap() {
        return fighterSkillCommandHashMap;
    }


    public ArrayList<String> getMageSkillList() {
        return mageSkillList;
    }

    public HashMap<String, ICommand> getMageSkillCommandHashMap() {
        return mageSkillCommandHashMap;
    }

    public ArrayList<String> getRogueSkillList() {
        return rogueSkillList;
    }

    public HashMap<String, ICommand> getRogueSkillCommandHashMap() {
        return rogueSkillCommandHashMap;
    }
}
