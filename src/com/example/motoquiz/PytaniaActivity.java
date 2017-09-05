package com.example.motoquiz;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PytaniaActivity extends Activity {

	protected List<Pytania> quesList;
	private int punkty = 0;
	private int pytid=0;
	private Pytania AktualnePyt;
	private TextView txtQuestion, wynik;
	private RadioButton radioa, radiob, radioc, radiod;
	private ImageButton dalej;
	private Handler domyslnyHandler = new Handler();
	private long startTime = 0L;
	
	private long timeInMilliseconds = 0L;
	private long timeSwapBuff = 0L;
	private long updatedTime = 0L;
	private String endTime = "";

	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pytania);
		
		
		wynik = (TextView)findViewById(R.id.textView2);
	
		
		DbHelper db=new DbHelper(this);
		quesList=db.WszystkiePytania();
		AktualnePyt=quesList.get(pytid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		radioa=(RadioButton)findViewById(R.id.radio0);
		radiob=(RadioButton)findViewById(R.id.radio1);
		radioc=(RadioButton)findViewById(R.id.radio2);
		radiod=(RadioButton)findViewById(R.id.radio3);
		dalej=(ImageButton)findViewById(R.id.imageButton1);
		setQuestionView();
		
		//Timer
		 startTime = SystemClock.uptimeMillis();
		domyslnyHandler.postDelayed(updateTimerThread, 0);
		
		dalej.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				
				
				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton odpowiedz=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				Log.d("Odpowiedü: ", AktualnePyt.ZwrocPoprOdp()+" "+odpowiedz.getText());
				if(AktualnePyt.ZwrocPoprOdp().equals(odpowiedz.getText()))
				{
					punkty++;
					Log.d("Punkty:", "Twoja punktacja: "+ punkty);
				}
				if(pytid<5){					
					AktualnePyt=quesList.get(pytid);
					setQuestionView();
				}else{
					Intent IntentWynik = new Intent(PytaniaActivity.this, WynikiActivity.class);
					Bundle b = new Bundle();
					b.putInt("Punkty", punkty); //TwÛj wynik
					IntentWynik.putExtras(b);
					
					
					//Timer stop
					
					timeSwapBuff += timeInMilliseconds;
				    endTime = wynik.getText().toString();
				    Log.d("Czas:", endTime);
					Bundle c = new Bundle();
					c.putString("Czas", endTime);
					IntentWynik.putExtras(c);
					domyslnyHandler.removeCallbacks(updateTimerThread);
					
					startActivity(IntentWynik);
					finish();
				}
				
				
				
			}
		});
		
		
		
	}
	
	private Runnable updateTimerThread = new Runnable() {
		
	public void run() {
		
		timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
		updatedTime = timeSwapBuff + timeInMilliseconds;
	
		 
	
		            int secs = (int) (updatedTime / 1000);
		            int mins = secs / 60;
		            secs = secs % 60;
		            int milliseconds = (int) (updatedTime % 1000);
		            wynik.setText("" + mins + ":"
		
		                    + String.format("%02d", secs) + ":"
		
		                    + String.format("%03d", milliseconds));
		            
		            domyslnyHandler.postDelayed(this, 0);
		            
		        }
		    };

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pytania, menu);
		return true;
	}
	
	private void setQuestionView()
	{
		txtQuestion.setText(AktualnePyt.ZwrocPytanie());
		radioa.setText(AktualnePyt.ZwrocOdpA());
		radiob.setText(AktualnePyt.ZwrocOdpB());
		radioc.setText(AktualnePyt.ZwrocOdpC());
		radiod.setText(AktualnePyt.ZwrocOdpD());
		pytid++;
	}
}
