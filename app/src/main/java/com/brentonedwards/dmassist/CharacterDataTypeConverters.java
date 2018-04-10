package com.brentonedwards.dmassist;

import android.arch.persistence.room.TypeConverter;

import com.brentonedwards.dmassist.util.Action;
import com.brentonedwards.dmassist.util.SpecialAbility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CharacterDataTypeConverters {

    Gson gson = new Gson();

    @TypeConverter
    public static List<Action> stringToAction(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Action>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String actionsToString(List<Action> someObjects) {
       Gson gson = new Gson();
        return gson.toJson(someObjects);
    }



}
