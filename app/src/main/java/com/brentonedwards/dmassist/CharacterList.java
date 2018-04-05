package com.brentonedwards.dmassist;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;


import com.brentonedwards.dmassist.adapter.CharacterListAdapter;
import com.brentonedwards.dmassist.util.GsonParse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class CharacterList extends AppCompatActivity {


    public static ArrayList<CharacterData> characterData;
    public static CharacterData itemSelected;
    ListView listView;
    private static CharacterListAdapter adapter;

    View mainScreen;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.list);
        View root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.colorBackground));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Class List");
//        toolbar.setBackgroundColor(Color.parseColor("#BC8338"));
//        setSupportActionBar(toolbar);
        AutoCompleteTextView searchBar = (AutoCompleteTextView) findViewById(R.id.search_bar);

        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open("5e-SRD-Monsters.json");

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);

            GsonParse [] gsonArray = gson.fromJson(reader, GsonParse[].class);


            listView=(ListView)findViewById(R.id.list);


            characterData = new ArrayList<>();

        while(index<gsonArray.length ) {
  //          System.out.println(gsonArray[index].getName()+ gsonArray[index].getSize()+ gsonArray[index].getType()+ gsonArray[index].getSubtype()+ gsonArray[index].getAlignment()+ gsonArray[index].getArmorClass()+ gsonArray[index].getHitPoints()+ gsonArray[index].getHitDice()+gsonArray[index].getSpeed()+ gsonArray[index].getStrength()+ gsonArray[index].getDexterity()+ gsonArray[index].getConstitution()+ gsonArray[index].getWisdom()+ gsonArray[index].getIntelligence()+ gsonArray[index].getWisdom()+ gsonArray[index].getDamageVulnerabilities()+ gsonArray[index].getDamageResistances()+ gsonArray[index].getDamageImmunities()+ gsonArray[index].getConditionImmunities()+ gsonArray[index].getSenses()+ gsonArray[index].getChallengeRating()+ gsonArray[index].getSpecialAbilities()+ gsonArray[index].getActions());
            characterData.add(new CharacterData(gsonArray[index].getName(), gsonArray[index].getSize(), gsonArray[index].getType(), gsonArray[index].getSubtype(), gsonArray[index].getAlignment(), gsonArray[index].getArmorClass(), gsonArray[index].getHitPoints(), gsonArray[index].getHitDice(),gsonArray[index].getSpeed(), gsonArray[index].getStrength(), gsonArray[index].getDexterity(), gsonArray[index].getConstitution(), gsonArray[index].getWisdom(), gsonArray[index].getIntelligence(), gsonArray[index].getWisdom(), gsonArray[index].getDamageVulnerabilities(), gsonArray[index].getDamageResistances(), gsonArray[index].getDamageImmunities(), gsonArray[index].getConditionImmunities(), gsonArray[index].getSenses(), gsonArray[index].getChallengeRating(), gsonArray[index].getLanguages(), gsonArray[index].getSpecialAbilities(), gsonArray[index].getActions()));
            index++;
        }

            Collections.sort(characterData, new Comparator<CharacterData>() {
                @Override
                public int compare(CharacterData characterData, CharacterData t1) {

                    return characterData.charName.compareTo(t1.charName);
                }
            });


        }catch(IOException e) {
            e.printStackTrace();
        }

        adapter= new CharacterListAdapter(characterData,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(CharacterList.this, CharacterDetailActivity.class);
                myIntent.putExtra("Value", position);
                CharacterList.this.startActivity(myIntent);
                CharacterData characterData = CharacterList.characterData.get(position);
//                Snackbar.make(view, characterData.getCharName()+" has been created.", Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





//    private static Character addUser(final DMAssistantDatabase db, Character character) {
//        db.characterDao().insertAll(character);
//        return character;
//    }
//
//    private static void populateWithTestData(DMAssistantDatabase db) {
//        Character character = new Character();
//        character.setName("Ajay");
//        character.setAlignment("Saini");
//        character.setHit_dice("25");
//        addUser(db, character);
    }

