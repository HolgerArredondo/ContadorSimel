package com.contadorsimel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.contadorsimel.R;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Db {
	public static final String TBL_PERSONAL = "tblPersonal";
	String TBL_JORNADAS = "tblJornadas";
	static final String TBL = "tblContadorSimel";
	Context ourContext;
	dbHelper ourHelper;
	SQLiteDatabase ourDataBase;
	
	String DBNAME = "dbContadorSimel";
	int DBVER = 1;
	int cuSizeBkp = 14;
	int cuSize = 10;
	int gpsSize = 10;


	public static final String TABLE_NAME = "FRUITS";
	public static final String COL_1 = "ID";
	public static final String COL_2 = "ITEM";
	public static final String COL_3 = "QTY";

	public class dbHelper extends SQLiteOpenHelper {

		public dbHelper(Context context) {
			super(context, DBNAME, null, DBVER);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT," + COL_3 + " INTEGER)");


			db.execSQL("CREATE TABLE counter (_id INTEGER PRIMARY KEY AUTOINCREMENT);");
			
			db.execSQL("CREATE TABLE informante (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "contador TEXT);");
			
			
			db.execSQL("CREATE TABLE gps (_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ "idJornada TEXT, " 
					+ "tablet TEXT, "
					+ "app TEXT, "
					+ "clave TEXT, "
					+ "usuario TEXT, " 
					+ "nombre TEXT, " 
					+ "fecha TEXT, "
					+ "latitud TEXT, "
					+ "longitud TEXT, "
					+ "marked TEXT);");
			
			db.execSQL("CREATE TABLE " + TBL_PERSONAL + "(" 
					+ "user TEXT, " 
					+ "nombre TEXT, " 
					+ "sexo TEXT, "
					+ "tipo TEXT, " 
					+ "ciudad TEXT, " 
					+ "psw TEXT);");
			DbInsert.usuarios(db); 
			
			db.execSQL("CREATE TABLE sesion (_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ "user TEXT, " 
					+ "nombre TEXT, " 
					+ "sexo TEXT, "
					+ "tipo TEXT, " 
					+ "ciudad TEXT, "
					+ "psw TEXT, "
					+ "data TEXT, "
					+ "pais TEXT);");

			db.execSQL("INSERT INTO sesion (user,nombre,sexo,tipo,ciudad,psw,data,pais) VALUES ('real','prueba','m','Entrevistador/Enumerador','Piedras Negras','0','', '');");

			db.execSQL("CREATE TABLE tblJornadas ("
					+ "idJornada TEXT NOT NULL, "
					+ "idEmif TEXT NOT NULL, "
					+ "dateProg  TEXT NOT NULL, "
					+ "diaSemanaProg TEXT NOT NULL, "
					+ "flujo TEXT NOT NULL, "
					+ "region TEXT NOT NULL, "
					+ "regionClv TEXT NOT NULL, "
					+ "ciudad TEXT NOT NULL, "
					+ "ciudadClv TEXT NOT NULL, "
					+ "zona TEXT NOT NULL, "
					+ "zonaClv TEXT NOT NULL, "
					+ "punto TEXT NOT NULL, "
					+ "puntoClv TEXT NOT NULL, "
					+ "turno TEXT NOT NULL, "
					+ "turnoClv TEXT NOT NULL, "
					+ "ponderador TEXT, "
					+ "status TEXT NOT NULL);");
			
			DbInsert.jornadas(db);
			

			
			db.execSQL("CREATE TABLE cod ( "
					+ "p TEXT, " 	
					+ "clv TEXT, "
					+ "txt TEXT NOT NULL);");
			DbInsert.cod(db);
			
			db.execSQL("CREATE TABLE " + TBL + " ("
					+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, " 
					
					+ "tablet TEXT DEFAULT '', " 
					+ "version TEXT DEFAULT '', "
					+ "fechaEnvio TEXT DEFAULT '', "
					+ "envio TEXT DEFAULT '', "
					+ "tipoJornada TEXT DEFAULT '', "
					+ "marked TEXT DEFAULT '', "
					+ "clave TEXT DEFAULT '', "
					+ "usuario TEXT DEFAULT '', "
					+ "idJornada TEXT DEFAULT '', "
					+ "iniJornada TEXT DEFAULT '', "
					+ "finJornada TEXT DEFAULT '', "
					+ "genero TEXT DEFAULT '', "
					+ "contador TEXT DEFAULT '', "
					+ "fecha TEXT DEFAULT '');");
		 }
		
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (oldVersion < newVersion) {
		
				db.execSQL("DELETE FROM " + TBL_PERSONAL);
				DbInsert.usuarios(db);
				
				db.execSQL("DELETE FROM " + TBL_JORNADAS);
				DbInsert.jornadas(db);
				
			}
		}
	}
	
	public Db(Context c) {
		ourContext = c;
	}
	
	public Db open() {
		ourHelper = new dbHelper(ourContext);
		ourDataBase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	public boolean loginCheck(String user, String psw) {
		open();
		
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT * FROM " + TBL_PERSONAL
				+ " WHERE user = '" + user + "' AND psw ='" + psw + "'", null);
		if(c.moveToFirst()) {
			
			
			
//			ourDataBase.execSQL("UPDATE sesion SET user='" + user + "', tipo='" + tipo + "', psw='" + psw + "' where _id = 1;");
			ourDataBase.execSQL("UPDATE sesion SET tipo='" + c.getString(c.getColumnIndex("tipo"))
										+ "', nombre ='" +  c.getString(c.getColumnIndex("nombre")) + "', "
										+ "user ='" +  c.getString(c.getColumnIndex("user")) + "', "
										+ "ciudad = '" 
											+ c.getString(c.getColumnIndex("ciudad")) + "', psw='" + psw + "' where _id = 1;");
			close();
			return true;
		}
		close();	
		return false;
	}
	
	public boolean loginCheck2(String psw) {
		open();
		String tipo = "";
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT * FROM " + TBL_PERSONAL
				+ " WHERE  psw ='" + psw + "'", null);
		if(c.moveToFirst()) {
			
			if(psw.equals("10"))
				tipo = "Supervisor";
			else
				tipo = "Entrevistador/Enumerador";
			ourDataBase.execSQL("UPDATE sesion SET tipo='" + tipo + "', psw='" + psw + "' where _id = 1;");
			close();
			return true;
		}
		close();	
		return false;
	}
	
	
	public String getLastExistentField(String field) { 
		open();
		Cursor c = null;
		String value;
		c = ourDataBase.rawQuery("SELECT " + field + " FROM " + TBL
				+ " ORDER BY _id DESC LIMIT 1", null);
		if(!c.moveToFirst()) 
			value = "0";
		else if (c.getString(0) == null ) {
			c = null;
			c = ourDataBase.rawQuery("SELECT " + field + " FROM " + TBL
					+ " WHERE " + field + " IS NOT NULL  ORDER BY _id DESC LIMIT 1", null);
			c.moveToFirst();
			value = c.getString(0);
		}
			else 
				value = c.getString(0);
		close();
		return value;
	}
	
	public void update(String field, String value) {
		open();
		ourDataBase.execSQL("UPDATE " + TBL + " SET " + field + " = '" + value + "' WHERE _id = (SELECT MAX(_id) FROM " + TBL + ")");
		close();
	}
	

	
	
	public void insertNite() throws SQLException{ 
		open();
		
		StringBuilder data = new StringBuilder().append("INSERT INTO ")
				.append(TBL)
				.append(" (_id) VALUES (NULL)");
		
		ourDataBase.execSQL(data.toString());
		close();
	}
	
	public void insertNite2(String query) throws SQLException{ 
		open();
		
	
		
		
		ourDataBase.execSQL(query);
		close();
	}
	
	public String getSesion(String string) {
		open();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT " + string
				+ " FROM sesion LIMIT 1", null);
		c.moveToFirst();
		String name = c.getString(0);
		c.close();
		close();
		return name;
	}
	
	/**
	 * Regresa las ciudades de la region del entrevistador
	 * @param params tag = 0 today, 1 tomorrow, 2 region y 3 tag
	 * @param params tag = 2 region y 3 tag
	 * @return 
	 */
	public ArrayList<String> selectCities(String params[]) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;

		if(params[3].equals("1")){
			
//			selectQuery = "SELECT DISTINCT ciudad FROM " + TBL_JORNADAS
//					+ " WHERE (dateProg = '" + params[0] + "' OR dateProg = '"
//				+ params[1] + "') AND region = '" + params[2]
//				+ "' ORDER BY dateProg ASC";
			
			selectQuery = "SELECT DISTINCT ciudad FROM " + TBL_JORNADAS
					+ " WHERE dateProg = '" + params[0] + "'"
				+ " AND ciudad = '" + params[2]
				+ "' ORDER BY dateProg ASC";

			
			
		}else if(params[3].equals("2")){
//			selectQuery = "SELECT DISTINCT ciudad FROM " + TBL_JORNADAS
//					+ " WHERE region = '" + params[2]
//				+ "' ORDER BY dateProg ASC";
			selectQuery = "SELECT DISTINCT ciudad FROM " + TBL_JORNADAS
					+ " WHERE ciudad = '" + params[2]
				+ "' ORDER BY dateProg ASC";
		}
		
		Log.i("selectQuery", selectQuery);
		
		
		c = ourDataBase.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				labels.add(c.getString(c.getColumnIndex("ciudad")));
