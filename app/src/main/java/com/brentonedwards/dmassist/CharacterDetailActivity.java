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

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    private ViewPager mViewPager;

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

        TextView charNameTextView = (TextView) rootView.findViewById(R.id.character_name);

        charNameTextView.setText(CharacterList.characterData.get(itemSelected).charName);

        TextView statBlockStr = (TextView) rootView.findViewById(R.id.stat_str);
        TextView statBlockDex = (TextView) rootView.findViewById(R.id.stat_dex);
        TextView statBlockCon = (TextView) rootView.findViewById(R.id.stat_con);
        TextView statBlockInt = (TextView) rootView.findViewById(R.id.stat_int);
        TextView statBlockWis = (TextView) rootView.findViewById(R.id.stat_wis);
        TextView statBlockCha = (TextView) rootView.findViewById(R.id.stat_cha);
        TextView statBlockStrVal = (TextView) rootView.findViewById(R.id.stat_str_val);
        TextView statBlockDexVal = (TextView) rootView.findViewById(R.id.stat_dex_val);
        TextView statBlockConVal = (TextView) rootView.findViewById(R.id.stat_con_val);
        TextView statBlockIntVal = (TextView) rootView.findViewById(R.id.stat_int_val);
        TextView statBlockWisVal = (TextView) rootView.findViewById(R.id.stat_wis_val);
        TextView statBlockChaVal = (TextView) rootView.findViewById(R.id.stat_cha_val);
        TextView conditionalImmunitiesVal = (TextView) rootView.findViewById(R.id.condition_immunities_val);
        TextView conditionalImmunities = (TextView) rootView.findViewById(R.id.condition_immunities);
        TextView damageImmunitiesVal = (TextView) rootView.findViewById(R.id.damage_immunities_val);
        TextView damageImmunities = (TextView) rootView.findViewById(R.id.damage_immunities);
        TextView senses = (TextView) rootView.findViewById(R.id.senses);
        TextView sensesVal = (TextView) rootView.findViewById(R.id.senses_val);
        TextView challengeRating = (TextView) rootView.findViewById(R.id.challenge_rating);
        TextView languages = (TextView) rootView.findViewById(R.id.languages);
        TextView languagesVal = (TextView) rootView.findViewById(R.id.languages_val);
        TextView challengeRatingVal = (TextView) rootView.findViewById(R.id.challenge_rating_val);
        Button charSpecialAbilitiesButton = (Button) rootView.findViewById(R.id.special_abilities_button);
        Button charActionButton = (Button) rootView.findViewById(R.id.actions_button);

        challengeRating.setWidth(width/2);
        challengeRatingVal.setWidth(width/2);
        languages.setWidth(width/2);
        languagesVal.setWidth(width/2);

        senses.setWidth(width/2);
        sensesVal.setWidth(width/2);
        damageImmunitiesVal.setWidth(width/2);
        damageImmunities.setWidth(width/2);
        conditionalImmunities.setWidth(width/2);
        conditionalImmunitiesVal.setWidth(width/2);

        statBlockStr.setWidth(width/6);
        statBlockDex.setWidth(width/6);
        statBlockCon.setWidth(width/6);
        statBlockInt.setWidth(width/6);
        statBlockWis.setWidth(width/6);
        statBlockCha.setWidth(width/6);
        statBlockStrVal.setWidth(width/6);
        statBlockDexVal.setWidth(width/6);
        statBlockConVal.setWidth(width/6);
        statBlockIntVal.setWidth(width/6);
        statBlockWisVal.setWidth(width/6);
        statBlockChaVal.setWidth(width/6);





        charSpecialAbilitiesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(CharacterDetailActivity.this, SpecialAbilitiesActivity.class);
                CharacterDetailActivity.this.startActivity(myIntent);
            }
        });

        charActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(CharacterDetailActivity.this, CharacterActionsActivity.class);
                CharacterDetailActivity.this.startActivity(myIntent);
            }
        });
















        //        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
      //  mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent myIntent = new Intent(CharacterDetailActivity.this, MainActivity.class);
