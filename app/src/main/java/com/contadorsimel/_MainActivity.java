package com.contadorsimel;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _MainActivity extends AppCompatActivity {
    private static final String TAG = _MainActivity.class.getName();
    private Button btnRequest;
    Context ourContext = _MainActivity.this;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://run.mocky.io/v3/a7ee31ab-4f30-4d56-9db7-4586af073a0b";
//    private String URLVOLLEY = "http://calidad-de-vida.org/envio/volley.php";
    private String URLVOLLEY = "http://calidad-de-vida.org/dei/envio/envioTabletContadorV2Simel.php";

    private String URLBKP = "http://calidad-de-vida.org/dei/envio/respaldoContador_25.php";
    private String URLGPS = "http://calidad-de-vida.org/dei/envio/gpsContador.php";
    private String URLPOST = "http://calidad-de-vida.org/dei/envio/envioTabletContadorV2Simel.php";

    int i = 0;
    boolean aux = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.login);
     //   setContentView(R.layout.cf_01);

        //Button btn = (Button) findViewById(R.id.btn_1);

//        setContentView(R.layout.cu_botones_3);







        setContentView(R.layout.activity_main);
//
//
//
//
        btnRequest = (Button) findViewById(R.id.buttonRequest);
//

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressBkp = new ProgressDialog(_MainActivity.this);
                final ProgressDialog progressCyan = new ProgressDialog(_MainActivity.this);
                final Handler mihand = new Handler();

                progressBkp.setCancelable(false);
                progressBkp.setMessage("Preparando envio. Puede tardar varios segundos ...");
                progressBkp.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressBkp.show();

                int size = 100;
                int sizeGps = 50;
                final Handler cambio = new Handler() {
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

                final Handler handle = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        progressCyan.incrementProgressBy(1);
                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<5;i++){
                            // the thread will sleep for the 500 milli seconds
                            try {
                                Thread.sleep(500);
                            }
                            catch(InterruptedException e) {
                                System.out.println(e);
                            }
                            System.out.println(i);
                        }

                        if (sizeGps > 0) {
                            for(int i = 0; i < sizeGps; i++){
                                // the thread will sleep for the 500 milli seconds
                                try {
                                    Thread.sleep(500);
                                }
                                catch(InterruptedException e) {
                                    System.out.println(e);
                                }
                                System.out.println("gps: " + i);
                            }
                        }

                        progressBkp.dismiss();
                        cambio.sendEmptyMessage(0);

                        for(int i = 0; i < size; i++) {
                            try {
                                Thread.sleep(500);
                                handle.sendMessage(handle.obtainMessage());
                            }
                            catch(InterruptedException e) {
                                System.out.println(e);
                            }
                        }


                        progressCyan.dismiss();
                        mihand.post(imprimeThread("Envio exitoso."));
                    }
                }).start();
            }
        });
