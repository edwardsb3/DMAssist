package com.brentonedwards.dmassist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.brentonedwards.dmassist.util.Action;
import com.brentonedwards.dmassist.util.SpecialAbility;

import java.sql.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Brenton Edwards 3/20/2018
 */
@Entity(tableName = "character_data")
public class CharacterData {

    @PrimaryKey(autoGenerate = true)
        int uid;
    @ColumnInfo(name = "name")
    String charName;
    @ColumnInfo(name = "size")
    String size;
    @ColumnInfo(name = "type")
    String type;
    @ColumnInfo(name = "subtype")
    String subtype;
    @ColumnInfo(name = "alignment")
    String alignment;
    @ColumnInfo(name = "armor_class")
    int armorClass;
    @ColumnInfo(name = "hit_points")
    int hitPoints;
    @ColumnInfo(name = "hit_dice")
    String hitDice;
    @ColumnInfo(name = "speed")
    String speed;
    @ColumnInfo(name = "strength")
    int strength;
    @ColumnInfo(name = "dexterity")
    int dexterity;
    @ColumnInfo(name = "constitution")
    int constitution;
    @ColumnInfo(name = "wisdom")
    int wisdom;
    @ColumnInfo(name = "intelligence")
    int intelligence;
    @ColumnInfo(name = "charisma")
    int charisma;
    @ColumnInfo(name = "damage_vulnerabilities")
    String damageVulnerabilities;
    @ColumnInfo(name = "damage_resistance")
    String damageResistances;
    @ColumnInfo(name = "damage_immunities")
    String damageImmunities;
    @ColumnInfo(name = "condition_immunities")
    String conditionImmunities;
    @ColumnInfo(name = "senses")
    String senses;
    @ColumnInfo(name = "languages")
    String languages;
    @ColumnInfo(name = "challenge_rating")
    double challengeRating;
    @ColumnInfo(name = "special_abilities") @TypeConverters(SpecialAttributesDataTypeConverter.class)
    List<SpecialAbility> specialAbilities;
    @ColumnInfo(name = "actions") @TypeConverters(CharacterDataTypeConverters.class)
    List<Action> actions;

    public CharacterData(){

        this.charName = "Bilmy";
        this.size = "medium";
        this.type = "human";
        this.subtype = "humanoid";
        this.alignment = "neutral";
        this.armorClass = 10;
        this.hitPoints = 50;
        this.hitDice = "12";
        this.speed = "25";
        this.strength = 10;
        this.dexterity = 10;
        this.constitution = 10;
        this.wisdom = 10;
        this.intelligence = 10;
        this.charisma = 10;
        this.damageVulnerabilities = "None";
        this.damageResistances = "None";
        this.damageImmunities = "None";
        if(conditionImmunities!="") {this.conditionImmunities = conditionImmunities;}
        else{this.conditionImmunities="None";}
        this.senses = "Normal sight";
        this.languages = "Common";
        this.challengeRating = 0;
        this.specialAbilities = null;
        this.actions = null;

    }

    public CharacterData(String charName, String size, String type, String subtype, String alignment, int armorClass, int hitPoints, String hitDice, String speed, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma, String damageVulnerabilities, String damageResistances, String damageImmunities, String conditionImmunities, String senses, double challengeRating, String languages, List<SpecialAbility> specialAbilities, List<Action> actions) {

        this.charName = charName;
        this.size = size;
        this.type = type;
        this.subtype = subtype;
        this.alignment = alignment;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.hitDice = hitDice;
        this.speed = speed;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.damageVulnerabilities = damageVulnerabilities;
        this.damageResistances = damageResistances;
        this.damageImmunities = damageImmunities;
        if(conditionImmunities!="") {this.conditionImmunities = conditionImmunities;}
        else{this.conditionImmunities="None";}
        this.senses = senses;
        this.languages = languages;
        this.challengeRating = challengeRating;
        this.specialAbilities = specialAbilities;
        this.actions = actions;
    }


    @Ignore
    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }


    public String getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(String damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
    }

    public String getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(String damageResistances) {
        this.damageResistances = damageResistances;
    }

    public String getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(String damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public String getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(String conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public String getSenses() {
        return senses;
    }

    public void setSenses(String senses) {
        this.senses = senses;
    }

    public double getChallengeRating() {
        return challengeRating;
    }


    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public List<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(List<SpecialAbility> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}