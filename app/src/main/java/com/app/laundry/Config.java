package com.app.laundry;

import org.json.JSONObject;

import java.util.ArrayList;

public class Config {

    public static String website = "http://laundry.znsoftech.com/yii/";
    public static String manage_addresses = website + "webservice/get/address/user_id/";
    public static String update_default_address = website + "webservice/set/address/address_id/";
    public static String delete_address = website + "webservice/set/deleteaddress/address_id/";
    public static String add_address = website + "webservice/set/CreateAddress";
    public static String get_order = website + "webservice/get/order";
    public static JSONObject banner_json = null;
    public static String laundry_booking_data = website + "webservice/get/itemserviceprice/laundryid/";
    public static String set_ratings = website + "webservice/set/feedback";
    public static String set_ratings_onstart = website + "webservice/get/feedback/";
    public static String new_city_url = website + "webservice/get/cities";
    public static String new_country_url = website + "webservice/get/countries";
    public static String latest_offers_home = website + "webservice/get/deals";
    public static String new_laundries_home = website + "webservice/get/laundries/tab/latest";
    //public static String banner_url="http://laundry.znsoftech.com/banner.php";
    public static String banner_url = website + "webservice/get/banner";
    public static JSONObject latest_offers_json = null;
    public static JSONObject latest_laundries_json = null;
    public static JSONObject deals_json = null;
    public static JSONObject other_deals_json = null;
    public static JSONObject nearby_json = null;
    public static JSONObject leading_json = null;
    public static JSONObject fav_json = null;
    public static ArrayList<String> cityArray = new ArrayList<String>();
    public static ArrayList<String> newCityArray = new ArrayList<String>();
    public static ArrayList<String> Item_Array = new ArrayList<String>();
    public static ArrayList<String> Service_Array = new ArrayList<String>();
    public static ArrayList<String> Amount_Array = new ArrayList<String>();
    public static String Ratecard_Url = website + "webservice/get/ratecard";//Rate list
    public static String Others_Deals_Url = "http://laundry.znsoftech.com/others_deals.php";
    //public static ArrayList<String,String,String> priceArray=new ArrayList<String,String,String>();
    public static String Login_Url = website + "site/validate_user";
    public static String Register_Url = "http://laundry.znsoftech.com/register_user.php";
    public static String Get_All_Laundry = website + "webservice/get/laundries/tab/%s";
    public static String Get_Laundry_Detail_Url = website + "webservice/get/laundry/";
    public static String Bookmark_Url = website + "webservice/set/bookmark";
    public static String Feedback_Url = website + "webservice/set/feedback";
    public static String Review_Url = website + "webservice/get/feedback/laundryid/";
    public static String Deals_Url = website + "webservice/get/deals";
    public static String Order_Offline_Url = "http://laundry.znsoftech.com/order_offline.php";
    public static String Order_Online_Url = "http://laundry.znsoftech.com/yii/webservice/set/order";

    public static String City_Url = website + "webservice/get/cities";
    public static String latitude = "";
    public static String longitude = "";
    public static String phone = "";
    public static String name = "";
    public static String password = "";
    public static String mobile = "";
    public static String email = "";
    public static String userid = "";
    public static String address = "";
    public static String city = "";
    public static String country = "";
    public static String usertype = "";
    static Boolean isBookingService = false;

    /**
     * This class will be used asa a temporary class.
     * while going from deals list to Deals page
     * 'l' is nothing but the prefix to get immediate reference just by typing char l
     * author "Sandeep Rana"
     */

    static class LaundryDeals{
        public static String lID;
        public static String lName;
        public static String lAddress;
        public static String lCity;
        public static String lStateID;
        public static String lState;
        public static String lCountryID;
        public static String lCountry;
        public static String lZipcode;
        public static String lOwner;
        public static String lEmail;
        public static String lPhone1;
        public static String lPhone2;
        public static String lMobile1;
        public static String lMobile2;
        public static String lCallPreference;
        public static String lWebsite;
        public static String lImageUrl;
        public static String lLat;
        public static String lLong;
        public static String lCreatedBy;
        public static String lCreatedOn;
        public static String lModifiedON;
        public static String lWorking;
        public static String lDealID;
        public static String lDealTitle;
        public static String lDealType;
        public static String lDealText;
        public static String lDealImage;
        public static String lRedirectURl;
        public static String lDisplayOnHome;
        public static String lHomeStartTime;
        public static String lHomeEndTime;
        public static String lDisplayOnDeal;
        public static String lDealStartTime;
        public static String lDealEndTime;
        public static String lLive;


    }
}