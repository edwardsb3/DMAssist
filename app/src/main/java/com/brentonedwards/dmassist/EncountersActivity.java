package com.brentonedwards.dmassist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


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

    public static ArrayList<CharacterData> characterData = new ArrayList<CharacterData>();
    public static ArrayList<EncounterCharacter> encounterCharacters;
    List currentEncounterCharacters;
    ListView listView;
    public static CharacterDatabase db;
    int listViewHeight;
    private EncounterListAdapter adapter;
    public int width;
    int index = 0;
    TextView nameColHeader;
    com.github.clans.fab.FloatingActionButton addMonsterButton;
    com.github.clans.fab.FloatingActionButton addCharacterButton;
    com.github.clans.fab.FloatingActionButton rollInitiativeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listViewHeight = ((getScreenHeight()/10)*9);
        Log.d("myAlert", "Called");

        db = Room.databaseBuilder(getApplicationContext(),
                CharacterDatabase.class, "character-database").allowMainThreadQueries().build();
        if(savedInstanceState==null) {


            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setContentView(R.layout.activity_encounters2);
            View someView = findViewById(R.id.encounter_list);
            View root = someView.getRootView();
            root.setBackgroundColor(getResources().getColor(R.color.colorBackground));
//            nameColHeader = findViewById(R.id.name_col_header);
//            nameColHeader.setWidth(getScreenWidth()/2);
            rollInitiativeButton = findViewById(R.id.roll_initiative_button);
            addCharacterButton = findViewById(R.id.add_character_button);
            addMonsterButton = findViewById(R.id.add_monster_button);


            try {
                AssetManager assetManager = getAssets();
                InputStream ims = assetManager.open("5e-SRD-Monsters.json");

                Gson gson = new Gson();
                Reader reader = new InputStreamReader(ims);

                GsonParse[] gsonArray = gson.fromJson(reader, GsonParse[].class);


                listView = findViewById(R.id.encounter_list);
//                listView.setDividerHeight(listViewHeight);

                while (index < gsonArray.length) {
                    db.characterDao().insertAll(new CharacterData(gsonArray[index].getName(), gsonArray[index].getSize(), gsonArray[index].getType(), gsonArray[index].getSubtype(), gsonArray[index].getAlignment(), gsonArray[index].getArmorClass(), gsonArray[index].getHitPoints(), gsonArray[index].getHitDice(), gsonArray[index].getSpeed(), gsonArray[index].getStrength(), gsonArray[index].getDexterity(), gsonArray[index].getConstitution(), gsonArray[index].getWisdom(), gsonArray[index].getIntelligence(), gsonArray[index].getWisdom(), gsonArray[index].getDamageVulnerabilities(), gsonArray[index].getDamageResistances(), gsonArray[index].getDamageImmunities(), gsonArray[index].getConditionImmunities(), gsonArray[index].getSenses(), gsonArray[index].getChallengeRating(), gsonArray[index].getLanguages(), gsonArray[index].getSpecialAbilities(), gsonArray[index].getActions()));
                    index++;
                }
                Log.d("myAlert", String.valueOf(db.characterDao().countCharacterData()));
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
            encounterCharacters = new ArrayList<EncounterCharacter>();

//        encounterCharacter.add(new EncounterCharacter(1,"Bilmy", characterData.get(1)));
//            db.characterDao().insertAll(new EncounterCharacter(), new EncounterCharacter(), new EncounterCharacter());

            adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), getApplicationContext(), width);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent myIntent = new Intent(EncountersActivity.this, CharacterDetailActivity.class);
                    if(!db.characterDao().findEncounterCharacterByUid(position+1).isPlayerCharacter){ myIntent.putExtra("Value", position);}
                    else{myIntent.putExtra("Value", db.characterDao().findEncounterCharacterByUid(position+1).getCharacterSheetIndex());}
                    EncountersActivity.this.startActivity(myIntent);
//                EncounterCharacter encounterCharacter = EncountersActivity.encounterCharacter.get(position);
//                Snackbar.make(view, encounterCharacter.getCharName()+" has been created.", Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
                }
            });



            addMonsterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



            Intent myIntent = new Intent(EncountersActivity.this, CharacterList.class);
            EncountersActivity.this.startActivity(myIntent);

                }
            });
            rollInitiativeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    encounterCharacters.clear();
                    encounterCharacters.addAll(db.characterDao().getAllEncounterCharacters());
                    index = 0;
                    Random initiativeRoll = new Random();
                    while(index < encounterCharacters.size()) {
                        int initiativeValue = initiativeRoll.nextInt(20) +1;
                        Log.d("myAlert", String.valueOf(initiativeValue));
                        db.characterDao().updateInitiative(encounterCharacters.get(index).getIndex(), initiativeValue);
                        encounterCharacters.get(index).setInitiative(initiativeValue);
                        index++;
                    }


                    adapter = null;
                    adapter = new EncounterListAdapter(db.characterDao().initiativeList(), getApplicationContext(), width);
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
            db.characterDao().insertAll(new EncounterCharacter(db.characterDao().findCharacterDataByUid(newCharReferenceIndex).charName, newCharReferenceIndex));
            adapter = null;
            adapter = new EncounterListAdapter(db.characterDao().getAllEncounterCharacters(), this, width);
            listView.setAdapter(adapter);
            getIntent().removeExtra("index");
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

        }