//				labels.add(c.getString(c.getColumnIndex("punto")));
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	public ArrayList<String> selectCitiesPrueba(String params[]) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;
		
		selectQuery = "SELECT DISTINCT punto FROM " + TBL_JORNADAS
					+ " WHERE status = 'prueba'"
				+ " LIMIT 1";


		c = ourDataBase.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				labels.add(c.getString(c.getColumnIndex("punto")));
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	public ArrayList<String> selectCitiesByUsuario(String params[]) {
		
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = "";
		Cursor c = null;
		String ciudad = "";
		if(params[2].equals("1")){
			

			
			String[] ciudades = getRow("sesion", "ciudad", "", "").split("_");
			
			if (ciudades.length > 1)
				ciudad = "(ciudad = '" + ciudades[0] 
						+ "' OR ciudad = '" + ciudades[1] + "')";
			else
				ciudad = "ciudad = '" + ciudades[0] + "'";
			
			selectQuery = "SELECT DISTINCT ciudad FROM " + TBL_JORNADAS
					+ " WHERE dateProg = '" + params[0] + "'"
				+ " AND " + ciudad
				+ " ORDER BY dateProg ASC";
			
			Log.i("selectQuery", selectQuery);
			
		}
		else if(params[2].equals("2")){
//		else {

			
			//String ciudad = "";
			
			String[] ciudades = getRow("sesion", "ciudad", "", "").split("_");
			
			if (ciudades.length > 1)
				ciudad = "(ciudad = '" + ciudades[0] 
						+ "' OR ciudad = '" + ciudades[1] + "')";
			else
				ciudad = "ciudad = '" + ciudades[0] + "'";
			
			
			selectQuery = String.format("SELECT DISTINCT ciudad FROM %s WHERE %s ORDER BY dateProg ASC", TBL_JORNADAS, ciudad);

		}
		
		open();
		Log.i("query", selectQuery);
		
		c = ourDataBase.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				labels.add(c.getString(c.getColumnIndex("ciudad")));
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	/**
	 * Selecciona las jornadas segun el tag
	 * @param -key-, today, tomorrow, city, tag, cuidad
	 * @param -if- tag 3, 0 tag, 1 idJornada
	 * @return
	 */
	public ArrayList<String> selectJornadas(String params[]) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;

		if (params[3].equals("1")) {
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS
					+ " WHERE dateProg = '" + params[1] + "' "
					+ " AND ciudad = '" + params[5]
					+ "' AND ciudad = '" + params[5]
					+ "' ORDER BY dateProg ASC";
			
			
			c = ourDataBase.rawQuery(selectQuery, null);
		}
		else if (params[3].equals("2")) {

			selectQuery = "SELECT  * FROM " + TBL_JORNADAS
					+ " WHERE ciudad = '" + params[5] 
							+ "' AND ciudad = '" + params[5]
							+ "' ORDER BY dateProg ASC";
			
			c = ourDataBase.rawQuery(selectQuery, null);
		}
		else if (params[0].equals("3")) {
			
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS + " WHERE idJornada = '" + params[1] + "'";
			c = ourDataBase.rawQuery(selectQuery, null);
		}
		else if (params[3].equals("4")) {
			
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS + " WHERE status = 'prueba'";
			c = ourDataBase.rawQuery(selectQuery, null);
		}

		int _id = c.getColumnIndex("idJornada");
		int dateProg = c.getColumnIndex("dateProg");
		int diaSemanaProg = c.getColumnIndex("diaSemanaProg");
		int flujo = c.getColumnIndex("flujo");
		int zona = c.getColumnIndex("zona");
		int punto = c.getColumnIndex("punto");
		int turno = c.getColumnIndex("turno");

		if (c.moveToFirst()) {
			do {
				String jor = "IDJ[" + c.getString(_id) + "] | "
						+ c.getString(dateProg) + " | "
						+ c.getString(diaSemanaProg) + " | "
						+ c.getString(flujo) + " | " + c.getString(zona)
						+ " | " + c.getString(punto) + " | "
						+ c.getString(turno);// + "\n"
				labels.add(jor);
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	/**
	 * Regresa una lista con el nombre de los campos de la tabla
	 * @return
	 */
	public ArrayList<String> getColumnName() {
		ArrayList<String> columns = new ArrayList<String>();
		Cursor c = ourDataBase.rawQuery("PRAGMA table_info('" + TBL + "')",
				null);
		if (c.moveToFirst()) {
			do {
				columns.add(c.getString(c.getColumnIndex("name")));
			} while (c.moveToNext());
		}
		return columns;
	}
	
	/**
	 * Regresa toda la informacion de la jornada
	 * @param tag saber si es normal o reenvio 
	 * @param idJornada para el reenvio
	 * @return
	 */
	public HashMap<String, List<String>> selectCuestionario(String tag,
			String idJornada) {
		open();
		HashMap<String, List<String>> selectCu = new HashMap<String, List<String>>();

		Cursor c = null;

		if (tag.equals("1")) {
			c = ourDataBase.query(TBL, null, "marked =" + "0", null, null,
					null, null);
		}

		else if (tag.equals("2")) {
			c = ourDataBase.query(TBL, null, "idJornada = '" + idJornada + "'", null,
					null, null, null);
		}
		
		else if (tag.equals("3")) {
			c = ourDataBase.query(TBL, null, "marked =" + "0 limit 1", null, null,
					null, null);
		}
		else if (tag.equals("4")) {
			//c = ourDataBase.query(TBL, null, "marked = '0' ORDER BY _id DESC limit 1", null, null,
				//	null, null);
			c = ourDataBase.rawQuery("SELECT " 
					+ "_id, tablet, version, fechaEnvio, envio, clave, usuario, idJornada, genero, contador, fecha FROM " 
					+ TBL + " WHERE marked = '0' ORDER BY _id DESC LIMIT 1", null);
		}
		
		
//		ArrayList<String> cambio = getColumnName();
		
		//ArrayList<String> cambio = ArrayList<String>
		String[] cambio = {"_id", "tablet", "version", "fechaEnvio", "envio", "clave", "usuario", "idJornada", "genero", "contador", "fecha"};
		
		List<String> l0 = new ArrayList<String>();
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		List<String> l3 = new ArrayList<String>();
		List<String> l5 = new ArrayList<String>();
		List<String> l6 = new ArrayList<String>();
		List<String> l7 = new ArrayList<String>();
		List<String> l8 = new ArrayList<String>();
		
		String r = "";
		
	//	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		if (c.moveToFirst()) {

			do {
				l1.add(c.getString(c.getColumnIndex("_id")));
//				l2.add(c.getString(c.getColumnIndex("tipoFolio")));
				l3.add(c.getString(c.getColumnIndex("idJornada")));
				l5.add(c.getString(c.getColumnIndex("clave")));
//				l6.add(c.getString(c.getColumnIndex("ciudad")));
				l7.add(c.getString(c.getColumnIndex("fecha")));
				r = r + "INSERT INTO " + ourContext.getResources().getString(R.string.appContador)
						+ " (_id, tablet, version, fechaEnvio, envio, clave, usuario, idJornada, genero, contador, fecha) " 
						+ "VALUES (NULL,";
				
				for (int j = 1; j <= cuSize - 1; j++)
//				for (int j = 1; j < 10; j++)
					r = r + "\'"
							//+ c.getString(c.getColumnIndex(cambio.get(j)))
							+ c.getString(c.getColumnIndex(cambio[j]))
							+ "\'" + ",";

				r = r + "\'"
//						+ c.getString(c.getColumnIndex(cambio.get(cuSize)))
						+ c.getString(c.getColumnIndex(cambio[cuSize]))
//						+ c.getString(c.getColumnIndex(cambio.get(11)))
						+ "');\n";
				l0.add(r);
				r = "";

			} while (c.moveToNext());

		}
		
		l8.add(ourContext.getResources().getString(R.string.appContador));

		selectCu.put("resultado", l0);
		selectCu.put("id", l1);
//		selectCu.put("tipoFolio", l2);
		selectCu.put("idJornada", l3);
		selectCu.put("clave", l5);
//		selectCu.put("ciudad", l6);
		selectCu.put("fecha", l7);
		selectCu.put("app", l8);
		close();
		return selectCu;
	}
	
	public void setMarked(String id) throws SQLException {
		open();
		ourDataBase
		.execSQL("UPDATE " + TBL + " SET marked = '1' WHERE _id = " + id  );
		close();
//		open();
//		ContentValues cvUpdate = new ContentValues();
//		cvUpdate.put("marked", 1);
//		ourDataBase.update(TBL, cvUpdate, "_id = ?", new String[]{id});
//		close();
	}
	
	public ArrayList<String> getJornadasBkp() {
		open();
		ArrayList<String> idJornada = new ArrayList<String>();
		Cursor c = null;
		
		c = ourDataBase.rawQuery("SELECT DISTINCT idJornada FROM " + TBL + " WHERE marked = '0' ORDER BY _id DESC", null);
		
		if (c.moveToFirst()) {
			do {
				idJornada.add(c.getString(0));

			} while (c.moveToNext());
		}
		close();
		return idJornada;
	}
	
	public int getCountTbl() {
		open();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT COUNT(*) FROM " + TBL + " WHERE marked = '0'", null);
		c.moveToFirst();
		int name = Integer.parseInt(c.getString(0));
		close();
		return name;
	}
	
	public ArrayList<String> selectRespaldo(String idJornada) {
		open();
		Cursor c = null;

		c = ourDataBase.query(TBL, null, "idJornada = '" + idJornada + "'", null,
					null, null, null);
		
		ArrayList<String> cambio = getColumnName();
		ArrayList<String> l0 = new ArrayList<String>();
		
		String r = "";

		if (c.moveToFirst()) {

			do {
				r = r + "INSERT INTO table VALUES (NULL,";
				for (int j = 1; j <= cuSizeBkp - 1; j++)
					r = r + "'"
							+ c.getString(c.getColumnIndex(cambio.get(j)))
							+ "'" + ",";

				r = r + "'"
						+ c.getString(c.getColumnIndex(cambio.get(cuSizeBkp)))
						+ "');\n";
				l0.add(r);
				r = "";

			} while (c.moveToNext());

		}
		close();
		return l0;
	}
	
	public void insertIdCounter() throws SQLException {
		open();
		ourDataBase.execSQL("INSERT INTO counter (_id) VALUES (null);");
		close();
	}
	
	public void queryUpdate(String query){
		open();
		ourDataBase
		.execSQL("UPDATE " + TBL + " SET " + query + " WHERE _id = (SELECT MAX(_id) FROM " + TBL + ")");
		close();
	}
	
	public String getLastIdCounter() {
		open();
		Cursor c = null;
		c = ourDataBase
				.rawQuery(
						"SELECT * FROM counter WHERE _id = (SELECT MAX(_id) FROM counter)",
						null);
		c.moveToFirst();
		String name = c.getString(0);
		c.close();
		close();
		return name;
	}
	
	public String selectPersonal(String field, String psw) {
		open();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT " + field + " FROM "
				+ TBL_PERSONAL + " WHERE psw = '" + psw + "'", null);
		c.moveToFirst();
		String name = c.getString(0);
		c.close();
		close();
		return name;
	}
	
	public String getJornadas(String field, String idJornada) {
		open();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT " + field
				+ " FROM " + TBL_JORNADAS + " WHERE idJornada = '" + idJornada + "'", null);
		c.moveToFirst();
		String name = c.getString(0);
		c.close();
		close();
		return name;
	}
	
	public String[] getInfoJornadas(String idJornada) {
		open();
		String selectQuery = "SELECT  * FROM " + TBL_JORNADAS
				+ " WHERE idJornada = '" + idJornada
				+ "' ORDER BY dateProg ASC";

		Cursor c = ourDataBase.rawQuery(selectQuery, null);

		c.moveToFirst();

		String[] labels = { c.getString(c.getColumnIndex("dateProg")),
				c.getString(c.getColumnIndex("diaSemanaProg")),
				c.getString(c.getColumnIndex("ciudadClv")),
				c.getString(c.getColumnIndex("ciudadClv")),
				c.getString(c.getColumnIndex("zonaClv")),
				c.getString(c.getColumnIndex("puntoClv")),
				c.getString(c.getColumnIndex("turnoClv")),
				c.getString(c.getColumnIndex("ponderador"))
				};
		close();
		return labels;
	}
	
	public void jump(String start, String finish, String value) {
		open();
		String query = "UPDATE " + TBL + " SET ";
		ArrayList<String> cambio = getColumnName();
		
		int s = getIndexTbl(start);
		int f = getIndexTbl(finish);
		
		for (; s < f; s++)
			query += cambio.get(s) + "='" + value + "', ";
		
		query += cambio.get(f) + "='" + value
				+ "' WHERE _id = (SELECT MAX(_id) FROM " + TBL + ");";
		ourDataBase.execSQL(query);
		close();
	}
	
	public int getIndexTbl(String field) {
		int name = 0;
		boolean find = true;
		Cursor c = ourDataBase.rawQuery("PRAGMA table_info('" + TBL + "')",
				null);
		if (c.moveToFirst()) {
			do {
				if (c.getString(c.getColumnIndex("name")).equals(field)) {
					 name = Integer
							.parseInt(c.getString(c.getColumnIndex("cid")));
					 find = false;
					}
				else
					c.moveToNext();
			} while (find);
		}
		return name;
	}
	
	
		public ArrayList<String> getColumnName2(String tbl) {
			open();
			ArrayList<String> columns = new ArrayList<String>();
			Cursor c = ourDataBase.rawQuery("PRAGMA table_info('" + tbl + "')",
					null);
			if (c.moveToFirst()) {
				do {
					columns.add(c.getString(c.getColumnIndex("name")));
				} while (c.moveToNext());
			}
			close();
			return columns;
		}
	
	
	
	public ArrayList<String> getRowEdo(String table, String field, String whereField) {
		
		open();
		
		ArrayList<String> label = new ArrayList<String>();
		String selectQuery = "";
		Cursor c = null;
		
		selectQuery = "SELECT DISTINCT " + field + " FROM " + table;
				
		
//		selectQuery = "SELECT count(*) FROM tblMexico";
		
		c = ourDataBase.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()) {
			do {
				label.add(c.getString(0));
			} while(c.moveToNext());
		}
		
		
