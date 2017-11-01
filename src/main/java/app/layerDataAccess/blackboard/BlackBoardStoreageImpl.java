package app.layerDataAccess.blackboard;

import java.net.InetAddress;

public class BlackBoardStoreageImpl implements IBlackboardStorage{
	
	private int port;
	private InetAddress ip;

	public BlackBoardStoreageImpl() {		
	}

	@Override
	public String getUrl() {		
		return "http://" + ip + ":" + port;
	}

	@Override
	public void setIp(InetAddress ip) {
		System.out.println("Set ip: " + ip);
		this.ip = ip;
		
	}

	@Override
	public void setPort(int port) {
		System.out.println("Set port: " + port);
		this.port = port;
		
	}

	
	

}
