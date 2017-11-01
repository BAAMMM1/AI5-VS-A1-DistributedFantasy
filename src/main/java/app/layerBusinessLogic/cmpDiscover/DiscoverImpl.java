package app.layerBusinessLogic.cmpDiscover;

import app.layerDataAccess.blackboard.IBlackboardStorage;

public class DiscoverImpl implements IDiscover {
	
	private UDPListener udpListener;
	private IBlackboardStorage blackboardStorage;
	
	public DiscoverImpl(IBlackboardStorage storage, UDPListener udpListener) {
		this.blackboardStorage = storage;
		this.udpListener = udpListener;
	}

	@Override
	public void discoverBlackboard() {	
		System.out.println("starte die Erkundung");
		
		this.udpListener.listen();
		
		System.out.println("Packet empfangen, erstelle Blackboard");
		this.blackboardStorage.setIp(udpListener.getBlackboardIP());
		this.blackboardStorage.setPort(udpListener.getBlackboardPort());
		
		System.out.println(this.blackboardStorage.getUrl());
		
		
	}
	
	

}
