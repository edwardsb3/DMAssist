package com.brentonedwards.dmassist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.brentonedwards.dmassist.EncounterCharacterEntity;

import java.util.List;

/**
 * Created by Brenton on 3/26/2018.
 */
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM character")
    List<EncounterCharacter> getAllEncounterCharacters();

    @Query("SELECT * FROM character_data")
    List<CharacterData> getAllCharacterData();

    @Query("SELECT * FROM character where name = :name")
    EncounterCharacter findByName(String name);

    @Query("SELECT * FROM character_data WHERE uid = :uid")
    CharacterData findByUid(int uid);

    @Query("SELECT COUNT(*) from character")
    int countCharacters();

    @Query("SELECT COUNT(*) from character_data")
    int countCharacterData();

    @Insert
    void insertAll(CharacterData... characters_data);

    @Insert
    void insertAll(EncounterCharacter... characters);

    @Delete
    void delete(EncounterCharacter character);
}