//        btnRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//
//                boolean connected = false;
//                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//                    //we are connected to a network
//                    connected = true;
//                }
//                else
//                    connected = false;
//                if (connected) {
//                    i = 0;
//                    Db db = new Db(ourContext);
//
//                    final ArrayList<String> idJornadasBkp = db.getJornadasBkp();
//                    //		final ArrayList<String> idJornadasGps = db.getJornadasGps();
//                    final int sizeBkp = idJornadasBkp.size();
//                    final int size = db.getCountTbl();
//                    Log.i("size", "" + size);
//                    if (sizeBkp > 0) {
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                do {
//                                    final HashMap<String, List<String>> data = db
//                                            .selectCuestionario("4", "");
//
//                                    String tablet = db.getLastExistentField("tablet");
//                                    String idJornada = data.get("idJornada").get(0).toString();
//                                    String clave = data.get("clave").get(0).toString();
//                                    String fecha = data.get("fecha").get(0).toString();
//                                    String app = data.get("app").get(0).toString();
//
//                                    HashMap<String, String> inputs = new HashMap<String, String>();
//                                    inputs.put("resultado", data
//                                            .get("resultado").get(0).toString().toString().replace("'fechaEnvio'", "now()"));
//                                    inputs.put("tablet", tablet);
//                                    inputs.put("idJornada", idJornada);
//                                    inputs.put("clave", clave);
//                                    inputs.put("fecha", fecha);
//                                    inputs.put("app", app);
//                                    inputs.put("id", data.get("id").get(0).toString());
//
//                                    //System.out.println(Arrays.asList(inputs));
//                                    try {
//
//                                        sendAndRequestResponse(inputs);
////                                            Db db = new Db(ourContext);
//                                            db.setMarked(inputs.get("id").toString());
//
//
//                                    } catch (Exception error1) {
//                                        i = size;
//                                        Toast.makeText(getApplicationContext(),"Error 1:" + error1.toString(), Toast.LENGTH_LONG).show();
//                                    };
//                                    i++;
//                                } while (i < size);
//
//                               // Thread.interrupted();
//                            }
//                        }).start();
//                    }
//
//
//
//
//
//
//
//
////                    sendAndRequestResponse();
//                }
//                else
//                    Toast.makeText(getApplicationContext(),"No hay conexión a Internet:", Toast.LENGTH_LONG).show();//display the response on screen
//
//            }
//        });

        /**
         * GPS
         */
//        setContentView(R.layout.activity_main_2);
        /**
         * Fin GPS
         */
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.testmenu, popup.getMenu());
        popup.show();
    }

    private void sendAndRequestResponse(HashMap<String, String> inputs) {
//    private void sendAndRequestResponse() {


        //aux = false;
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);


//        HashMap<String, String> inputs = new HashMap<String, String>();
//        inputs.put("uno", "valueUno");
//        inputs.put("dos", "valueDos");


//        Db db = new Db(_MainActivity.this);
//        db.insertNite();
//        db.insertData("perro", 1);

        //String Request initialized

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URLVOLLEY, new JSONObject(inputs),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        try {
//
//                        Toast.makeText(getApplicationContext(),"Response 1:" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//                           // JSONObject object = new JSONObject(response.toString());
////                        try {
//////                            JSONArray jsonArray = response.getJSONArray("uno");
////
////
////                           // Toast.makeText(getApplicationContext(),"Response :" + jsonArray.toString(), Toast.LENGTH_LONG).show();//display the response on screen
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
//
//
//
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Error: ", error.getMessage());
//            }
//        });



        mStringRequest = new StringRequest(Request.Method.POST, URLVOLLEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//            public void onResponse(JSONObject response) {

//                String users = null;
//
//                try {
//
//
//                    JSONObject object = new JSONObject(response.toString());
//                    users = object.getJSONArray("users").get(0).toString();
//
//                    JSONObject object2 = new JSONObject(users);
//                    users = object2.getString("id");
//                    //JSONArray arrayObject = new JSONArray(object.getJSONArray("users"));
//                  //  users = arrayObject.get
//                   // Toast.makeText(getApplicationContext(),"Response :" + users, Toast.LENGTH_LONG).show();//display the response on screen
//                    Toast.makeText(getApplicationContext(),"Response :" + object.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


                //  Toast.makeText(getApplicationContext(),"Response 2:" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                Toast.makeText(getApplicationContext(),"Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen

//                System.out.println(Arrays.asList(inputs));
//                Db db = new Db(ourContext);
//                db.setMarked(inputs.get("id").toString());
                //    aux = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(TAG + " " + "Error :" + error.toString());
//                Log.i(TAG,"Error :" + error.toString());
                //   aux = false;
            }
        }){
            @Override
            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
                Map<String,String> params = inputs;
                //params.put("uno", "valueUno");
                //params.put("dos", "valueDos");
                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
        // mRequestQueue.add(jsonObjectRequest);

        //  return aux;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.testmenu, menu);
        return true;
    }

    public Runnable imprimeThread(final String MSG) {
        final Runnable trono = new Runnable() {
            public void run() {
                Toast.makeText(ourContext, MSG, Toast.LENGTH_SHORT).show();
            }
        };
        return trono;
    }
}