import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;

/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * DatagramSocket which seperate Thread to recieve 0-100 from android controller.
 */
public class SocketReciever extends Observable {
	int port = 8000;
    byte[] buf = new byte[1000];
    DatagramPacket dgp = new DatagramPacket(buf, buf.length);
    DatagramSocket sk;

	/**
	 * Constructor of Socket.
	 * @param port
	 */
	public SocketReciever(int port) {
		this.port = port;
		try {
			sk = new DatagramSocket(port);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		System.out.println("Server started");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						sk.receive(dgp);
					} catch (IOException e) {
						e.printStackTrace();
					}
					setChanged();
					try {
						notifyObservers(bytesToInt(buf));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public byte[] intToBytes(int my_int) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeInt(my_int);
		out.close();
		byte[] int_bytes = bos.toByteArray();
		bos.close();
		return int_bytes;
	}

	public int bytesToInt(byte[] int_bytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(int_bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		int my_int = ois.readInt();
		ois.close();
		return my_int;
	}
}