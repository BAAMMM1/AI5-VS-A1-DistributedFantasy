package app.layerLogicAndService.cmpBlackboard;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class Blackboard {

	private int port;
	private InetAddress ip;
	private String name;
	private String password;
	private String token;
	private String valid;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}
