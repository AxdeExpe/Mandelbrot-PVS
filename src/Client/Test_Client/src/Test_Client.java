import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.ByteBuffer;
public class Test_Client{

    public static void main(String[] args){

        try {
            // RMI-Registry suchen
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);




            // Remote-Objekt vom Server abrufen
            RMI remoteObjekt = (RMI) registry.lookup("rmi");


            byte[] bytes = new byte[10];

            ByteBuffer b = ByteBuffer.wrap(bytes);
            b.putInt(4);

            System.out.println("fdgjishjk: " + bytes[1]);


            // Remote-Methode aufrufen, um Daten vom Server zu empfangen
            String receivedData = remoteObjekt.sayHello();


           // System.out.println("Threads: " + receivedData.getInt());

            System.out.println("Empfangene Daten: " + receivedData);
        } catch (Exception e) {
            System.err.println("Fehler beim Verbinden mit dem Server: " + e.toString());
        }

    }


}