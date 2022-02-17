package com.contadorsimel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class Menu extends AppCompatActivity {
	Context ourContext = Menu.this;
	Activity ourActivity = this;
	Db db = new Db(ourContext);


	int[] i = {0};
	int[] c = {0};
	boolean[] j = {false};
	int sizeBkp;
	HashMap<String, String> inputs;

	ProgressDialog progressBkp;
	Handler cambio;
	Handler mihand;
	Handler handle;
	int size;
	ProgressDialog progressCyan;
	private RequestQueue mRequestQueue;
	private StringRequest mStringRequest;

//	LocationManager locationManager;
//	String provider;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//				LocationListener mLoc = new mylocationlistener();
//
//				Criteria criteria = new Criteria();
//				criteria.setAccuracy(Criteria.ACCURACY_FINE);
//
//				provider = locationManager.getBestProvider(criteria,
//						true);
//				locationManager.requestLocationUpdates(provider, 0, 0,
//						mLoc);
//		}
//
		defineType();
	}
//
	public void defineType() {
		setContentView(R.layout.menu);
		if(db.getRow("sesion", "tipo", "", "").equals("Supervisor")
				|| db.getRow("sesion", "tipo", "", "").equals("Asistente"))
			supervisor();
		 else
			 interviewer(db.getSesion("psw"));
	}
//
	public void supervisor() {
		final Button btn1 = (Button) findViewById(R.id.btn_1);
		final Button btn2 = (Button) findViewById(R.id.btn_2);
		Button btn3 = (Button) findViewById(R.id.btn_3);
		Button btn4 = (Button) findViewById(R.id.btn_4);
		Button btn5 = (Button) findViewById(R.id.btn_5);

		Toast.makeText(ourContext,"Hola " + db.getRow("sesion", "nombre", "", ""), Toast.LENGTH_SHORT).show();

		btn1.setText("Enviar cuestionarios a CYAN");
		btn2.setText("Reporte de jornada");
		btn3.setText("Comprobar conectividad de envio");
		btn4.setText("Actualizar usuarios");
		btn5.setText("Cerrar sesión");

		btn4.setVisibility(View.GONE);

		btn2.setVisibility(View.GONE);

		btn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
//				send(btn1);
				send();
			}
		});



		btn3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				final ProgressDialog progressBar = new ProgressDialog(v.getContext());
				progressBar.setCancelable(false);
				progressBar
						.setMessage("Revisando conexión de internet. Puede tardar varios segundos ...");
				progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

				final Handler mihand = new Handler();

				final Runnable msj1 = new Runnable() {

					public void run() {

						Toast.makeText(
								ourContext,
								"Conexión a internet exitosa. Proceda a enviar folios.",
								Toast.LENGTH_SHORT).show();
					}
				};

				final Runnable msj2 = new Runnable() {

					public void run() {

						Toast.makeText(
								ourContext,
								"No se encuentra con acceso a internet. NO ENVIAR FOLIOS.",
								Toast.LENGTH_LONG).show();
					}
				};

				progressBar.show();

				new Thread(new Runnable() {

					public void run() {
						try {
							final URL url = new URL("http://www.google.com");

							final HttpURLConnection urlConn = (HttpURLConnection) url
									.openConnection();
							urlConn.setConnectTimeout(500);

							final long startTime = System.currentTimeMillis();
							urlConn.connect();
							final long endTime = System.currentTimeMillis();
							if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
								mihand.post(msj1);
							progressBar.dismiss();
							return;
						} catch (final MalformedURLException e1) {
							e1.printStackTrace();
						} catch (final IOException e) {
							e.printStackTrace();
						}
						mihand.post(msj2);
						progressBar.dismiss();
						return;

					}

				}).start();

			}
		});

//		btn4.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				actualizarUsuarios();
//			}
//		});

		btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.contadorsimel.Login"));
			}
		});
	}
