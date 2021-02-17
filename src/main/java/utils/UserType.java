package utils;

public enum UserType {
    AUXILIAR("auxiliar"),
    VETERINARY("veterinary"),
    ADMINISTRATOR("administrator");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
