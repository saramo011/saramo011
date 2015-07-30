package com.app.laundry.util;

import android.app.ProgressDialog;
import android.content.Context;


public class ProgressDialogClass {
	static ProgressDialog pd;
    public static void showProgressDialog(Context context,String Msg){
    	if(context!=null){
    	pd=new ProgressDialog(context);
    	pd.setCancelable(true);
		pd.setCanceledOnTouchOutside(false);
		pd.setMessage(Msg);
		pd.show();
    	}
    }
    public static void dismissProgressDialog(){
    	pd.dismiss();
    }
}
