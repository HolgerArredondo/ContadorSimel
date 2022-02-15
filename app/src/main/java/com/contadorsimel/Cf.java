package com.contadorsimel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;


import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Cf extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
	
	Context ourContext = Cf.this;
	Db db = new Db(ourContext);
//	static String lastIdCounter;
//	static int sub = 1;

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	//hola mundo
	String fechaJornada;
	
	String provider;
	
//	int numero = 0;
	TextView tv;
	TextView tv2;
	
	Button fdi;
	
	Menu menuMain;

	boolean fueraDeIntento = false;
	
	SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss:SSS");
	PopupMenu popup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//		fechaJornada = dateFormat.format(System.currentTimeMillis());
//
		cedulaFiltro();
//
//
//
		
	}
//
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//
//        menuMain = menu;
//
//        return true;
//    }
//
//
//
	public void cedulaFiltro() {
		setContentView(R.layout.cf_01);
//		final Button btnF1 = (Button) findViewById(R.id.btn_f1);


//		Toolbar toolbar = findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
		final Button btnHombre = findViewById(R.id.btn_hombre);
		final Button btnMenu = findViewById(R.id.btn_menu);
		final Button btnMujer = findViewById(R.id.btn_mujer);
//		final Button btnMenu = (Button) findViewById(R.id.btn_menu);
//
//
//		//final TextView
//
//
//
//
		tv = findViewById(R.id.textView1);
		tv2 = findViewById(R.id.textView2);
//
		tv.setText("");


		btnMenu.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				showPopup(view);
//				Toast.makeText(ourContext,
//										"Hola mundo",
//										Toast.LENGTH_SHORT).show();
				return true;
			}
		});



		//DMX DMX
