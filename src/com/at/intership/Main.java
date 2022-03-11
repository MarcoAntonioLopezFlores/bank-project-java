package com.at.intership;

public class Main {

    private static final String PROP_USERNAME = "com.at.intership.username";
    private static final String PROP_PASSWORD = "com.at.intership.password";
    private static final byte TRY_LIMIT = 3;

    public static void main(String[] args) {
        try {
            PropertyHandler.load("/application-default.properties", "application.properties");
            System.out.println("Welcome...");
            System.out.println("Enter your credentials...");

            String username, password;

            boolean isLoggedIn = false;
            byte tryCount = 0;
            do {
                System.out.print("usuario: ");
                username = System.console().readLine();
                System.out.print("contraseña: ");
                password = new String(System.console().readPassword());

                if(username.equals(PropertyHandler.getStringProperty(PROP_USERNAME)) && password.equals(PropertyHandler.getStringProperty(PROP_PASSWORD))){
                    isLoggedIn = true;
                }else{
                    System.err.println("Incorrect username or password\n\n");
                    tryCount++;
                }

            } while (!isLoggedIn && tryCount < TRY_LIMIT);

            if(isLoggedIn) {
                System.out.printf("Successfully logged in as %s%n", username);
                runCommandListener();
            }
            else
                System.err.println("You have reached your attempts limit");

            PropertyHandler.persist();
            System.out.println("PROGRAM END");
        } catch (Exception e) {
            System.err.printf("%s: %s%n", e.getClass().getName(), e.getMessage());
        }
    }

    private static void runCommandListener() {
        String command;
        do {
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "help":
                    printHelp();
                    break;

                case "exit":
                    break;
                default:
                    System.err.printf("\"%s\" is not a recognized command%n", command);
            }
        } while(!"exit".equalsIgnoreCase(command));
    }

    private static void printHelp() {
        System.out.println("- help\n" +
                "- exit");
    }


}
