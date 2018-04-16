package com.brentonedwards.dmassist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import static com.brentonedwards.dmassist.EncountersActivity.db;


public class CreateCharacter extends AppCompatActivity {


    List<TextView> statBlockViews;
    List<EditText> statBlockValViews;
    View someView;
    View rootView;
    com.github.clans.fab.FloatingActionButton addButton;
    TextView statBlockStr;
    TextView statBlockDex;
    TextView statBlockCon;
    TextView statBlockInt;
    TextView statBlockWis;
    TextView statBlockCha;
    TextView senses;
    TextView languages;
    EditText charSpeedTextView;
    EditText statBlockStrValEditText;
    EditText statBlockDexValEditText;
    EditText statBlockConValEditText;
    EditText statBlockIntValEditText;
    EditText statBlockWisValEditText;
    EditText statBlockChaValEditText;
    EditText characterNameEditText;
    EditText armorClassEditText;
    EditText healthEditText;
    public int width;
    int itemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);
        someView = findViewById(R.id.character_name);
        rootView = someView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        addButton = findViewById(R.id.add_character_button);
        itemSelected = getIntent().getIntExtra("Value", 0);
        statBlockStr = rootView.findViewById(R.id.stat_str);
        statBlockDex = rootView.findViewById(R.id.stat_dex);
        statBlockCon = rootView.findViewById(R.id.stat_con);
        statBlockInt = rootView.findViewById(R.id.stat_int);
        statBlockWis = rootView.findViewById(R.id.stat_wis);
        statBlockCha = rootView.findViewById(R.id.stat_cha);


        charSpeedTextView = rootView.findViewById(R.id.speed_val);

        statBlockStrValEditText = rootView.findViewById(R.id.stat_str_val);


        statBlockDexValEditText = rootView.findViewById(R.id.stat_dex_val);


        statBlockConValEditText = rootView.findViewById(R.id.stat_con_val);


        statBlockIntValEditText = rootView.findViewById(R.id.stat_int_val);


        statBlockWisValEditText = rootView.findViewById(R.id.stat_wis_val);


        statBlockChaValEditText = rootView.findViewById(R.id.stat_cha_val);

        characterNameEditText = rootView.findViewById(R.id.character_name);

        armorClassEditText = rootView.findViewById(R.id.armor_class_val);

        healthEditText = rootView.findViewById(R.id.hit_points_val);




        width = getScreenWidth();
        statBlockViews = Arrays.asList(statBlockStr, statBlockDex, statBlockCon, statBlockInt, statBlockWis, statBlockCha);
        setEvenTextViewWidth(statBlockViews, width);
        statBlockValViews = Arrays.asList(statBlockStrValEditText, statBlockDexValEditText, statBlockConValEditText, statBlockIntValEditText, statBlockWisValEditText, statBlockChaValEditText);
        setEvenEditTextWidth(statBlockValViews, width);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EncounterCharacter character = new EncounterCharacter();
                character.setName(String.valueOf(characterNameEditText.getText()));
                character.setArmorClass(Integer.valueOf(armorClassEditText.getText().toString()));
                character.setCharacterSheetIndex(db.characterDao().countCharacterData()+1);
                db.characterDao().insertAll(character);

                CharacterData characterData = new CharacterData();

                characterData.setCharName(characterNameEditText.getText().toString());
                characterData.setStrength(Integer.valueOf(statBlockStrValEditText.getText().toString()));
                characterData.setDexterity(Integer.valueOf(statBlockDexValEditText.getText().toString()));
                characterData.setConstitution(Integer.valueOf(statBlockConValEditText.getText().toString()));
                characterData.setIntelligence(Integer.valueOf(statBlockIntValEditText.getText().toString()));
                characterData.setWisdom(Integer.valueOf(statBlockWisValEditText.getText().toString()));
                characterData.setCharisma(Integer.valueOf(statBlockChaValEditText.getText().toString()));


                db.characterDao().insertAll(characterData);



                Intent myIntent = new Intent(CreateCharacter.this, EncountersActivity.class);
                startActivity(myIntent);


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_character, menu);
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

    //Sets Views to be an even width
    public void setEvenTextViewWidth(List<TextView> viewList, int screenWidth) {
        int index = 0;
        while (index <= viewList.size() - 1) {
            viewList.get(index).setWidth(screenWidth / viewList.size());

            index++;
        }

    }


    public void setEvenEditTextWidth(List<EditText> viewList, int screenWidth) {
        int index = 0;
        while (index <= viewList.size() - 1) {
            viewList.get(index).setWidth(screenWidth / viewList.size());

            index++;
        }

    }


    public int getScreenWidth(){
        //Get display information to set width of views
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;

    }

}