//
	public void interviewer(final String KEY) {
		final Button btn1 = (Button) findViewById(R.id.btn_hombre);
		final Button btn2 = (Button) findViewById(R.id.btn_mujer);
		final Button btn3 = (Button) findViewById(R.id.btn_cancel);
		final Button btn4 = (Button) findViewById(R.id.btn_4);
		final Button btn5 = (Button) findViewById(R.id.btn_5);

		btn1.setText("Seleccionar jornada");

		if (db.getSesion("user").equals("prueba"))
			btn2.setVisibility(View.GONE);
		else
			btn2.setText("Reprogramar/Reponer jornada");

		btn3.setText("Cerrar sesión");

		if (db.getSesion("ciudad").equals("Santa Elena"))
			btn4.setText("Jornada extra");
		else
			btn4.setVisibility(View.GONE);

		btn5.setText("Extra DMX/DEUA");
		//btn5.setVisibility(100);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		final String TODAY = dateFormat.format(System.currentTimeMillis());
		final String TOMORROW = dateFormat.format(calendar.getTime());

		final String PARAMS[] = { TODAY, db.getSesion("ciudad"),
				btn1.getTag().toString() };

		Toast.makeText(ourContext,"Hola " + db.getSesion("nombre"), Toast.LENGTH_SHORT).show();

		btn1.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {

				List<String> cityRegion;

				if (db.getSesion("user").equals("prueba"))
					cityRegion = db.selectCitiesPrueba(PARAMS);
				else
					cityRegion = db.selectCitiesByUsuario(PARAMS);

				if (cityRegion.isEmpty()) {
					Toast.makeText(ourContext, "No hay jornadas disponibles",
							Toast.LENGTH_SHORT).show();
				} else {
					setContentView(R.layout.menu);

					final Button btnC1 = (Button) findViewById(R.id.btn_hombre);
					final Button btnC2 = (Button) findViewById(R.id.btn_mujer);
					final Button btnC3 = (Button) findViewById(R.id.btn_cancel);
					final Button btnC4 = (Button) findViewById(R.id.btn_4);
					final Button btnC5 = (Button) findViewById(R.id.btn_5);

					if(cityRegion.size() == 2) {
						btnC1.setText(cityRegion.get(0));
						btnC2.setText(cityRegion.get(1));
						btnC3.setVisibility(View.GONE);
						btnC4.setVisibility(View.GONE);
					} else if(cityRegion.size() == 1) {
						btnC1.setText(cityRegion.get(0));
						btnC2.setVisibility(View.GONE);
						btnC3.setVisibility(View.GONE);
						btnC4.setVisibility(View.GONE);
					}

					btnC5.setVisibility(View.GONE);

					btnC1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							regiones(new String[] { KEY, TODAY, TOMORROW, btn1.getTag().toString(), "normal", btnC1.getText().toString(), db.getSesion("ciudad")});
						}
					});

					btnC2.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							regiones(new String[] { KEY, TODAY, TOMORROW, btn1.getTag().toString(), "normal", btnC2.getText().toString(), db.getSesion("ciudad")});
						}
					});

				}
				return true;
			}
		});

		btn2.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				List<String> cityRegion;

				cityRegion = db.selectCitiesByUsuario(new String[] {
							TODAY,
							db.getSesion("ciudad"),
							btn2.getTag().toString()
						}
			);
				if (cityRegion.isEmpty()) {
					Toast.makeText(ourContext, "No hay jornadas disponibles",
							Toast.LENGTH_SHORT).show();
				}  else {

					setContentView(R.layout.menu);

					final Button btnC1 = (Button) findViewById(R.id.btn_hombre);
					final Button btnC2 = (Button) findViewById(R.id.btn_mujer);
					final Button btnC3 = (Button) findViewById(R.id.btn_cancel);
					final Button btnC4 = (Button) findViewById(R.id.btn_4);
					final Button btnC5 = (Button) findViewById(R.id.btn_5);

					if(cityRegion.size() == 2) {
						btnC1.setText(cityRegion.get(0));
						btnC2.setText(cityRegion.get(1));
						btnC3.setVisibility(View.GONE);
						btnC4.setVisibility(View.GONE);
					} else if(cityRegion.size() == 1) {
						btnC1.setText(cityRegion.get(0));
						btnC2.setVisibility(View.GONE);
						btnC3.setVisibility(View.GONE);
						btnC4.setVisibility(View.GONE);
					}

					btnC5.setVisibility(View.GONE);

					btnC1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							regiones(new String[] { KEY, TODAY, TOMORROW, "2", "repuesta", btnC1.getText().toString(), db.getSesion("ciudad")});
						}
					});

					btnC2.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							regiones(new String[] { KEY, TODAY, TOMORROW, "2", "repuesta", btnC2.getText().toString(), db.getSesion("ciudad")});
						}
					});

				}

				return true;
			}
		});

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.contadorsimel.Login"));
			}
		});

		btn4.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				Log.i("Ciudad: ", db.getSesion("ciudad"));

				if (db.getSesion("ciudad").equals("Tecun Uman_El Carmen")
						|| db.getSesion("ciudad").equals("El Carmen_Tecun Uman"))
					db.updateRow("sesion", "ciudad", db.getSesion("ciudad"), "Tecun Uman");


				Log.i("Ciudad: ", db.getSesion("ciudad"));

				if(db.getSesion("ciudad").equals("Santa Elena")) {
					setContentView(R.layout.menu);

					final Button btn1 = (Button) findViewById(R.id.btn_hombre);
					final Button btn2 = (Button) findViewById(R.id.btn_mujer);
					final Button btn3 = (Button) findViewById(R.id.btn_cancel);
					final Button btn4 = (Button) findViewById(R.id.btn_4);
					final Button btn5 = (Button) findViewById(R.id.btn_5);


					btn1.setText("AMCRU");
					btn2.setText("CONACELIB");
					btn3.setVisibility(View.GONE);
					btn4.setVisibility(View.GONE);
					btn5.setVisibility(View.GONE);


					btn1.setOnLongClickListener(new OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {


							jornadaExtra(PARAMS, KEY, TODAY, TOMORROW, "1");
							return true;
						}
					});

					btn2.setOnLongClickListener(new OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {


							jornadaExtra(PARAMS, KEY, TODAY, TOMORROW, "2");
							return true;
						}
					});
				}
				else {
					jornadaExtra(PARAMS, KEY, TODAY, TOMORROW, "4");
				}



				return true;
			}
		});