//		if(db.getField("idJornada").startsWith("79")
//				|| db.getField("idJornada").startsWith("89")) {
//
//			btnHombre.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View v) {
//					// TODO Auto-generated method stub
//
//					final Dialog dialog = new Dialog(ourContext);
//					dialog.setContentView(R.layout.camion);
//					dialog.setCancelable(true);
//					dialog.show();
//
//					Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
//					Button cancel = (Button) dialog
//							.findViewById(R.id.btn_cancel);
//					final EditText numero = (EditText) dialog
//							.findViewById(R.id.numero);
//
//					TextView tv1 = (TextView) dialog.findViewById(R.id.textView1);
//
//					tv1.setText("Hombres");
//
//					cancel.setOnClickListener(new OnClickListener() {
//						public void onClick(View arg0) {
//							dialog.hide();
//							getWindow().setSoftInputMode(
//								      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//						}
//					});
//
//					btnAceptar.setOnClickListener(new OnClickListener() {
//
//						public void onClick(View arg0) {
//
//							if (numero.getText().toString().isEmpty())
//								Toast.makeText(ourContext,
//										"Introducir n�mero.",
//										Toast.LENGTH_SHORT).show();
//							else {
//
//
//
//									dialog.dismiss();
//									getWindow().setSoftInputMode(
//										      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//
//
//									final ProgressDialog progressBar = new ProgressDialog(arg0.getContext());
//									progressBar.setCancelable(false);
//									progressBar
//											.setMessage("Puede tardar varios segundos ...");
//									progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//									final Handler mihand = new Handler();
//
//									final Runnable msj1 = new Runnable() {
//
//										public void run() {
//
//											Toast.makeText(
//													ourContext,
//													"Guardado exitosamente",
//													Toast.LENGTH_SHORT).show();
//										}
//									};
//
//									progressBar.show();
//
//									new Thread(new Runnable() {
//
//										public void run() {
//											try {
//												for (int i = 0; i < Integer.parseInt(numero.getText().toString()); i++)
//													setHeader("1");
//
//												mihand.post(msj1);
//
//											}catch (Exception e) {
//												e.printStackTrace();
//											}
//
//											progressBar.dismiss();
//
//
//
//										}
//									}).start();
//
//									tv.setText(tv.getText().toString() + "Hombres: " + numero.getText().toString() + "\n");
//
//
//							}
//						}
//					});
//
//					return true;
//				}
//			});
//
//
//			btnMujer.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View v) {
//					// TODO Auto-generated method stub
//
//					final Dialog dialog = new Dialog(ourContext);
//					dialog.setContentView(R.layout.camion);
//					dialog.setCancelable(true);
//					dialog.show();
//
//					Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
//					Button cancel = (Button) dialog
//							.findViewById(R.id.btn_cancel);
//					final EditText numero = (EditText) dialog
//							.findViewById(R.id.numero);
//
//					TextView tv1 = (TextView) dialog.findViewById(R.id.textView1);
//
//					tv1.setText("Mujeres");
//
//					cancel.setOnClickListener(new OnClickListener() {
//						public void onClick(View arg0) {
//							dialog.hide();
//							getWindow().setSoftInputMode(
//								      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//						}
//					});
//
//					btnAceptar.setOnClickListener(new OnClickListener() {
//
//						public void onClick(View arg0) {
//
//							if (numero.getText().toString().isEmpty())
//								Toast.makeText(ourContext,
//										"Introducir n�mero.",
//										Toast.LENGTH_SHORT).show();
//							else {
//
//
//
//									dialog.dismiss();
//									getWindow().setSoftInputMode(
//										      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//
//
//									final ProgressDialog progressBar = new ProgressDialog(arg0.getContext());
//									progressBar.setCancelable(false);
//									progressBar
//											.setMessage("Puede tardar varios segundos ...");
//									progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//									final Handler mihand = new Handler();
//
//									final Runnable msj1 = new Runnable() {
//
//										public void run() {
//
//											Toast.makeText(
//													ourContext,
//													"Guardado exitosamente",
//													Toast.LENGTH_SHORT).show();
//										}
//									};
//
//									progressBar.show();
//
//									new Thread(new Runnable() {
//
//										public void run() {
//											try {
//												for (int i = 0; i < Integer.parseInt(numero.getText().toString()); i++)
//													setHeader("2");
//
//												mihand.post(msj1);
//
//											}catch (Exception e) {
//												e.printStackTrace();
//											}
//
//											progressBar.dismiss();
//
//
//
//										}
//									}).start();
//
//
//									tv.setText(tv.getText().toString() + "Mujeres: " + numero.getText().toString() + "\n");
//
//							}
//						}
//					});
//
//					return true;
//				}
//			});
//
//
//			btnHombre.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//
//					//db.insertNite();
//
//
//					setHeader("1");
//					if (fueraDeIntento)
//						db.update("contador", "3");
//
//
//
//
//					tv.setText("" + printFormat.format(System.currentTimeMillis()));
//
//
//
//				}
//			});
////
//			btnMujer.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//
//					//db.insertNite();
//					setHeader("2");
//
//					if (fueraDeIntento)
//						db.update("contador", "3");
//
//					tv.setText("" + printFormat.format(System.currentTimeMillis()));
//
//
//
//				}
//			});
//		}
//		//PMX PGT
//		else {
			btnHombre.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					//db.insertNite();


					setHeader("1");
					if (fueraDeIntento)
						db.update("contador", "3");




					tv.setText("" + printFormat.format(System.currentTimeMillis()));



				}
			});

			btnMujer.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					//db.insertNite();
					setHeader("2");

					if (fueraDeIntento)
						db.update("contador", "3");

					tv.setText("" + printFormat.format(System.currentTimeMillis()));



				}
			});
