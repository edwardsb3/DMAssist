package com.brentonedwards.dmassist.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brentonedwards.dmassist.CharacterData;
import com.brentonedwards.dmassist.CharacterDetailActivity;
import com.brentonedwards.dmassist.EncounterCharacter;
import com.brentonedwards.dmassist.EncountersActivity;
import com.brentonedwards.dmassist.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import static com.brentonedwards.dmassist.EncountersActivity.db;
import static com.brentonedwards.dmassist.EncountersActivity.encounterCharacterList;
import static com.brentonedwards.dmassist.EncountersActivity.encounterCharacters;


/**
 * Created by Brenton Edwards on 03/20/2018.
 */
public class EncounterListAdapter extends ArrayAdapter<EncounterCharacter> implements View.OnClickListener{

    private List<EncounterCharacter> dataSet;
    Context mContext;
    public int width;
    public int colWidth;
    // View lookup cache
    private static class ViewHolder {
        TextView nameTextView;
        TextView armorClassTextView;
        TextView hitPointsTextView;
        TextView initiativeTextView;
        ImageView armorClassImageView;
    }



    public EncounterListAdapter(List<EncounterCharacter> data, Context context, int screenWidth) {
        super(context, R.layout.encounter_row_item, data);
        this.dataSet = data;
        this.mContext=context;
        width = screenWidth;
        colWidth = width/2;
    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        EncounterCharacter selectedCharacter= getItem(position);
        //       int encounterCharacterIndex = index;




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
        final EncounterCharacter selectedEncounterCharacter = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.encounter_row_item, parent, false);
            viewHolder.nameTextView = convertView.findViewById(R.id.name);
            viewHolder.armorClassTextView = convertView.findViewById(R.id.armor_class);
            viewHolder.hitPointsTextView = convertView.findViewById(R.id.hit_points);
            viewHolder.initiativeTextView = convertView.findViewById(R.id.initiative);
            viewHolder.armorClassImageView = convertView.findViewById(R.id.armor_class_image);
            viewHolder.nameTextView.setWidth(width / 2);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

            viewHolder.nameTextView.setText(selectedEncounterCharacter.getName());
            viewHolder.armorClassTextView.setText(String.valueOf(selectedEncounterCharacter.getArmorClass()));
            viewHolder.hitPointsTextView.setText(String.valueOf(selectedEncounterCharacter.getHealth()));
                viewHolder.initiativeTextView.setText(String.valueOf(selectedEncounterCharacter.getInitiative()));
            Log.d("createCell", selectedEncounterCharacter.getName());


        return convertView;
    }

    public void setIncrementingIndex(List<EncounterCharacter> list){
        int index = 1;

        while(list.size() > index){

            list.get(index).setIndex(index);

            index++;
        }
    }

}