//		btn5.setOnLongClickListener(new OnLongClickListener() {
//
//			@Override
//			public boolean onLongClick(View v) {
//
//				Log.i("Ciudad: ", db.getSesion("ciudad"));
//
//
//				setContentView(R.layout.camion);
//
//				Button btnAceptar = (Button) findViewById(R.id.btnAceptar);
//				Button cancel = (Button) findViewById(R.id.btn_cancel);
//
//				final EditText idJornada = (EditText) findViewById(R.id.numero);
//
//				TextView tv1 = (TextView) findViewById(R.id.textView1);
//
//				tv1.setText("Jornada extra");
//				cancel.setText("Regresar");
//				btnAceptar.setOnLongClickListener(new OnLongClickListener() {
//
//					@Override
//					public boolean onLongClick(View v) {
//						// TODO Auto-generated method stub
//
//						if (idJornada.getText().toString().trim().length() > 0)
//							//Toast.makeText(ourContext, idJornada.getText().toString(),
//								//	Toast.LENGTH_SHORT).show();
//							jornadaExtraDmxDeua(idJornada.getText().toString(), PARAMS, KEY, TODAY, TOMORROW, "1");
//
//						else
//							Toast.makeText(ourContext, "Falta indicar idJornada",
//									Toast.LENGTH_SHORT).show();
//
//						return true;
//					}
//				});
//
//				//
//
//				cancel.setOnLongClickListener(new OnLongClickListener() {
//
//					@Override
//					public boolean onLongClick(View v) {
//						// TODO Auto-generated method stub
//						setContentView(R.layout.menu);
//						interviewer(db.getSesion("psw"));
//						return true;
//					}
//				});
//
//				return true;
//			}
//		});
	}
//
//
	public void jornadaExtra(String PARAMS[], final String KEY, final String TODAY, final String TOMORROW, final String puntoPunto) {

		List<String> cityCiudad;

		String i_ano = ""
				+ Calendar.getInstance().get(Calendar.YEAR);

		String i_mes = ""
				+ ((Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1) : (Calendar.getInstance().get(Calendar.MONTH) + 1));

		String i_dia = ""
				+ ((Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) < 10 ? "0" + (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) : (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));

		String idEmif = getResources().getString(R.string.fase)
				+ ((Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1) : (Calendar.getInstance().get(Calendar.MONTH) + 1))
				+ i_ano.substring(i_ano.length() - 2);

		String query = "";

		Log.i("sesionPais", db.getRow("sesion", "pais", "", ""));


		//Santa Elena
			//Punto AMCRU 1
			//Punto CONACELIB 2
			//Punto Hoteles 3

		//La Mesilla y Tecun Uman
			//Punto Hoteles 4

		String punto = puntoPunto;
		String turno = "1";
		String ciudadClv  = "94";

		if(db.getSesion("ciudad").equals("Tecun Uman")) {
//			punto = "01";
//			turno = "1";
			ciudadClv = "91";
		}

		if(db.getSesion("ciudad").equals("La Mesilla")) {
//			punto = "01";
//			turno = "1";
			ciudadClv = "93";
		}

		query = "INSERT INTO tblJornadas VALUES ('"
				+ "6" + ciudadClv + puntoPunto +  i_ano + i_mes + i_dia + "','" //idJornada
				+ idEmif + "','" //idEmif
				+ i_ano + "-" + i_mes + "-" + i_dia + "','1'," //dateProg diaSemanaProg
				+ "'','','','" //flujo region regionClv
				+ db.getRow("sesion", "ciudad", "", "") + "'," //ciudad
				+ "'" + ciudadClv + "'," //ciudadClv
				+ "'',''," //zona zonaClv
				+ "'Extra','" + punto + "'," //punto puntoClv
				+ "'Extra','" + turno  + "'," //turno turnoClv
				+ "'','');"; //ponderador status
				//+ db.getRow("sesion", "pais", "", "") + "');"; //pais


		Log.i("query", query);

		db.jornadaExtra(query);

		if (db.getRow("sesion", "psw", "", "").equals("100")
				|| db.getRow("sesion", "psw", "", "").equals("200")
				|| db.getRow("sesion", "psw", "", "").equals("300"))
			cityCiudad = db.selectCitiesPrueba(PARAMS);
		else
			cityCiudad = db.selectCitiesByUsuario(PARAMS);

		if (cityCiudad.isEmpty()) {
			Toast.makeText(ourContext, "No hay jornadas disponibles",
					Toast.LENGTH_SHORT).show();
		} else {
			setContentView(R.layout.menu);

			final Button btnC1 = (Button) findViewById(R.id.btn_hombre);
			final Button btnC2 = (Button) findViewById(R.id.btn_mujer);
			final Button btnC3 = (Button) findViewById(R.id.btn_cancel);
			final Button btnC4 = (Button) findViewById(R.id.btn_4);
			final Button btnC5 = (Button) findViewById(R.id.btn_5);

			if(cityCiudad.size() == 2) {
				btnC1.setText(cityCiudad.get(0));
				btnC2.setText(cityCiudad.get(1));

			} else if(cityCiudad.size() == 1) {
//				if (cityCiudad.get(0).equals("Santa Elena")) {
//					btnC1.setText(cityCiudad.get(0) + " Terminal");
//					btnC2.setText(cityCiudad.get(0) + " Hoteles");
//					btnC3.setVisibility(View.GONE);
//					btnC4.setVisibility(View.GONE);
//				}
//				else {
					btnC1.setText(cityCiudad.get(0));
					btnC2.setVisibility(View.GONE);
					btnC3.setVisibility(View.GONE);
					btnC4.setVisibility(View.GONE);
					btnC5.setVisibility(View.GONE);
//				}
			}

			btnC1.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
//					if (btnC1.getText().equals("Santa Elena Terminal")) {
//						db.updateRow("sesion", "data", "", "Terminal");
//						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra",    "Santa Elena", db.getSesion("ciudad")});
//					}
//					else
						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra",        btnC1.getText().toString(), db.getSesion("ciudad")});


					return true;
				}
			});


			btnC2.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
