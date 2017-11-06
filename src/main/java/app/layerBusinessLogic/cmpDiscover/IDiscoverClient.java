package app.layerBusinessLogic.cmpDiscover;

import java.net.URL;

public interface IDiscoverClient {
	
	void discoverBlackboard(int port);

	URL getBlackboardURL();

}
