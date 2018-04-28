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
    public static List<EncounterCharacter> encounterCharacterList;
    public static ArrayList<CharacterData> nonPlayerCreatedMonstersArrayList = new ArrayList<CharacterData>();
    public static List<CharacterData> nonPlayerCreatedMonstersList;
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
    private Boolean firstTime = null;
    Handler encounterActivityHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            if (isFirstTime()) {

                try {
                    AssetManager assetManager = getAssets();
                    InputStream ims = assetManager.open("5e-SRD-Monsters.json");

                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(ims);

                    nonPlayerCreatedMonsters = gson.fromJson(reader, GsonParse[].class);


                    @SuppressLint("HandlerLeak")
                    final Handler encounterActivityHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            adapter = null;
                            if(encounterCharacterList!=null) {
                                adapter = new EncounterListAdapter(encounterCharacterList, getApplicationContext(), width);
                                listView = findViewById(R.id.encounter_list);
                                listView.setAdapter(adapter);
                            }
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
                            adapter = null;
                            adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);
                            encounterActivityHandler.sendEmptyMessage(0);

                            if (CharacterList.characterListHandler != null) {
                                CharacterList.characterListHandler.sendEmptyMessage(0);
                            }


                        }
                    };

                    dbQueryThread = new Thread(dbQuery);

                    dbQueryThread.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {


                @SuppressLint("HandlerLeak")
                final Handler initList = new Handler(){

                    @Override
                    public void handleMessage(Message msg) {
                        if(encounterCharacterList != null) {
                            adapter = null;
                            adapter = new EncounterListAdapter(encounterCharacterList, getApplicationContext(), getScreenWidth());
                            listView.setAdapter(adapter);
                        }
                    }
                };
                Runnable dataPull = new Runnable() {
                    @Override
                    public void run() {

                        nonPlayerCreatedMonstersList = db.characterDao().getAllCharacterData();
                        encounterCharacterList = db.characterDao().getAllEncounterCharacters();
//                        setIncrementingIndex(encounterCharacterList);
                        if (CharacterList.characterListHandler != null) {
                            CharacterList.characterListHandler.sendEmptyMessage(0);
                        }
                        initList.sendEmptyMessage(0);
                    }
                };

                Thread dbPullThread = new Thread(dataPull);
                dbPullThread.start();
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final int itemSelected = position;


                    @SuppressLint("HandlerLeak") final Handler detailHandler = new Handler(){

                        @Override
                        public void handleMessage(Message msg) {
                            Intent myIntent= new Intent();

                            //is not player
                            if(msg.arg1 == 0) {
                                myIntent = new Intent(EncountersActivity.this, CharacterDetailActivity.class);
                                }
                                //is player
                            if(msg.arg1 == 1) {
                                myIntent = new Intent(EncountersActivity.this, CreateCharacter.class);
                            }

                            EncountersActivity.this.startActivity(myIntent);


                        }
                    };


                    Runnable dbNavigationQuery = new Runnable() {

                        public void run() {
                            Message isPlayer = new Message();

                            EncounterCharacter selectedCharacter = db.characterDao().findEncounterCharacterByIndex(itemSelected);
                            if(selectedCharacter.isPlayerCharacter){isPlayer.arg1 = 1;}
                            else{isPlayer.arg1 = 0;}
                        detailHandler.sendEmptyMessage(0);
                        }
                    };
                    Thread dbNavQueryThread = new Thread(dbNavigationQuery);
                    dbNavQueryThread.run();


                }
            });


            addMonsterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    Intent myIntent = new Intent(EncountersActivity.this, CharacterList.class);
                    EncountersActivity.this.startActivity(myIntent);

                }
            });
            clearListButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("HandlerLeak")
                Handler handler = new Handler(){

                    @Override
                    public void handleMessage(Message msg) {


                        listView.setAdapter(adapter);
                    }
                };
                public void onClick(View v) {
                    Runnable dbDeleteQuery = new Runnable() {

                        public void run() {
                            db.characterDao().deleteAllEncounterCharacters();
                            encounterCharacterList = null;
                            adapter = null;
                            adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);
                            handler.sendEmptyMessage(0);

                        }
                    };
                    Thread dbDeleteThread = new Thread(dbDeleteQuery);
                    dbDeleteThread.start();


                }
            });
            rollInitiativeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    @SuppressLint("HandlerLeak") final Handler handleInitiative = new Handler(){

                        @Override
                        public void handleMessage(Message msg) {

                            adapter = null;
                            adapter = new EncounterListAdapter(encounterCharacterList, getApplicationContext(), getScreenWidth());
                            listView.setAdapter(adapter);
                        }
                    };

                    Runnable dbUpdateInitiative = new Runnable() {

                        public void run() {


                            ArrayList<EncounterCharacter> encounterCharacterArrayList = new ArrayList<>();
                            encounterCharacterArrayList.addAll(db.characterDao().getAllEncounterCharacters());
                            index = 0;
                            Random initiativeRoll = new Random();
                            while (index < encounterCharacterArrayList.size()) {
                                int initiativeValue = initiativeRoll.nextInt(20) + 1;
                                db.characterDao().updateInitiative(encounterCharacterArrayList.get(index).getIndex(), initiativeValue);
                                encounterCharacterArrayList.get(index).setInitiative(initiativeValue);
                                index++;
                            }
                        encounterCharacterList = db.characterDao().initiativeList();
//                            setIncrementingIndex(encounterCharacterList);
                            handleInitiative.sendEmptyMessage(0);
                        }
                    };
                    Thread initiativeThread = new Thread(dbUpdateInitiative);
                    initiativeThread.start();


                }
            });
            addCharacterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    Intent myIntent = new Intent(EncountersActivity.this, CreateCharacter.class);
                    EncountersActivity.this.startActivity(myIntent);

                }
            });
        }




    @Override
    protected void onResume() {
        super.onResume();
        @SuppressLint("HandlerLeak")
        final Handler encounterActivityHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter = null;
//                setIncrementingIndex(encounterCharacterList);
                adapter = new EncounterListAdapter(encounterCharacterList, getApplicationContext(), width);
                listView.setAdapter(adapter);
            }
        };

        if(getIntent().hasExtra("index")) {
            final int intentIndex = getIntent().getIntExtra("index", 0);
            getIntent().removeExtra("index");
            Runnable dbQuery = new Runnable() {
                @Override
                public void run() {
                    int numberOfCharacters = db.characterDao().countCharacters();
                    db.characterDao().insertAll(new EncounterCharacter(db.characterDao().findCharacterDataByUid(intentIndex).charName, intentIndex, numberOfCharacters+1));
                    encounterCharacterList = db.characterDao().getAllEncounterCharacters();
                    encounterActivityHandler.sendEmptyMessage(0);
                }
            };

            dbQueryThread = new Thread(dbQuery);
            dbQueryThread.start();

        }

            }
    public int getScreenHeight(){

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
    public void setIncrementingIndex(List<EncounterCharacter> list){
        int index = 0;

        while(list.size() > index){

            list.get(index).setIndex(index);

            index++;
        }
    }
        }

