package com.brentonedwards.dmassist;

public class PlayerEncounterCharacter extends EncounterCharacter {

    int level;
    String charClass;
    String race;

    public PlayerEncounterCharacter(int index, String name, CharacterData characterSheet, int level, String charClass, String race) {
        super(index, name, characterSheet);
        this.level = level;
        this.charClass = charClass;
        this.race = race;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
