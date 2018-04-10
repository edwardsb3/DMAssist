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

import java.util.Arrays;
import java.util.List;


public class CharacterDetailActivity extends AppCompatActivity {


    List<TextView> statBlockViews;
    View someView;
    View rootView;
    FloatingActionButton addButton;
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
    TextView charNameTextView;
    TextView charSubNameTextView;
    TextView charArmorClassTextView;
    TextView charHitPointsTextView;
    TextView charSpeedTextView;
    TextView statBlockStrValTextView;
    TextView statBlockDexValTextView;
    TextView statBlockConValTextView;
    TextView statBlockIntValTextView;
    TextView statBlockWisValTextView;
    TextView statBlockChaValTextView;
    TextView conditionalImmunitiesValTextView;
    TextView damageImmunitiesValTextView;
    TextView sensesValTextView;
    TextView languagesValTextView;
    TextView challengeRatingValTextView;
    TextView actionValTextView;
    TextView specialAbilitiesValTextView;
    StringBuilder abilityTextBuilder;
    StringBuilder actionTextBuilder;
    String actionText = "";
    String abilityText = "";
    int width;
    int itemSelected;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        someView = findViewById(R.id.challenge_rating_val);
        rootView = someView.getRootView();
        rootView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        addButton = findViewById(R.id.fab);
        itemSelected = getIntent().getIntExtra("Value", 0);
        statBlockStr = rootView.findViewById(R.id.stat_str);
        statBlockDex = rootView.findViewById(R.id.stat_dex);
        statBlockCon = rootView.findViewById(R.id.stat_con);
        statBlockInt = rootView.findViewById(R.id.stat_int);
        statBlockWis = rootView.findViewById(R.id.stat_wis);
        statBlockCha = rootView.findViewById(R.id.stat_cha);
        conditionalImmunities = rootView.findViewById(R.id.condition_immunities);
        damageImmunities = rootView.findViewById(R.id.damage_immunities);
        senses = rootView.findViewById(R.id.senses);
        challengeRating = rootView.findViewById(R.id.challenge_rating);
        languages = rootView.findViewById(R.id.languages);


        /*
         * TextViews with character data
         */

        charNameTextView = rootView.findViewById(R.id.character_name);
        charNameTextView.setText(EncountersActivity.characterData.get(itemSelected).charName);

        charSubNameTextView = rootView.findViewById(R.id.sub_name);
        charSubNameTextView.setText(EncountersActivity.characterData.get(itemSelected).getSize() + " " + EncountersActivity.characterData.get(itemSelected).getType() + " " + EncountersActivity.characterData.get(itemSelected).getAlignment());

        charArmorClassTextView = rootView.findViewById(R.id.armor_class_val);
        charArmorClassTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).armorClass));

        charHitPointsTextView = rootView.findViewById(R.id.hit_points_val);
        charHitPointsTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).hitPoints));

        charSpeedTextView = rootView.findViewById(R.id.speed_val);
        charSpeedTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).speed));


        statBlockStrValTextView = rootView.findViewById(R.id.stat_str_val);
        statBlockStrValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getStrength()));


        statBlockDexValTextView = rootView.findViewById(R.id.stat_dex_val);
        statBlockDexValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getDexterity()));


        statBlockConValTextView = rootView.findViewById(R.id.stat_con_val);
        statBlockConValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getConstitution()));


        statBlockIntValTextView = rootView.findViewById(R.id.stat_int_val);
        statBlockIntValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getIntelligence()));


        statBlockWisValTextView = rootView.findViewById(R.id.stat_wis_val);
        statBlockWisValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getWisdom()));


        statBlockChaValTextView = rootView.findViewById(R.id.stat_cha_val);
        statBlockChaValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).getCharisma()));

        conditionalImmunitiesValTextView = rootView.findViewById(R.id.condition_immunities_val);
        conditionalImmunitiesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).conditionImmunities));


        damageImmunitiesValTextView = rootView.findViewById(R.id.damage_immunities_val);
        damageImmunitiesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).damageImmunities));


        sensesValTextView = rootView.findViewById(R.id.senses_val);
        sensesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).senses));


        languagesValTextView = rootView.findViewById(R.id.languages_val);
        languagesValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).languages));


        challengeRatingValTextView = rootView.findViewById(R.id.challenge_rating_val);
        challengeRatingValTextView.setText(String.valueOf(EncountersActivity.characterData.get(itemSelected).challengeRating));

        actionValTextView = rootView.findViewById(R.id.actions_val);


        actionValTextView.setText(buildActionString());

        specialAbilitiesValTextView = rootView.findViewById(R.id.special_abilities_val);
        specialAbilitiesValTextView.setText(buildSpecialAbilitiesString());


        width = getScreenWidth();


        challengeRating.setWidth(width / 2);
        challengeRatingValTextView.setWidth(width / 2);
        languages.setWidth(width / 2);
        languagesValTextView.setWidth(width / 2);
        senses.setWidth(width / 2);
        sensesValTextView.setWidth(width / 2);
        damageImmunitiesValTextView.setWidth(width / 2);
        damageImmunities.setWidth(width / 2);
        conditionalImmunities.setWidth(width / 2);
        conditionalImmunitiesValTextView.setWidth(width / 2);

        statBlockViews = Arrays.asList(statBlockStr, statBlockDex, statBlockCon, statBlockInt, statBlockWis, statBlockCha);
        setEvenWidth(statBlockViews, width);
        statBlockViews = Arrays.asList(statBlockStrValTextView, statBlockDexValTextView, statBlockConValTextView, statBlockIntValTextView, statBlockWisValTextView, statBlockChaValTextView);
        setEvenWidth(statBlockViews, width);

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

    //Sets Views to be an even width
    public void setEvenWidth(List<TextView> viewList, int screenWidth) {
        int index = 0;
        while (index <= statBlockViews.size() - 1) {
            statBlockViews.get(index).setWidth(width / viewList.size());

            index++;
        }

    }

    //Methods for building the string for the Actions section
    public String buildActionString() {

        while (index < EncountersActivity.characterData.get(itemSelected).getActions().size()) {
            if (index == 0) {
                actionTextBuilder = new StringBuilder("Name: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");
            } else {

                actionTextBuilder.append("\n" + "\n" + "Name: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + EncountersActivity.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");

            }
            index++;
            if (index == EncountersActivity.characterData.get(itemSelected).actions.size()) {
                return actionText = actionTextBuilder.toString();
            }

        }
        return "None";
    }


    //Methods for building the string for the Special Abilities section
    public String buildSpecialAbilitiesString(){
        index =0;
        if (EncountersActivity.characterData.get(itemSelected).specialAbilities != null) {
            while (index < EncountersActivity.characterData.get(itemSelected).getSpecialAbilities().size()) {

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

            if (index == EncountersActivity.characterData.get(itemSelected).getSpecialAbilities().size()) {
               return abilityTextBuilder.toString();
            }
        } else {
            return "None";
        }
        return "None";
    }

    public int getScreenWidth(){
    //Get display information to set width of views
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.widthPixels;
    }
}
