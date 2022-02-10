package com.contadorsimel;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Login extends AppCompatActivity {
	Context ourContext = Login.this;
	Db db = new Db(ourContext);
	LocationManager locationManager;
	String provider;
	TextView txtMsg;
	Button btnUpdate;

	private static final int REQ_ENTER_PATTERN = 2;

	public void onCreate(Bundle saveInstanceState) {

		super.onCreate(saveInstanceState);

		setContentView(R.layout.cu_botones_8_2);

		Button btn1 = (Button) findViewById(R.id.btn_1);
		Button btn2 = (Button) findViewById(R.id.btn_2);
		Button btn3 = (Button) findViewById(R.id.btn_3);
		Button btn4 = (Button) findViewById(R.id.btn_4);
		Button btn5 = (Button) findViewById(R.id.btn_5);
		Button btn6 = (Button) findViewById(R.id.btn_6);
		Button btn7 = (Button) findViewById(R.id.btn_7);
		Button btn8 = (Button) findViewById(R.id.btn_8);

		btn1.setText("En cualquier sector de la economía (no lo tiene definido)");
		btn2.setText("En cualquier sector de la economía (no lo tiene definido) En cualquier En dos");
		btn3.setText("Visitar familiares o amistades en México");
		btn4.setText("Para conocer México");
		btn5.setText("Para trabajar en Estados Unidosn");
		btn6.setText("Para vivir en Estados Unidos");
		btn7.setText("Visitar familiares o amistades en Estados Unidos");
		btn8.setText("Otra razón");

//		btnH.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				setContentView(R.layout.cu_botones_4_1);
//			}
//		});
////		startActivity(new Intent("com.contadorsimel.Cf"));
////		setContentView(R.layout.cf_01_0);
////
////		Toolbar toolbar =  findViewById(R.id.toolbar);
////		setSupportActionBar(toolbar);
////		getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//		setContentView(R.layout.login);
//////
//		txtMsg = (TextView) findViewById(R.id.toSend);
//		txtMsg.setTextColor(Color.RED);
//		txtMsg.setText("");
//		btnUpdate = (Button) findViewById(R.id.btnUpdate);
//////
//		btnUpdate.setVisibility(View.GONE);
//////
//		if (Integer.parseInt(db.getRow("tblContadorSimel", "COUNT(*)", "marked", "0")) > 0)
//			blink("EXISTEN FOLIOS PENDIENTES POR ENVIAR.");
//		else {
//			//txt.setText("");
//			checkUpdate();
//		}
//		loginCheck();

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menucontador, menu);
//		return super.onCreateOptionsMenu(menu);
//	}

	public void checkUpdate() {
		String URLVOLLEY = "http://emif.mx/paradigma/envio/getNumeroVersion.php";
		RequestQueue mRequestQueue = Volley.newRequestQueue(this);
		StringRequest mStringRequest;
		String TAG = Login.class.getName();
		boolean connected = false;

		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
				connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			//we are connected to a network
			connected = true;
		}
		else
			connected = false;

		if (connected) {
			HashMap<String, String> inputs = new HashMap<String, String>();
			inputs.put("app", getResources().getString(R.string.app_name));
			inputs.put("version", getResources().getString(R.string.app_ver));
			inputs.put("numeroVersion", getResources().getString(R.string.numero_version));

			mStringRequest = new StringRequest(Request.Method.POST, URLVOLLEY, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					//Toast.makeText(getApplicationContext(), "Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen
//					Toast.makeText(getApplicationContext(), "Response 2:" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

					try {

						String users = null;

						JSONObject object = new JSONObject(response.toString());
						users = object.get("numeroVersion").toString();
						boolean status = false;
						status = (boolean) object.get("status");

						//Toast.makeText(getApplicationContext(), "Response:" + users.toString(), Toast.LENGTH_LONG).show();//display the response on screen

						if (status) {
							Toast.makeText(getApplicationContext(), "Response 2:" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
							String nVersion = object.get("numeroVersion").toString();
							blink("Nueva versión disponible");
							btnUpdate.setVisibility(View.VISIBLE);
						} else {
							txtMsg.setText("");
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}


				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					System.out.println(TAG + " " + "Error :" + error.toString());
				}
			}) {
				@Override
				protected Map<String, String> getParams() {
					Map<String, String> params = inputs;
					return params;
				}
			};

			mRequestQueue.add(mStringRequest);

		}
		else
			Toast.makeText(getApplicationContext(),"No hay conexión a Internet:", Toast.LENGTH_LONG).show();//display the response on screen
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
	private void blink(final String TXT) {
		final Handler handler = new Handler();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				handler.post(new Runnable() {
					@Override
					public void run() {

						txtMsg.setText(TXT);

						if (txtMsg.getVisibility() == View.VISIBLE)
							txtMsg.setVisibility(View.INVISIBLE);
						else
							txtMsg.setVisibility(View.VISIBLE);

						blink(TXT);
					}
				});
			}
		}).start();
	}
//
	public void loginCheck() {

		Button btnLogin = (Button) findViewById(R.id.btn_login);

		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
					//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

					final int nVersion = Integer.parseInt(ourContext.getResources().getString(R.string.numero_version)) + 1;

					UpdateApp atualizaApp = new UpdateApp();
					atualizaApp.setContext(ourContext);

					atualizaApp.execute("http://emif.mx/paradigma/envio/app/"
							+ ourContext.getResources().getString(R.string.app_name) + "/"
							+ ourContext.getResources().getString(R.string.app_name) + "_"
							+ nVersion
							+ ".apk");
				}
				else {
				//	if(ActivityCompat.shouldShowRequestPermissionRationale(Login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
						ActivityCompat.requestPermissions(Login.this, new String[]{
								Manifest.permission.WRITE_EXTERNAL_STORAGE,
								Manifest.permission.READ_EXTERNAL_STORAGE,
								Manifest.permission.INTERNET
						}, 100);
						//Toast.makeText(Resumen.this, "Se ocupa el permiso", Toast.LENGTH_SHORT).show();
				//	}
				}



			}
		});

		btnLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				EditText user = (EditText) findViewById(R.id.user);
				EditText psw = (EditText) findViewById(R.id.psw);

				if (user.getText().toString().isEmpty()
						|| psw.getText().toString().isEmpty())
					Toast.makeText(ourContext,
							"Introducir clave y/o contraseña de usuario.",
							Toast.LENGTH_SHORT).show();
				else {

					if (db.loginCheck(user.getText().toString(), psw.getText()
							.toString())) {


						startActivity(new Intent("com.contadorsimel.Menu"));
//						startActivity(new Intent("com.contadorsur.Cf"));
//						startActivity(new Intent("com.pds.Cu"));


						//db.updateRow("sesion", "data", "", "");

						Toast.makeText(ourContext,
								db.getRow("sesion", "tipo", "", ""),
								Toast.LENGTH_SHORT).show();

					}
					else
						Toast.makeText(ourContext,
								"Error en validación de usuario.",
								Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
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


}
