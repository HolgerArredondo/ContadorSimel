package com.contadorsimel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

public class UpdateApp extends AsyncTask<String, Void, Void> {
	private Context context;
	private ProgressDialog dialog;
	
	public void setContext(Context contextf) {
		context = contextf;
		
		
	        
	}

	protected void onPreExecute() {

		dialog = new ProgressDialog(context);
		dialog.setMessage("Descargando...");
//		dialog.setTitle("Progreso");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		dialog.setCancelable(false);
//
//		dialog.setProgress(0);
//		dialog.setMax(100);
		dialog.setCancelable(false);
		dialog.show(); // Mostramos el di�logo antes de comenzar
	}
	 
	@Override
	protected Void doInBackground(String... arg0) {
		
		 /**
         * Simularemos que descargamos un fichero
         * mediante un sleep
         */

//         for (int i = 0; i < 250; i++) {
//               //Simulamos cierto retraso
//               try {Thread.sleep(200); }
//               catch (InterruptedException e) {}
//
//               onProgressUpdate(i/250f); //Actualizamos los valores
//           }
//
//         return 250;
         
         
		try {
			
			System.out.println("Entro");
//			Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
			
			
			URL url = new URL(arg0[0]);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();

		//	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

			//String PATH = "/mnt/sdcard/Download/";
			//String PATH = "/storage/emulated/0/Download/";
			//String storage = Environment.getExternalStorageDirectory().toString();
			String storage = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
			File file = new File(storage);
			//File file = new File(PATH);
			file.mkdirs();
			File outputFile = new File(file, "update.apk");
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileOutputStream fos = new FileOutputStream(outputFile);

			InputStream is = c.getInputStream();

			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len1);
			}
			fos.close();
			is.close();

//			Intent intent = new Intent(Intent.ACTION_VIEW);
//			intent.setDataAndType(
//					Uri.fromFile(new File(PATH + "/update.apk")),
//					"application/vnd.android.package-archive");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag
//															// android returned
//															// a intent error!
//			context.startActivity(intent);



//			storage = Environment.getExternalStorageDirectory().toString() + "/update.apk";
			storage = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/update.apk";

			file = new File(storage);
			Uri uri;
			Intent viewFile;

			if (Build.VERSION.SDK_INT < 24) {
				uri = Uri.fromFile(file);
				viewFile = new Intent(Intent.ACTION_VIEW);
				viewFile.setDataAndType(uri, "application/vnd.android.package-archive");
				viewFile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			} else {

				File toInstall = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "update.apk");

				Uri apkUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", toInstall);
				viewFile = new Intent(Intent.ACTION_INSTALL_PACKAGE);
				viewFile.setData(apkUri);
				viewFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
				//activity.startActivity(intent);



//				uri = Uri.parse(file.getPath()); // My work-around for new SDKs, worked for me in Android 10 using Solid Explorer Text Editor as the external editor.

//				viewFile = new Intent(Intent.ACTION_INSTALL_PACKAGE);
//				viewFile.setDataAndType(uri, "application/vnd.android.package-archive");
//				viewFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

//				file = new File(context.getFilesDir(), "update.apk");
//				uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);

//				uri = FileProvider.getUriForFile(Objects.requireNonNull(context),
//						BuildConfig.APPLICATION_ID + ".provider", file);


//				intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
//				viewFile.setData(uri);
//				intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//				viewFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			}

			context.startActivity(viewFile);



			
			dialog.dismiss();
			
		} catch (Exception e) {
			//Log.e("UpdateAPP", "Update error! " + e.getMessage());
			System.out.println("Update error! " + e.getMessage());
		}
		
         
         return null;
	}
	
	protected void onProgressUpdate(Float... valores) {
//		int p = Math.round(100 * valores[0]);
//		dialog.setProgress(p);
		dialog.dismiss();
	}

	protected void onPostExecute(Integer bytes) {
		dialog.dismiss();
	}

}
