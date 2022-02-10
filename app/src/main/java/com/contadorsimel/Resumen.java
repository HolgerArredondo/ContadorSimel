package com.contadorsimel;


import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class Resumen extends AppCompatActivity {
    Context ourContext = Resumen.this;
    Db db = new Db(ourContext);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        resumenJornada();
    }

    public void resumenJornada(){
        setContentView(R.layout.resumen);

        final Button continuar = (Button) findViewById(R.id.continuar);
        final TextView idJornadaResumen = (TextView) findViewById(R.id.idJornada);


        final Button btnNoSeTiene = (Button) findViewById(R.id.btn_no);
        final TextView tot_per = (TextView) findViewById(R.id.tot_per);
        final TextView observaciones = (TextView) findViewById(R.id.observaciones);

        final TableRow tr = (TableRow) findViewById(R.id.tableRow6);


        observaciones.setVisibility(View.GONE);

        btnNoSeTiene.setVisibility(View.GONE);
        tr.setVisibility(View.GONE);
        tot_per.setVisibility(View.GONE);



//			final String idJornada = db.getLastExistentField("idJornada");

        final String idJornada = db.getField("idJornada");



        String labels[] = db.getInfoJornadas(idJornada);


        String infoJornada = "IDJ[" + idJornada + "] | " + labels[0] + " " +
                labels[1] + " " + getResources().getString(R.string.flujo)
                + " " + labels[4] + " " + labels[5] + " " + labels[6];

//			ArrayList<String> claves = db.selecLastClaves("clave", idJornada);



        idJornadaResumen.setText(infoJornada);





//				progressBar.dismiss();
//
//			}
//		}).start();


//		btnNoSeTiene.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				tot_per.setText("");
//				btnNoSeTiene.setPressed(true);
//				return true;
//			}
//		});

        tot_per.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // InputMethodManager imm = (InputMethodManager)
                // getSystemService(Context.INPUT_METHOD_SERVICE);
                // imm.showSoftInput(tot_per, InputMethodManager.SHOW_IMPLICIT);
                btnNoSeTiene.setPressed(false);
                return false;
            }
        });

        continuar.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                String tot = tot_per.getText().toString().replaceAll("[^\\dA-Za-z:).@ ]", "");
                String obv = observaciones.getText().toString().replaceAll("[^\\dA-Za-z:).@ ]", "");



                if (ContextCompat.checkSelfPermission(Resumen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    //  Toast.makeText(Resumen.this, "El permiso ya esta autorizado", Toast.LENGTH_SHORT).show();
                    respaldo(tot, obv, btnNoSeTiene.isPressed(), v.getContext());
                    //ActivityCompat.requestPermissions(Resumen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                }
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(Resumen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(Resumen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                        //Toast.makeText(Resumen.this, "Se ocupa el permiso", Toast.LENGTH_SHORT).show();
                    }


//                                    ActivityCompat.requestPermissions(Resumen.this,
//                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                            2);
                }
//				final String idJornada = db.getLastExistentField("idJornada");


            }
        });
    }

    public void respaldo(String tot_per,  String observaciones, boolean btnNoSeTiene, Context view) {

        final String idJornada = db.getField("idJornada");

        final String dir = getResources().getString(R.string.bkp_dir);

        String tot = tot_per;
        String obv = observaciones;





        if (obv.isEmpty())
            obv = "SIN OBSERVACIONES";

        if (btnNoSeTiene)
            tot = "00";

//				if (!tot.isEmpty()) {
        TableRow t = (TableRow) findViewById(R.id.tableRow6);
        t.setVisibility(View.GONE);

        TextView tv = (TextView) findViewById(R.id.TextView01);
        tv.setVisibility(View.GONE);
        //observaciones.setVisibility(View.GONE);
//                continuar.setVisibility(View.GONE);

//					db.update("observacionesJor", obv);
//					db.update("totPerJor", tot);
        db.update("finJornada", dateFormat.format(System.currentTimeMillis()));
        try {

            final ProgressDialog progressBar = new ProgressDialog(view);
            progressBar.setCancelable(false);
            progressBar
                    .setMessage("Creando respaldo. Puede tardar varios segundos ...");
            progressBar
                    .setProgressStyle(ProgressDialog.STYLE_SPINNER);

            final Handler mihand = new Handler();

            final Runnable msj = new Runnable() {

                public void run() {
                    Toast.makeText(Resumen.this,
                            "Respaldo creado exitosamente.",
                            Toast.LENGTH_SHORT).show();
                }
            };

            progressBar.show();

            new Thread(new Runnable() {

                public void run() {
                    try {







                        //Crear envio
                        //String idJornada = db.getLastExistentField("idJornada");
                        int jornadaSize = db.getCountIdJornada2(idJornada);
                        ArrayList<String> id = db.getId(idJornada);


                        int count = 1;

                        do{
                            db.setEnvio2(idJornada, "" + count, id.get( count - 1 ).toString(), "" + jornadaSize );
                            count++;
                        }while(count <= jornadaSize);
                        //Fin crear

//									if(!tot_per.getText().toString().isEmpty())
//										db.setTotPerJor(idJornada, tot_per.getText().toString());
//									else
//										db.setTotPerJor(idJornada, "00");
//									final HashMap<String, List<String>> data = db
//											.selectCuestionario("2", idJornada);
//
//									String  r = data.get("resultado").toString();
//
//									ArrayList<String> resp = new ArrayList<String>();
//
//									resp.add(r);

                        ArrayList<String> resp = db.selectRespaldo(idJornada);

                        File root = new File(dir);



//                                if (ActivityCompat.checkSelfPermission(Resumen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                    //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//                                    return;
//                                }

//                                if (ActivityCompat.checkSelfPermission(Resumen.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                    //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//                                    return;
//                                }
//                                if (ActivityCompat.checkSelfPermission(Resumen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                    ActivityCompat.requestPermissions(Resumen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//                                }

                        // int algo = ContextCompat.checkSelfPermission(Resumen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);




                        if (!root.exists()) {
                            root.mkdirs();
                        }
                        //myFile.mkdirs();

                        String nombre = "bkp_contadorSimel_"
                                + idJornada + "_" + dateFormat.format(System.currentTimeMillis())
                                + ".txt";

                        nombre = nombre.replace("-", "_");
                        nombre = nombre.replace(":", "_");

                        File myFile = new File(root , nombre);

                        FileWriter writer = new FileWriter(myFile);
                        writer.append("" + resp);
                        writer.flush();
                        writer.close();


                        mihand.post(imprimeThread2("Respaldo creado exitosamente."));



                    } catch (final MalformedURLException e1) {
                        mihand.post(imprimeThread2("3 " + e1.toString()));
                    } catch (final IOException e) {
                        mihand.post(imprimeThread2("2 " + e.toString()));
                    }

                    progressBar.dismiss();

                    mihand.post(imprimeThread2("Respaldo creado exitosamente."));

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        mihand.post(imprimeThread2("1 " + e.toString()));
                    }


                    startActivity(new Intent("com.contadorsimel.Login"));

                }
            }).start();




        } catch (Exception e) {
        }




//				} else {
//					Toast.makeText(Resumen.this,
//							"Falta n?mero total de personas", Toast.LENGTH_SHORT)
//							.show();
//				}
    }
    public Runnable imprimeThread2(final String MSG) {
        final Runnable trono = new Runnable() {

            public void run() {
                Toast.makeText(ourContext, MSG, Toast.LENGTH_SHORT).show();

            }
        };
        return trono;
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//
//
//            case 2:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    permisoDeAlmacenamientoConcedido();
//                } else {
//                    permisoDeAlmacenamientoDenegado();
//                }
//                break;
//
//            // Aqui mas casos dependiendo de los permisos
//            // case OTRO_CODIGO_DE_PERMISOS...
//
//        }
//    }


//	@Override
//	public void onAttachedToWindow() {
//		super.onAttachedToWindow();
//		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//	}
}