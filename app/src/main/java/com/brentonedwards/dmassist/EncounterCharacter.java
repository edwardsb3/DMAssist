package com.brentonedwards.dmassist;

import android.support.annotation.NonNull;

public class EncounterCharacter {

    int index;
    String name;
    CharacterData characterSheet;
    int initiative;
    boolean blinded;
    boolean charmed;
    boolean deafened;
    boolean frightened;
    boolean grappled;
    boolean incapacitated;
    boolean notVisable;
    boolean paralyzed;
    boolean petrified;
    boolean poisoned;
    boolean restrained;
    boolean stunned;

    public EncounterCharacter() {

        this.index = 0;
        this.name = "Bilmy";
        this.characterSheet = EncountersActivity.characterData.get(0);
        this.initiative =0;
        this.blinded = false;
        this.charmed = false;
        this.deafened = false;
        this.frightened = false;
        this.grappled = false;
        this.incapacitated = false;
        this.notVisable = false;
        this.paralyzed = false;
        this.petrified = false;
        this.poisoned = false;
        this.restrained = false;
        this.stunned = false;
    }

    public EncounterCharacter(int index, String name, CharacterData characterSheet) {
        this.index = index;
        this.name = name;
        this.characterSheet = characterSheet;
        this.initiative =0;
        this.blinded = false;
        this.charmed = false;
        this.deafened = false;
        this.frightened = false;
        this.grappled = false;
        this.incapacitated = false;
        this.notVisable = false;
        this.paralyzed = false;
        this.petrified = false;
        this.poisoned = false;
        this.restrained = false;
        this.stunned = false;

    }

    public EncounterCharacter makeNewObject() {

        return new EncounterCharacter();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterData getCharacterSheet() {
        return characterSheet;
    }

    public void setCharacterSheet(CharacterData characterSheet) {
        this.characterSheet = characterSheet;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public boolean isBlinded() {
        return blinded;
    }

    public void setBlinded(boolean blinded) {
        this.blinded = blinded;
    }

    public boolean isCharmed() {
        return charmed;
    }

    public void setCharmed(boolean charmed) {
        this.charmed = charmed;
    }

    public boolean isDeafened() {
        return deafened;
    }

    public void setDeafened(boolean deafened) {
        this.deafened = deafened;
    }

    public boolean isFrightened() {
        return frightened;
    }

    public void setFrightened(boolean frightened) {
        this.frightened = frightened;
    }

    public boolean isGrappled() {
        return grappled;
    }

    public void setGrappled(boolean grappled) {
        this.grappled = grappled;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public void setIncapacitated(boolean incapacitated) {
        this.incapacitated = incapacitated;
    }

    public boolean isNotVisable() {
        return notVisable;
    }

    public void setNotVisable(boolean notVisable) {
        this.notVisable = notVisable;
    }

    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    public boolean isPetrified() {
        return petrified;
    }

    public void setPetrified(boolean petrified) {
        this.petrified = petrified;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public boolean isRestrained() {
        return restrained;
    }

    public void setRestrained(boolean restrained) {
        this.restrained = restrained;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

}