//
//
//			btnMenu.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View v) {
//					// TODO Auto-generated method stub
//
//					final Dialog dialog = new Dialog(ourContext);
//					dialog.setContentView(R.layout.menu_4);
//					dialog.setCancelable(false);
//					dialog.show();
//
//					Button btnTerminarJornada = (Button) dialog.findViewById(R.id.btn_1);
//					Button btnCamion = (Button) dialog.findViewById(R.id.btn_2);
//					fdi = (Button) dialog.findViewById(R.id.btn_4);
//					Button btnCambio = (Button) dialog.findViewById(R.id.btn_3);
//
//					btnTerminarJornada.setOnLongClickListener(new OnLongClickListener() {
//
//						@Override
//						public boolean onLongClick(View v) {
//
//							dialog.hide();
//
//							AlertDialog.Builder builder = new AlertDialog.Builder(Cf.this);
//					        builder.setTitle("Terminar jornada");
//					        builder.setMessage("Esta seguro(a)?");
//					        builder.setPositiveButton("Aceptar", null);
//
//					      //  AlertDialog dialog = builder.create();
//					       // dialog.show();
//
//					        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//					    	    public void onClick(DialogInterface dialog, int whichButton) {
//					    	    	setHeader2("");
//									db.update("contador", "cierre");
//									senderF1();
//					    	    }})
//					    	 .setNegativeButton(android.R.string.no, null).show();
//
//							return true;
//						}
//					});
////
//					btnCamion.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//							dialog.hide();
//							camion();
//						}
//					});
//
//
//					if (fueraDeIntento) {
//						fdi.setText("Conteo con intentos");
//					}
//					else {
//						fdi.setText("Conteo fuera de intentos");
//					}
//
//
//					fdi.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//
//							dialog.hide();
//
//							//MenuItem menuItem = menuMain.findItem(R.id.fdi);
//
//
//							RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_1);
//
//							if (!fueraDeIntento) {
//								fdi.setText("Conteo con intentos");
//								tv2.setText("Conteo fuera de intentos");
//								rl.setBackgroundColor(Color.BLACK);
//								tv.setTextColor(Color.WHITE);
//								tv2.setTextColor(Color.WHITE);
//								fueraDeIntento = true;
//							}
//							else {
//								fdi.setText("Conteo fuera de intentos");
//								tv2.setText("Conteo con intentos");
//								rl.setBackgroundColor(Color.WHITE);
//								tv.setTextColor(Color.BLACK);
//								tv2.setTextColor(Color.BLACK);
//								fueraDeIntento = false;
//							}
//						}
//					});
//
//					btnCambio.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//
//							dialog.hide();
//							setHeader("4");
//					    	db.update("contador", "4");
//							tv.setText("" + printFormat.format(System.currentTimeMillis()));
//						}
//					});
//
////					switch (item.getItemId()) {
////
////
////
////				    case R.id.fdi:
////
//////				    	setHeader("3");
//////				    	db.update("contador", "3");
//////						tv.setText("" + printFormat.format(System.currentTimeMillis()));
////
////				    	//tv.setText("" + printFormat.format(System.currentTimeMillis()));
////
////						//MenuItem menuItem = (MenuItem) findViewById(R.id.fdi);
////						MenuItem menuItem = menuMain.findItem(R.id.fdi);
////
////
////						RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_1);
////
////						if (!fueraDeIntento) {
////							menuItem.setTitle("Conteo con intentos");
////							tv2.setText("Conteo fuera de intentos");
////							rl.setBackgroundColor(Color.BLACK);
////							tv.setTextColor(Color.WHITE);
////							tv2.setTextColor(Color.WHITE);
////							fueraDeIntento = true;
////						}
////						else {
////							menuItem.setTitle("Conteo fuera de intentos");
////							tv2.setText("Conteo con intentos");
////							rl.setBackgroundColor(Color.WHITE);
////							tv.setTextColor(Color.BLACK);
////							tv2.setTextColor(Color.BLACK);
////							fueraDeIntento = false;
////						}
////
////				      return true;
////				    case R.id.cambio:
////
////				    	setHeader("4");
////				    	db.update("contador", "4");
////						tv.setText("" + printFormat.format(System.currentTimeMillis()));
////
////				      return true;
////				    default:
////				      return super.onOptionsItemSelected(item);
////				    }
//
//					return true;
//				}
//			});
//		}


	}

	private void setSupportActionBar(Toolbar toolbar) {
	}
	/*
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
	    int action = event.getAction();
	    int keyCode = event.getKeyCode();

	    //Log.i("action", "" + action);
	    //Log.i("keyCode", "" + keyCode);


	        switch (keyCode) {



	        case KeyEvent.KEYCODE_VOLUME_UP:
	            if (action == KeyEvent.ACTION_UP) {

	            	db.insertNite();
					setHeader();


					//int numero = Integer.parseInt(tv.getText().toString());



//					numero++;

					tv.setText("");
					db.update("contador", "1");
					db.update("fecha", "" + dateFormat.format(System.currentTimeMillis()));

					SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss:SSS");

					tv.setText("" + printFormat.format(System.currentTimeMillis()));




	            }
	            else if (action == KeyEvent.ACTION_DOWN) {
	            	tv.setText("");

	            }
	            return true;

	        case KeyEvent.KEYCODE_ENTER:
	            if (action == KeyEvent.ACTION_UP) {

	            	db.insertNite();
					setHeader();


					//int numero = Integer.parseInt(tv.getText().toString());



//					numero++;

					tv.setText("");
					db.update("contador", "1");
					db.update("fecha", "" + dateFormat.format(System.currentTimeMillis()));

//					tv.setText("" + numero);
					SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss:SSS");

					tv.setText("" + printFormat.format(System.currentTimeMillis()));



	            }
	            else if (action == KeyEvent.ACTION_DOWN) {

	            	tv.setText("");


	            }
	            return true;

//	        case KeyEvent.KEYCODE_VOLUME_DOWN:
//	            if (action == KeyEvent.ACTION_DOWN) {
//	            	db.insertNite();
//					setHeader();
//
//
//					//int numero = Integer.parseInt(tv.getText().toString());
//
//
//
//					numero--;
//
//
//					db.update("contador", "" + numero);
//					db.update("fecha", "" + numero);
//
//
//					tv.setText("" + numero);
//	            }
//	            return true;
	        default:
	            return super.dispatchKeyEvent(event);
	        }
	    }

	*/


	public void setHeader(String genero){

//		Bundle extras = getIntent().getExtras();
		String idJornada = "";
		String psw = db.getRow("sesion", "psw", "", "");

		StringBuilder header2 = new StringBuilder()

			.append("INSERT INTO ")
			.append("tblContadorSimel")
			.append(" (`version`, `marked`, `clave`, `usuario`, `idJornada`, `genero`, `contador`, `fecha`, `fechaEnvio`) VALUES ( '");




			header2
				.append(getResources().getString(R.string.app_ver)).append("', '")
				.append("0").append("', '")
				.append(psw).append("', '")
				.append(db.getRow("sesion", "user", "", "")).append("', '")
				//.append(extras.getString("idJornada")).append("', '")
				.append(db.getField("idJornada")).append("', '")

				.append(genero).append("', '")
				.append("1").append("', '")
				.append(dateFormat.format(System.currentTimeMillis())).append("', '")
				.append("fechaEnvio").append("')");

		db.insertNite2(header2.toString());


		if (ContextCompat.checkSelfPermission(Cf.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
			Toast.makeText(Cf.this, "No Se ocupa el permiso", Toast.LENGTH_SHORT).show();
			TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			Log.i("tm", "el tm: " + tm.toString() + " " + Build.MANUFACTURER);
		}
		else {
			//Log.i("else", "else");
			Toast.makeText(Cf.this, "Se ocupa el permiso", Toast.LENGTH_SHORT).show();

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
			}

//			if(ActivityCompat.shouldShowRequestPermissionRationale(Cf.this, Manifest.permission.READ_PHONE_STATE)) {
//				ActivityCompat.requestPermissions(Cf.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
//				//Toast.makeText(Resumen.this, "Se ocupa el permiso", Toast.LENGTH_SHORT).show();
//			}
		}



//		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//			//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//			return;
//		}
//
//		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//
//		Log.i("tm", "el tm: " + tm.toString() + " " + android.os.Build.MANUFACTURER);
//
//		if(tm.getDeviceId() == null)
//			db.update("tablet", Build.MANUFACTURER + "_" + Build.SERIAL + "_tab");
//		else
//			db.update("tablet", Build.MANUFACTURER + "_" + tm.getDeviceId() + "_tel");


	}



