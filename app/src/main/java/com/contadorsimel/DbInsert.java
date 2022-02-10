package com.contadorsimel;

import android.database.sqlite.SQLiteDatabase;

public class DbInsert {
	public static void usuarios(SQLiteDatabase db) {



		/**
		 * Claves pruebas de supervisor y entrevistador
		 * */
		db.execSQL("INSERT INTO tblPersonal VALUES ('prueba','Prueba','m','Supervisor','Tecun Uman','10');");
		db.execSQL("INSERT INTO tblPersonal  VALUES ('prueba','Prueba La Mesilla','f','Entrevistador/Enumerador','La Mesilla','100');");
		/**
		 * Fin claves de supervisor y entrevistador
		 * */

		/*
		 * Tecun Uman
		 */

		db.execSQL("INSERT INTO tblPersonal VALUES ('juan','Juan Hernandez','','Supervisor','Tecun Uman','911001');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('claudia','Claudia Bautista','','Encuestador/Enumerador','Tecun Uman_El Carmen','913003');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('sulema','Sulema Robles','','Encuestador/Enumerador','Tecun Uman_El Carmen','913004');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('thelma','Thelma Castaneda','','Encuestador/Enumerador','Tecun Uman_El Carmen','913005');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('juan','Juan Hernandez','','Encuestador/Enumerador','Tecun Uman_El Carmen','913009');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('paola','Paola Marisela Frutos Ramos','','Encuestador/Enumerador','Tecun Uman_El Carmen','913010');");

//		db.execSQL("INSERT INTO tblPersonal VALUES ('glendi','Glendi Rodas','','Encuestador/Enumerador','Tecun Uman_El Carmen','913001');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('vanessa','Vanessa Fuentes','','Encuestador/Enumerador','Tecun Uman_El Carmen','913002');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('henry','Henry Puerto','','Encuestador/Enumerador','Tecun Uman_El Carmen','913006');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('esperanza','Esperanza Bautista','','Encuestador/Enumerador','Tecun Uman_El Carmen','913007');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('isabel','Isabel Preciado','','Encuestador/Enumerador','Tecun Uman_El Carmen','913008');");

		//holger
		db.execSQL("INSERT INTO tblPersonal VALUES ('h','Paola Marisela Frutos Ramos','','Encuestador/Enumerador','Tecun Uman_El Carmen','1');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('h','Paola Marisela Frutos Ramos','','Encuestador/Enumerador','La Mesilla','2');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('h','Paola Marisela Frutos Ramos','','Encuestador/Enumerador','El Carmen','3');");


		//2019-10-31
//		db.execSQL("INSERT INTO tblPersonal VALUES ('wendymc','Wendy Janeth Matias Cuyuch','','Encuestador/Enumerador','Tecun Uman_El Carmen','913011');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('dulcelc','Dulce Alejandra de León Castillo','','Encuestador/Enumerador','Tecun Uman_El Carmen','913012');");
		//2019-11-04
//		db.execSQL("INSERT INTO tblPersonal VALUES ('gloriahe','Gloria Amanda Hernández Escobar','','Encuestador/Enumerador','Tecun Uman_El Carmen','913013');");


		//2019-12-30
//		db.execSQL("INSERT INTO tblPersonal VALUES ('victorm','Víctor Mérida','','Encuestador/Enumerador','Tecun Uman_El Carmen','913014');");

		//2020-01-29
//		db.execSQL("INSERT INTO tblPersonal VALUES ('julissapj','Julissa Elizabeth Pérez Juárez','','Encuestador/Enumerador','Tecun Uman_El Carmen','913015');");

		//db.execSQL("INSERT INTO tblPersonal VALUES ('h','Paola Marisela Frutos Ramos','','Encuestador/Enumerador','Tecun Uman_El Carmen','123');");


		//Genericos Tecun Uman
		db.execSQL("INSERT INTO tblPersonal VALUES ('tecununo','Tecun Generico Uno','','Encuestador/Enumerador','Tecun Uman_El Carmen','913997');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('tecundos','Tecun Generico Dos','','Encuestador/Enumerador','Tecun Uman_El Carmen','913998');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('tecuntres','Tecun Generico Tres','','Encuestador/Enumerador','Tecun Uman_El Carmen','913999');");

		/*
		 * El Carmen
		 */

		db.execSQL("INSERT INTO tblPersonal VALUES ('orfa','Orfa de Leon','','Supervisor','El Carmen','921001');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('emiliro','Emili Roxana Recinos Ochoa','','Encuestador/Enumerador','El Carmen_Tecun Uman','923015');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('marlonaa','Marlon Angel Alvarez Agueda','','Encuestador/Enumerador','El Carmen_Tecun Uman','923014');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('marleni','Marleni Chilel','','Encuestador/Enumerador','El Carmen_Tecun Uman','923005');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('orfa','Orfa de Leon','','Encuestador/Enumerador','El Carmen_Tecun Uman','923008');");


//		db.execSQL("INSERT INTO tblPersonal VALUES ('madai','Madai Calderon','','Encuestador/Enumerador','El Carmen_Tecun Uman','923001');");
//
//		//db.execSQL("INSERT INTO tblPersonal VALUES ('vivian','Vivian Mazariegos','','Encuestador/Enumerador','El Carmen_Tecun Uman','923003');");
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('marveli','Marveli Roblero','','Encuestador/Enumerador','El Carmen_Tecun Uman','923006');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('marissa','Marissa Monroy','','Encuestador/Enumerador','El Carmen_Tecun Uman','923007');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('laura','Laura Vanessa Chavez Gomez','','Encuestador/Enumerador','El Carmen_Tecun Uman','923009');");
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('sandra','Sandra Velazquez','','Encuestador/Enumerador','El Carmen_Tecun Uman','923011');");
//
//		//2019-10-31
//		db.execSQL("INSERT INTO tblPersonal VALUES ('evelynda','Evelyn Xiomara Diaz Aguilar','','Encuestador/Enumerador','El Carmen_Tecun Uman','923012');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('byrongn','Byron Alfonso Gómez Nolasco','','Encuestador/Enumerador','El Carmen_Tecun Uman','923013');");



		//Genericos El Carmen
		db.execSQL("INSERT INTO tblPersonal VALUES ('carmenuno','Carmen Generico Uno','','Encuestador/Enumerador','El Carmen_Tecun Uman','923997');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('carmendos','Carmen Generico Dos','','Encuestador/Enumerador','El Carmen_Tecun Uman','923998');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('carmentres','Carmen Generico Tres','','Encuestador/Enumerador','El Carmen_Tecun Uman','923999');");

		/*
		 * La Mesilla
		 */
		db.execSQL("INSERT INTO tblPersonal VALUES ('jassonne','Jasson Napoleón Escobar','','Supervisor','La Mesilla','931002');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('jasson','Jasson Napoleon Escobar Campos','','Encuestador/Enumerador','La Mesilla','933010');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('hugolc','Hugo Leonel de León Capriel','','Encuestador/Enumerador','La Mesilla','933019');");
		//2021-0423
		db.execSQL("INSERT INTO tblPersonal VALUES ('fernandoma','Fernando José Martínez Alvarado','','Encuestador/Enumerador','La Mesilla','933021');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('kimberlyncg','Kimberlyn Mairany Crisóstomo Gómez','','Encuestador/Enumerador','La Mesilla','933022');");

//		db.execSQL("INSERT INTO tblPersonal VALUES ('eunice','Eunice Jocabed Gonzalez Merida','','Supervisor','La Mesilla','931001');");
//
//
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('heidi',' Heidi Corina Gonzalez Merida','','Encuestador/Enumerador','La Mesilla','933001');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('eliceo','Eliceo David Roblero Perez','','Encuestador/Enumerador','La Mesilla','933002');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('jeyson','Jeyson Oswaldo Hernandez Perez','','Encuestador/Enumerador','La Mesilla','933003');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('reynaldo','Reynaldo  Villatoro Monzon','','Encuestador/Enumerador','La Mesilla','933004');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('yesica','Yesica Paola Portillo Perez','','Encuestador/Enumerador','La Mesilla','933005');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('kenia','Kenia Damaris Villatoro Hidalgo','','Encuestador/Enumerador','La Mesilla','933006');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('eunice','Eunice Jocabed Gonzalez Merida','','Encuestador/Enumerador','La Mesilla','933007');");
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('evelin','Evelin Elizabeth Hidalgo Herrera','','Encuestador/Enumerador','La Mesilla','933008');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('juan','Juan Manuel Funes Palacios','','Encuestador/Enumerador','La Mesilla','933009');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('jasson','Jasson Napoleon Escobar Campos','','Encuestador/Enumerador','La Mesilla','933010');");
//
//		/*Nuevos Agregados 2017-04-28  by: Frando*/
//		db.execSQL("INSERT INTO tblPersonal VALUES ('mirna','Mirna Isolina Tello Cruz','','Encuestador/Enumerador','La Mesilla','933011');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('miguel','Miguel Angel Sales Vasquez','','Encuestador/Enumerador','La Mesilla','933012');");
//		//db.execSQL("INSERT INTO tblPersonal VALUES ('erick','Erick Josue Cobon','','Encuestador/Enumerador','La Mesilla','933013');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('jenifer','Jenifer Isabel Calito Herrera','','Encuestador/Enumerador','La Mesilla','933014');");
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('boris','Boris Alexander Tomas Sosa','','Encuestador/Enumerador','La Mesilla','933015');");
//
//		db.execSQL("INSERT INTO tblPersonal VALUES ('cesaraa','Cesar Augusto Alarcón Argueta','','Encuestador/Enumerador','La Mesilla','933016');");
//
//		//2019-10-31
//		db.execSQL("INSERT INTO tblPersonal VALUES ('sandrayl','Sandra Yanneth López Gómez.','','Encuestador/Enumerador','La Mesilla','933017');");
//		db.execSQL("INSERT INTO tblPersonal VALUES ('hendeldm','Hendel Estuardo Domingo Martínez','','Encuestador/Enumerador','La Mesilla','933018');");
//
//
//		//2020-03-04
//		db.execSQL("INSERT INTO tblPersonal VALUES ('mibzarms','Mibzar Abisai Martínez Samayoa','','Encuestador/Enumerador','La Mesilla','933020');");


		//Genericos la mesilla
		db.execSQL("INSERT INTO tblPersonal VALUES ('mesillauno','Mesilla Generico Uno','','Encuestador/Enumerador','La Mesilla','933997');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('mesillados','Mesilla Generico Dos','','Encuestador/Enumerador','La Mesilla','933998');");
		db.execSQL("INSERT INTO tblPersonal VALUES ('mesillatres','Mesilla Generico Tres','','Encuestador/Enumerador','La Mesilla','933999');");



	}

