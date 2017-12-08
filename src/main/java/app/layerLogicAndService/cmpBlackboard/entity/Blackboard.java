package app.layerLogicAndService.cmpBlackboard.entity;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class Blackboard {

	public static String URL;

	private int blackboardPort;
	private InetAddress blackboardIp;
	private User user;

	private static Blackboard instance;

	private Blackboard() {

	}

	public static Blackboard getInstance() {

		if (Blackboard.instance == null) {
			Blackboard.instance = new Blackboard ();

		}

		return Blackboard.instance;

	}

	public  void setBlackboardPort(int blackboardPort) {
		this.blackboardPort = blackboardPort;
	}

	public void setBlackboardIp(InetAddress ip) {
		this.blackboardIp = ip;
	}

	public URL getUrl() {

		try {
			return new URL("http:/" + blackboardIp + ":" + blackboardPort);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getBlackboardPort() {
		return blackboardPort;
	}

	public InetAddress getBlackboardIp() {
		return blackboardIp;
	}

	public void setUser(String name, String userToken, String userTokenValidTime){
		this.user = new User(name, userToken, userTokenValidTime);
	}

	public User getUser() {
		return user;
	}
}
