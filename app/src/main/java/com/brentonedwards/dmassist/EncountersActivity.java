package com.brentonedwards.dmassist;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;


import com.brentonedwards.dmassist.adapter.EncounterListAdapter;
import com.brentonedwards.dmassist.util.GsonParse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EncountersActivity extends AppCompatActivity {

    public static ArrayList<EncounterCharacter> encounterCharacters;
    ListView listView;
    public static CharacterDatabase db;
    private EncounterListAdapter adapter;
    public int width;
    int index = 0;
    com.github.clans.fab.FloatingActionButton addMonsterButton;
    com.github.clans.fab.FloatingActionButton addCharacterButton;
    com.github.clans.fab.FloatingActionButton rollInitiativeButton;
    com.github.clans.fab.FloatingActionButton clearListButton;
    Thread dbQueryThread;
    public GsonParse[] nonPlayerCreatedMonsters;
    public static ArrayList<CharacterData> nonPlayerCreatedMonstersArrayList = new ArrayList<CharacterData>();
    public static List<CharacterData> nonPlayerCreatedMonstersList;
    private Boolean firstTime = null;
    Handler dbDataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(savedInstanceState==null) {
            db = Room.databaseBuilder(getApplicationContext(),
                    CharacterDatabase.class, "character-database").build();

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setContentView(R.layout.activity_encounters2);
            View someView = findViewById(R.id.encounter_list);
            View root = someView.getRootView();
            root.setBackgroundColor(getResources().getColor(R.color.colorBackground));
            rollInitiativeButton = findViewById(R.id.roll_initiative_button);
            addCharacterButton = findViewById(R.id.add_character_button);
            addMonsterButton = findViewById(R.id.add_monster_button);
            clearListButton = findViewById(R.id.clear_list);
            listView = findViewById(R.id.encounter_list);

            if(isFirstTime()) {


                try {
                    AssetManager assetManager = getAssets();
                    InputStream ims = assetManager.open("5e-SRD-Monsters.json");

                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(ims);

                    nonPlayerCreatedMonsters = gson.fromJson(reader, GsonParse[].class);




                    @SuppressLint("HandlerLeak")
                    final Handler handler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            listView.setAdapter(adapter);
                        }
                    };

                    Runnable dbQuery = new Runnable() {
                        @Override
                        public void run() {
                            while (index < nonPlayerCreatedMonsters.length) {

                                CharacterData newCharData = new CharacterData(nonPlayerCreatedMonsters[index].getName(), nonPlayerCreatedMonsters[index].getSize(), nonPlayerCreatedMonsters[index].getType(), nonPlayerCreatedMonsters[index].getSubtype(), nonPlayerCreatedMonsters[index].getAlignment(), nonPlayerCreatedMonsters[index].getArmorClass(), nonPlayerCreatedMonsters[index].getHitPoints(), nonPlayerCreatedMonsters[index].getHitDice(), nonPlayerCreatedMonsters[index].getSpeed(), nonPlayerCreatedMonsters[index].getStrength(), nonPlayerCreatedMonsters[index].getDexterity(), nonPlayerCreatedMonsters[index].getConstitution(), nonPlayerCreatedMonsters[index].getWisdom(), nonPlayerCreatedMonsters[index].getIntelligence(), nonPlayerCreatedMonsters[index].getWisdom(), nonPlayerCreatedMonsters[index].getDamageVulnerabilities(), nonPlayerCreatedMonsters[index].getDamageResistances(), nonPlayerCreatedMonsters[index].getDamageImmunities(), nonPlayerCreatedMonsters[index].getConditionImmunities(), nonPlayerCreatedMonsters[index].getSenses(), nonPlayerCreatedMonsters[index].getChallengeRating(), nonPlayerCreatedMonsters[index].getLanguages(), nonPlayerCreatedMonsters[index].getSpecialAbilities(), nonPlayerCreatedMonsters[index].getActions());
                                db.characterDao().insertAll(newCharData);
                                nonPlayerCreatedMonstersArrayList.add(newCharData);
                                index++;
                            }
                            nonPlayerCreatedMonstersList = nonPlayerCreatedMonstersArrayList;
                            adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);
                            handler.sendEmptyMessage(0);

                            if(CharacterList.characterListHandler != null){
                                CharacterList.characterListHandler.sendEmptyMessage(0);
                            }


                        }
                    };

                    dbQueryThread = new Thread(dbQuery);

                    dbQueryThread.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else {
                Runnable dbQuery = new Runnable(){
                    @Override
                    public void run() {
                    Log.d("myAlert", "We got here");
                        nonPlayerCreatedMonstersList = db.characterDao().getAllCharacterData();
                        if(CharacterList.characterListHandler != null) {
                            CharacterList.characterListHandler.sendEmptyMessage(0);
                        }
                    }
                };

                dbQueryThread = new Thread(dbQuery);
                dbQueryThread.start();
            }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final int itemSelected = position;
                        final Intent myIntent = new Intent(EncountersActivity.this, CharacterDetailActivity.class);
                        dbQueryThread = new Thread() {

                            public void run() {

                                if (!db.characterDao().findEncounterCharacterByUid(itemSelected + 1).isPlayerCharacter) {
                                    myIntent.putExtra("Value", itemSelected);
                                } else {
                                    myIntent.putExtra("Value", db.characterDao().findEncounterCharacterByUid(itemSelected + 1).getCharacterSheetIndex());
                                }


                            }
                        };

                        dbQueryThread.run();
                        EncountersActivity.this.startActivity(myIntent);

                    }
                });



            addMonsterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



            Intent myIntent = new Intent(EncountersActivity.this, CharacterList.class);
            EncountersActivity.this.startActivity(myIntent);

                }
            });
            clearListButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dbQueryThread = new Thread() {

                        public void run() {
                        db.characterDao().deleteAllEncounterCharacters();
                        adapter = null;
                        adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);
                        }
                    };
                    dbQueryThread.run();
                    listView.setAdapter(adapter);





                }
            });
            rollInitiativeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    dbQueryThread = new Thread() {

                        public void run() {

                            encounterCharacters.clear();
                            encounterCharacters.addAll(db.characterDao().getAllEncounterCharacters());
                            index = 0;
                            Random initiativeRoll = new Random();
                            while(index < encounterCharacters.size()) {
                                int initiativeValue = initiativeRoll.nextInt(20) +1;
                                db.characterDao().updateInitiative(encounterCharacters.get(index).getIndex(), initiativeValue);
                                encounterCharacters.get(index).setInitiative(initiativeValue);
                                index++;
                            }

                        }
                    };
                    listView.setAdapter(adapter);


                }
            });
            addCharacterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



                    Intent myIntent = new Intent(EncountersActivity.this, CreateCharacter.class);
                    EncountersActivity.this.startActivity(myIntent);

                }
            });
        }

//        else {
//            savedInstanceState.getIntegerArrayList("encounterCharacter");
//
//        }
//    }



    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().hasExtra("index")) {
            dbQueryThread = new Thread() {

                public void run() {
                    db.characterDao().insertAll(new EncounterCharacter(db.characterDao().findCharacterDataByUid(getIntent().getIntExtra("index", 0)).charName, getIntent().getIntExtra("index", 0)));


                    adapter = null;
                    adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);
                    listView.setAdapter(adapter);
                    getIntent().removeExtra("index");

                }
            };
            dbQueryThread.run();

        }

            }
    public int getScreenHeight(){
        //Get display information to set width of views
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getScreenWidth(){
        //Get display information to set width of views
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }
        }

