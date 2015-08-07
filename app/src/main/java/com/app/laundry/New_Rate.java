package com.app.laundry;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.laundry.json.JGetParsor;
import com.app.laundry.network.Network;
import com.app.laundry.util.AlertUtil;
import com.app.laundry.util.ProgressDialogClass;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class New_Rate extends FragmentActivity {

    Handler mHandler = new Handler();
    JSONObject json;
    String url_to_call;

    ImageView imageView_star1;
    ImageView imageView_star2;
    ImageView imageView_star3;
    ImageView imageView_star4;
    ImageView imageView_star5;

    ImageView imageView_star11;
    ImageView imageView_star12;
    ImageView imageView_star13;
    ImageView imageView_star14;
    ImageView imageView_star15;

    ImageView imageView_star21;
    ImageView imageView_star22;
    ImageView imageView_star23;
    ImageView imageView_star24;
    ImageView imageView_star25;

    ImageView imageView_star31;
    ImageView imageView_star32;
    ImageView imageView_star33;
    ImageView imageView_star34;
    ImageView imageView_star35;

    Button ok;
    EditText editText_msg;
    int tag1, tag2, tag3, tag4;
    String review, laundryId;

    OnClickListener clickLis = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int tag = Integer.parseInt(v.getTag().toString());
            tag1 = tag;
            update_rating(tag, new ImageView[]{imageView_star1, imageView_star2, imageView_star3, imageView_star4, imageView_star5});

        }
    };
    OnClickListener clickLis1 = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int tag = Integer.parseInt(v.getTag().toString());
            tag2 = tag;
            update_rating(tag, new ImageView[]{imageView_star11, imageView_star12, imageView_star13, imageView_star14, imageView_star15});

        }
    };
    //
    OnClickListener clickLis2 = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int tag = Integer.parseInt(v.getTag().toString());
            tag3 = tag;
            update_rating(tag, new ImageView[]{imageView_star21, imageView_star22, imageView_star23, imageView_star24, imageView_star25});

        }
    };
    //
    OnClickListener clickLis3 = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int tag = Integer.parseInt(v.getTag().toString());
            tag4 = tag;
            update_rating(tag, new ImageView[]{imageView_star31, imageView_star32, imageView_star33, imageView_star34, imageView_star35});

        }
    };

    // private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_rating);

        editText_msg = (EditText) findViewById(R.id.editText1);
        ok = (Button) findViewById(R.id.button_ok);
        tag1 = tag2 = tag3 = tag4 = 1;
        Intent intent = getIntent();
        laundryId = intent.getExtras().getString("LaundryID");

        ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Network.HaveNetworkConnection(New_Rate.this)) {
                    review = editText_msg.getText().toString();
                    update_rating();
                } else {
                    final AlertUtil alert = new AlertUtil();
                    alert.confirmationAlert(New_Rate.this, getResources()
                                    .getString(R.string.network_title), getResources()
                                    .getString(R.string.network_message),
                            new OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    alert.release();
                                }
                            });

                }

            }
        });


        imageView_star1 = (ImageView) findViewById(R.id.imageView_star1);
        imageView_star2 = (ImageView) findViewById(R.id.imageView_star2);
        imageView_star3 = (ImageView) findViewById(R.id.imageView_star3);
        imageView_star4 = (ImageView) findViewById(R.id.imageView_star4);
        imageView_star5 = (ImageView) findViewById(R.id.imageView_star5);

        imageView_star11 = (ImageView) findViewById(R.id.imageView_star11);
        imageView_star12 = (ImageView) findViewById(R.id.imageView_star12);
        imageView_star13 = (ImageView) findViewById(R.id.imageView_star13);
        imageView_star14 = (ImageView) findViewById(R.id.imageView_star14);
        imageView_star15 = (ImageView) findViewById(R.id.imageView_star15);

        imageView_star21 = (ImageView) findViewById(R.id.imageView_star21);
        imageView_star22 = (ImageView) findViewById(R.id.imageView_star22);
        imageView_star23 = (ImageView) findViewById(R.id.imageView_star23);
        imageView_star24 = (ImageView) findViewById(R.id.imageView_star24);
        imageView_star25 = (ImageView) findViewById(R.id.imageView_star25);

        imageView_star31 = (ImageView) findViewById(R.id.imageView_star31);
        imageView_star32 = (ImageView) findViewById(R.id.imageView_star32);
        imageView_star33 = (ImageView) findViewById(R.id.imageView_star33);
        imageView_star34 = (ImageView) findViewById(R.id.imageView_star34);
        imageView_star35 = (ImageView) findViewById(R.id.imageView_star35);

        rating_on_oncreate(clickLis, new ImageView[]{imageView_star1, imageView_star2, imageView_star3, imageView_star4, imageView_star5});

        rating_on_oncreate(clickLis1, new ImageView[]{imageView_star11, imageView_star12, imageView_star13, imageView_star14, imageView_star15});

        rating_on_oncreate(clickLis2, new ImageView[]{imageView_star21, imageView_star22, imageView_star23, imageView_star24, imageView_star25});

        rating_on_oncreate(clickLis3, new ImageView[]{imageView_star31, imageView_star32, imageView_star33, imageView_star34, imageView_star35});

        update_rating_onStart();

    }

    private void rating_on_oncreate(OnClickListener listener, ImageView[] image) {
        for (int i = 0; i < image.length; i++) {
            image[i].setOnClickListener(listener);
            image[i].setTag(i + 1);
        }
    }

    private void update_rating_onStart() {
        // TODO Auto-generated method stub

        json = null;
        ProgressDialogClass.showProgressDialog(New_Rate.this, getResources().getString(R.string.please_wait));
        final Thread getdata = new Thread() {
            @SuppressWarnings("deprecation")
            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JGetParsor j = new JGetParsor();
                url_to_call = Config.set_ratings_onstart + "laundryid/" + laundryId + "/userid/" + Config.userid;
                json = j.makeHttpRequest(url_to_call, "POST", params);
            }
        };
        getdata.start();

        final Thread show = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    getdata.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }

                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            ProgressDialogClass.dismissProgressDialog();
                            if (json != null) {
                                try {
                                    if (json.getInt("status") == 200) {
                                        //Toast.makeText(New_Rate.this, json.getString("status_message"),Toast.LENGTH_LONG).show();
                                        JSONArray j_arr = json.getJSONArray("data");
                                        JSONObject j_obj = j_arr.getJSONObject(0);

                                        Float pq = Float.parseFloat(j_obj.getString("PriceQuality"));
                                        Float sq = Float.parseFloat(j_obj.getString("ServiceQuality"));
                                        Float pq1 = Float.parseFloat(j_obj.getString("PickupQuality"));
                                        Float dq = Float.parseFloat(j_obj.getString("DropoffQuality"));

                                        int PriceQuality = Math.round(pq);
                                        int ServiceQuality = Math.round(sq);
                                        int PickupQuality = Math.round(pq1);
                                        int DropoffQuality = Math.round(dq);

                                        tag1 = PriceQuality;
                                        tag2 = ServiceQuality;
                                        tag3 = PickupQuality;
                                        tag4 = DropoffQuality;

                                        String Comments = j_obj.getString("Comments");

                                        update_rating(PriceQuality, new ImageView[]{imageView_star1, imageView_star2, imageView_star3, imageView_star4, imageView_star5});

                                        update_rating(ServiceQuality, new ImageView[]{imageView_star11, imageView_star12, imageView_star13, imageView_star14, imageView_star15});

                                        update_rating(PickupQuality, new ImageView[]{imageView_star21, imageView_star22, imageView_star23, imageView_star24, imageView_star25});

                                        update_rating(DropoffQuality, new ImageView[]{imageView_star31, imageView_star32, imageView_star33, imageView_star34, imageView_star35});

                                        editText_msg.setText(Comments);

                                        // Toast.makeText(New_Rate.this, PriceQuality + " " + ServiceQuality + " " + PickupQuality + " " + DropoffQuality, Toast.LENGTH_LONG).show();

                                    }
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        });
        show.start();
    }

    private void update_rating(int value, ImageView[] image) {

        for (int i = 0; i < image.length; i++)
            image[i].setImageResource(R.drawable.rating_before);

        switch (value) {

            case 5:
                image[4].setImageResource(R.drawable.rating_after);
            case 4:
                image[3].setImageResource(R.drawable.rating_after);
            case 3:
                image[2].setImageResource(R.drawable.rating_after);
            case 2:
                image[1].setImageResource(R.drawable.rating_after);
            case 1:
                image[0].setImageResource(R.drawable.rating_after);
            default:
                break;

        }

    }

    private void update_rating() {
        // TODO Auto-generated method stub
        ProgressDialogClass.showProgressDialog(New_Rate.this, getResources().getString(R.string.updating));
        final Thread getdata = new Thread() {
            @SuppressWarnings("deprecation")
            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("LaundryID", laundryId));
                params.add(new BasicNameValuePair("UserID", Config.userid));
                params.add(new BasicNameValuePair("PriceQuality", tag1 + ""));
                params.add(new BasicNameValuePair("ServiceQuality", tag2 + ""));
                params.add(new BasicNameValuePair("PickupQuality", tag3 + ""));
                params.add(new BasicNameValuePair("DropoffQuality", tag4 + ""));
                params.add(new BasicNameValuePair("Comments", review));
                JGetParsor j = new JGetParsor();
                json = j.makeHttpRequest(Config.set_ratings, "POST", params);
            }
        };
        getdata.start();

        final Thread show = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    getdata.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }

                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            if (json != null) {
                                try {
                                    //Toast.makeText(New_Rate.this, json.toString(),Toast.LENGTH_LONG).show();
                                    if (json.getInt("status") == 200) {
                                        Toast.makeText(New_Rate.this, getResources().getString(R.string.update_successfully), Toast.LENGTH_LONG).show();
                                        //  Toast.makeText(New_Rate.this, tag1 + " " + tag2 + " " + tag3 + " " + tag4, Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                    }
                                    //Toast.makeText(New_Rate.this, json.getInt("status"),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    //e.printStackTrace();
                                }
                            }
                            ProgressDialogClass.dismissProgressDialog();
                        }
                    });
                }
            }
        });
        show.start();
    }
}
