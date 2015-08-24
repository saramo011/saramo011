package com.app.laundry;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by sandeeprana on 24/08/15.
 */
public class DialogCities extends DialogFragment{

    FragmentManager fragmentManager;
    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        this.fragmentManager=manager;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        ArrayList<String> cityArrayList=Config.newCityArray;

        builder.setTitle("Select Cities")
        .setItems(cityArrayList.toArray(new String[cityArrayList.size()]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                if (selected_item.equals(Config.newCityArray.get(which))) {
                Config.city = Config.newCityArray.get(which);

                if (HomeFragment.pager != null) {
                    int index = HomeFragment.pager.getCurrentItem();
                    Fragment new_fragment = new HomeFragment();
                    Bundle data = new Bundle();
                    String[] str = new String[]{"all", "nearby", "suggest", "favorite"};

                    if (index < 4) {
                        data.putString("Tab", str[index]);

                        new_fragment.setArguments(data);
//                            fragment = new_fragment;
                        fragmentManager.beginTransaction().replace(R.id.frame_container, new_fragment).commit();
                    }
                    //Toast.makeText(getApplicationContext(), index+" If condition.", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return builder.create();
    }
}
