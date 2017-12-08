package app.layerLogicAndService.cmpBlackboard.service;

import app.layerLogicAndService.cmpBlackboard.entity.Blackboard;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ListenerServiceImpl extends Thread implements IListenerService {

    private int udpPort;
    private static final int BUFFER_LENGHT = 1024;
    private boolean run = false;
    private boolean isFirstSet = false;

    private DatagramSocket socket;

    private Gson gson;

    public ListenerServiceImpl(int port) {
        this.udpPort = port;
        this.gson = new Gson();
    }

    /**
     * Initialisiert den UDP-Socket
     *
     * @throws SocketException
     */
    private void init() throws SocketException {
        socket = new DatagramSocket(udpPort);
        this.run = true;
    }

    /**
     * Lauscht auf dem Port
     */
    @Override
    public void run() {
        System.out.println("listen to udp_port: " + this.udpPort);

        try {

            this.init();

            while (run) {

                try {

                    this.reciveDatagramPacket();

                    // Behandlung der Fehler, die beim Empfangen entstehen
                } catch (Exception e) {
                    // TODO - Logger log fehler
                    //System.out.println("listen failed: " + e.getMessage());

                }

            }

            // Behandlung der Fehler die durch den Socket im Initialize enstehen
        } catch (SocketException e) {
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

        // TODO - Logger
        //System.out.println("wait for datagrampacket");

        socket.setSoTimeout(5000);


        socket.receive(packet);


        // Empfänger auslesen
        InetAddress sourceIp = packet.getAddress();
        int port = packet.getPort();
        int len = packet.getLength();
        byte[] data = packet.getData();

        // TODO - Logger
        //System.out.printf("recive request \n - IP: %s\n - form port: %d\n - lenght: %d %n - %s%n",
                //sourceIp, port, len, new String(data, 0, len));

        AnnouncementResponeDTO responeDTO = gson.fromJson(new String(data, 0, len), AnnouncementResponeDTO.class);

        // TODO - Frage wie wird geprüft, ob das Packet vom Well-Known Server kommt?
        if (Blackboard.getInstance().getBlackboardIp() != sourceIp) {
            Blackboard.getInstance().setBlackboardIp(sourceIp);
        }

        if (responeDTO.getBlackboard_port() != 0) {
            if (Blackboard.getInstance().getBlackboardPort() != responeDTO.getBlackboard_port()) {
                Blackboard.getInstance().setBlackboardPort(responeDTO.getBlackboard_port());
                this.isFirstSet = true;
            }
        }

        Blackboard.BLACKBOARD = "http:/" + sourceIp + ":" + responeDTO.getBlackboard_port();
        System.out.println(Blackboard.BLACKBOARD);


        // TODO - Für den Logger. Log bitte wenn sich etwas ändert
        //System.out.println(" - " + Blackboard.getInstance().getUrl() + "\n");


    }

    private class AnnouncementResponeDTO {

        private int blackboard_port;

        public AnnouncementResponeDTO(int blackboard_port) {
            this.blackboard_port = blackboard_port;
        }

        public int getBlackboard_port() {
            return blackboard_port;
        }
    }



}