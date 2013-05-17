package com.example.gyroscopeandroid;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MyGyroActivity is the gyroscope display activity.
 * 
 * @author Sikarin Larnamwong 5510546174
 * @author Krittayot Techasombooranakit 5510545976
 * @version 2013.05.01
 * 
 */
public class MyGyroActivity extends Activity implements SensorEventListener {

	/**
	 * txtIP is the text view to show InetAddress
	 */
	private TextView txtIP;
	/**
	 * txtPort is the text view to show the port
	 */
	private TextView txtport;
	/**
	 * txtRotation is the text view to show the rotation.
	 */
	private TextView txtRotation;
	/**
	 * sensorManager is the sensor manager.
	 */
	private SensorManager sensorManager;
	/**
	 * txtSend is EditText for the text to send
	 */
	private EditText txtSend;
	/**
	 * socketProcess is the Socket process to manage the socket.
	 */
	private SocketProcess socketProcess;
	/**
	 * port is the port number.
	 */
	private int port;
	/**
	 * IP is the InetAddress.
	 */
	private String IP;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_gyro);
		initializeComponents();
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		txtIP = (TextView) findViewById(R.id.txtIPInput);
		txtport = (TextView) findViewById(R.id.txtPort);

		Bundle extras = getIntent().getExtras();
		port = extras.getInt("port");
		IP = extras.getString("IP");

		txtIP.setText("IP : " + IP);
		txtport.setText("port" + port);
		socketProcess = new SocketProcess(port, IP);

	}

	private void initializeComponents() {

		txtRotation = (TextView) findViewById(R.id.txtRotationX);
		txtSend = (EditText) findViewById(R.id.txtSend);
		txtSend.setEnabled(false);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do nothing

	}

	@Override
	protected void onResume() {
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
		super.onResume();
	}

	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// if sensor is unreliable, return void
		if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
			return;
		}

		float x = event.values[2];
		float y = event.values[1];
		float z = event.values[0];
		float sendData = 0;

		int min = 330;
		int max = 30;

		sendData = 50 + Math.max(-50, Math.min(50, y * 2));
		socketProcess.send((int) sendData);
		txtSend.setText("Send : " + sendData);

		txtRotation.setText("Orientation X (Roll) :"
				+ Float.toString(event.values[2]) + "\n"
				+ "Orientation Y (Pitch) :" + Float.toString(event.values[1])
				+ "\n" + "Orientation Z (Yaw) :"
				+ Float.toString(event.values[0]));

	}
}
