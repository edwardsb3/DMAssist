package com.brentonedwards.dmassist;

import android.arch.persistence.room.TypeConverter;

import com.brentonedwards.dmassist.util.SpecialAbility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SpecialAttributesDataTypeConverter {

    @TypeConverter
    public static List<SpecialAbility> stringToSpecialAbility(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<SpecialAbility>>() {}.getType();

        return gson.fromJson(data, listType);
    }



    @TypeConverter
    public static String specialAbilitiesToString(List<SpecialAbility> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