//		if (c.moveToFirst()) {
////			int columnIndex = c.getColumnIndex(field);
////			Log.i("desde DB", "entro IF");
//			label = c.getString(0);
//			Log.i("desde DB", "YA QUEDO");
//			
//		}else {
//			Log.i("desde DB", "TIRANDO SHINE");
//		}
		
		close();
		return label;
	}
	
	public String getClv(String table, String field, String whereField) {
		
		open();
		
		String label = "";
		String selectQuery = "";
		Cursor c = null;
		
		selectQuery = "SELECT " + field + " FROM " + table 
						+ " WHERE " + whereField + " LIMIT 1";
		
		c = ourDataBase.rawQuery(selectQuery, null);
		
		if (c.moveToFirst()) 
				label = c.getString(0);
			
		close();
		return label;
	}
	
	public ArrayList<String> getRows(String table, String field,
			String whereField, String value) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;
		if (whereField.length() > 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table
					+ " WHERE " + whereField + " = '" + value + "' ORDER BY " + field;
		else if (whereField.length() == 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table + " ORDER BY " + field;
		
		c = ourDataBase.rawQuery(selectQuery, null);
		int columnIndex = c.getColumnIndex(field);
		if (c.moveToFirst()) {
			do {
				labels.add(c.getString(columnIndex));
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	public String[] getRowsString(String table, String field, String whereField, String value) {
		open();
		String selectQuery = null;
		String[] labels = null;
		Cursor c = null;
		int i = 0;
		if (whereField.length() > 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table
					+ " WHERE " + whereField + " = '" + value + "' ORDER BY " + field;
		else if (whereField.length() == 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table + " ORDER BY " + field;
		
		c = ourDataBase.rawQuery(selectQuery, null);
		int columnIndex = c.getColumnIndex(field);
		
		if (c.moveToFirst()) {
			labels = new String[c.getCount()];
			do {
				labels[i] = c.getString(columnIndex);
				i++;
			} while (c.moveToNext());
		}
		
		close();
		return labels;
	}
	
	public  ArrayList<String> getRowsLoc(String table, String field, String whereField) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;
		
		selectQuery = "SELECT DISTINCT " + field + " FROM " + table
					+ " WHERE " + whereField;
		
		
		
		c = ourDataBase.rawQuery(selectQuery, null);
	
		int columnIndex = c.getColumnIndex(field);
		
		if (c.moveToFirst()) {
			do {
				labels.add(c.getString(columnIndex));
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	public String getRow(String table, String field, String whereField,
			String value) {
		open();
		String label = "";
		String selectQuery = null;
		Cursor c = null;
		if (whereField.length() > 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table
					+ " WHERE " + whereField + " = '" + value + "'";
		else if (whereField.length() == 0)
			selectQuery = "SELECT DISTINCT " + field + " FROM " + table;
		
		
		
		c = ourDataBase.rawQuery(selectQuery, null);
		int columnIndex = c.getColumnIndex(field);
		if (c.moveToFirst()) {
			label = c.getString(columnIndex);
		} else
			label = "0";
		close();
		return label;
	}
	
	public String getRowMun(String table, String field, String whereField) {
		open();
		String label = "0";
		String selectQuery = null;
		Cursor c = null;
		
		selectQuery = "SELECT DISTINCT " + field + " FROM " + table
					+ " WHERE " + whereField;
		
		
		
		c = ourDataBase.rawQuery(selectQuery, null);
		int columnIndex = c.getColumnIndex(field);
		if (c.moveToFirst()) {
			label = c.getString(columnIndex);
		}
		close();
		return label;
	}
	
	public String getCod(String whereField) {
		open();
		String label = "0";
		String selectQuery = null;
		Cursor c = null;
		
		selectQuery = "SELECT DISTINCT clv FROM cod WHERE " + whereField;
		
		c = ourDataBase.rawQuery(selectQuery, null);
		int columnIndex = c.getColumnIndex("clv");
		if (c.moveToFirst()) {
			label = c.getString(columnIndex);
		}
		close();
		return label;
	}
	
	public String getField(String field) {
		open();
		Cursor c = null;
		
		Log.i("query", "SELECT " + field + " FROM " + TBL
				+ " WHERE _id = (SELECT MAX(_id) FROM " + TBL + ")");
		
		
		c = ourDataBase.rawQuery("SELECT " + field + " FROM " + TBL
				+ " WHERE _id = (SELECT MAX(_id) FROM " + TBL + ")", null);
		c.moveToFirst();
		close();
		return c.getString(0);
	}
	
	public ArrayList<String> selecLastClaves(String field, String idJornada) {
		open();
		ArrayList<String> claves = new ArrayList<String>();
		Cursor c = null;
		c = ourDataBase
				.rawQuery(
						"SELECT DISTINCT " + field + " FROM " + TBL + " WHERE idJornada = '"
								+ idJornada + "'", null);
		// cur.moveToFirst();
		if (c.moveToFirst()) {
			do {
				claves.add(c.getString(0));
			} while (c.moveToNext());
		}
		c.close();
		close();
		return claves;
	}
	
	public String selectFolios(String idJornada, String clave, String tipoFolio, String status) {
		open();
		String name = "";
		try {
			Cursor c = null;

			c = ourDataBase.rawQuery(
					"SELECT COUNT(*) FROM " + TBL + " WHERE tipoFolio = '"+ tipoFolio +"' and 	idJornada = '"
							+ idJornada + "' and status = '" + status + "' and clave = "
							+ clave, null);
			c.moveToFirst();
			name = c.getString(0);
			c.close();
			close();
			return name;
		} catch (Exception e) {
			return name = "0";
		}
	}
	
	public int getCountIdJornada2(String idJornada) {
		open();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT COUNT(*) FROM " + TBL
					+ " WHERE idJornada = '" + idJornada + "'", null);
		c.moveToFirst();
		int name = Integer.parseInt(c.getString(0));
		
		close();
		return name;
	}
	
	public ArrayList<String> getId(String idJornada) {
		open();
		ArrayList<String> id = new ArrayList<String>();
		Cursor c = null;
		c = ourDataBase.rawQuery("SELECT _id FROM " + TBL
				+ " WHERE idJornada = '" + idJornada + "'", null);
		
		if (c.moveToFirst()) {
			do {
				id.add(c.getString(0));

			} while (c.moveToNext());
		}
		close();
		return id;
	}	
	
	public void setEnvio2(String idJornada, String value, String id, String size) {
		open();
		ourDataBase.execSQL("UPDATE " + TBL + " SET envio = '" + value + " de "
				+ size + "' WHERE idJornada = '" + idJornada + "' and _id = "
				+ id + ";");
		close();
	}
	
	public void setTotPerJor(String idJornada, String value) {
		open();
		ourDataBase.execSQL("UPDATE " + TBL + " SET totPerJor = '" + value
				+ "' WHERE idJornada = '" + idJornada
				+ "' AND totPerJor IS NULL;");
		close();
	}
	public ArrayList<String> selectJornadasPrueba(String[] params) {
		open();
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;

		selectQuery = "SELECT  * FROM " + TBL_JORNADAS + " WHERE idJornada = '" + params[1] + "'";
			c = ourDataBase.rawQuery(selectQuery, null);
		

		int _id = c.getColumnIndex("idJornada");
		int dateProg = c.getColumnIndex("dateProg");
		int diaSemanaProg = c.getColumnIndex("diaSemanaProg");
		int flujo = c.getColumnIndex("flujo");
		int zona = c.getColumnIndex("zona");
		int punto = c.getColumnIndex("punto");
		int turno = c.getColumnIndex("turno");

		if (c.moveToFirst()) {
			do {
				String jor = "IDJ[" + c.getString(_id) + "] | "
						+ c.getString(dateProg) + " | "
						+ c.getString(diaSemanaProg) + " | "
						+ c.getString(flujo) + " | " + c.getString(zona)
						+ " | " + c.getString(punto) + " | "
						+ c.getString(turno);// + "\n"
				labels.add(jor);
			} while (c.moveToNext());
		}
		close();
		return labels;
	}
	
	public List<String> getJornadas() {
		open();
		List<String> labels = new ArrayList<String>();
		List<String> claves = new ArrayList<String>();

		Cursor c = null;
		c = ourDataBase.rawQuery(
				"SELECT DISTINCT idJornada FROM " + TBL , null);

		if (c.moveToFirst()) {
			do {
				String jor = c.getString(0);
				claves.add(jor);
			} while (c.moveToNext());
		}

		c.close();
		
		for (int i = 0; i < claves.size(); i++) 
			labels.add(selectJornadas("", "", claves.get(i), "3").get(0));
		close();
		return labels;
	}
	
	public ArrayList<String> selectJornadas(String today, String tomorrow,
			String ciudad, String tag) {
		ArrayList<String> labels = new ArrayList<String>();
		String selectQuery = null;
		Cursor c = null;

		if (tag.equals("1")) {
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS
					+ " WHERE (dateProg = '" + today + "' OR dateProg = '"
					+ tomorrow + "') AND ciudad = '" + ciudad
					+ "' ORDER BY dateProg ASC";
			c = ourDataBase.rawQuery(selectQuery, null);
		}
		else if (tag.equals("2")) {
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS
					+ " WHERE ciudad = '" + ciudad + "' ORDER BY dateProg ASC";
			c = ourDataBase.rawQuery(selectQuery, null);
		}
		else if (tag.equals("3")) {
			selectQuery = "SELECT  * FROM " + TBL_JORNADAS + " WHERE idJornada = '" + ciudad + "'";
			c = ourDataBase.rawQuery(selectQuery, null);
		}

		int _id = c.getColumnIndex("idJornada");
		int dateProg = c.getColumnIndex("dateProg");
		int diaSemanaProg = c.getColumnIndex("diaSemanaProg");
		int flujo = c.getColumnIndex("flujo");
		int zona = c.getColumnIndex("zona");
		int punto = c.getColumnIndex("punto");
		int turno = c.getColumnIndex("turno");

		if (c.moveToFirst()) {
			do {
				String jor = "IDJ[" + c.getString(_id) + "] | "
						+ c.getString(dateProg) + " | "
						+ c.getString(diaSemanaProg) + " | "
						+ c.getString(flujo) + " | " + c.getString(zona)
						+ " | " + c.getString(punto) + " | "
						+ c.getString(turno);// + "\n"
				labels.add(jor);
			} while (c.moveToNext());
		}
		return labels;
	}
	
	public ArrayList<String> getRightFinca(String edo, String mun, String finca) {
		open();
		ArrayList<String> data = new ArrayList<String>();
		Cursor c = null;
		
		c = ourDataBase
				.rawQuery(
						"SELECT clvMun, clvFinca FROM tblFincas WHERE edo = '"
								+ edo + "' AND mun = '" + mun 
								+ "' AND finca ='" + finca + "'", null);
		
		if (c.moveToFirst()) {
			do {
				data.add(c.getString(0));
				data.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();
		close();
		return data;
	}
	
	public int getJornadasGps() {
		open();
		Cursor c = null;
		
		c = ourDataBase.rawQuery("SELECT COUNT(*) FROM gps WHERE marked = '0'", null);
		c.moveToFirst();
		int total = Integer.parseInt(c.getString(0));
		close();
		return total;
	}
	

	public HashMap<String, List<String>> selectCuestionarioGps() {
		open();
		HashMap<String, List<String>> selectCu = new HashMap<String, List<String>>();

		Cursor c = null;

		c = ourDataBase.query("gps", null, "marked = '0' ORDER BY _id DESC limit 1", null, null,
					null, null);
		
		
		
		ArrayList<String> cambio = getColumnNameGps();
		
		List<String> l0 = new ArrayList<String>();
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		
		String r = "";
		
	//	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		if (c.moveToFirst()) {

			do {
				
				l1.add(c.getString(c.getColumnIndex("_id")));
				
				
				r = r + "INSERT INTO table VALUES (NULL,";
				for (int j = 1; j <= gpsSize - 1; j++)
					r = r + "'"
							+ c.getString(c.getColumnIndex(cambio.get(j)))
							+ "'" + ",";

				r = r + "'"
						+ c.getString(c.getColumnIndex(cambio.get(gpsSize)))
						+ "');\n";
				l0.add(r);
				r = "";

			} while (c.moveToNext());

		}

		l2.add(ourContext.getResources().getString(R.string.appContador));
		
		selectCu.put("resultado", l0);
		selectCu.put("id", l1);
		selectCu.put("app", l2);
		
		close();
		return selectCu;
	}
	
	public ArrayList<String> getColumnNameGps() {
		ArrayList<String> columns = new ArrayList<String>();
		Cursor c = ourDataBase.rawQuery("PRAGMA table_info('gps')",
				null);
		if (c.moveToFirst()) {
			do {
				columns.add(c.getString(c.getColumnIndex("name")));
				Log.i("name", "" + c.getString(c.getColumnIndex("name")));
			} while (c.moveToNext());
		}
		return columns;
	}
	
	public void setMarkedGps(String id) throws SQLException {
		open();
		ourDataBase
		.execSQL("UPDATE gps SET marked = '1' WHERE _id = " + id  );
		close();
	}
	
	public void gps(String query) {
		open();
		ourDataBase.execSQL("INSERT INTO gps (_id, idJornada, tablet, app, clave, usuario, nombre, fecha, latitud, longitud, marked) VALUES (NULL," + query + ");");
		close();
	}
	
	public void insertInformante(String value) throws SQLException {
		open();
		ourDataBase.execSQL("INSERT INTO informante (_id, contador) VALUES (null, '" +
				value + "');");
		
		Log.i("insert", "INSERT INTO informante (_id, contador) VALUES (null, '" +
				value + "');");
		close();
	}
	
	public void queryInsert(String query) {
		open();
		ourDataBase.execSQL(query);
		close();
	}
	
	
	
	public void updateRow(String table, String field, String whereField,
			String value) {
		open();
		ourDataBase.execSQL("UPDATE " + table + " SET " + field + "='" + value 
				+ "' where _id = 1;");
		close();
	}
	
	public void jornadaExtra(String query) {
		open();
		ourDataBase.execSQL(query);
		close();
	}



	public boolean insertData(String item, int qty){

		open();
		ContentValues values = new ContentValues();

		values.put(COL_2,item);
		values.put(COL_3,qty);

		long res = ourDataBase.insert(TABLE_NAME,null,values);

		close();

        return res != -1;


	}
}