//	public void f1() {
//
//		setContentView(R.layout.observations);
//
//		final TextView observaciones = (TextView) findViewById(R.id.obs);
//		final Button btnSigObservaciones = (Button) findViewById(R.id.accept);
//		final Button btnNoSeTiene = (Button) findViewById(R.id.btn_no_data);
//		final TextView tot_per = (TextView) findViewById(R.id.data);
//
//		btnNoSeTiene.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				tot_per.setText("");
//				btnNoSeTiene.setPressed(true);
//				return true;
//			}
//		});
//
//		tot_per.setOnTouchListener(new OnTouchListener() {
//
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				imm.showSoftInput(tot_per, InputMethodManager.SHOW_IMPLICIT);
//				btnNoSeTiene.setPressed(false);
//				return false;
//			}
//		});
//
//		observaciones.setOnTouchListener(new OnTouchListener() {
//
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				imm.showSoftInput(observaciones,
//						InputMethodManager.SHOW_IMPLICIT);
//				return false;
//			}
//		});
//
//		btnSigObservaciones.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//				String obv = observaciones.getText().toString().replaceAll("[^\\dA-Za-z:).@ ]", "");
//
//				String tot = tot_per.getText().toString().replaceAll("[^\\dA-Za-z:).@ ]", "");
//
//				if (obv.trim().length() == 0)
//					obv = "-";
//
//				if (btnNoSeTiene.isPressed())
//					tot = "00";
//
//				if (!tot.isEmpty()) {
//
//
//
////					db.update("observaciones", obv);
//
////					db.update("totPer", tot);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////					imm.hideSoftInputFromWindow(observaciones.getWindowToken(),
////							0);
////					imm.hideSoftInputFromWindow(tot_per.getWindowToken(), 0);
//
//
//
//					senderF1();
//				} else {
//					Toast.makeText(Cf.this, "Falta n�mero total de personas",
//							Toast.LENGTH_LONG).show();
//				}
//
//			}
//		});
//	}


	public void setHeader2(String genero){

	//	Bundle extras = getIntent().getExtras();
		String idJornada = "";
//		String tipoJornada = "";
		String psw = db.getRow("sesion", "psw", "", "");






		StringBuilder header2 = new StringBuilder()

			.append("INSERT INTO ")
			.append("tblContadorSimel")
			.append(" (`version`, `fechaEnvio`, `envio`, `tipoJornada`, `marked`, `clave`, `usuario`, `idJornada`, `iniJornada`, `finJornada`, `genero`, `contador`, `fecha`) VALUES ( '")



//			.append("tablet = '").append(android.os.Build.MANUFACTURER).append("_").append(Build.SERIAL).append("',")


//			.append("version = '").append(getResources().getString(R.string.app_ver)).append("',")
			.append(getResources().getString(R.string.app_ver)).append("', '")

//			.append("fechaEnvio = '").append("").append("',")
			.append("fechaEnvio").append("', '")

//			.append("envio = '").append("").append("',")
			.append("").append("', '");





//		if (extras != null) {
		//	idJornada = extras.getString("idJornada");
			//tipoJornada = extras.getString("tipoJornada");


			header2
				//.append("tipoJornada = '").append(extras.getString("tipoJornada")).append("', ")
				//.append(extras.getString("tipoJornada")).append("', '")
				.append(db.getField("idJornada")).append("', '")

//				.append("marked = '0', ")
				.append("0").append("', '")

//				.append("clave = '").append(psw).append("',")
				.append(psw).append("', '")

//				.append("usuario = '").append(db.getRow("sesion", "user", "", "")).append("',")
				.append(db.getRow("sesion", "user", "", "")).append("', '")

//				.append("idJornada = '").append(idJornada).append("'")
				//.append(extras.getString("idJornada")).append("', '")
				.append(db.getField("idJornada")).append("', '")

//				.append("iniJornada = '").append(fechaJornada).append("', ")
//				.append(fechaJornada).append("', '")
				.append("0000-00-00 00:00:00").append("', '")

//				.append("finJornada = '").append("0000-00-00 00:00:00").append("',")
				.append("0000-00-00 00:00:00").append("', '")

//				.append("genero = '").append(genero).append("',")
				.append(genero).append("', '")

//				.append("contador = '").append("1").append("',")
				.append("1").append("', '")

//				.append("fecha = '").append(dateFormat.format(System.currentTimeMillis())).append("', ")
				.append(dateFormat.format(System.currentTimeMillis())).append("')");



			//db.queryUpdate("iniJornada = '" + fechaJornada + "', tipoJornada = '" + extras.getString("tipoJornada") + "'");


//		} else{
//
//			header2.append("tipoJornada = '").append(db.getLastExistentField("tipoJornada")).append("', ")
//				.append("iniJornada = '").append("0000-00-00 00:00:00").append("', ");
//
//
//			idJornada = db.getLastExistentField("idJornada");
//			//db.queryUpdate("tipoJornada = '" + db.getLastExistentField("tipoJornada") + "', iniJornada = '0000-00-00 00:00:00'");
//
//
//		}





//		header2.append("idJornada = '").append(idJornada).append("'");

		//db.update("usuario", db.getSesion("user"));
		db.insertNite2(header2.toString());

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

			return;
		}

		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		if(tm.getDeviceId() == null)
			db.update("tablet", Build.MANUFACTURER + "_" + Build.SERIAL + "_tab");
		else
			db.update("tablet", Build.MANUFACTURER + "_" + tm.getDeviceId() + "_tel");
	}

	public void senderF1() {
		setContentView(R.layout.legend);



		Button btn = findViewById(R.id.btn_hombre);
		btn.setTextSize(50);
		btn.setText("Concluyendo jornada.");

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.contadorsimel.Resumen"));

			}
		});

	}

