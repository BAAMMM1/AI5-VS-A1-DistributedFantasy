import app.layerBusinessLogic.cmpDiscover.DiscoverClientImpl;
import app.layerBusinessLogic.cmpDiscover.IDiscoverClient;

public class Main {

	public static void main(String[] args) {

		// 1. Bevor Kommandos entgegen genommen werden können:
        // Auf welchem Port kündigt sich das Blackboard an? -> Port as int
		IDiscoverClient discover = new DiscoverClientImpl();
		discover.discoverBlackboard(24000);

	}

}
