package com.brentonedwards.dmassist.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brentonedwards.dmassist.CharacterData;
import com.brentonedwards.dmassist.EncounterCharacter;
import com.brentonedwards.dmassist.EncountersActivity;
import com.brentonedwards.dmassist.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by Brenton Edwards on 03/20/2018.
 */
public class EncounterListAdapter extends ArrayAdapter<Integer> implements View.OnClickListener{

    private ArrayList<Integer> dataSet;
    Context mContext;


    // View lookup cache
    private static class ViewHolder {
        TextView nameTextView;
        TextView armorClassTextView;
        TextView hitPointsTextView;
        TextView passivePerceptionTextView;
        TextView inspiration;
    }



    public EncounterListAdapter(ArrayList<Integer> data, Context context) {
        super(context, R.layout.encounter_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        int index= getItem(position);
        int encounterCharacterIndex = index;




        switch (v.getId())
        {

            case R.id.item_info:

//                Snackbar.make(v, "Release date " + character.getChallengeRating(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();

                break;


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        int encounterCharacterIndex = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.encounter_row_item, parent, false);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.armorClassTextView = (TextView) convertView.findViewById(R.id.armor_class);
            viewHolder.hitPointsTextView = (TextView) convertView.findViewById(R.id.hit_points);
            viewHolder.passivePerceptionTextView = (TextView) convertView.findViewById(R.id.passive_perception);
            viewHolder.inspiration = (TextView) convertView.findViewById(R.id.inspiration);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.nameTextView.setText(EncountersActivity.characterData.get(encounterCharacterIndex).getCharName());
//        viewHolder.armorClassTextView.setText(String.valueOf(encounterCharacter.getCharacterSheet().getArmorClass()));
//
//        viewHolder.hitPointsTextView.setText(String.valueOf(encounterCharacter.getCharacterSheet().getHitPoints()));
//        viewHolder.passivePerceptionTextView.setText(String.valueOf(encounterCharacter.getCharacterSheet().getSenses()));
//        viewHolder.inspiration.setText("Inspired");
        // Return the completed view to render on screen
        return convertView;
    }


}
