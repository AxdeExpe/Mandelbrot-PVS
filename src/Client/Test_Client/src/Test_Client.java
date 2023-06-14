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


            ArrayList<Object> list = remoteObjekt.getConnection(2);
            int ID = (int) list.get(0);
            int place = (int) list.get(1);

            for(int i = 0; i < 2; i++) { //2 = Threads

                ArrayList<Object> list2 = new ArrayList<Object>();
                list2 = (ArrayList<Object>) list.get(2);
                System.out.println("sdfk");
                double width = (Double) list2.get(0);
                double height = (Double) list2.get(1);
                double xmin = (Double) list2.get(2);
                double xmax = (Double) list2.get(3);
                double ymin = (Double) list2.get(4);
                double ymax = (Double) list2.get(5);


                System.out.println("Color[][]: " + list);
                System.out.println("ID: " + ID + "\nWidth: " + width + "\nHeight: " + height + "\nxmin: " + xmin + "\nxmax: " + xmax + "\nymin: " + ymin + "\nymax: " + ymax);

                Color[][] c = new Color[(int) height][(int) width]; //höhe und breite nur so aufm Laptop

                for (int y = 0; y < width; ++y) {
                    for (int x = 0; x < height; ++x) {
                        if (c[x][y] == null) {
                            c[x][y] = Color.RED;
                        }
                    }
                }

                ArrayList<Object> data = new ArrayList<Object>();
                data.add(ID);
                data.add(place);
                data.add(c);
                System.out.println("fdhjiaogs89z732r");

                ArrayList<Double> dataReturn = remoteObjekt.sendData(data);
                System.out.println("HIER SIND DIE DATEN: " + dataReturn);

            }




            //System.out.println("Threads: " + receivedData.getInt());

            //System.out.println("Empfangene Daten: " + receivedData);
        } catch (Exception e) {
            System.err.println("Fehler beim Verbinden mit dem Server: " + e.toString());
        }

    }


}