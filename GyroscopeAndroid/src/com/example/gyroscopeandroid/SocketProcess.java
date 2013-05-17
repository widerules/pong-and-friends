package com.example.gyroscopeandroid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

import android.util.Log;

/**
 * Socket process is the class for socket management
 * 
 * @author Sikarin Larnamwong 5510546174
 * @author Krittayot Techasombooranakit 5510545976
 * @version 2013.05.01
 * 
 */
public class SocketProcess {
	private byte[] buffer = null;
	private DatagramSocket datagramSocket = null;
	private DatagramPacket packet = null;
	private Scanner s = new Scanner(System.in);
	private int server_port = 8888;
	private static String IP = "";

	/**
	 * Socket Process is the constructor method of the class
	 * 
	 * @param num
	 *            is the number of the port
	 * @param IP
	 *            is the inet address
	 */
	public SocketProcess(int num, String IP) {
		this.server_port = num;
		this.IP = IP;
	}

	/**
	 * send is the method to send integer through socket.
	 * 
	 * @param n
	 *            is the integer to send
	 */
	public void send(final int n) {
		try {
			datagramSocket = new DatagramSocket(server_port);
			datagramSocket.setBroadcast(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		buffer = new byte[10000];
		packet = new DatagramPacket(buffer, buffer.length);
		s = new Scanner(System.in);

		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				byte[] b = null;
				try {
					b = intToBytes(n);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				DatagramSocket s;

				try {
					s = new DatagramSocket();
					InetAddress local = InetAddress.getByName(SocketProcess.IP);
					DatagramPacket p = new DatagramPacket(b, b.length, local,
							server_port);
					s.send(p);
					Log.d("UDP", "send");
				} catch (Exception e) {
					Log.d("UDP", e.toString());
				}

			}
		});

		a.start();
	}

	/**
	 * 
	 * @param my_int
	 *            is the integer to convert to byte
	 * @return byte data that is converted from integer
	 * @throws IOException
	 */
	public byte[] intToBytes(int my_int) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeInt(my_int);
		out.close();
		byte[] int_bytes = bos.toByteArray();
		bos.close();
		return int_bytes;
	}

	/**
	 * 
	 * @param int_bytes
	 *            is the byte array integer
	 * @return integer that is converted from byte array
	 * @throws IOException
	 */
	public int bytesToInt(byte[] int_bytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(int_bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		int my_int = ois.readInt();
		ois.close();
		return my_int;
	}

}