	public static void jornadas(SQLiteDatabase db) {

		db.execSQL("INSERT INTO tblJornadas VALUES ('0', '0', '0000-00-00', 'Lunes', 'Norte-Sur', 'prueba', '1', 'prueba', '1', 'prueba', '1', 'prueba', '1', '00 - 00', '1', '1', 'prueba');");

/**
 * SIMEL
 */
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013201','010521','2021-05-01','6','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013201','010521','2021-05-03','1','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022201','010521','2021-05-03','1','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012201','010521','2021-05-05','3','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012201','010521','2021-05-05','3','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013201','010521','2021-05-06','4','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192023201','010521','2021-05-06','4','','','','El Carmen','92','','','Comedor Mary','2','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012202','010521','2021-05-07','5','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012202','010521','2021-05-08','6','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033201','010521','2021-05-09','7','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023201','010521','2021-05-09','7','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033202','010521','2021-05-11','2','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013202','010521','2021-05-11','2','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013202','010521','2021-05-12','3','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013203','010521','2021-05-12','3','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013202','010521','2021-05-13','4','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012203','010521','2021-05-13','4','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022202','010521','2021-05-14','5','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013203','010521','2021-05-15','6','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192012201','010521','2021-05-15','6','','','','El Carmen','92','','','Caseta Pepsi','1','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023202','010521','2021-05-15','6','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012203','010521','2021-05-16','7','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013204','010521','2021-05-16','7','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192012202','010521','2021-05-17','1','','','','El Carmen','92','','','Caseta Pepsi','1','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012204','010521','2021-05-17','1','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013204','010521','2021-05-18','2','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192022201','010521','2021-05-18','2','','','','El Carmen','92','','','Comedor Mary','2','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022203','010521','2021-05-18','2','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191023201','010521','2021-05-19','3','','','','Tecun Uman','91','','','Pullman - Parrillas','2','12:00-17:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033203','010521','2021-05-19','3','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191021201','010521','2021-05-21','5','','','','Tecun Uman','91','','','Pullman - Parrillas','2','00:00-05:59','1','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033204','010521','2021-05-22','6','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012205','010521','2021-05-22','6','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013205','010521','2021-05-23','7','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033205','010521','2021-05-23','7','','','','El Carmen','92','','','Tienda Iris','3','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022204','010521','2021-05-23','7','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191022201','010521','2021-05-24','1','','','','Tecun Uman','91','','','Pullman - Parrillas','2','06:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033206','010521','2021-05-24','1','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013205','010521','2021-05-24','1','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012204','010521','2021-05-25','2','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033207','010521','2021-05-26','3','','','','El Carmen','92','','','Tienda Iris','3','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012205','010521','2021-05-27','4','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023203','010521','2021-05-27','4','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033208','010521','2021-05-28','5','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013206','010521','2021-05-29','6','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013203','010521','2021-05-29','6','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022205','010521','2021-05-29','6','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012206','010521','2021-05-30','7','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023204','010521','2021-05-31','1','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033209','010621','2021-06-01','2','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191022202','010621','2021-06-02','3','','','','Tecun Uman','91','','','Pullman - Parrillas','2','06:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013204','010621','2021-06-02','3','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023205','010621','2021-06-02','3','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013207','010621','2021-06-03','4','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192023202','010621','2021-06-03','4','','','','El Carmen','92','','','Comedor Mary','2','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013206','010621','2021-06-03','4','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013205','010621','2021-06-04','5','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013207','010621','2021-06-04','5','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013208','010621','2021-06-05','6','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192022202','010621','2021-06-06','7','','','','El Carmen','92','','','Comedor Mary','2','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023206','010621','2021-06-08','2','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022206','010621','2021-06-09','3','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191023202','010621','2021-06-10','4','','','','Tecun Uman','91','','','Pullman - Parrillas','2','12:00-17:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033210','010621','2021-06-10','4','','','','El Carmen','92','','','Tienda Iris','3','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022207','010621','2021-06-10','4','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013209','010621','2021-06-11','5','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033211','010621','2021-06-11','5','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012207','010621','2021-06-11','5','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192023203','010621','2021-06-12','6','','','','El Carmen','92','','','Comedor Mary','2','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191021202','010621','2021-06-13','7','','','','Tecun Uman','91','','','Pullman - Parrillas','2','00:00-05:59','1','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013206','010621','2021-06-13','7','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012206','010621','2021-06-14','1','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033212','010621','2021-06-14','1','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012208','010621','2021-06-14','1','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013210','010621','2021-06-15','2','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192012203','010621','2021-06-15','2','','','','El Carmen','92','','','Caseta Pepsi','1','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022208','010621','2021-06-15','2','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191023203','010621','2021-06-16','3','','','','Tecun Uman','91','','','Pullman - Parrillas','2','12:00-17:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013208','010621','2021-06-16','3','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191022203','010621','2021-06-17','4','','','','Tecun Uman','91','','','Pullman - Parrillas','2','06:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012207','010621','2021-06-18','5','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033213','010621','2021-06-18','5','','','','El Carmen','92','','','Tienda Iris','3','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193023207','010621','2021-06-18','5','','','','La Mesilla','93','','','Terminal de buses','2','13:00-20:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192013207','010621','2021-06-22','2','','','','El Carmen','92','','','Caseta Pepsi','1','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012209','010621','2021-06-22','2','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013211','010621','2021-06-23','3','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022209','010621','2021-06-23','3','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012210','010621','2021-06-24','4','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191013212','010621','2021-06-25','5','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192012204','010621','2021-06-25','5','','','','El Carmen','92','','','Caseta Pepsi','1','05:00-11:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013209','010621','2021-06-25','5','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191021203','010621','2021-06-26','6','','','','Tecun Uman','91','','','Pullman - Parrillas','2','00:00-05:59','1','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033214','010621','2021-06-26','6','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012208','010621','2021-06-27','7','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033215','010621','2021-06-27','7','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193012211','010621','2021-06-28','1','','','','La Mesilla','93','','','Banco Industrial','1','06:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193013210','010621','2021-06-29','2','','','','La Mesilla','93','','','Banco Industrial','1','13:00-19:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1191012209','010621','2021-06-30','3','','','','Tecun Uman','91','','','Malacatan-Coatepeque','1','05:00-12:59','2','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1192033216','010621','2021-06-30','3','','','','El Carmen','92','','','Tienda Iris','3','12:00-18:59','3','','');");
		db.execSQL("INSERT INTO tblJornadas VALUES ('1193022210','010621','2021-06-30','3','','','','La Mesilla','93','','','Terminal de buses','2','06:00-12:59','2','','');");

	}

