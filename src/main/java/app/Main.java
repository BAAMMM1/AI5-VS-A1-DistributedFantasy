package app;

import app.layerBusinessLogic.cmpDiscover.DiscoverImpl;
import app.layerBusinessLogic.cmpDiscover.IDiscover;
import app.layerBusinessLogic.cmpDiscover.UDPPackageListener;
import app.layerDataAccess.blackboard.BlackBoardStoreageImpl;

public class Main {

	public static void main(String[] args) {
		IDiscover discover = new DiscoverImpl(new BlackBoardStoreageImpl(), new UDPPackageListener(24000));
		discover.discoverBlackboard();

	}

}
