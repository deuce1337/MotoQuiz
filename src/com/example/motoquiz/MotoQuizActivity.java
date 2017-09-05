package com.example.motoquiz;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class MotoQuizActivity extends Activity {

	
	static final private int INFO_DIALOG = 1;
	
	protected int dzwiekid;
	protected MediaPlayer dzwiek;
	protected ImageButton start, wyjscie, wyniki;
	protected ToggleButton wycisz;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moto_quiz);
		
		start = (ImageButton)findViewById(R.id.imageButton1);
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent IntentPytania = new Intent(MotoQuizActivity.this, PytaniaActivity.class);
				MotoQuizActivity.this.startActivity(IntentPytania);
				
			}
		});
		
		wyniki = (ImageButton)findViewById(R.id.imageButton3);
		
		wyniki.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent IntentTablica = new Intent(MotoQuizActivity.this, TabelaWynikiActivity.class);
				MotoQuizActivity.this.startActivity(IntentTablica);
				
			}
		});
		
		wyjscie = (ImageButton)findViewById(R.id.imageButton2);
		
		wyjscie.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				System.exit(0);
				
			}
		});
		
		wycisz = (ToggleButton)findViewById(R.id.toggleButton);
		
		wycisz.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(wycisz.isChecked() == true)
				{
					dzwiek.setVolume(0,0);
				}
				else
				{
					dzwiek.setVolume(1,1);
				}
				
			}
		});
		
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		dzwiekid = R.raw.menu;
		
		if(dzwiek != null) {

			dzwiek.release();

			}
		
		dzwiek = MediaPlayer.create(this, dzwiekid);
		dzwiek.start();
		
		
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		
		dzwiek.stop();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		dzwiekid = R.raw.menu;
		
		if(dzwiek != null) {

			dzwiek.release();

			}
		
		dzwiek = MediaPlayer.create(this, dzwiekid);
		dzwiek.start();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.moto_quiz, menu);
		return true;
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) 
		{
		case R.id.action_info: 
			showDialog(INFO_DIALOG);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case INFO_DIALOG:
	        return createInfoDialog();
	    default:
	        return null;
	 
	    }
	}
	
	
	private Dialog createInfoDialog() 
	{
	    AlertDialog.Builder oknodialogowe = new AlertDialog.Builder(this);
	    oknodialogowe.setTitle("O programie...");
	    oknodialogowe.setMessage("Program wykonali:\nKamil Grajdek\nJakubWierzchowski\nMicha³ S³owik");
	    oknodialogowe.setCancelable(false);
	    
	    oknodialogowe.setNeutralButton("OK", new Dialog.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int whichButton) {
	           
	        }
	    });
	    
	    
	    return oknodialogowe.create();
	}
	
	
}
