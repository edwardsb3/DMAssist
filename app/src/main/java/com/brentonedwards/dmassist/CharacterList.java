package com.brentonedwards.dmassist;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.brentonedwards.dmassist.adapter.CharacterListAdapter;
import com.brentonedwards.dmassist.util.GsonParse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.brentonedwards.dmassist.EncountersActivity.db;

public class CharacterList extends AppCompatActivity {

    EditText searchBar;
    ListView listView;
    View root;
    ArrayList<EncounterCharacter> searchResult = new ArrayList<>();
    private static CharacterListAdapter adapter;

    View mainScreen;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.list);
        root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        searchBar = findViewById(R.id.search_bar);
           listView = (ListView) findViewById(R.id.list);


        searchBar.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'

                    adapter = null;
                    listView.setAdapter(new CharacterListAdapter(db.characterDao().searchforContainedString(searchBar.getText().toString()+"%"), root.getContext()));
                }
                return false;
            }
        });


        adapter = new CharacterListAdapter(db.characterDao().getAllCharacterData(), getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(CharacterList.this, CharacterDetailActivity.class);
                myIntent.putExtra("Value", position);
                CharacterList.this.startActivity(myIntent);
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



