package app.layerBusinessLogic.cmpDiscover;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPPackageListener {

    private int udpPort;
    private static final int BUFFER_LENGHT = 1024;
    private boolean run = false;

    private DatagramSocket socket;

    private Gson gson;

    private InetAddress sourceIp = null;
    private int transmittedPort;

    public UDPPackageListener(int port) {
    	this.udpPort = port;
    	this.gson = new Gson();
	}

    /**
     * Initialisiert den UDP-Socket
     * @throws SocketException
     */
    private void init() throws SocketException {
        socket = new DatagramSocket(udpPort);
        this.run = true;
    }

    /**
     * Lauscht auf dem Port
     */
    public void receive() {
    	System.out.println("listen to udp_port: " + this.udpPort);

        try {

            this.init();

            while (run) {

                this.reciveDatagramPacket();

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            this.socket.close();

        }
    }

    /**
     * Diese Methode lauscht auf einem UDP-Port. Wenn ein Packet empfangen wird, wird der Inhalt des Packets auf der
     * Console ausgegeben.
     *
     * @throws IOException falls Fehler beim Empfangen des Packets.
     */
    private void reciveDatagramPacket() throws IOException {

        // Auf Anfrage warten
        DatagramPacket packet = new DatagramPacket(new byte[BUFFER_LENGHT], BUFFER_LENGHT);

        System.out.println("wait for datagrampacket");
        socket.receive(packet);
        
        this.run = false;

        // Empfänger auslesen
        this.sourceIp = packet.getAddress();
        int port = packet.getPort();
        int len = packet.getLength();
        byte[] data = packet.getData();

        System.out.printf("recive request \n - IP: %s\n - form port: %d\n - lenght: %d %n - %s%n\n",
                sourceIp, port, len, new String(data, 0, len));

        AnnouncementResponeDTO responeDTO = gson.fromJson(new String(data, 0, len), AnnouncementResponeDTO.class);

        this.transmittedPort = responeDTO.getBlackboard_port();

    }

    public InetAddress getSourceIp() {
    	return this.sourceIp;
    }
    
    public int getTransmittedPort() {
    	return this.transmittedPort;
    }
    
    
}