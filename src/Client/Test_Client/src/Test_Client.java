import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.nio.ByteOrder;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Test_Client{

    public static void main(String[] args){

        try {
            // RMI-Registry suchen
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);




            // Remote-Objekt vom Server abrufen
            RMI remoteObjekt = (RMI) registry.lookup("rmi");

/*
            byte[] bytes = new byte[4];

            ByteBuffer b = ByteBuffer.wrap(bytes);
            b.order(ByteOrder.BIG_ENDIAN);
            b.putInt(8);

           // ByteBuffer a = ByteBuffer.wrap(bytes);


            System.out.println("fdgjishjk: " + bytes[0]);


            // Remote-Methode aufrufen, um Daten vom Server zu empfangen
            byte[] receivedData = new byte[100];
            receivedData = remoteObjekt.getConnection(bytes);
            //senden funktioniert aber das Paket kommt nicht zurück

*/


            System.out.println("Color[][]: " + remoteObjekt.getConnection(20));


            //System.out.println("Threads: " + receivedData.getInt());

            //System.out.println("Empfangene Daten: " + receivedData);
        } catch (Exception e) {
            System.err.println("Fehler beim Verbinden mit dem Server: " + e.toString());
        }

    }


}