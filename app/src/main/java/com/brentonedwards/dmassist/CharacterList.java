package com.brentonedwards.dmassist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.brentonedwards.dmassist.adapter.CharacterListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.brentonedwards.dmassist.EncountersActivity.db;

public class CharacterList extends AppCompatActivity {

    public static Handler characterListHandler;
    EditText searchBar;
    ListView characterListView;
    View root;
    ArrayList<EncounterCharacter> searchResult = new ArrayList<>();
    private static CharacterListAdapter adapter;
    Thread dbQueryThread = new Thread();
    List<CharacterData> nonPlayerCreatedList = EncountersActivity.nonPlayerCreatedMonstersList;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.list);
        root = someView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        searchBar = findViewById(R.id.search_bar);
        characterListView = findViewById(R.id.list);

        if(nonPlayerCreatedList != null) {
            adapter = new CharacterListAdapter(EncountersActivity.nonPlayerCreatedMonstersList, getApplicationContext());
            characterListView.setAdapter(adapter);

        }
        characterListHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                adapter = new CharacterListAdapter(EncountersActivity.nonPlayerCreatedMonstersList, getApplicationContext());
                characterListView.setAdapter(adapter);
            }
        };




        searchBar.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    dbQueryThread = new Thread() {

                        public void run() {
                            adapter = null;
                            characterListView.setAdapter(new CharacterListAdapter(db.characterDao().searchforContainedString(searchBar.getText().toString()+"%"), root.getContext()));



                        }
                    };
                    dbQueryThread.run();

                }
                return false;
            }
        });


        characterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(CharacterList.this, CharacterDetailActivity.class);
                myIntent.putExtra("Value", position+1);
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



