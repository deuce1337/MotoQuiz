package com.example.motoquiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.util.Log;

public class Pytania extends Activity {
	
	private int id;
	private String pytanie;
	private String odpA;
	private String odpB;
	private String odpC;
	private String odpD;
	private String poprawnaOdp;
	
	public Pytania()
	{
		this.id=0;
		this.pytanie="";
		this.odpA="";
		this.odpB="";
		this.odpC="";
		this.odpD="";
		this.poprawnaOdp="";
	}
	
	public Pytania(String pyt, String a, String b, String c, String d, String pop)
	{
		this.pytanie = pyt;
		this.odpA = a;
		this.odpB = b;
		this.odpC = c;
		this.odpD = d;
		this.poprawnaOdp = pop;
	}	
	
	public Pytania(int id)
	{
		this.pytanie = readFromFile(id);
		this.odpA = readFromFile(id+1);
		this.odpB = readFromFile(id+2);
		this.odpC = readFromFile(id+3);
		this.odpD = readFromFile(id+4);
		this.poprawnaOdp = readFromFile(id+5);
	}
	
	public int ZwrocID()
	{
		return id;
	}
	public String ZwrocPytanie()
	{
		return pytanie;
	}
	public String ZwrocOdpA()
	{
		return odpA;
	}
	public String ZwrocOdpB()
	{
		return odpB;
	}
	public String ZwrocOdpC()
	{
		return odpC;
	}
	public String ZwrocOdpD()
	{
		return odpD;
	}
	public String ZwrocPoprOdp()
	{
		return poprawnaOdp;
	}
	public void UstawID(int x)
	{
		this.id = x;
	}
	public void UstawPytanie(String x)
	{
		this.pytanie = x;
	}
	public void UstawOdpA(String x)
	{
		this.odpA = x;
	}
	public void UstawOdpB(String x)
	{
		this.odpB = x;
	}
	public void UstawOdpC(String x)
	{
		this.odpC = x;
	}
	public void UstawOdpD(String x)
	{
		this.odpD = x;
	}
	public void UstawPoprOdp(String x)
	{
		this.poprawnaOdp = x;
	}
	
	
	private String readFromFile(int linia) {

        String ret = "";

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.plik);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
	
                	for(int i =0; i<linia; i++)
                	{
                	receiveString = bufferedReader.readLine();
                	}
                    stringBuilder.append(receiveString);
              

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
	
}
