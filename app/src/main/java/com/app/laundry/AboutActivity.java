package com.app.laundry;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AboutActivity extends ActionBarActivity {
	


	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.left_in, R.anim.right_out);
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		
		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196f3")));
		bar.setDisplayShowHomeEnabled(true);
		bar.setDisplayHomeAsUpEnabled(true);
	    bar.setHomeButtonEnabled(true);
		bar.setTitle("About Us");
		
		TextView textView_about=(TextView)findViewById(R.id.textView_about);

		//textView_about.setText(Html.fromHtml(getString(R.string.formatted))); This is for normal formatting like bold italic
		//TextViewJustify.justifyText(textView_about, 340f);
		
		TextView textView_terms=(TextView)findViewById(R.id.textView_terms);
		TextView textView_privacy=(TextView)findViewById(R.id.textView_privacy);
		
		textView_privacy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AboutActivity.this,PolicyActivity.class));
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
		});
		
		textView_terms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(AboutActivity.this,TermsActivity.class));
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
		});
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_menu, menu);	
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		  if (id==android.R.id.home){
	        onBackPressed();
	        return true;
		
	    }
		  else if(id==R.id.direct_to_home)
		  {
			  startActivity(new Intent(AboutActivity.this,
						BaseFragmentActivity.class));
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
		  }  
		  
		return super.onOptionsItemSelected(item);
	}
	}

	