//					if (btnC2.getText().equals("Santa Elena Hoteles")) {
//						db.updateRow("sesion", "data", "", "Hoteles");
//						regiones(new String[] { KEY, TODAY, TOMORROW, "2", "extra", "Santa Elena", db.getSesion("ciudad")});
//					}
//					else
						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra", btnC2.getText().toString(), db.getSesion("ciudad")});
					return true;
				}
			});

		}
	}
//
//
	public void jornadaExtraDmxDeua(String newIdJornada, String PARAMS[], final String KEY, final String TODAY, final String TOMORROW, final String puntoPunto) {

		List<String> cityCiudad;

		String i_ano = ""
				+ Calendar.getInstance().get(Calendar.YEAR);

		String i_mes = ""
				+ ((Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1) : (Calendar.getInstance().get(Calendar.MONTH) + 1));

		String i_dia = ""
				+ ((Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) < 10 ? "0" + (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) : (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));

		String idEmif = getResources().getString(R.string.fase)
				+ ((Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1) : (Calendar.getInstance().get(Calendar.MONTH) + 1))
				+ i_ano.substring(i_ano.length() - 2);

		String query = "";

		Log.i("sesionPais", db.getRow("sesion", "pais", "", ""));


		//Santa Elena
			//Punto AMCRU 1
			//Punto CONACELIB 2
			//Punto Hoteles 3

		//La Mesilla y Tecun Uman
			//Punto Hoteles 4

		String punto = puntoPunto;
		String turno = "1";
		String ciudadClv  = "94";

		query = "INSERT INTO tblJornadas VALUES ('"
				//+ "6" + ciudadClv + puntoPunto +  i_ano + i_mes + i_dia + "','" //idJornada
				+ newIdJornada + "','" //idJornada
				+ idEmif + "','" //idEmif
				+ i_ano + "-" + i_mes + "-" + i_dia + "','1'," //dateProg diaSemanaProg
				+ "'','','','" //flujo region regionClv
				+ db.getRow("sesion", "ciudad", "", "") + "'," //ciudad
				+ "'" + ciudadClv + "'," //ciudadClv
				+ "'',''," //zona zonaClv
				+ "'Extra','" + punto + "'," //punto puntoClv
				+ "'Extra','" + turno  + "'," //turno turnoClv
				+ "'','');"; //ponderador status
				//+ db.getRow("sesion", "pais", "", "") + "');"; //pais


		Log.i("query", query);

		db.jornadaExtra(query);

		PARAMS = new String[] { KEY, TODAY, TOMORROW, "1", "extra",   db.getRow("sesion", "ciudad", "", ""), db.getSesion("ciudad")};

		db.insertInformante("1");

		final Intent i = new Intent(ourContext, Cf.class);
		i.putExtra("clave", PARAMS[0]);
		i.putExtra("idJornada", newIdJornada);
		i.putExtra("tipoJornada", PARAMS[4]);

//		f0(newIdJornada, PARAMS[4]);

//		if (locationManager
//				.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//
//			final ProgressDialog progressBar = new ProgressDialog(
//					Menu.this);
//			progressBar.setCancelable(false);
//			progressBar
//					.setMessage("Cargando interfaz. Puede tardar varios segundos ...");
//			progressBar
//					.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//			progressBar.show();
//
//			new Thread(new Runnable() {
//				public void run() {
//					try {
//
//						Thread.sleep(10000);
//
//					} catch (Exception e) {
//
//					}
//
//					progressBar.dismiss();
//
//					startActivity(i);
//
//				}
//			}).start();
//
//		} else
//			startActivity(i);


		//regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra",   db.getRow("sesion", "ciudad", "", ""), db.getSesion("ciudad")});

//		if (db.getRow("sesion", "psw", "", "").equals("100")
//				|| db.getRow("sesion", "psw", "", "").equals("200")
//				|| db.getRow("sesion", "psw", "", "").equals("300"))
//			cityCiudad = db.selectCitiesPrueba(PARAMS);
//		else
//			cityCiudad = db.selectCitiesByUsuario(PARAMS);
//
//		if (cityCiudad.isEmpty()) {
//			Toast.makeText(ourContext, "No hay jornadas disponibles",
//					Toast.LENGTH_SHORT).show();
//		} else {
//			setContentView(R.layout.city);
//
//			final Button btnC1 = (Button) findViewById(R.id.btn_1);
//			final Button btnC2 = (Button) findViewById(R.id.btn_2);
//			final Button btnC3 = (Button) findViewById(R.id.btn_3);
//			final Button btnC4 = (Button) findViewById(R.id.btn_4);
//
//			if(cityCiudad.size() == 2) {
//				btnC1.setText(cityCiudad.get(0));
//				btnC2.setText(cityCiudad.get(1));
//
//			} else if(cityCiudad.size() == 1) {
////				if (cityCiudad.get(0).equals("Santa Elena")) {
////					btnC1.setText(cityCiudad.get(0) + " Terminal");
////					btnC2.setText(cityCiudad.get(0) + " Hoteles");
////					btnC3.setVisibility(View.GONE);
////					btnC4.setVisibility(View.GONE);
////				}
////				else {
//					btnC1.setText(cityCiudad.get(0));
//					btnC2.setVisibility(View.GONE);
//					btnC3.setVisibility(View.GONE);
//					btnC4.setVisibility(View.GONE);
////				}
//			}
//
//			btnC1.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View v) {
////					if (btnC1.getText().equals("Santa Elena Terminal")) {
////						db.updateRow("sesion", "data", "", "Terminal");
////						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra",    "Santa Elena", db.getSesion("ciudad")});
////					}
////					else
//						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra",        btnC1.getText().toString(), db.getSesion("ciudad")});
//
//
//					return true;
//				}
//			});
//
//
//			btnC2.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View v) {
////					if (btnC2.getText().equals("Santa Elena Hoteles")) {
////						db.updateRow("sesion", "data", "", "Hoteles");
////						regiones(new String[] { KEY, TODAY, TOMORROW, "2", "extra", "Santa Elena", db.getSesion("ciudad")});
////					}
////					else
//						regiones(new String[] { KEY, TODAY, TOMORROW, "1", "extra", btnC2.getText().toString(), db.getSesion("ciudad")});
//					return true;
//				}
//			});
//
//		}
	}
