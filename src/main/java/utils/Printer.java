package utils;

public class Printer {

    public void printLoginMessage() {
        System.out.println("Hello to Vet Stucom!");
        System.out.println("What do you want to do?");
        System.out.println("1 - Login");
        System.out.println("2 - Register");
    }

    public void printLogin() {
        System.out.println("Introduce your credentials");
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

}