//	public void cfTermine() {
//		setContentView(R.layout.legend);
//
//		Button btn =  (Button) findViewById(R.id.btn);
//		btn.setTextSize(50);
//		btn.setText("PASE A LA SIGUIENTE PERSONA");
//
//		btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				db.queryUpdate(	  "fechaFinal = '" + dateFormat.format(System.currentTimeMillis()) 	+ "',"
//						+ "f_mes = '" + (Calendar.getInstance().get(Calendar.MONTH) + 1) 	+ "',"
//						+ "f_dia = '" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) 	+ "',"
//						+ "f_ano = '" + Calendar.getInstance().get(Calendar.YEAR) 			+ "',"
//						+ "hor_f = '" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY)	+ "',"
//						+ "min_f = '" + Calendar.getInstance().get(Calendar.MINUTE)			+ "',"
//						+ "status = 'completo'");
//
////		cedulaFiltro();
//		startActivity(new Intent("com.pds.Cf"));
//
//			}
//		});
//
//	}


//		public void actualizarCronometro(double tiempo) {
//			tv.setText(String.format("%.2f", tiempo) + "s");
//		}

		public void camion() {
			final Dialog dialog = new Dialog(ourContext);
			dialog.setContentView(R.layout.camion);
			dialog.setCancelable(true);
			dialog.show();

			Window window = dialog.getWindow();
			window.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);

			Button btnAceptar = dialog.findViewById(R.id.btn_mujer);
			Button cancel = dialog
					.findViewById(R.id.btn_cancel);
			final EditText numero = dialog
					.findViewById(R.id.btn_hombre);
			numero.setInputType(InputType.TYPE_CLASS_NUMBER);
			numero.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});

			cancel.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					dialog.hide();
					getWindow().setSoftInputMode(
						      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
				}
			});

			btnAceptar.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {

					if (numero.getText().toString().isEmpty())
						Toast.makeText(ourContext,
								"Introducir número.",
								Toast.LENGTH_SHORT).show();
					else {



							dialog.dismiss();
							getWindow().setSoftInputMode(
								      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



							final ProgressDialog progressBar = new ProgressDialog(arg0.getContext());
							progressBar.setCancelable(false);
							progressBar
									.setMessage("Puede tardar varios segundos ...");
							progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

							final Handler mihand = new Handler();

							final Runnable msj1 = new Runnable() {

								public void run() {

									Toast.makeText(
											ourContext,
											"Guardado exitosamente",
											Toast.LENGTH_SHORT).show();
								}
							};

							progressBar.show();

							new Thread(new Runnable() {

								public void run() {
									try {
										for (int i = 0; i < Integer.parseInt(numero.getText().toString()); i++)
											setHeader("5");

										mihand.post(msj1);

									}catch (Exception e) {
										e.printStackTrace();
									}

									progressBar.dismiss();
								}
							}).start();




					}
				}
			});
		}


