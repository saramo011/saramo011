package com.app.laundry.tabs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.laundry.Config;
import com.app.laundry.DealsIntermediateActivity;
import com.app.laundry.R;
import com.app.laundry.json.JGetParsor;
import com.app.laundry.laundryDetailActivity;
import com.app.laundry.lazyloading.ImageLoader;
import com.app.laundry.network.Network;
import com.app.laundry.util.AlertUtil;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DealsFragment extends Fragment implements OnItemClickListener {

    ProgressBar progressBar1;
    ImageView imageView_banner;
    ImageLoader imageLoader;
    ListView listView1, listView2;
    LinearLayout ll, ll1;
    Handler mHandler = new Handler();
    ArrayList<HashMap<String, String>> all_list1;
    ArrayList<HashMap<String, String>> all_list2;
    JSONObject json;

    Context mContext = null;

    public static DealsFragment newInstance() {
        DealsFragment fragment = new DealsFragment();
        return fragment;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;
        if (listAdapter.getCount() <= 1) return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (mContext == null)
            mContext = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mContext == null)
            mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.deal_listview, container, false);

        progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);

        listView1 = (ListView) view.findViewById(R.id.listView1_men);
        listView2 = (ListView) view.findViewById(R.id.listView2);

        ll = (LinearLayout) view.findViewById(R.id.ll);
        ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        ll.setVisibility(View.GONE);
        ll1.setVisibility(View.GONE);

        if (mContext == null)
            mContext = getActivity();

        all_list1 = new ArrayList<HashMap<String, String>>();
        all_list2 = new ArrayList<HashMap<String, String>>();

        imageView_banner = (ImageView) view.findViewById(R.id.imageView_banner);
        imageView_banner.setVisibility(View.GONE);

        imageLoader = new ImageLoader(getActivity().getApplicationContext());
        if (Network.HaveNetworkConnection(getActivity())) {
            getBanner();
        }

        if (Network.HaveNetworkConnection(getActivity())) {
            new loginAccess().execute();
        } else {
            AlertUtil alert = new AlertUtil();
            alert.messageAlert(getActivity(), getResources()
                    .getString(R.string.network_title), getResources().getString(R.string.network_message));
        }

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String laundry_id = ((TextView) view.findViewById(R.id.laundry_id)).getText().toString();
        String laundry_name = ((TextView) view.findViewById(R.id.laundry_name)).getText().toString();

        if (laundry_id.contains("http:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(laundry_id));
            startActivity(intent);
        } else {
            HashMap<String, String> hashmap = new HashMap<String, String>();
            hashmap=all_list1.get(position);

            Intent intent = new Intent(getActivity(), DealsIntermediateActivity.class);
            intent.putExtra("LaundryID", laundry_id);
            intent.putExtra("LaundryName", laundry_name);
            intent.putExtra("deal_id",hashmap.get("deal_id"));
            intent.putExtra("deal_title",hashmap.get("deal_title"));
            intent.putExtra("deal_text",hashmap.get("laundry_offer"));
            intent.putExtra("deal_image_url",hashmap.get("deal_image_url"));
            intent.putExtra("deal_address",hashmap.get("deal_address"));
            intent.putExtra("lat",hashmap.get("lat"));
            intent.putExtra("log",hashmap.get("log"));




            startActivity(intent);

            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    private void getBanner() {
        imageLoader = new ImageLoader(mContext);
        json = null;
        final Thread image_url = new Thread() {
            @Override
            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                JGetParsor j = new JGetParsor();
                if (Config.banner_json != null) {
                    json = Config.banner_json;
                } else {
                    json = j.makeHttpRequest(Config.banner_url, "POST", params);
                }
            }
        };
        image_url.start();

        final Thread show = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    image_url.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
                if (mHandler != null) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try {
                                if (json.getInt("status") == 200) {
                                    JSONArray j_arr = json.getJSONArray("data");
                                    JSONObject j_obj = j_arr.getJSONObject(0);
                                    imageLoader.DisplayImage(j_obj.getString("BannerURL"), imageView_banner, false);
                                    Config.banner_json = json;
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                //e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        show.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

//        int city_array_size = Config.cityArray.size();
//        if (city_array_size > 0)
//            for (int i = 0; i < city_array_size; i++)
//                menu.findItem(i).setVisible(false);

    }

    class loginAccess extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... arg0) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JGetParsor j = new JGetParsor();
            JSONObject json = null;
            if (Config.deals_json == null)
                json = j.makeHttpRequest(Config.Deals_Url, "POST", params);
            else
                json = Config.deals_json;

            String check = "failed";

            try {
                int success = json.getInt("status");
                if (success == 200) {
                    check = "Success";
                    JSONArray list_array = json.getJSONArray("data");

                    for (int i = 0; i < list_array.length(); i++) {
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        JSONObject jo = list_array.getJSONObject(i);
                        String laundry_name = jo.getString("LaundryName");
                        String laundry_address = jo.getString("LaundryAddress");
                        String laundry_id = jo.getString("LaundryID");

                        String dealId=jo.getString("DealID");
                        String dealTitle=jo.getString("DealTitle");
                        String laundry_offer_dealText = jo.getString("DealText");
                        String dealImageURl=jo.getString("DealImage");
                        String lat=jo.getString("LaundryLat");
                        String log=jo.getString("LaundryLong");



                        String address = "<b>Address:</b><br>";
                        if (!jo.getString("LaundryAddress").equals(""))
                            address = address + jo.getString("LaundryAddress");
                        if (!address.equals("")) {
                            address = address + "<br>";
                        }

                        if (!jo.getString("LaundryCity").equals(""))
                            address = address + jo.getString("LaundryCity") + ", ";

                        if (!address.equals("") && !jo.getString("LaundryZipCode").equals("")) {
                            address = address + "Zip code: ";
                        }
                        if (!jo.getString("LaundryZipCode").equals(""))
                            address = address + jo.getString("LaundryZipCode") + "<br>";




                        map1.put("laundry_name", laundry_name);
                        map1.put("laundry_address", laundry_address);
                        map1.put("laundry_offer", laundry_offer_dealText);
                        map1.put("laundry_id", laundry_id);

                        map1.put("deal_title",dealTitle);
                        map1.put("deal_id",dealId);
                        map1.put("deal_image_url",dealImageURl);
                        map1.put("deal_address",address);
                        map1.put("lat",lat);
                        map1.put("log",log);



                        all_list1.add(map1);
                    }
                    Config.deals_json = json;
                }
            } catch (JSONException e) {
                //e.printStackTrace();
                Config.deals_json = null;
            }



            JSONObject json1 = null;
            if (Config.other_deals_json == null)
                json1 = j.makeHttpRequest(Config.Others_Deals_Url, "POST", params);
            else
                json1 = Config.other_deals_json;

            try {
                int success = json1.getInt("status");
                if (success == 200) {
                    check = "Success";
                    JSONArray list_array = json1.getJSONArray("data");

                    for (int i = 0; i < list_array.length(); i++) {
                        HashMap<String, String> map2 = new HashMap<String, String>();
                        JSONObject jo = list_array.getJSONObject(i);
                        String laundry_name = jo.getString("Deal_title");
                        String laundry_address = "";
                        String laundry_offer = jo.getString("Deal_description");
                        String laundry_id = jo.getString("Deal_link");

                        map2.put("laundry_name", laundry_name);
                        map2.put("laundry_address", laundry_address);
                        map2.put("laundry_offer", laundry_offer);
                        map2.put("laundry_id", laundry_id);

                        all_list2.add(map2);
                    }
                    Config.other_deals_json = json1;
                }
            } catch (JSONException e) {
                //e.printStackTrace();
                Config.other_deals_json = null;
            }

            return check;
        }

        protected void onPostExecute(String file_url) {
            if (all_list1.size() > 0)
                ll.setVisibility(View.VISIBLE);
            if (mContext == null)
                mContext = getActivity();
            ListAdapter adapter1 = new SimpleAdapter(mContext, all_list1, R.layout.deal_list_row, new String[]{"laundry_name", "laundry_address", "laundry_offer", "laundry_id"}, new int[]{R.id.laundry_name, R.id.laundry_address, R.id.laundry_offer, R.id.laundry_id});
            listView1.setAdapter(adapter1);
            setListViewHeightBasedOnChildren(listView1);
            listView1.setOnItemClickListener(DealsFragment.this);

            if (all_list1.size() > 0)
                ll1.setVisibility(View.VISIBLE);
            ListAdapter adapter2 = new SimpleAdapter(mContext, all_list2, R.layout.deal_list_row, new String[]{"laundry_name", "laundry_address", "laundry_offer", "laundry_id"}, new int[]{R.id.laundry_name, R.id.laundry_address, R.id.laundry_offer, R.id.laundry_id});
            listView2.setAdapter(adapter2);
            setListViewHeightBasedOnChildren(listView2);
            listView2.setOnItemClickListener(DealsFragment.this);

            progressBar1.setVisibility(View.GONE);
        }

    }
}