//
//	/**
//	 * Sp1 jornadas
//	 * @param params [5] today, tag, tipoJornada, ciudad, region
//	 */
	public void regiones(final String PARAMS[]) {


		setContentView(R.layout.display_jornadas);
		final Spinner sp1 = (Spinner) findViewById(R.id.sp1);
		final Button btn = (Button) findViewById(R.id.btn_cancel);
		List<String> labels;

		if (db.getSesion("user").equals("prueba"))
			PARAMS[3] = "4";

		labels = db.selectJornadas(PARAMS);


		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ourContext,
				android.R.layout.simple_spinner_dropdown_item);
				//R.layout.spinner_item);


		dataAdapter.add("SELECCIONA JORNADA");
		dataAdapter.addAll(labels);
		dataAdapter
		.setDropDownViewResource(android.R.layout.simple_spinner_item);
		//.setDropDownViewResource(R.layout.jornadas);
		sp1.setAdapter(dataAdapter);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (sp1.getSelectedItemPosition() > 0) {

					String getIDJ = sp1.getSelectedItem().toString();
					String onlyId = getIDJ.replace("IDJ[", "");
					StringBuffer reverse = new StringBuffer(onlyId);
					reverse.reverse();
					Integer find = reverse.indexOf("]");
					String revrev = reverse.toString();
					StringBuffer finalId = new StringBuffer(revrev.replace("]",
							""));
					String rev = finalId.substring(find).toString();
					StringBuffer sendId = new StringBuffer(rev);
					String idJornada = sendId.reverse().toString();

					//db.update("tipoJornada", PARAMS[4]);

//					Cf cf = new Cf();
//					cf.sub = 1;

					db.insertInformante("1");

					final Intent i = new Intent(ourContext, Cf.class);
					i.putExtra("clave", PARAMS[0]);
					i.putExtra("idJornada", idJornada);
					i.putExtra("tipoJornada", PARAMS[4]);

					f0(idJornada, PARAMS[4]);
//
//					if (locationManager
//							.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//
						final ProgressDialog progressBar = new ProgressDialog(
								Menu.this);
						progressBar.setCancelable(false);
						progressBar
								.setMessage("Cargando interfaz. Puede tardar varios segundos ...");
						progressBar
								.setProgressStyle(ProgressDialog.STYLE_SPINNER);

						progressBar.show();

						new Thread(new Runnable() {
							public void run() {
								try {

									Thread.sleep(10000);

								} catch (Exception e) {

								}

								progressBar.dismiss();

								startActivity(i);

							}
						}).start();
//
//					} else
//						startActivity(i);
				} else
					Toast.makeText(ourContext, "Seleccionar Jornada",
							Toast.LENGTH_SHORT).show();
			}


		});

	}

