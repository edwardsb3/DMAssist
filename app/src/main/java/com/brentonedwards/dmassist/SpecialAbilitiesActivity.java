package com.brentonedwards.dmassist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.brentonedwards.dmassist.CharacterList.characterData;

public class SpecialAbilitiesActivity extends AppCompatActivity {

    ListView listView;
    private static SpecialAbilitiesListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_abilities);
        View someView = findViewById(R.id.list);
        View root = someView.getRootView();
        root.setBackgroundColor(Color.parseColor("#FDF1D9"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Special Abilities");
        toolbar.setBackgroundColor(Color.parseColor("#BC8338"));
        setSupportActionBar(toolbar);





        listView=(ListView)findViewById(R.id.list);






        adapter= new SpecialAbilitiesListAdapter(characterData,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                CharacterData characterData = CharacterList.characterData.get(position);

                Snackbar.make(view, characterData.getCharName()+" has been created.", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
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
}
