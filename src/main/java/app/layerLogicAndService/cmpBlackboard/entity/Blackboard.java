package app.layerLogicAndService.cmpBlackboard.entity;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class Blackboard {

	private int blackboardPort;
	private InetAddress blackboardIp;
	private String userName;
	private String userPassword;
	private String userToken;
	private String userTokenValidTime;

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

	public void setIP(InetAddress ip) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getUserTokenValidTime() {
		return userTokenValidTime;
	}

	public void setUserTokenValidTime(String userTokenValidTime) {
		this.userTokenValidTime = userTokenValidTime;
	}

}
