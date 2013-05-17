package com.example.gyroscopeandroid;

import android.os.Bundle;

import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * SettingActivity is the class for setting activity.
 * 
 * @author Sikarin Larnamwong 5510546174
 * @author Krittayot Techasombooranakit 5510545976
 * @version 2013.05.01
 * 
 */
public class SettingActivity extends Activity {

	/**
	 * btPlayer1 is button to be player 1.
	 */
	private Button btPlayer1;
	/**
	 * btPlayer2 is button to be player2.
	 */
	private Button btPlayer2;
	/**
	 * txtIP is the EditText to set the InetAddress
	 */
	private EditText txtIP;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		btPlayer1 = (Button) findViewById(R.id.btPlayer1);
		btPlayer2 = (Button) findViewById(R.id.btPlayer2);
		txtIP = (EditText) findViewById(R.id.txtIP);

		btPlayer1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent p1 = new Intent(getApplicationContext(),
						MyGyroActivity.class);
				p1.putExtra("IP", txtIP.getText().toString());
				p1.putExtra("port", 8888);
				startActivity(p1);

			}
		});

		btPlayer2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent p2 = new Intent(getApplicationContext(),
						MyGyroActivity.class);
				p2.putExtra("IP", txtIP.getText().toString());
				p2.putExtra("port", 8889);
				startActivity(p2);

			}
		});

	}

}
