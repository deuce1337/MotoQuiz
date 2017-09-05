package com.example.motoquiz;


import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class WynikiActivity extends Activity {

	protected int dzwiekid, punkty;
	protected MediaPlayer dzwiek;
	protected EditText nazwa;
	protected String imie, czas1;
	protected ImageButton ok;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_wyniki);
			
			RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); 
			bar.setNumStars(5);
			bar.setStepSize(0.5f);
		
			TextView t=(TextView)findViewById(R.id.textView1);
			TextView czas = (TextView)findViewById(R.id.textResult);
			
			Bundle b = getIntent().getExtras();
			punkty= b.getInt("Punkty");
			
			
			bar.setRating(punkty);
			switch (punkty)
			{
			case 0: t.setText("Rly ? Nic ?");
					dzwiekid = R.raw.star0;
					break;
			case 1: t.setText("Cieniutko ...");
					dzwiekid = R.raw.star1;
					break;
			case 2: t.setText("S³aby wynik");
					dzwiekid = R.raw.star2;
					break;
			case 3: t.setText("Mog³o byæ lepiej");
					dzwiekid = R.raw.star3;
					break;
			case 4:t.setText("Ca³kiem nieŸle");
					dzwiekid = R.raw.star4;
					break;
			case 5:t.setText("Super wynik !");
					dzwiekid = R.raw.star5;
					break;
			}
			
			if(dzwiek != null) {

				dzwiek.release();

				}
			
			dzwiek = MediaPlayer.create(this, dzwiekid);
			dzwiek.start();
			
			Bundle c = getIntent().getExtras();
			czas1 = c.getString("Czas");
			
			
			czas.setText("Zrobi³eœ to w: " + czas1);
			
			
			
			nazwa = (EditText)findViewById(R.id.editText1);
			
			
			
			ok = (ImageButton)findViewById(R.id.imageButton1);
			
			ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					imie = nazwa.getText().toString();
					
					String nazwa = "Imiê: " + imie + " | Punkty: " + punkty + " | Czas: " + czas1 + "\n";
					 
					
					 try {
					 FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_APPEND);
					 OutputStreamWriter zapis=new OutputStreamWriter(fileout);
					 zapis.write(nazwa);
					 
					 
					 zapis.close();
					 
					 //Wiadomoœæ o zapisie 
					 Toast.makeText(getBaseContext(), "File saved successfully!",
					 Toast.LENGTH_SHORT).show();
					 
					 } catch (Exception e) {
					 e.printStackTrace();
					  
					 }
						Log.d("Napis:", nazwa);
							
						
				}
			});
			
		
			
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.wyniki, menu);
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

