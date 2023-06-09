import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.ByteBuffer;
public class Test_Client{

    public static void main(String[] args){

        try {
            // RMI-Registry suchen
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);


            System.out.println("<dkj");

            // Remote-Objekt vom Server abrufen
            RMI remoteObjekt = (RMI) registry.lookup("rmi");

            ByteBuffer b = ByteBuffer.allocate(2);
            b.putInt(4);

            // Remote-Methode aufrufen, um Daten vom Server zu empfangen
            ByteBuffer receivedData = remoteObjekt.getConnection(b);

            System.out.println("Threads: " + receivedData.getInt());

            System.out.println("Empfangene Daten: " + receivedData);
        } catch (Exception e) {
            System.err.println("Fehler beim Verbinden mit dem Server: " + e.toString());
        }

    }


}