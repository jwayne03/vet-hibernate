package utils;

public class Printer {

    public void printLoginMessage() {
        System.out.println("Hello to Vet Stucom!");
        System.out.println("What do you want to do?");
        System.out.println("1 - Login");
        System.out.println("2 - Register");
    }

    public void printLogin() {
        System.out.println("Introduce your credentials:");
    }

    public void printRegisterMessage() {
        System.out.println("Registration:");
        System.out.println("Introduce the information");
    }

    public void printNeedToIntroduceAnOption() {
        System.out.println("You need to introduce an option");
    }

    public void printRole() {
        System.out.println("1 - Veterinary");
        System.out.println("2 - Auxiliar");
        System.out.println("3 - Administrator");
    }

    public void printMainMenu() {
        System.out.println("What do you want to do: ");
        System.out.println("1 - Consult records");
        System.out.println("2 - Register a record");
        System.out.println("3 - Unsubscribe a record");
        System.out.println("4 - Edit a record");
        System.out.println("5 - Register an user");
        System.out.println("6 - Unsubscribe an user");
        System.out.println("7 - Edit a user");
        System.out.println("8 - Consult a User");
        System.out.println("0 - Exit");
    }

    public void printExit() {
        System.out.println("You choose to exit. Goodbye!");
    }

    public void printTypes() {
        System.out.println("1 - Auxiliary");
        System.out.println("2 - Vet");
        System.out.println("3 - Admin");
    }

    public void printAuxiliaryMenu() {
        System.out.println("What do you want to do: ");
        System.out.println("1 - Consult records");
        System.out.println("0 - Exit");
    }

    public void printVetMenu() {
        System.out.println("What do you want to do: ");
        System.out.println("1 - Consult records");
        System.out.println("2 - Register a record");
        System.out.println("3 - Unsubscribe a record");
        System.out.println("4 - Edit a record");
        System.out.println("0 - Exit");
    }
}
