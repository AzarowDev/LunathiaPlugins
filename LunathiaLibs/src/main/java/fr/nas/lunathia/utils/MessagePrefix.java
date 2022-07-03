package fr.nas.lunathia.utils;

public enum MessagePrefix {

    BANQUE("#FFD700", "Banque"),
    CONSOLE("", "Console")
    ;

    private String hex;
    private String prefix;

    MessagePrefix(String hex, String prefix) {
        this.hex = hex;
        this.prefix = prefix;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
