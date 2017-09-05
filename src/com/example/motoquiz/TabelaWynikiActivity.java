package com.example.motoquiz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TabelaWynikiActivity extends Activity {

	TextView rezultat;
	ImageButton czysc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabela_wyniki);
		
		rezultat = (TextView)findViewById(R.id.highscore);
		czysc = (ImageButton)findViewById(R.id.imageButton1);
		

		 try {
		 FileInputStream fileIn=openFileInput("mytextfile.txt");
		 InputStreamReader InputRead= new InputStreamReader(fileIn);
		 
		 char[] inputBuffer= new char[5000];
		 String s="";
		 int charRead;
		 
		 while ((charRead=InputRead.read(inputBuffer))>0) {
		 String readstring=String.copyValueOf(inputBuffer,0,charRead);
		 s +=readstring; 
		 }
		 InputRead.close();
		 rezultat.setText(s);
		 
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 
		 czysc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String nazwa = "";
				
				 try {
					 FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
					 OutputStreamWriter zapis=new OutputStreamWriter(fileout);
					 zapis.write(nazwa);
					 
					 zapis.close();
					 
					 
					 Toast.makeText(getBaseContext(), "Wyczyszczono!",
					 Toast.LENGTH_SHORT).show();
					 
					 rezultat.setText("Brak wyników");
					 
					 } catch (Exception e) {
					 e.printStackTrace();
					 
					 
					  
					 }
						Log.d("Napis:", nazwa);
									
				}
				
		});
		 
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tabela_wyniki, menu);
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