//		public boolean onOptionsItemSelected(MenuItem item) {
//		    switch (item.getItemId()) {
//		    case R.id.item1:
//
//		    	new AlertDialog.Builder(this)
//		    	.setTitle("Terminar jornada")
//		    	.setMessage("Esta seguro(a)?")
//		    	.setIcon(android.R.drawable.ic_dialog_alert)
//		    	.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//		    	    public void onClick(DialogInterface dialog, int whichButton) {
//		    	    	setHeader2("");
//						db.update("contador", "cierre");
//						senderF1();
//		    	    }})
//		    	 .setNegativeButton(android.R.string.no, null).show();
//		      return true;
//
//		    case R.id.camion:
//
//		    	camion();
//		      return true;
//
//
//		    case R.id.fdi:
//
////		    	setHeader("3");
////		    	db.update("contador", "3");
////				tv.setText("" + printFormat.format(System.currentTimeMillis()));
//
//		    	//tv.setText("" + printFormat.format(System.currentTimeMillis()));
//
//				//MenuItem menuItem = (MenuItem) findViewById(R.id.fdi);
//				MenuItem menuItem = menuMain.findItem(R.id.fdi);
//
//
//				RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_1);
//
//				if (!fueraDeIntento) {
//					menuItem.setTitle("Conteo con intentos");
//					tv2.setText("Conteo fuera de intentos");
//					rl.setBackgroundColor(Color.BLACK);
//					tv.setTextColor(Color.WHITE);
//					tv2.setTextColor(Color.WHITE);
//					fueraDeIntento = true;
//				}
//				else {
//					menuItem.setTitle("Conteo fuera de intentos");
//					tv2.setText("Conteo con intentos");
//					rl.setBackgroundColor(Color.WHITE);
//					tv.setTextColor(Color.BLACK);
//					tv2.setTextColor(Color.BLACK);
//					fueraDeIntento = false;
//				}
//
//		      return true;
//		    case R.id.cambio:
//
//		    	setHeader("4");
//		    	db.update("contador", "4");
//				tv.setText("" + printFormat.format(System.currentTimeMillis()));
//
//		      return true;
//		    default:
//		      return super.onOptionsItemSelected(item);
//		    }
//	    }

