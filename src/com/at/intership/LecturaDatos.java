package com.at.intership;

import java.util.Scanner;

public class LecturaDatos {

    Scanner scanner = new Scanner(System.in);

    public String readString(String prompt){
        String value;
        System.out.println(prompt);
        value=scanner.nextLine();
        return value;
    }

    public String readStringWithoutNumbers(String prompt){
        String value;
        boolean isCorrect;
        do {
            isCorrect = true;
            System.out.println(prompt);
            value=scanner.nextLine();
            if (value.isEmpty() || value.contains("0") || value.contains("1") || value.contains("2") || value.contains("3") || value.contains("4") || value.contains("5") || value.contains("6") || value.contains("7") || value.contains("8") || value.contains("9")) {
                System.out.println("TU NOMBRE NO PUEDE CONTENER NÚMEROS");
                isCorrect = false;
            }
        } while (!isCorrect);
        
        return value;
    }

    public double readDouble(String prompt){
        double value = 0;
        boolean isNumber;
        do {
            isNumber = true;
            try {
                System.out.println(prompt);
                value= Double.parseDouble(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("NECESITAS INGRESAR UN VALOR NÚMERICO");
                isNumber = false;
            }
        } while (!isNumber);

        return value;
    }

    public int readInteger(String prompt){
        int value = 0;
        boolean isNumber;
        do {
            isNumber = true;
            try {
                System.out.println(prompt);
                value= Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("NECESITAS INGRESAR UN VALOR NÚMERICO");
                isNumber = false;
            }
        } while (!isNumber);

        return value;
    }
}
