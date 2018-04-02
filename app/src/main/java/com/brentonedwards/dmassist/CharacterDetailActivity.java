package com.brentonedwards.dmassist;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;


import com.brentonedwards.dmassist.R;
import com.brentonedwards.dmassist.adapter.CharacterListAdapter;

public class CharacterDetailActivity extends AppCompatActivity {

    int index = 0;
    StringBuilder abilityTextBuilder;
    StringBuilder actionTextBuilder;
    String actionText="";
    String abilityText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        View someView = findViewById(R.id.challenge_rating_val);
        View rootView = someView.getRootView();
        rootView.setBackgroundColor(Color.parseColor("#FDF1D9"));


         int itemSelected = getIntent().getIntExtra("Value", 0);


        /*
        *Static TextViews
         */
        TextView statBlockStr = (TextView) rootView.findViewById(R.id.stat_str);

        TextView statBlockDex = (TextView) rootView.findViewById(R.id.stat_dex);

        TextView statBlockCon = (TextView) rootView.findViewById(R.id.stat_con);

        TextView statBlockInt = (TextView) rootView.findViewById(R.id.stat_int);

        TextView statBlockWis = (TextView) rootView.findViewById(R.id.stat_wis);

        TextView statBlockCha = (TextView) rootView.findViewById(R.id.stat_cha);

        TextView conditionalImmunities = (TextView) rootView.findViewById(R.id.condition_immunities);

        TextView damageImmunities = (TextView) rootView.findViewById(R.id.damage_immunities);

        TextView senses = (TextView) rootView.findViewById(R.id.senses);

        TextView challengeRating = (TextView) rootView.findViewById(R.id.challenge_rating);

        TextView languages = (TextView) rootView.findViewById(R.id.languages);


        /*
        * TextViews with character data
         */

        TextView charNameTextView = (TextView) rootView.findViewById(R.id.character_name);
        charNameTextView.setText(CharacterList.characterData.get(itemSelected).charName);

        TextView charSubNameTextView = (TextView) rootView.findViewById(R.id.sub_name);
        charSubNameTextView.setText(CharacterList.characterData.get(itemSelected).getSize() + " " + CharacterList.characterData.get(itemSelected).getType() + " " + CharacterList.characterData.get(itemSelected).getAlignment());