//                //myIntent.putExtra("key", value); //Optional parameters
//                CharacterDetailActivity.this.startActivity(myIntent);
//
//            }
//        });

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            View rootView = inflater.inflate(R.layout.activity_character_details, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.character_name);
            TextView charNameTextView = (TextView) rootView.findViewById(R.id.character_name);
            TextView statBlockStr = (TextView) rootView.findViewById(R.id.stat_str);
            TextView statBlockDex = (TextView) rootView.findViewById(R.id.stat_dex);
            TextView statBlockCon = (TextView) rootView.findViewById(R.id.stat_con);
            TextView statBlockInt = (TextView) rootView.findViewById(R.id.stat_int);
            TextView statBlockWis = (TextView) rootView.findViewById(R.id.stat_wis);
            TextView statBlockCha = (TextView) rootView.findViewById(R.id.stat_cha);
            TextView statBlockStrVal = (TextView) rootView.findViewById(R.id.stat_str_val);
            TextView statBlockDexVal = (TextView) rootView.findViewById(R.id.stat_dex_val);
            TextView statBlockConVal = (TextView) rootView.findViewById(R.id.stat_con_val);
            TextView statBlockIntVal = (TextView) rootView.findViewById(R.id.stat_int_val);
            TextView statBlockWisVal = (TextView) rootView.findViewById(R.id.stat_wis_val);
            TextView statBlockChaVal = (TextView) rootView.findViewById(R.id.stat_cha_val);
            TextView conditionalImmunitiesVal = (TextView) rootView.findViewById(R.id.condition_immunities_val);
            TextView conditionalImmunities = (TextView) rootView.findViewById(R.id.condition_immunities);
            TextView damageImmunitiesVal = (TextView) rootView.findViewById(R.id.damage_immunities_val);
            TextView damageImmunities = (TextView) rootView.findViewById(R.id.damage_immunities);
            TextView senses = (TextView) rootView.findViewById(R.id.senses);
            TextView sensesVal = (TextView) rootView.findViewById(R.id.senses_val);
            TextView challengeRating = (TextView) rootView.findViewById(R.id.challenge_rating);
            TextView languages = (TextView) rootView.findViewById(R.id.languages);
            TextView languagesVal = (TextView) rootView.findViewById(R.id.languages_val);
            TextView challengeRatingVal = (TextView) rootView.findViewById(R.id.challenge_rating_val);



//            damageImmunities.setHeight(damageImmunitiesVal.getHeight());
//           conditionalImmunities.setHeight(conditionalImmunitiesVal.getHeight());
            challengeRating.setWidth(width/2);
            challengeRatingVal.setWidth(width/2);
            languages.setWidth(width/2);
            languagesVal.setWidth(width/2);

            senses.setWidth(width/2);
            sensesVal.setWidth(width/2);
            damageImmunitiesVal.setWidth(width/2);
            damageImmunities.setWidth(width/2);
            conditionalImmunities.setWidth(width/2);
            conditionalImmunitiesVal.setWidth(width/2);

            statBlockStr.setWidth(width/6);
            statBlockDex.setWidth(width/6);
            statBlockCon.setWidth(width/6);
            statBlockInt.setWidth(width/6);
            statBlockWis.setWidth(width/6);
            statBlockCha.setWidth(width/6);
            statBlockStrVal.setWidth(width/6);
            statBlockDexVal.setWidth(width/6);
            statBlockConVal.setWidth(width/6);
            statBlockIntVal.setWidth(width/6);
            statBlockWisVal.setWidth(width/6);
            statBlockChaVal.setWidth(width/6);




            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {

                case 1:
                        break;
                case 2: textView.setText("Perks & Spells");
                        break;
                case 3: textView.setText("Campaign Effects");
                        break;
                default: textView.setText("Character Sheet");
            }
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }



    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
