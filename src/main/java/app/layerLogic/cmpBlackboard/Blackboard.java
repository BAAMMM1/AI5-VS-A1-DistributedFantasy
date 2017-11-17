package app.layerLogic.cmpBlackboard;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class Blackboard {

	private int port;
	private InetAddress ip;

	private static Blackboard instance;

	private Blackboard() {

	}

	public static Blackboard getInstance() {

		if (Blackboard.instance == null) {
			Blackboard.instance = new Blackboard ();

		}

		return Blackboard.instance;

	}

	public  void setPort(int port) {
		this.port = port;
	}

	public void setIP(InetAddress ip) {
		this.ip = ip;
	}

	public URL getUrl() {

		try {
			return new URL("http:/" + ip + ":" + port);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