        TextView charArmorClassTextView = (TextView) rootView.findViewById(R.id.armor_class_val);
        charArmorClassTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).armorClass));

        TextView charHitPointsTextView = (TextView) rootView.findViewById(R.id.hit_points_val);
        charHitPointsTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).hitPoints));

        TextView charSpeedTextView = (TextView) rootView.findViewById(R.id.speed_val);
        charSpeedTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).speed));



        TextView statBlockStrValTextView= (TextView) rootView.findViewById(R.id.stat_str_val);
        statBlockStrValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getStrength()));


        TextView statBlockDexValTextView= (TextView) rootView.findViewById(R.id.stat_dex_val);
        statBlockDexValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getDexterity()));


        TextView statBlockConValTextView = (TextView) rootView.findViewById(R.id.stat_con_val);
        statBlockConValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getConstitution()));


        TextView statBlockIntValTextView = (TextView) rootView.findViewById(R.id.stat_int_val);
        statBlockIntValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getIntelligence()));


        TextView statBlockWisValTextView = (TextView) rootView.findViewById(R.id.stat_wis_val);
        statBlockWisValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getWisdom()));


        TextView statBlockChaValTextView = (TextView) rootView.findViewById(R.id.stat_cha_val);
        statBlockChaValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).getCharisma()));

        TextView conditionalImmunitiesValTextView = (TextView) rootView.findViewById(R.id.condition_immunities_val);
        conditionalImmunitiesValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).conditionImmunities));



        TextView damageImmunitiesValTextView = (TextView) rootView.findViewById(R.id.damage_immunities_val);
        damageImmunitiesValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).damageImmunities));



        TextView sensesValTextView = (TextView) rootView.findViewById(R.id.senses_val);
        sensesValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).senses));


        TextView languagesValTextView = (TextView) rootView.findViewById(R.id.languages_val);
        languagesValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).languages));



        TextView challengeRatingValTextView= (TextView) rootView.findViewById(R.id.challenge_rating_val);
        challengeRatingValTextView.setText(String.valueOf(CharacterList.characterData.get(itemSelected).challengeRating));

        TextView actionValTextView= (TextView) rootView.findViewById(R.id.actions_val);


        while(index < CharacterList.characterData.get(itemSelected).getActions().size()) {
            if(index == 0) {
                actionTextBuilder = new StringBuilder("Name: " + CharacterList.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + CharacterList.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + CharacterList.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + CharacterList.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + CharacterList.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");
            }
            else{

                actionTextBuilder.append("\n" + "\n" + "Name: " + CharacterList.characterData.get(itemSelected).actions.get(index).getName() + "\n");
                actionTextBuilder.append("Attack Bonus: " + CharacterList.characterData.get(itemSelected).actions.get(index).getAttackBonus() + "\n");
                actionTextBuilder.append("Damage Bonus:" + CharacterList.characterData.get(itemSelected).actions.get(index).getDamageBonus() + "\n");
                actionTextBuilder.append("Dice: " + CharacterList.characterData.get(itemSelected).actions.get(index).getDamageDice() + "\n" + "\n");
                actionTextBuilder.append("Description:\n" + CharacterList.characterData.get(itemSelected).actions.get(index).getDesc() + "\n");

            }
            index++;
            if(index== CharacterList.characterData.get(itemSelected).actions.size()){actionText = actionTextBuilder.toString();}
        }
        index=0;

        while(index < CharacterList.characterData.get(itemSelected).getSpecialAbilities().size()) {
            if(index == 0) {
                abilityTextBuilder = new StringBuilder("Name: " + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getName() + "\n");
                abilityTextBuilder.append("Attack Bonus: " + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getAttackBonus() + "\n" + "\n");
                abilityTextBuilder.append("Description:\n" + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getDesc() + "\n");
            }
            else{

                abilityTextBuilder.append("\n" + "\n" + "Name: " + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getName() + "\n");
                abilityTextBuilder.append("Attack Bonus: " + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getAttackBonus() + "\n" + "\n");
                abilityTextBuilder.append("Description:\n" + CharacterList.characterData.get(itemSelected).specialAbilities.get(index).getDesc()+"\n");

            }
            index++;
            if(index== CharacterList.characterData.get(itemSelected).getSpecialAbilities().size()){abilityText = abilityTextBuilder.toString();}
        }

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
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
////        public static PlaceholderFragment newInstance(int sectionNumber) {
////            PlaceholderFragment fragment = new PlaceholderFragment();
////            Bundle args = new Bundle();
////            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
////            fragment.setArguments(args);
////            return fragment;
////        }
////
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                                 Bundle savedInstanceState) {
////            DisplayMetrics displayMetrics = new DisplayMetrics();
////            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
////            int width = displayMetrics.widthPixels;
////            View rootView = inflater.inflate(R.layout.activity_character_details, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.character_name);
////            TextView charNameTextView = (TextView) rootView.findViewById(R.id.character_name);
////            TextView statBlockStr = (TextView) rootView.findViewById(R.id.stat_str);
////            TextView statBlockDex = (TextView) rootView.findViewById(R.id.stat_dex);
////            TextView statBlockCon = (TextView) rootView.findViewById(R.id.stat_con);
////            TextView statBlockInt = (TextView) rootView.findViewById(R.id.stat_int);
////            TextView statBlockWis = (TextView) rootView.findViewById(R.id.stat_wis);
////            TextView statBlockCha = (TextView) rootView.findViewById(R.id.stat_cha);
////            TextView statBlockStrValTextView= (TextView) rootView.findViewById(R.id.stat_str_val);
////            TextView statBlockDexValTextView= (TextView) rootView.findViewById(R.id.stat_dex_val);
////            TextView statBlockConValTextView= (TextView) rootView.findViewById(R.id.stat_con_val);
////            TextView statBlockIntValTextView= (TextView) rootView.findViewById(R.id.stat_int_val);
////            TextView statBlockWisValTextView= (TextView) rootView.findViewById(R.id.stat_wis_val);
////            TextView statBlockChaValTextView= (TextView) rootView.findViewById(R.id.stat_cha_val);
////            TextView conditionalImmunitiesValTextView= (TextView) rootView.findViewById(R.id.condition_immunities_val);
////            TextView conditionalImmunities = (TextView) rootView.findViewById(R.id.condition_immunities);
////            TextView damageImmunitiesValTextView= (TextView) rootView.findViewById(R.id.damage_immunities_val);
////            TextView damageImmunities = (TextView) rootView.findViewById(R.id.damage_immunities);
////            TextView senses = (TextView) rootView.findViewById(R.id.senses);
////            TextView sensesValTextView= (TextView) rootView.findViewById(R.id.senses_val);
////            TextView challengeRating = (TextView) rootView.findViewById(R.id.challenge_rating);
////            TextView languages = (TextView) rootView.findViewById(R.id.languages);
////            TextView languagesValTextView= (TextView) rootView.findViewById(R.id.languages_val);
////            TextView challengeRatingValTextView= (TextView) rootView.findViewById(R.id.challenge_rating_val);
////
////
////
//////            damageImmunities.setHeight(damageImmunitiesVal.getHeight());
//////           conditionalImmunities.setHeight(conditionalImmunitiesVal.getHeight());
////            challengeRating.setWidth(width/2);
////            challengeRatingVal.setWidth(width/2);
////            languages.setWidth(width/2);
////            languagesVal.setWidth(width/2);
////
////            senses.setWidth(width/2);
////            sensesVal.setWidth(width/2);
////            damageImmunitiesVal.setWidth(width/2);
////            damageImmunities.setWidth(width/2);
////            conditionalImmunities.setWidth(width/2);
////            conditionalImmunitiesVal.setWidth(width/2);
////
////            statBlockStr.setWidth(width/6);
////            statBlockDex.setWidth(width/6);
////            statBlockCon.setWidth(width/6);
////            statBlockInt.setWidth(width/6);
////            statBlockWis.setWidth(width/6);
////            statBlockCha.setWidth(width/6);
////            statBlockStrVal.setWidth(width/6);
////            statBlockDexVal.setWidth(width/6);
////            statBlockConVal.setWidth(width/6);
////            statBlockIntVal.setWidth(width/6);
////            statBlockWisVal.setWidth(width/6);
////            statBlockChaVal.setWidth(width/6);
////
////
////
////
////            return rootView;
////        }
////
////
////
////    }
////
////    /**
////     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
////     * one of the sections/tabs/pages.
////     */
////    public class SectionsPagerAdapter extends FragmentPagerAdapter {
////
////        public SectionsPagerAdapter(FragmentManager fm) {
////            super(fm);
////        }
////
////        @Override
////        public Fragment getItem(int position) {
////            // getItem is called to instantiate the fragment for the given page.
////            // Return a PlaceholderFragment (defined as a static inner class below).
////            return PlaceholderFragment.newInstance(position + 1);
////        }
////
////        @Override
////        public int getCount() {
////            // Show 3 total pages.
////            return 3;
////}
}
