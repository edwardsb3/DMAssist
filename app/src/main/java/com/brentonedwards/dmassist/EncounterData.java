package com.brentonedwards.dmassist;

public class EncounterData {

    String encounterTitle;
    int players;
    int monsters;
    int challengeRating;

    public EncounterData(String encounterTitle, int players, int monsters, int challengeRating) {
        this.encounterTitle = encounterTitle;
        this.players = players;
        this.monsters = monsters;
        this.challengeRating = challengeRating;
    }

    public String getEncounterTitle() {
        return encounterTitle;
    }

    public void setEncounterTitle(String encounterTitle) {
        this.encounterTitle = encounterTitle;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getMonsters() {
        return monsters;
    }

    public void setMonsters(int monsters) {
        this.monsters = monsters;
    }

    public int getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(int challengeRating) {
        this.challengeRating = challengeRating;
    }
}
