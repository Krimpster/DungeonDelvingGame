package org.example;

import org.example.interfaces.ICommand;
import org.example.map.MapBuilder;
import org.example.map.Node;
import org.example.objects.monsters.BossMonster;
import org.example.objects.monsters.Monster;
import org.example.objects.monsters.NormalMonster;
import org.example.objects.player.PlayerCharacter;
import org.example.objects.player.PlayerSkills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.example.FileManager.bestiary;

public class Combat {
    private ArrayList<NormalMonster> monsterList = new ArrayList<>();
    private ArrayList<BossMonster> bossList = new ArrayList<>();
    private PlayerSkills playerSkills;
    private PlayerCharacter playerCharacter;
    private NormalMonster normalMonster;
    private FileManager fileManager = new FileManager();
    private ArrayList<String> combatChoices = new ArrayList<>();
    private HashMap<String, ICommand> combatCommandHashMap = new HashMap<>();
    private Random random = new Random();
    private int turn = 1;
    public Combat(PlayerCharacter playerCharacter,
                  Node[][] nodeMap,
                  Node start,
                  Node goal,
                  MapBuilder mapBuilder,
                  Exploration exploration){

    }
    public Combat(PlayerCharacter playerCharacter, int turnsTaken){

    }

    public Combat(PlayerCharacter playerCharacter){
        this.playerCharacter = playerCharacter;
    }

    public void startCombat(){

    }

    public void startBossEncounter(){

    }

    public void startTestEncounter(){
        fileManager.load(bestiary.toString());
        monsterList = fileManager.getMonsters();
        int selectedMonster = random.nextInt(monsterList.size()-1);
        normalMonster = monsterList.get(selectedMonster);

        boolean running = true;
        while(running){
            if(turn % 2 != 0){
                playerTakesTurn(playerCharacter);
                turn++;
            }
            else{
                monsterTakesTurn(normalMonster);
                turn++;
            }
        }
    }

    private void playerTakesTurn(PlayerCharacter playerCharacter){

    }
    private void monsterTakesTurn(NormalMonster normalMonster){

    }
}
