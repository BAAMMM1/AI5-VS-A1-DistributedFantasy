import app.layerBusinessLogic.cmpDiscover.DiscoverClientImpl;
import app.layerBusinessLogic.cmpDiscover.IDiscoverClient;
import app.layerDataAccess.restConsumer.registerConsume.IRegisterConsumer;
import app.layerDataAccess.restConsumer.registerConsume.RegisterConsumer;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		// 1. Bevor Kommandos entgegen genommen werden können:
        // Auf welchem Port kündigt sich das Blackboard an? -> Port as int
		IDiscoverClient discover = new DiscoverClientImpl();
		discover.discoverBlackboard(24000);

		IRegisterConsumer registerConsumer = new RegisterConsumer(discover);

		registerConsumer.getAuthenticationToken("MeinSuperTestUser", "test1234");

		registerConsumer.checkLogin("1");


	}

}
