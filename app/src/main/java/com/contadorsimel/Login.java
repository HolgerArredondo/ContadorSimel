package com.contadorsimel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

//		setContentView(R.layout.pruebaimagen);
//
//		ImageView iv = (ImageView) findViewById(R.id.imageView2);
//		Button btn = (Button) findViewById(R.id.button2);
//
//		btn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
//				Bitmap bitmap = bitmapDrawable.getBitmap();
//				saveTOGallery(bitmap);
//			}
//		});

		setContentView(R.layout.login);

//		setContentView(R.layout.cu_botones_8_2);
//
//		Button btn1 = (Button) findViewById(R.id.btn_1);
//		Button btn2 = (Button) findViewById(R.id.btn_2);
//		Button btn3 = (Button) findViewById(R.id.btn_3);
//		Button btn4 = (Button) findViewById(R.id.btn_4);
//		Button btn5 = (Button) findViewById(R.id.btn_5);
//		Button btn6 = (Button) findViewById(R.id.btn_6);
//		Button btn7 = (Button) findViewById(R.id.btn_7);
//		Button btn8 = (Button) findViewById(R.id.btn_8);
//
//		btn1.setText("En cualquier sector de la economía (no lo tiene definido)");
//		btn2.setText("En cualquier sector de la economía (no lo tiene definido) En cualquier En dos");
//		btn3.setText("Visitar familiares o amistades en México");
//		btn4.setText("Para conocer México");
//		btn5.setText("Para trabajar en Estados Unidosn");
//		btn6.setText("Para vivir en Estados Unidos");
//		btn7.setText("Visitar familiares o amistades en Estados Unidos");
//		btn8.setText("Otra razón");

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
		txtMsg = (TextView) findViewById(R.id.toSend);
		txtMsg.setTextColor(Color.RED);
		txtMsg.setText("");
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
////
		btnUpdate.setVisibility(View.GONE);
//////
		if (Integer.parseInt(db.getRow("tblContadorSimel", "COUNT(*)", "marked", "0")) > 0)
			blink("EXISTEN FOLIOS PENDIENTES POR ENVIAR.");
//		else {
//			//txt.setText("");
//			checkUpdate();
//		}
		loginCheck();

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

//		ConnectivityManager connectivityManager = (ConnectivityManager) ourContext.getSystemService(Context.CONNECTIVITY_SERVICE);


		connected = isConnected(ourContext);
//		if(connectivityManager.getActiveNetworkInfo() != null && (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//				connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
//			//we are connected to a network
//			connected = true;
//		}
//		else
//			connected = false;

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


	public static boolean isConnected(Context context){
		ConnectivityManager
				cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
	}


	private void saveTOGallery(Bitmap bitmap) {

	//	Toast.makeText(getApplicationContext(), "Entro a btn 2:", Toast.LENGTH_LONG).show();//display the response on screen

		OutputStream fos;
		try {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {


				ContentValues values = new ContentValues();
				values.put(MediaStore.MediaColumns.DISPLAY_NAME, "menuCategory");       //file name
				values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");        //file extension, will automatically add to file
				values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Kamen Rider Decade/");     //end "/" is not mandatory

				Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);      //important!

				OutputStream outputStream = getContentResolver().openOutputStream(uri);

				outputStream.write("This is menu category data.".getBytes());

				outputStream.close();

//				ContentResolver resolver = getContentResolver();
//				ContentValues contentValues = new ContentValues();
//				contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "Image_" + ".jpg");
//				contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
//				contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "TestFolder");
//
//				Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//				fos = resolver.openOutputStream(Objects.requireNonNull(imageUri));
//				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//				Objects.requireNonNull(fos);


				Toast.makeText(ourContext, "Full", Toast.LENGTH_LONG).show();
			}
			else {
				String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
				String fileName = System.currentTimeMillis() + ".jpg";


					File f = new File(imageDir, "AAA.txt");
					f.createNewFile();
					FileOutputStream fOut = new FileOutputStream(
							f);
					OutputStreamWriter myOutWriter = new OutputStreamWriter(
							fOut);
					myOutWriter.append("Hola mundo");

					myOutWriter.close();
					fOut.close();
					Toast.makeText(getApplicationContext(), "Full 2", Toast.LENGTH_LONG).show();//display the response on screen
//				File file = new File(imageDir, fileName);
//
//				try {
//					fos = new FileOutputStream(file);
//					Toast.makeText(getApplicationContext(), "Full 2", Toast.LENGTH_LONG).show();//display the response on screen
//				} catch (Exception e) {
//					Toast.makeText(this, "Shine2", Toast.LENGTH_LONG).show();
//				}


			}
		}
		catch (Exception e) {
			Toast.makeText(this, "Shine", Toast.LENGTH_LONG).show();
		}
	}
}
