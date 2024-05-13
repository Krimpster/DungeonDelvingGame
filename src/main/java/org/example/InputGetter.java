package org.example;

import java.util.Scanner;

public class InputGetter {
    Scanner scanner = new Scanner(System.in);

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
