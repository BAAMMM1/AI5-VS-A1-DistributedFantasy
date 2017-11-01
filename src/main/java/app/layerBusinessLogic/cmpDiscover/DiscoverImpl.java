package app.layerBusinessLogic.cmpDiscover;

import app.layerDataAccess.blackboard.IBlackboardStorage;

public class DiscoverImpl implements IDiscover {
	
	private UDPPackageListener udpPackageListener;
	private IBlackboardStorage blackboardStorage;
	
	public DiscoverImpl(IBlackboardStorage storage, UDPPackageListener udpPackageListener) {
		this.blackboardStorage = storage;
		this.udpPackageListener = udpPackageListener;
	}

	@Override
	public void discoverBlackboard() {	
		System.out.println("starte die Erkundung");
		
		this.udpPackageListener.receive();
		
		System.out.println("Packet empfangen, erstelle Blackboard");
		this.blackboardStorage.setIp(udpPackageListener.getSourceIp());
		this.blackboardStorage.setPort(udpPackageListener.getTransmittedPort());
		
		System.out.println(this.blackboardStorage.getUrl());
		
		
	}
	
	

}
