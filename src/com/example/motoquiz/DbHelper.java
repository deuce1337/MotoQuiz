package com.example.motoquiz;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Nazwa bazy danych
	private static final String DATABASE_NAME = "MotoQuiz";
	// Nazwa tabeli
	private static final String TABLE_QUEST = "pytania";
	// Nazwy kolum w tabeli
	private static final String KEY_ID = "id";
	private static final String KEY_PYT = "pytanie";
	private static final String KEY_ODP = "odpowiedz"; //poprawna odp
	private static final String KEY_ODPA= "odpa"; //A
	private static final String KEY_ODPB= "odpb"; //B
	private static final String KEY_ODPC= "odpc"; //C
	private static final String KEY_ODPD= "odpd"; //C
	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_PYT
				+ " TEXT, " + KEY_ODP+ " TEXT, "+KEY_ODPA +" TEXT, "
				+KEY_ODPB +" TEXT, "+KEY_ODPC +" TEXT, "+KEY_ODPD+" TEXT)";
		db.execSQL(sql);		
		DodajPytania();
	}
	private void DodajPytania()
	{	
			
		Pytania pyt6=new Pytania("Opony wy�cigowe pozbawione bie�nika znane s� jako 'slick'. Dlaczego nie wolno ich u�ywa� na drogach publicznych?", "S� zbyt szybkie", "Szybko si� niszcz�", "Trac� przyczepno�� na drodze", "Nie wida� po niech stopnia zu�ycia", "Nie wida� po niech stopnia zu�ycia");
		this.DodajPytanie(pyt6);
		Pytania pyt7=new Pytania("Kt�ry z japo�skich koncern�w motoryzacyjnych s�ynie z produkcji silnik�w?", "Toyota", "Honda", "Mitsubishi", "Nissan", "Honda");
		this.DodajPytanie(pyt7);
		Pytania pyt8=new Pytania("Stosowany w niekt�rych silnikach intercooler to , jak wskazuje nazwa, urz�dzenie sch�adzaj�ce. Co zostaje sch�odzone?", "Blok silnika", "Powietrze", "Mieszanka paliwowa", "P�yn ch�odniczy", "Powietrze");
		this.DodajPytanie(pyt8);
		Pytania pyt9=new Pytania("Cztery fazy pracy t�oka w silniku spalinowych to ssanie- spr�anie - praca -i co jeszcze?", "Wydech", "Zap�on", "Powr�t", "Suw", "Wydech");
		this.DodajPytanie(pyt9);
		Pytania pyt10=new Pytania("Jaki gaz sprzedawany jest jako LPG?", "Metan", "Propan-butan", "Heksan", "Gaz w�glowy", "Propan-butan");
		this.DodajPytanie(pyt10);
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Usuni�cie starej tabeli je�li istnia�a
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Ponownie stworzenie tabeli
		onCreate(db);
	}
	// Dodawanie nowego pytania
	public void DodajPytanie(Pytania pytanie) {

		ContentValues values = new ContentValues();
		values.put(KEY_PYT, pytanie.ZwrocPytanie()); 
		values.put(KEY_ODP, pytanie.ZwrocPoprOdp());
		values.put(KEY_ODPA, pytanie.ZwrocOdpA());
		values.put(KEY_ODPB, pytanie.ZwrocOdpB());
		values.put(KEY_ODPC, pytanie.ZwrocOdpC());
		values.put(KEY_ODPD, pytanie.ZwrocOdpD());
		// Wybieranie kolumn
		dbase.insert(TABLE_QUEST, null, values);		
	}
	
	public List<Pytania> WszystkiePytania() {
		List<Pytania> ListaPytan = new ArrayList<Pytania>();
		// Wyb�r wszystkich zapyta�
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// Przelatywanie po wszystkich wierszach w tabeli
		if (cursor.moveToFirst()) {
			do {
				Pytania  pytanie = new Pytania();
				pytanie.UstawID(cursor.getInt(0));
				pytanie.UstawPytanie(cursor.getString(1));
				pytanie.UstawPoprOdp(cursor.getString(2));
				pytanie.UstawOdpA(cursor.getString(3));
				pytanie.UstawOdpB(cursor.getString(4));
				pytanie.UstawOdpC(cursor.getString(5));
				pytanie.UstawOdpD(cursor.getString(6));
				ListaPytan.add(pytanie);
			} while (cursor.moveToNext());
		}
		// Zwracanie Listy Pyta�
		return ListaPytan;
	}
	
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
	
	
}

