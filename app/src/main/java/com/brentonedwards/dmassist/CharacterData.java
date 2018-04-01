package com.brentonedwards.dmassist;

import java.io.Serializable;

/**
 * Created by Brenton Edwards 3/20/2018
 */
public class CharacterData {

    String charName;
    String charType;
    String charChallengeLevel;
    String charAllignment;
    int strength = 15;
    int dexterity = 14;
    int constitution = 13;
    int wisdom = 12;
    int intelligence = 10;
    int charisma = 8;

    public CharacterData(String charName, String charType, String charChallengeLevel, String charAllignment) {
        this.charName=charName;
        this.charType=charType;
        this.charChallengeLevel= charChallengeLevel;
        this.charAllignment=charAllignment;
        int strength = 15;
        int dexterity = 14;
        int constitution = 13;
        int wisdom = 12;
        int intelligence = 10;
        int charisma = 8;


    }


    public String getCharName() {
        return charName;
    }


    public String getCharType() {
        return charType;
    }


    public String getCharChallengeLevel() {
        return charChallengeLevel;
    }


    public String getCharAllignment() {
        return charAllignment;
    }



    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getCharisma() {
        return charisma;
    }


}
