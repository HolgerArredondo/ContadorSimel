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

    //int i = 0;
    boolean aux = false;

    int[] i = {0};
    int[] c = {0};
    boolean[] j = {false};
    int sizeBkp;
    HashMap<String, String> inputs;
    Db db;
    ProgressDialog progressBkp;
    Handler cambio;
    Handler mihand;
    Handler handle;
    int size;
    ProgressDialog progressCyan;

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

//        btnRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final ProgressDialog progressBkp = new ProgressDialog(_MainActivity.this);
//                final ProgressDialog progressCyan = new ProgressDialog(_MainActivity.this);
//                final Handler mihand = new Handler();
//
//                progressBkp.setCancelable(false);
//                progressBkp.setMessage("Preparando envio. Puede tardar varios segundos ...");
//                progressBkp.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progressBkp.show();
//
//                int size = 10;
//                int sizeGps = 10;
//                final Handler cambio = new Handler() {
//                    public void handleMessage(Message msg) {
//                        progressCyan.setCancelable(false);
//                        progressCyan.setMessage("Enviando");
//                        progressCyan
//                                .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                        progressCyan.setProgress(0);
//                        progressCyan.setMax(size);
//                        progressCyan.setProgressNumberFormat(null);
//                        progressCyan.show();
//                    }
//                };
//
//                final Handler handle = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        progressCyan.incrementProgressBy(1);
//                    }
//                };
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        for(int i=1;i<1;i++){
//                            // the thread will sleep for the 500 milli seconds
//                            try {
//                                Thread.sleep(500);
//                            }
//                            catch(InterruptedException e) {
//                                System.out.println(e);
//                            }
//                            System.out.println(i);
//                        }
//
////
////                        if (sizeGps > 0) {
////                            for(int i = 0; i < sizeGps; i++){
////                                // the thread will sleep for the 500 milli seconds
////                                try {
////                                    Thread.sleep(500);
////                                }
////                                catch(InterruptedException e) {
////                                    System.out.println(e);
////                                }
////                                System.out.println("gps: " + i);
////                            }
////                        }
////
////                        progressBkp.dismiss();
////                        cambio.sendEmptyMessage(0);
////
////                        for(int i = 0; i < size; i++) {
////                            try {
////                                Thread.sleep(500);
////                                handle.sendMessage(handle.obtainMessage());
////                            }
////                            catch(InterruptedException e) {
////                                System.out.println(e);
////                            }
////                        }
//
//
//                        progressCyan.dismiss();
//                        mihand.post(imprimeThread("Envio exitoso."));
//                    }
//                }).start();
//            }
//        });
        btnRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

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


                        progressBkp = new ProgressDialog(_MainActivity.this);
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

                                //     do {



                                //  Log.i("bkp " + i[0], resp.toString());



                                //System.out.println(Arrays.asList(inputs));
                                try {




                                    envioBkp();

                                    //    db.setMarked(inputs.get("id").toString());


                                } catch (Exception error1) {
                                    i[0] = sizeBkp;
                                    Log.i("Error 1:", error1.toString());
//                                        System.out.println("Error 1:" + error1.toString());
                                    j[0] = true;
                                    progressBkp.dismiss();

                                    //Toast.makeText(getApplicationContext(),"Error 1:" + error1.toString(), Toast.LENGTH_LONG).show();
                                };
                                // i[0]++;
                                //  } while (i[0] < sizeBkp);

                                if(j[0]) {
                                    progressBkp.dismiss();
                                }
                                //envio los contadores
                                else {
//                                    progressBkp.dismiss();
//                                    i[0] = 0;
//                                    cambio.sendEmptyMessage(0);
//
//                                    //fin bkp
//                                    do {
//                                        final HashMap<String, List<String>> data = db
//                                                .selectCuestionario("4", "");
//
//                                        String tablet = db.getLastExistentField("tablet");
//                                        String idJornada = data.get("idJornada").get(0).toString();
//                                        String clave = data.get("clave").get(0).toString();
//                                        String fecha = data.get("fecha").get(0).toString();
//                                        String app = data.get("app").get(0).toString();
//
//                                        HashMap<String, String> inputs = new HashMap<String, String>();
//                                        inputs.put("resultado", data
//                                                .get("resultado").get(0).toString().toString().replace("'fechaEnvio'", "now()"));
//                                        inputs.put("tablet", tablet);
//                                        inputs.put("idJornada", idJornada);
//                                        inputs.put("clave", clave);
//                                        inputs.put("fecha", fecha);
//                                        inputs.put("app", app);
//                                        inputs.put("id", data.get("id").get(0).toString());
//
//                                        //System.out.println(Arrays.asList(inputs));
//                                        try {
//
//                                            //mRequestQueue = Volley.newRequestQueue(this);
//                                            mStringRequest = new StringRequest(Request.Method.POST, URLVOLLEY, new Response.Listener<String>() {
//                                                @Override
//                                                public void onResponse(String response) {
//                                                  //  Toast.makeText(getApplicationContext(),"Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//                                                    c[0]++;
//                                                    db.setMarked(data.get("id").get(0).toString());
//                                                    handle.sendMessage(handle.obtainMessage());
//                                                    i[0]++;
//
//
//                                                }
//                                            }, new Response.ErrorListener() {
//                                                @Override
//                                                public void onErrorResponse(VolleyError error) {
//                                                  //  Toast.makeText(getApplicationContext(),"Error 2:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//                                                    i[0] = size;
//                                                }
//                                            }){
//                                                @Override
//                                                protected Map<String,String> getParams(){
//
//                                                    Map<String,String> params = inputs;
//                                                    return params;
//                                                }
//                                            };
//
//                                            mRequestQueue.add(mStringRequest);
//
//
//    //                                            Db db = new Db(ourContext);
//                                               // db.setMarked(inputs.get("id").toString());
//
//
//                                        } catch (Exception error1) {
//                                            i[0] = size;
//                                            System.out.println("Error final" + error1.toString());
//                                            //Toast.makeText(getApplicationContext(),"Error 1:" + error1.toString(), Toast.LENGTH_LONG).show();
//                                        };
//                                     //   i[0]++;
//                                    } while (i[0] < size);
//                                    progressCyan.dismiss();

                                }
                                // Thread.interrupted();
                            }
                        }).start();
                    }








//                    sendAndRequestResponse();
                }
                else
                    Toast.makeText(getApplicationContext(),"No hay conexión a Internet:", Toast.LENGTH_LONG).show();//display the response on screen

            }
        });

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
                Toast.makeText(getApplicationContext(),"Error 2:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//                System.out.println(TAG + " " + "Error :" + error.toString());
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


    private void sendAndRequestResponseBkp(String url, HashMap<String, String> inputs) {

        mRequestQueue = Volley.newRequestQueue(this);


        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Response 2:" + inputs.get("id").toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error 2:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
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
    public void envioBkp() {
        url = "http://elcolef.net/Emif/envio/respaldoContador_17.php";

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

            progressBkp.dismiss();

            cambio.sendEmptyMessage(0);

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
}