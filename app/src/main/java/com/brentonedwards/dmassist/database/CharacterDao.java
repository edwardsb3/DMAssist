package com.brentonedwards.dmassist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Brenton on 3/26/2018.
 */
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM character")
    List<EncounterCharacterEntity> getAll();

//    @Query("SELECT * FROM character where name LIKE  :name AND alignment LIKE :alignment")
//    Character findByName(String name, String alignment);

    @Query("SELECT COUNT(*) from character")
    int countCharacters();

    @Insert
    void insertAll(EncounterCharacterEntity... characters);

    @Delete
    void delete(EncounterCharacterEntity character);
}
