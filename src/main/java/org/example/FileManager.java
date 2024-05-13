package org.example;


import org.example.objects.items.Equipment;
import org.example.objects.items.Potion;

import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.NormalMonster;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private ArrayList<NormalMonster> monsters = new ArrayList<>();
    private ArrayList<BossMonster> bosses = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private static File saved_files = new File("src/main/resources/saved_files");
    public static File bestiary = new File("src/main/resources/saved_files/bestiary");
    private static File itemsandequipment = new File("saved_files/items");
    private static File leaderboard = new File("saved_files/leaderboard");
    public FileManager(ArrayList<NormalMonster> monsters, ArrayList<BossMonster> bosses, ArrayList<Potion> potions, ArrayList<Equipment> equipment){
        this.monsters = monsters;
        this.bosses = bosses;
        this.potions = potions;
        this.equipment = equipment;
    }
    public FileManager(){
    }

    public void save(String filePath){
        try{
            saved_files.mkdir();
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            saveHelper(filePath, bw);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void load(String filePath){
        try{
            saved_files.mkdir();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            loadHelper(filePath, br);
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    private void saveHelper(String filePath, BufferedWriter bw){
        if(filePath.equals(bestiary.toString())){
            try {
                for (int i = 0; i < monsters.size(); i++) {
                    bw.write(monsters.get(i).getCSV());
                    if (i < monsters.size() - 1){
                        bw.newLine();
                    }
                    else {
                        bw.newLine();
                        bw.write("%");
                        bw.newLine();
                    }
                    for(BossMonster b : bosses){
                        bw.write(b.getCSV());
                        bw.newLine();
                    }

                }
                bw.close();
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        if (filePath.equals(itemsandequipment.toString())) {
            try {
                for (int i = 0; i < potions.size(); i++) {
                    bw.write(potions.get(i).getCSV());
                    if(i < potions.size() - 1){
                        bw.newLine();
                    }
                    else {
                        bw.newLine();
                        bw.write("%");
                        bw.newLine();
                    }
                }
                for (Equipment e : equipment){
                    bw.write(e.getCSV());
                    bw.newLine();
                }
                bw.close();
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        if ((filePath.equals(leaderboard.toString()))) {
            System.out.println("Saving leaderboard.");
        }
    }

    private void loadHelper(String filePath, BufferedReader br){
        if(filePath.equals(bestiary.toString())) {
            try {
                String line = br.readLine();
                boolean boss = false;
                while (line != null) {
                    if (line.equals("%")) {
                        boss = true;
                        line = br.readLine();
                    }
                    if (!boss){
                        String[] values = line.split(",");
                        String name = values[0];
                        int hp = Integer.parseInt(values[1]);
                        int baseAttack = Integer.parseInt(values[2]);
                        int baseDefense = Integer.parseInt(values[3]);
                        int baseFocusPoints = Integer.parseInt(values[4]);
                        int focusPoints = Integer.parseInt(values[5]);
                        int focusPointsPerTurn = Integer.parseInt(values[6]);
                        int skillDamageVariance = Integer.parseInt(values[7]);
                        String monsterSkill = values[8];
                        String bossSkill = values[9];
                        int expReward = Integer.parseInt(values[10]);
                        monsters.add(new NormalMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                                focusPointsPerTurn, skillDamageVariance, monsterSkill, bossSkill, expReward));
                        line = br.readLine();
                    }else{
                        String[] values = line.split(",");
                        String name = values[0];
                        int hp = Integer.parseInt(values[1]);
                        int baseAttack = Integer.parseInt(values[2]);
                        int baseDefense = Integer.parseInt(values[3]);
                        int baseFocusPoints = Integer.parseInt(values[4]);
                        int focusPoints = Integer.parseInt(values[5]);
                        int focusPointsPerTurn = Integer.parseInt(values[6]);
                        int skillDamageVariance = Integer.parseInt(values[7]);
                        String monsterSkill = values[8];
                        String bossSkill = values[9];
                        int expReward = Integer.parseInt(values[10]);
                        bosses.add(new BossMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                                focusPointsPerTurn, skillDamageVariance, monsterSkill, bossSkill, expReward));
                        line = br.readLine();
                    }

                }
            }catch (Exception ex) {
                System.out.println(ex);
            }

        }
        if(filePath.equals(itemsandequipment.toString())) {
            try {
                String line = br.readLine();
                boolean equipment = false;
                while (line != null) {
                    if (line.equals("%")) {
                        equipment = true;
                        line = br.readLine();
                    }
                    if (!equipment) {
                        String[] values = line.split(",");
                        String itemName = values[0];
                        String itemType = values[1];
                        String itemEffect = values[2];
                        line = br.readLine();
                    } else {
                        String[] values = line.split(",");
                        String itemName = values[0];
                        String itemType = values[1];
                        int attackModifier = Integer.parseInt(values[2]);
                        line = br.readLine();
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (filePath.equals(leaderboard.toString())){
            System.out.println("Loading leaderboard.");
        }
    }

    public ArrayList<NormalMonster> getMonsters() {
        return monsters;
    }

    public ArrayList<BossMonster> getBosses() {
        return bosses;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

}
