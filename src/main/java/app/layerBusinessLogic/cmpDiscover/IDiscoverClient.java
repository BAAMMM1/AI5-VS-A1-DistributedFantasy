package app.layerBusinessLogic.cmpDiscover;

public interface IDiscoverClient {
	
	void discoverBlackboard(int port);

	String getBlackboardURL();

}
