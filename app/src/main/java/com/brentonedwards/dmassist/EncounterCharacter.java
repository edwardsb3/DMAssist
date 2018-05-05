package com.brentonedwards.dmassist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "character")
public class EncounterCharacter {

    @PrimaryKey(autoGenerate = true)
    int index;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "player_character")
    boolean isPlayerCharacter;

    @ColumnInfo(name = "armor_class")
    int armorClass;

    @ColumnInfo(name = "health")
    int health;


    @ColumnInfo(name = "character_sheet_index")
    int characterSheetIndex;

    @ColumnInfo(name = "initiative")
    int initiative;
    @ColumnInfo(name = "blinded")
    boolean blinded;
    @ColumnInfo(name = "charmed")
    boolean charmed;
    @ColumnInfo(name = "deafened")
    boolean deafened;
    @ColumnInfo(name = "frightened")
    boolean frightened;
    @ColumnInfo(name = "grappled")
    boolean grappled;
    @ColumnInfo(name = "incapacitated")
    boolean incapacitated;
    @ColumnInfo(name = "not_visable")
    boolean notVisable;
    @ColumnInfo(name = "paralyzed")
    boolean paralyzed;
    @ColumnInfo(name = "petrified")
    boolean petrified;
    @ColumnInfo(name = "poisoned")
    boolean poisoned;
    @ColumnInfo(name = "restrained")
    boolean restrained;
    @ColumnInfo(name = "stunned")
    boolean stunned;

    @Ignore
    public EncounterCharacter() {


        this.isPlayerCharacter = true;
        this.name = "Bilmy";
        this.health = 100;
        this.armorClass = 10;
        this.characterSheetIndex = 0;
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

    public EncounterCharacter(String name, int characterSheetIndex, int armorClass, int health) {

        this.isPlayerCharacter = false;
        this.name = name;
        this.health = health;
        this.characterSheetIndex = characterSheetIndex;
        this.armorClass = armorClass;
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

    public int getCharacterSheet() {
        return characterSheetIndex;
    }

    public void setCharacterSheet(int characterSheetIndex) {
        this.characterSheetIndex = characterSheetIndex;
    }

    public boolean isPlayerCharacter() {
        return isPlayerCharacter;
    }

    public void setPlayerCharacter(boolean playerCharacter) {
        isPlayerCharacter = playerCharacter;
    }

    public int getCharacterSheetIndex() {
        return characterSheetIndex;
    }

    public void setCharacterSheetIndex(int characterSheetIndex) {
        this.characterSheetIndex = characterSheetIndex;
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

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}