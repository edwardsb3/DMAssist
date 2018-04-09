package com.brentonedwards.dmassist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;


import com.brentonedwards.dmassist.adapter.EncounterListAdapter;
import com.brentonedwards.dmassist.util.GsonParse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class EncountersActivity extends AppCompatActivity {

    EncounterCharacter newChar;
    public static ArrayList<CharacterData> characterData = new ArrayList<CharacterData>();
    public static ArrayList<EncounterCharacter> encounterCharacter;
    ListView listView;
    public static CharacterDatabase db;

    private EncounterListAdapter adapter;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("myAlert", "Called");

        db = Room.databaseBuilder(getApplicationContext(),
                CharacterDatabase.class, "character-database").allowMainThreadQueries().build();
        if(savedInstanceState==null) {


            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setContentView(R.layout.activity_encounters2);
            View someView = findViewById(R.id.encounter_list);
            View root = someView.getRootView();
            root.setBackgroundColor(getResources().getColor(R.color.colorBackground));

            FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.fab);
            AutoCompleteTextView searchBar = (AutoCompleteTextView) findViewById(R.id.search_bar);


        int charNum = db.characterDao().countCharacters();
        Log.d("myAlert", String.valueOf(charNum));

            try {
                AssetManager assetManager = getAssets();
                InputStream ims = assetManager.open("5e-SRD-Monsters.json");

                Gson gson = new Gson();
                Reader reader = new InputStreamReader(ims);

                GsonParse[] gsonArray = gson.fromJson(reader, GsonParse[].class);


                listView = (ListView) findViewById(R.id.encounter_list);


                while (index < gsonArray.length) {
                    characterData.add(new CharacterData(gsonArray[index].getName(), gsonArray[index].getSize(), gsonArray[index].getType(), gsonArray[index].getSubtype(), gsonArray[index].getAlignment(), gsonArray[index].getArmorClass(), gsonArray[index].getHitPoints(), gsonArray[index].getHitDice(), gsonArray[index].getSpeed(), gsonArray[index].getStrength(), gsonArray[index].getDexterity(), gsonArray[index].getConstitution(), gsonArray[index].getWisdom(), gsonArray[index].getIntelligence(), gsonArray[index].getWisdom(), gsonArray[index].getDamageVulnerabilities(), gsonArray[index].getDamageResistances(), gsonArray[index].getDamageImmunities(), gsonArray[index].getConditionImmunities(), gsonArray[index].getSenses(), gsonArray[index].getChallengeRating(), gsonArray[index].getLanguages(), gsonArray[index].getSpecialAbilities(), gsonArray[index].getActions()));
                    index++;
                }

//            Collections.sort(encounterCharacter, new Comparator<EncounterCharacter>() {
//                @Override
//                public int compare(EncounterCharacter encounterCharacter, EncounterCharacter t1) {
//
//                    return encounterCharacter.initiative.compareTo(t1.initiative);
//                }
//            });


            } catch (IOException e) {
                e.printStackTrace();
            }
            encounterCharacter = new ArrayList<EncounterCharacter>();

//        encounterCharacter.add(new EncounterCharacter(1,"Bilmy", characterData.get(1)));
//            db.characterDao().insertAll(new EncounterCharacter(), new EncounterCharacter(), new EncounterCharacter());

            adapter = new EncounterListAdapter(db.characterDao().getAll(), getApplicationContext());

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent myIntent = new Intent(EncountersActivity.this, CharacterDetailActivity.class);
                    myIntent.putExtra("Value", position);
                    EncountersActivity.this.startActivity(myIntent);
//                EncounterCharacter encounterCharacter = EncountersActivity.encounterCharacter.get(position);
//                Snackbar.make(view, encounterCharacter.getCharName()+" has been created.", Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
                }
            });


            addButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



            Intent myIntent = new Intent(EncountersActivity.this, CharacterList.class);
            EncountersActivity.this.startActivity(myIntent);

                }
            });
        }

        else {
            savedInstanceState.getIntegerArrayList("encounterCharacter");

        }
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

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().hasExtra("index")) {
            int newCharReferenceIndex = getIntent().getIntExtra("index", 0);
            db.characterDao().insertAll(new EncounterCharacter(characterData.get(newCharReferenceIndex).charName, newCharReferenceIndex));
            adapter = null;
            adapter = new EncounterListAdapter(db.characterDao().getAll(), this);
            listView.setAdapter(adapter);
        }

            }

        }