//	 @Override
//		public void onBackPressed() {
//			// TODO Auto-generated method stub
//			// super.onBackPressed();
//		}
//
//		@Override
//		public void onAttachedToWindow() {
//			super.onAttachedToWindow();
//			this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//		}


	public void showPopup(View v) {
		popup = new PopupMenu(this, v);
		popup.setOnMenuItemClickListener(Cf.this);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.menucontador, popup.getMenu());

		Menu menuOpts = popup.getMenu();

		if (!fueraDeIntento)
			menuOpts.getItem(2).setTitle("Conteo con intentos");
		else
			menuOpts.getItem(2).setTitle("Conteo fuera de intentos");

		popup.show();



	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
	//	return true;
		switch (item.getItemId()) {
			case R.id.item1:
				new AlertDialog.Builder(this)
		    	.setTitle("Terminar jornada")
		    	.setMessage("Esta seguro(a)?")
		    	.setIcon(android.R.drawable.ic_dialog_alert)
		    	.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

		    	    public void onClick(DialogInterface dialog, int whichButton) {
		    	    	setHeader2("");
						db.update("contador", "cierre");
						senderF1();
		    	    }})
		    	 .setNegativeButton(android.R.string.no, null).show();
		      return true;

		    case R.id.camion:
				//camion
		    	camion();
		      return true;


		    case R.id.fdi:
				//fdi
//		    	setHeader("3");
//		    	db.update("contador", "3");
//				tv.setText("" + printFormat.format(System.currentTimeMillis()));

		    	//tv.setText("" + printFormat.format(System.currentTimeMillis()));

				//MenuItem menuItem = (MenuItem) findViewById(R.id.fdi);
				//MenuItem menuItem = menuMain.findItem(R.id.fdi);

				ConstraintLayout rl = findViewById(R.id.relative_1);


				Menu menuOpts = popup.getMenu();


				if (!fueraDeIntento) {
//					menuItem.setTitle("Conteo con intentos");
//					tv2.setText("Conteo fuera de intentos");
					menuOpts.getItem(2).setTitle("Conteo con intentos");
					tv2.setText("Conteo fuera de intentos");
					rl.setBackgroundColor(Color.BLACK);
					tv.setTextColor(Color.WHITE);
					tv2.setTextColor(Color.WHITE);
					fueraDeIntento = true;
				}
				else {
//					menuItem.setTitle("Conteo fuera de intentos");
//					tv2.setText("Conteo con intentos");
					menuOpts.getItem(2).setTitle("Conteo fuera de intentos");
					tv2.setText("Conteo con intentos");
					rl.setBackgroundColor(Color.WHITE);
					tv.setTextColor(Color.BLACK);
					tv2.setTextColor(Color.BLACK);
					fueraDeIntento = false;
				}

		      return true;
		    case R.id.cambio:
				//cambio
		    	setHeader("4");
		    	db.update("contador", "4");
				tv.setText("" + printFormat.format(System.currentTimeMillis()));

		      return true;

		      default:
				return false;
		}
	}

//	public boolean onCreateOptionMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menucontador, menu);
//		return super.onCreateOptionsMenu(menu);
////		MenuInflater inflater = getMenuInflater();
////		inflater.inflate(R.menu.menucontador, menu);
////		return true;
//	}

}