package com.contadorsimel;





import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashScreen extends AppCompatActivity {
	/*
	 Muestra el logo principal
	 Espera un tiempo
	 Te manda a la pantalla de Login
	 */
	@Override
	public void onCreate(Bundle saveInstanceState) {

		super.onCreate(saveInstanceState);
		setContentView(R.layout.splash);

		ImageView iv = (ImageView) findViewById(R.id.imageView1);

//		ActivityCompat.requestPermissions(SplashScreen.this, new String[]{
//				Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.contadorsimel.Login"));
			//	startActivity(new Intent("com.contadorsimel.Resumen"));
//				startActivity(new Intent("com.contadorsimel._MainActivity"));
//				startActivity(new Intent("com.contadorsimel.Cf"));
			}
		});
	}
}