	public static void cod(SQLiteDatabase db) {

		db.execSQL("INSERT INTO cod VALUES ('paisCu','6','Anguila');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','7','Antigua y Barbuda');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','8','Antillas Neerlandesas (Holandesas)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','9','Argentina');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','10','Aruba');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','11','Belice');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','12','Bolivia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','13','Brasil');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','14','Canada');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','15','Chile');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','16','Colombia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','17','Costa Rica ');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','18','Cuba');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','19','Ecuador');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','20','El Salvador');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','21','Estados Unidos de America');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','22','Groenlandia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','23','Guatemala');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','24','Guyana Francesa');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','25','Guyana Inglesa');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','26','Haiti');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','27','Honduras');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','28','Isla de Jamaica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','29','Isla de Martinica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','30','Isla Dominica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','31','Isla Granada');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','32','Isla Monserrat');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','33','Isla Santa Lucia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','34','Islas Bahamas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','35','Islas Barbados');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','36','Islas Bermudas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','37','Islas Caiman');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','38','Islas Guadalupe');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','39','Islas San Cristobal y Nevis');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','40','Islas San Pedro Miguelon');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','41','Islas San Vicente y las Granadinas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','42','Islas Turcas y Caicos');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','43','Islas Virgenes (Britanicas)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','44','Islas Virgenes (estadounidenses)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','45','Malvinas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','46','Mexico');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','47','Nicaragua');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','48','Panama');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','49','Paraguay');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','50','Peru');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','51','Puerto Rico');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','52','Republica Dominicana');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','53','Surinam ');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','54','Trinidad y Tobado');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','55','Uruguay');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','56','Venezuela');");
		db.execSQL("INSERT INTO cod VALUES ('paisCu','57','Otro pais no especificado anteriormente');");

		db.execSQL("INSERT INTO cod VALUES ('paisCf','1','Anguila');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','2','Antigua y Barbuda');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','3','Antillas Neerlandesas (Holandesas)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','4','Argentina');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','5','Aruba');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','6','Belice');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','7','Bolivia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','8','Brasil');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','9','Canada');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','10','Chile');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','11','Colombia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','12','Costa Rica ');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','13','Cuba');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','14','Ecuador');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','15','El Salvador');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','16','Estados Unidos de America');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','17','Groenlandia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','18','Guatemala');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','19','Guyana Francesa');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','20','Guyana Inglesa');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','21','Haiti');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','22','Honduras');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','23','Isla de Jamaica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','24','Isla de Martinica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','25','Isla Dominica');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','26','Isla Granada');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','27','Isla Monserrat');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','28','Isla Santa Lucia');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','29','Islas Bahamas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','30','Islas Barbados');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','31','Islas Bermudas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','32','Islas Caiman');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','33','Islas Guadalupe');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','34','Islas San Cristobal y Nevis');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','35','Islas San Pedro Miguelon');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','36','Islas San Vicente y las Granadinas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','37','Islas Turcas y Caicos');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','38','Islas Virgenes (Britanicas)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','39','Islas Virgenes (estadounidenses)');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','40','Malvinas');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','41','Mexico');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','42','Nicaragua');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','43','Panama');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','44','Paraguay');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','45','Peru');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','46','Puerto Rico');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','47','Republica Dominicana');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','48','Surinam ');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','49','Trinidad y Tobado');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','50','Uruguay');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','51','Venezuela');");
		db.execSQL("INSERT INTO cod VALUES ('paisCf','52','Otro pais no especificado anteriormente');");

		db.execSQL("INSERT INTO cod VALUES ('9_1','1218001','OCOS');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1217001','TECUN UMAN');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1215013','EL CARMEN');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1209084','TOQUIAN GRANDE');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1312047','LA MESILLA');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1305024','GRACIAS A DIOS');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1420059','INGENIEROS');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1705004','BETHEL');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1705016','NARANJO');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1705015','EL CEIBO');");
		db.execSQL("INSERT INTO cod VALUES ('9_1','1705099','LA TECNICA');");



		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','1','Alabama');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','2','Alaska');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','4','Arizona');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','5','Arkansas');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','6','California');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','8','Colorado');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','9','Connecticut');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','10','Delaware');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','11','District of Columbia');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','12','Florida');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','13','Georgia');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','15','Hawaii');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','16','Idaho');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','17','Illinois');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','18','Indiana');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','19','Iowa');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','20','Kansas');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','21','Kentucky');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','22','Louisiana');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','23','Maine');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','24','Maryland');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','25','Massachusetts');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','26','Michigan');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','27','Minnesota');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','28','Mississippi');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','29','Missouri');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','31','Nebraska');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','32','Nevada');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','33','New Hampshire');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','34','New Jersey');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','35','New Mexico');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','36','New York');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','37','North Carolina');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','39','Ohio');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','40','Oklahoma');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','41','Oregon');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','42','Pennsylvania');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','44','Rhode Island');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','45','South Carolina');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','46','South Dakota');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','47','Tennessee');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','48','Texas');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','49','Utah');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','50','Vermont');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','51','Virginia');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','53','Washington');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','54','West Virginia');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','55','Wisconsin');");
		db.execSQL("INSERT INTO cod VALUES ('estadoUsa','60','Montana');");

		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','020040001','Tijuana');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','020030001','Tecate');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','020020001','Mexicali');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260550001','San Luis Rio Colorado');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','020020111','Vicente Guerrero (Algodones)');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260390001','Naco');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260190001','Cananea');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260020001','Agua Prieta');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260430001','Nogales');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080050068','Puerto Palomas de Villa');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080370001','Ciudad Juarez');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080280016','Doctor Porfirio Parra');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080280163','Porvenir');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080520001','Manuel Ojinaga');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','050250001','Piedras Negras');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','050020001','Ciudad Acuna');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','050120001','Guerrero');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080320001','Hidalgo');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080360001','Jose Mariano Jimenez');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280220001','Heroica Matamoros');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280331097','Rio Bravo');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280320001','Reynosa');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280150001','Gustavo Diaz Ordaz');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280070001','Ciudad Camargo');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280330282','Miguel Aleman (Brecha 124 entre kilometro 15 y 16 norte)');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280270001','Nuevo Laredo');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','190050209','Colombia');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280140001','Nueva Ciudad Guerrero');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280330291','Nuevo Progreso');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080410082','San Jeronimo');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260600044','Sasabe');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260040001','Altar');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260600001','Saric');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260700001','Sonoyta');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260700053','San Emeterio');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','020000000','Otras ciudades de  B.C.');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','260000000','Otras ciudades de  Sonora');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','080000000','Otras ciudades de  Chihuahua');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','050000000','Otras ciudades de  Coahuila');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','190000000','Otras ciudades de  Nuevo Leon');");
		db.execSQL("INSERT INTO cod VALUES ('cruzeMx','280000000','Otras ciudades de  Tamaulipas');");
	}




}