//	/**
//	 * Envia todos los marked 0 o segun el id
//	 * @param tag para envio o reenvio
//	 */

	public Runnable imprimeThread(final String MSG) {
		final Runnable trono = new Runnable() {

			public void run() {
				Toast.makeText(ourContext, MSG, Toast.LENGTH_SHORT).show();
			}
		};
		return trono;
	}
//
	public Runnable imprimeThread2(final String MSG) {
		final Runnable trono = new Runnable() {

			public void run() {
				Toast.makeText(ourContext, MSG, Toast.LENGTH_LONG).show();

			}
		};
		return trono;
	}
//
//	private StringBuilder inputStreamToString(InputStream is) {
//		String rLine = "";
//		StringBuilder answer = new StringBuilder();
//		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//
//		try {
//			while ((rLine = rd.readLine()) != null) {
//				answer.append(rLine);
//			}
//		}
//
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		return answer;
//	}
//
//	private class mylocationlistener implements LocationListener {
//		@Override
//		public void onLocationChanged(Location location) {
//
//			// latituteField.setText("" + location.getLatitude());
//			// longitudeField.setText("" + location.getLongitude() +
//			// "\n Accuracy: " + location.getAccuracy());
//		}
//
//		@Override
//		public void onProviderDisabled(String provider) {
//		}
//
//		@Override
//		public void onProviderEnabled(String provider) {
//		}
//
//		@Override
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//		}
//	}
//
	public void f0(String idJornada, String tipoJornada) {


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

		db.insertNite();


		String psw = db.getRow("sesion", "psw", "", "");

		String fechaJornada = dateFormat.format(System.currentTimeMillis());

		db.queryUpdate("iniJornada = '" + fechaJornada + "', tipoJornada = '" + tipoJornada + "'");

		StringBuilder header2 = new StringBuilder()
			.append("tablet = '").append(Build.MANUFACTURER).append("_").append(Build.SERIAL).append("',")
			.append("version = '").append(getResources().getString(R.string.app_ver)).append("',")
			.append("marked = '0',")

			.append("clave = '").append(psw).append("',")
			.append("genero = '").append("").append("',")
			.append("fechaEnvio = '").append("fechaEnvio").append("',")
			.append("fecha = '").append(fechaJornada).append("',")
			.append("finJornada = '").append("0000-00-00 00:00:00").append("',")
			.append("idJornada = '").append(idJornada).append("'");






		String[] infoJornada = db.getInfoJornadas(idJornada);

		String[] jornadaProg = infoJornada[0].split("-");

		db.update("usuario", db.getSesion("user"));
		db.update("contador", "inicio");
		db.queryUpdate(header2.toString());



		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

			return;
		}

		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		//String device_id = tm.getDeviceId();


		if(tm.getDeviceId() == null)
			db.update("tablet", Build.MANUFACTURER + "_" + Build.SERIAL + "_tab");
		else
			db.update("tablet", Build.MANUFACTURER + "_" + tm.getDeviceId() + "_tel");


	}
