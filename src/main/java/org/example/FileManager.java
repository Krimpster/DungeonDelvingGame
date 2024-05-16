package org.example;


import org.example.objects.Leaderboard;
import org.example.objects.items.Equipment;

import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.NormalMonster;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private ArrayList<NormalMonster> monsters = new ArrayList<>();
    private ArrayList<BossMonster> bosses = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private ArrayList<Leaderboard> leaderboards = new ArrayList<>();
    private static File saved_files = new File("src/main/resources/saved_files");
    public static File bestiaryFilePath = new File("src/main/resources/saved_files/bestiary");
    public static File equipmentFilePath = new File("src/main/resources/saved_files/equipment");
    public static File leaderboardFilePath = new File("src/main/resources/saved_files/leaderboard");
    public FileManager(ArrayList<NormalMonster> monsters, ArrayList<BossMonster> bosses,
                       ArrayList<Equipment> equipment, ArrayList<Leaderboard> leaderboard){
        this.monsters = monsters;
        this.bosses = bosses;
        this.equipment = equipment;
        this.leaderboards = leaderboard;
    }
    public FileManager(){
    }

    public void saveMonsters(String filePath){
        try{
            saved_files.mkdir();
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
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
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void saveEquipment(String filePath){
        try{
            saved_files.mkdir();
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Equipment e : equipment){
                bw.write(e.getCSV());
                bw.newLine();
            }
            bw.close();
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void saveLeaderBoard(String filePath){
        try{
            saved_files.mkdir();
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Leaderboard l : leaderboards){
                bw.write(l.getCSV());
            }
            bw.close();
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void loadMonsters(String filePath){
        try{
            saved_files.mkdir();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
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
                    int skillDamageVarianceBound = Integer.parseInt(values[7]);
                    int skillDamageVarianceOrigin = Integer.parseInt(values[8]);
                    String monsterSkill = values[9];
                    String bossSkill = values[10];
                    int expReward = Integer.parseInt(values[11]);
                    monsters.add(new NormalMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                            focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                            monsterSkill, bossSkill, expReward));
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
                    int skillDamageVarianceBound = Integer.parseInt(values[7]);
                    int skillDamageVarianceOrigin = Integer.parseInt(values[8]);
                    String monsterSkill = values[9];
                    String bossSkill = values[10];
                    int expReward = Integer.parseInt(values[11]);
                    bosses.add(new BossMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                            focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                            monsterSkill, bossSkill, expReward));
                    line = br.readLine();
                }

            }
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void loadEquipment(String filePath){
        try{
            saved_files.mkdir();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] values = line.split(",");
                String itemName = values[0];
                String itemType = values[1];
                int hpModifier = Integer.parseInt(values[2]);
                int attackModifier = Integer.parseInt(values[3]);
                int defenseModifier = Integer.parseInt(values[4]);
                int fpModifier = Integer.parseInt(values[5]);
                int fpPerTurnModifier = Integer.parseInt(values[6]);
                line = br.readLine();
            }
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void loadLeaderBoard(String filePath){
        try{
            saved_files.mkdir();
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                    String[] values = line.split(",");
                    String playerName = values[0];
                    String playerClass = values[1];
                    int score = Integer.parseInt(values[2]);
                    int turnsTaken = Integer.parseInt(values[3]);
                    leaderboards.add(new Leaderboard(playerName, playerClass, score, turnsTaken));
                    line = br.readLine();
            }
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    private void saveHelper(String filePath, BufferedWriter bw){
        if(filePath.equals(bestiaryFilePath.toString())){
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
        if (filePath.equals(equipmentFilePath.toString())) {
            try {
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
        if ((filePath.equals(leaderboardFilePath.toString()))) {
            try {
                for(Leaderboard l : leaderboards){
                    bw.write(l.getCSV());
                }
                bw.close();
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    private void loadHelper(String filePath, BufferedReader br){
        if(filePath.equals(bestiaryFilePath.toString())) {
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
                        int skillDamageVarianceBound = Integer.parseInt(values[7]);
                        int skillDamageVarianceOrigin = Integer.parseInt(values[8]);
                        String monsterSkill = values[9];
                        String bossSkill = values[10];
                        int expReward = Integer.parseInt(values[11]);
                        monsters.add(new NormalMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                                monsterSkill, bossSkill, expReward));
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
                        int skillDamageVarianceBound = Integer.parseInt(values[7]);
                        int skillDamageVarianceOrigin = Integer.parseInt(values[8]);
                        String monsterSkill = values[9];
                        String bossSkill = values[10];
                        int expReward = Integer.parseInt(values[11]);
                        bosses.add(new BossMonster(name, hp, baseAttack, baseDefense, baseFocusPoints, focusPoints,
                                focusPointsPerTurn, skillDamageVarianceBound, skillDamageVarianceOrigin,
                                monsterSkill, bossSkill, expReward));
                        line = br.readLine();
                    }

                }
            }catch (Exception ex) {
                System.out.println(ex);
            }

        }
        if(filePath.equals(equipmentFilePath.toString())) {
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
        if (filePath.equals(leaderboardFilePath.toString())){
            System.out.println("Loading leaderboard.");
        }
    }

    public ArrayList<NormalMonster> getMonsters() {
        return monsters;
    }

    public ArrayList<BossMonster> getBosses() {
        return bosses;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public ArrayList<Leaderboard> getLeaderboards() {
        return leaderboards;
    }

    public void addToLeaderboardList(Leaderboard leaderboard){
        leaderboards.add(leaderboard);
    }
}
