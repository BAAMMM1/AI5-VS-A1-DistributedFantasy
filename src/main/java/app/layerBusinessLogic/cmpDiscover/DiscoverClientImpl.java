package app.layerBusinessLogic.cmpDiscover;

import java.net.URL;

public class DiscoverClientImpl implements IDiscoverClient {
	
	private Blackboard blackboard;
	
	public DiscoverClientImpl() {
	}

	@Override
	public void discoverBlackboard(int port) {
        UDPPackageListener udpPackageListener = new UDPPackageListener(port);

		System.out.println("discover blackboard");
		udpPackageListener.receive();
		
		System.out.println("create Blackboard");
		blackboard = new Blackboard(udpPackageListener.getSourceIp(), udpPackageListener.getTransmittedPort());

		System.out.println(this.blackboard.toString());
		System.out.println("Blackboard URL: " + this.blackboard.getUrl());
		
		
	}

    @Override
    public URL getBlackboardURL() {
        return this.blackboard.getUrl();
    }


}
