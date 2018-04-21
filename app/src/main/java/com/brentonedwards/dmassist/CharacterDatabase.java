package com.brentonedwards.dmassist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.brentonedwards.dmassist.EncounterCharacterEntity;

@Database(entities = {CharacterData.class, EncounterCharacter.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

        private static CharacterDatabase INSTANCE;

        public abstract CharacterDao characterDao();

        public static CharacterDatabase getAppDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), CharacterDatabase.class, "character-database")

                          //      .allowMainThreadQueries()
                                .build();
            }
            return INSTANCE;
        }



    public static void destroyInstance() {
            INSTANCE = null;
        }
    }



