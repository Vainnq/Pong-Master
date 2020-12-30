package server;

import java.io.IOException;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that turns ON server.
 */

public class ServerCommunication {
	public static void main(String[] args) throws IOException {
        System.out.println("Server ON");
        ServerEvents server = new ServerEvents();
        server.start(1905);
    }
}
