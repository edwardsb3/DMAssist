package com.brentonedwards.dmassist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;



public class CharacterDetailActivity extends AppCompatActivity {

    int index = 0;
    StringBuilder abilityTextBuilder;
    StringBuilder actionTextBuilder;
    int width;
    String actionText="";
    String abilityText="";
    View someView;
    View rootView;
    FloatingActionButton addButton;
    int itemSelected;
    TextView statBlockStr;
    TextView statBlockDex;
    TextView statBlockCon;
    TextView statBlockInt;
    TextView statBlockWis;
    TextView statBlockCha;
    TextView conditionalImmunities;
    TextView damageImmunities;
    TextView senses;
    TextView challengeRating;
    TextView languages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        someView = findViewById(R.id.challenge_rating_val);
        rootView = someView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        addButton = (FloatingActionButton) findViewById(R.id.fab);
        itemSelected = getIntent().getIntExtra("Value", 0);
        statBlockStr = (TextView) rootView.findViewById(R.id.stat_str);
        statBlockDex = (TextView) rootView.findViewById(R.id.stat_dex);
        statBlockCon = (TextView) rootView.findViewById(R.id.stat_con);
        statBlockInt = (TextView) rootView.findViewById(R.id.stat_int);
        statBlockWis = (TextView) rootView.findViewById(R.id.stat_wis);
        statBlockCha = (TextView) rootView.findViewById(R.id.stat_cha);
        conditionalImmunities = (TextView) rootView.findViewById(R.id.condition_immunities);
        damageImmunities = (TextView) rootView.findViewById(R.id.damage_immunities);
        senses = (TextView) rootView.findViewById(R.id.senses);
        challengeRating = (TextView) rootView.findViewById(R.id.challenge_rating);
        languages = (TextView) rootView.findViewById(R.id.languages);


        /*
        * TextViews with character data
         */

        TextView charNameTextView = (TextView) rootView.findViewById(R.id.character_name);
        charNameTextView.setText(EncountersActivity.characterData.get(itemSelected).charName);

        TextView charSubNameTextView = (TextView) rootView.findViewById(R.id.sub_name);
        charSubNameTextView.setText(EncountersActivity.characterData.get(itemSelected).getSize() + " " + EncountersActivity.characterData.get(itemSelected).getType() + " " + EncountersActivity.characterData.get(itemSelected).getAlignment());

        TextView charArmorClassTextView = (TextView) rootView.findViewById(R.id.armor_class_val);
        charArmorClassTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).armorClass));

        TextView charHitPointsTextView = (TextView) rootView.findViewById(R.id.hit_points_val);
        charHitPointsTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).hitPoints));

        TextView charSpeedTextView = (TextView) rootView.findViewById(R.id.speed_val);
        charSpeedTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).speed));



        TextView statBlockStrValTextView= (TextView) rootView.findViewById(R.id.stat_str_val);
        statBlockStrValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getStrength()));


        TextView statBlockDexValTextView= (TextView) rootView.findViewById(R.id.stat_dex_val);
        statBlockDexValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getDexterity()));


        TextView statBlockConValTextView = (TextView) rootView.findViewById(R.id.stat_con_val);
        statBlockConValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getConstitution()));


        TextView statBlockIntValTextView = (TextView) rootView.findViewById(R.id.stat_int_val);
        statBlockIntValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getIntelligence()));


        TextView statBlockWisValTextView = (TextView) rootView.findViewById(R.id.stat_wis_val);
        statBlockWisValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getWisdom()));


        TextView statBlockChaValTextView = (TextView) rootView.findViewById(R.id.stat_cha_val);
        statBlockChaValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getCharisma()));

        TextView conditionalImmunitiesValTextView = (TextView) rootView.findViewById(R.id.condition_immunities_val);
        conditionalImmunitiesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).conditionImmunities));



        TextView damageImmunitiesValTextView = (TextView) rootView.findViewById(R.id.damage_immunities_val);
        damageImmunitiesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).damageImmunities));



        TextView sensesValTextView = (TextView) rootView.findViewById(R.id.senses_val);
        sensesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).senses));


        TextView languagesValTextView = (TextView) rootView.findViewById(R.id.languages_val);
        languagesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).languages));



        TextView challengeRatingValTextView= (TextView) rootView.findViewById(R.id.challenge_rating_val);
        challengeRatingValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).challengeRating));

        TextView actionValTextView= (TextView) rootView.findViewById(R.id.actions_val);

index = 0;
        while(index < EncountersActivity.characterData.get(itemSelected).getActions().size()) {
            if(index == 0) {
                actionTextBuilder = new StringBuilder("Name: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");
            }
            else{

                actionTextBuilder.append("\n" + "\n" + "Name: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");

            }
            index++;
            if(index== EncountersActivity.characterData.get(itemSelected).actions.size()){actionText = actionTextBuilder.toString();}
        }
        index=0;
        if(EncountersActivity.characterData.get(itemSelected).specialAbilities!=null){
        while(index < EncountersActivity.characterData.get(itemSelected).getSpecialAbilities().size()) {

            if (index == 0) {
                abilityTextBuilder = new StringBuilder("Name: " + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getName() + "\n");
                abilityTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getAttackBonus() + "\n" + "\n");
                abilityTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getDesc() + "\n");
            } else {

                abilityTextBuilder.append("\n" + "\n" + "Name: " + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getName() + "\n");
                abilityTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getAttackBonus() + "\n" + "\n");
                abilityTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).specialAbilities.get(index).getDesc() + "\n");

            }
         index++;
        }

            if(index== EncountersActivity.characterData.get(itemSelected).getSpecialAbilities().size()){abilityText = abilityTextBuilder.toString();}
        }
        else{abilityText= "None";}

        actionValTextView.setText(actionText);

        TextView specialAbilitiesValTextView= (TextView) rootView.findViewById(R.id.special_abilities_val);
        specialAbilitiesValTextView.setText(abilityText);


        challengeRating.setWidth(width/2);
        challengeRatingValTextView.setWidth(width/2);
        languages.setWidth(width/2);
        languagesValTextView.setWidth(width/2);
        senses.setWidth(width/2);
        sensesValTextView.setWidth(width/2);
        damageImmunitiesValTextView.setWidth(width/2);
        damageImmunities.setWidth(width/2);
        conditionalImmunities.setWidth(width/2);
        conditionalImmunitiesValTextView.setWidth(width/2);


        statBlockStr.setWidth(width/6);
        statBlockDex.setWidth(width/6);
        statBlockCon.setWidth(width/6);
        statBlockInt.setWidth(width/6);
        statBlockWis.setWidth(width/6);
        statBlockCha.setWidth(width/6);
        statBlockStrValTextView.setWidth(width/6);
        statBlockDexValTextView.setWidth(width/6);
        statBlockConValTextView.setWidth(width/6);
        statBlockIntValTextView.setWidth(width/6);
        statBlockWisValTextView.setWidth(width/6);
        statBlockChaValTextView.setWidth(width/6);


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CharacterDetailActivity.this, EncountersActivity.class);
                myIntent.putExtra("index", itemSelected);
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

}