//
//
//
//public void actualizarUsuarios() {
//
////		btn.setClickable(false);
//
//		//final ProgressDialog progressBkp = new ProgressDialog(ourActivity);
//		final ProgressDialog progressCyan = new ProgressDialog(ourActivity);
//
//		progressCyan.setCancelable(false);
//		progressCyan.setMessage("Buscando");
//		progressCyan
//				.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//
//
//
//
//		final HttpPost httppost = new HttpPost(
//				"http://emif.mx/paradigma/envio/getUsuarios.php");
//
//		// Time Out
//		HttpParams httpParameters = new BasicHttpParams();
//		int timeoutConnection = 10000;
//		HttpConnectionParams.setConnectionTimeout(httpParameters,
//				timeoutConnection);
//		int timeoutSocket = 10000;
//		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//		//
//
//		final HttpClient httpclient = new DefaultHttpClient(httpParameters);
//		final Handler mihand = new Handler();
//
//		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo netInfo = cm.getActiveNetworkInfo();
//
//		final ArrayList<String> idJornadasBkp = db.getJornadasBkp();
////		final ArrayList<String> idJornadasGps = db.getJornadasGps();
//		final int sizeBkp = idJornadasBkp.size();
//
//
//		final int sizeGps = db.getJornadasGps();
//
//		final int size = db.getCountTbl();
//
//
//
//
//
//
//
//		final Handler handle = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				progressCyan.incrementProgressBy(1);
//			}
//		};
//
//		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//
////			if (sizeBkp > 0) {
//
//				/********************************/
//				progressCyan.show();
//				new Thread(new Runnable() {
//					public void run() {
//				    	int i = 0, c = 0;
//						boolean j = false, k = false, l = false, m = false, n = false, o = false;
//
//
//								//Bkp completo
//								//progressBkp.dismiss();
//
//								i = 0;
//								//cambio.sendEmptyMessage(0);
//
//
//									List<NameValuePair> nvp = new ArrayList<NameValuePair>();
//
//								//	Log.i("ciudad", db.getRow("sesion", "ciudad", "", ""));
//
//									nvp.add(new BasicNameValuePair("ciudad", db.getRow("sesion", "ciudad", "", "")));
//
//
//									HttpResponse response = null;
//									try{
////										if(data.get("ciudad").get(0).toString().equals("prueba")){
////
////
////											httppostTest.setEntity(new UrlEncodedFormEntity(nvp));
////											response = httpclient.execute(httppostTest);
////										}
////										else{
//											httppost.setEntity(new UrlEncodedFormEntity(nvp));
//											response = httpclient.execute(httppost);
////										}
//										// Recibe
//										String jsonResult = inputStreamToString(
//												response.getEntity().getContent())
//												.toString();
//										// pasa json
//										JSONObject object = new JSONObject(jsonResult);
//
//										// index
//										boolean status = object.getBoolean("status");
//
//										String usuarios = object.getString("usuarios");
////												getBoolean("status");
//
//										if (status) {
////											c++;
////											db.setMarked(data.get("id").get(0).toString());
////											handle.sendMessage(handle.obtainMessage());
////											i++;
//
//
//											db.queryInsert(usuarios);
//
//
//
//
////											mihand.post(imprimeThread2(usuarios));
//
//											mihand.post(imprimeThread2("Usuarios actualizados"));
//
//
//										} else {
//											i = size;
//											k = true;
//										}
//
//									} catch (UnsupportedEncodingException e1) {
//										// json mal parsiado
//										progressCyan.dismiss();
//										//btn.setClickable(true);
//										i = size;
//										l = true;
//
//									} catch (JSONException e) {
//										// valor null en json
//										progressCyan.dismiss();
//										//btn.setClickable(true);
//										i = size;
//										m = true;
//
//									} catch (ClientProtocolException e) {
//										// time out expiro
//										progressCyan.dismiss();
//										//btn.setClickable(true);
//										i = size;
//										n = true;
//
//									} catch (IOException e) {
//										// http reponse invalido
//										i = size;
//										o = true;
//									}
//
////									i++;
//
//
//								progressCyan.dismiss();
//								//btn.setClickable(true);
////
//								if (j)
////									mihand.post(imprimeThread2("El envio no se ha completado porque se perdio la conexi�n de internet con el CYAN.\nFaltaron por enviar: "
////											+ (size - c) + " folios"));
//
//									mihand.post(imprimeThread2("El envio no se ha completado porque se perdio la conexi�n de internet"));
//
//
//								// fallo el insert
//								else if (k)
////									mihand.post(imprimeThread2("Error en la inserci�n del envio al servidor CYAN. Faltaron por enviar: "
////											+ (size - c) + " folios"));
//									mihand.post(imprimeThread2("Error en la inserci�n del envio al servidor"));
//								else if (l) {
//									//mihand.post(imprimeThread2("TRONO L"));
//									mihand.post(imprimeThread2("Error en conversi�n de datos"));
//								}
//								else if (m) {
//									//mihand.post(imprimeThread2("TRONO M"));
//									mihand.post(imprimeThread2("Error con respuesta de servidor"));
//								}
//								else if (n) {
//									//mihand.post(imprimeThread2("TROMO N"));
//									mihand.post(imprimeThread2("Error en tiempo de respuesta"));
//								}
//								else if (o) {
//									//mihand.post(imprimeThread2("TRONO O"));
//									mihand.post(imprimeThread2("Error en URL de env�o"));
//								}
//								// envio exitoso
//								else
//									mihand.post(imprimeThread("Envio exitoso."));
//
//
//					}
//				}).start();
//
//				/********************************/
//
////			}
////				else {
////				Toast.makeText(ourContext, "No hay folios para enviar.",
////						Toast.LENGTH_SHORT).show();
////				//btn.setClickable(true);
////				}
//		}
//				else {
//			Toast.makeText(ourContext, "No hay conexi�n a Internet.",
//					Toast.LENGTH_SHORT).show();
//			//btn.setClickable(true);
//				}
//	}
//
//
//
//
//
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		// super.onBackPressed();
//	}
//
//	@Override
//	public void onAttachedToWindow() {
//		super.onAttachedToWindow();
//		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//	}


	public void send() {
		final boolean[] connected = {checkInternetConnection(ourContext)};

		if (connected[0]) {

			db = new Db(ourContext);
			final ArrayList<String> idJornadasBkp = db.getJornadasBkp();
			//		final ArrayList<String> idJornadasGps = db.getJornadasGps();
			sizeBkp = idJornadasBkp.size();


			if (sizeBkp > 0) {


				size = db.getCountTbl();
				Log.i("size", "" + size);
				progressCyan = new ProgressDialog(ourContext);
				mihand = new Handler();
				cambio = new Handler() {
					public void handleMessage(Message msg) {
						progressCyan.setCancelable(false);
						progressCyan.setMessage("Enviando");
						progressCyan
								.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						progressCyan.setProgress(0);
						progressCyan.setMax(size);
						progressCyan.setProgressNumberFormat(null);
						progressCyan.show();
					}
				};

				handle = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						progressCyan.incrementProgressBy(1);
					}
				};


				progressBkp = new ProgressDialog(ourContext);
				progressBkp.setMessage("Preparando envio. Puede tardar varios segundos ...");
				progressBkp.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressBkp.show();

				// i = 0;




				Log.i("sizeBkp", "" + sizeBkp);

				new Thread(new Runnable() {
					@Override
					public void run() {


						boolean k = false;
						boolean l = false;
						boolean m = false;
						boolean n = false;
						boolean o = false;

						//bkp
						Log.i("sizeBkp", "" + sizeBkp);

						mRequestQueue = Volley.newRequestQueue(ourContext);

						try {
							envioBkp();
						}
						catch (Exception error1) {
							i[0] = sizeBkp;
							Log.i("Error 1:", error1.toString());
                          j[0] = true;
							progressBkp.dismiss();

						};

						if(j[0]) {
							progressBkp.dismiss();
						}
						//envio los contadores
						else {

						}

					}
				}).start();
			}
			else
				Toast.makeText(ourContext, "No hay folios para enviar.", Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(getApplicationContext(),"No hay conexión a Internet:", Toast.LENGTH_LONG).show();//display the response on screen
	}

	public void envioBkp() {
		String url = "http://elcolef.net/Emif/envio/respaldoContador_17.php";

		final ArrayList<String> idJornadasBkp = db.getJornadasBkp();
		ArrayList<String> resp = db.selectRespaldo(idJornadasBkp.get(i[0]));

		inputs = new HashMap<String, String>();

		inputs.put("resultado", resp.toString());
		inputs.put("idJornada", idJornadasBkp.get(i[0]));

		mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				//Toast.makeText(getApplicationContext(),"Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen
				Log.i("i antes", "" + i[0]);
				i[0]++;
				Log.i("i despues", "" + i[0]);

				if(i[0] < sizeBkp)
					envioBkp();
				else {
					//progressBkp.dismiss();
					i[0] = 0;
					progressBkp.dismiss();

					cambio.sendEmptyMessage(0);
					envioContador();
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				i[0] = sizeBkp;
				j[0] = true;
				// Toast.makeText(getApplicationContext(),"Error 2:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
			}
		}){
			@Override
			protected Map<String,String> getParams(){
				Map<String,String> params = inputs;
				return params;
			}
		};

		mRequestQueue.add(mStringRequest);
	}

	public void envioContador() {



		//fin bkp
//            do {
		final HashMap<String, List<String>> data = db
				.selectCuestionario("4", "");

		String tablet = db.getLastExistentField("tablet");
		String idJornada = data.get("idJornada").get(0).toString();
		String clave = data.get("clave").get(0).toString();
		String fecha = data.get("fecha").get(0).toString();
		String app = data.get("app").get(0).toString();

		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("resultado", data
				.get("resultado").get(0).toString().toString().replace("'fechaEnvio'", "now()"));
		inputs.put("tablet", tablet);
		inputs.put("idJornada", idJornada);
		inputs.put("clave", clave);
		inputs.put("fecha", fecha);
		inputs.put("app", app);
		inputs.put("id", data.get("id").get(0).toString());

		final String URLVOLLEY = "http://calidad-de-vida.org/dei/envio/envioTabletContadorV2Simel.php";

		//System.out.println(Arrays.asList(inputs));
		try {

			//mRequestQueue = Volley.newRequestQueue(this);
			mStringRequest = new StringRequest(Request.Method.POST, URLVOLLEY, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					//  Toast.makeText(getApplicationContext(),"Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen

					c[0]++;
					db.setMarked(data.get("id").get(0).toString());
					handle.sendMessage(handle.obtainMessage());
					i[0]++;
					Log.i("envioContador i: ", "" + i[0]);

					if(i[0] < size)
						envioContador();
					else {
						progressCyan.dismiss();
						mihand.post(imprimeThread("Envio exitoso."));
					}


				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					//  Toast.makeText(getApplicationContext(),"Error 2:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
					progressCyan.dismiss();
					i[0] = size;
				}
			}){
				@Override
				protected Map<String,String> getParams(){

					Map<String,String> params = inputs;
					return params;
				}
			};

			mRequestQueue.add(mStringRequest);


//                                            Db db = new Db(ourContext);
			// db.setMarked(inputs.get("id").toString());


		} catch (Exception error1) {
			i[0] = size;
			System.out.println("Error final" + error1.toString());
			progressCyan.dismiss();
			//Toast.makeText(getApplicationContext(),"Error 1:" + error1.toString(), Toast.LENGTH_LONG).show();
		};
		//   i[0]++;
//            } while (i[0] < size);
		// progressCyan.dismiss();
	}

	public static boolean checkInternetConnection(Context context) {
		try
		{
			ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected())
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}



		return false;
	}
}
