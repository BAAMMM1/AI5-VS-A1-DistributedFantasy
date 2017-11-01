package app.layerDataAccess.blackboard;

import java.net.InetAddress;

public interface IBlackboardStorage {
	
	String getUrl();
	void setIp(InetAddress ip);
	void setPort(int port);

}
