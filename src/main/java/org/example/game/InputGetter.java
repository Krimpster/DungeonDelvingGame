package org.example.game;

import org.example.objects.monsters.MonsterSkills;

import java.util.ArrayList;
import java.util.Scanner;

public class InputGetter {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> skillNames = new ArrayList<>();

    public int getIntInput(String message){
        while(true){
            System.out.println(message);
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e){
                System.out.println("That isn't a number. Please input only whole numbers!");
            }
        }
    }

    public String getStringInput(String message){
        while(true) {
            System.out.println(message);
            String inputString = scanner.nextLine();
            if(!inputString.isBlank()){
                return inputString;
            } else if (isNumeric(inputString)) {
                System.out.println("That is a number. Please input only words or names!");
            }
            System.out.println("That isn't a valid input!");
        }
    }

    public String getSkillStringInput(String message){
        while(true) {
            System.out.println(message);
            skillNames = new MonsterSkills().getSkillNames();
            for (int i = 0; i < skillNames.size(); i++){
                if(i == skillNames.size() - 1) {
                    System.out.println(skillNames.get(i));
                }
                else {
                    System.out.print(skillNames.get(i) + ", ");
                }
            }
            String inputString = scanner.nextLine();
            boolean found = false;
            for (String s : skillNames){
                if(inputString.equals(s)){
                    found = true;
                }
            }
            if(found){
                return inputString;
            } else if (isNumeric(inputString)) {
                System.out.println("That is a number. Please input only words or names!");
            }
            System.out.println("That isn't a valid input!");
        }
    }

    private boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
