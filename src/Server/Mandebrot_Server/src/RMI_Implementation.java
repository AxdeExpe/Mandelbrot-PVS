import javax.xml.crypto.Data;
import java.awt.*;
import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class RMI_Implementation extends UnicastRemoteObject implements RMI {


    //Relation: ID[0][0] = "x","y";
    // x € N0; y € N*;
    // x = "ID"; y = "Threads"
    //[[x,y],[1,8],[2,4],...]
    private List ID = new List();

    //[[index, image],[1,image],[2,0],...]
    //index € N*
    //image € N0
    //if image = 0; no image for this index, => need to wait
    //image != 0; image object exists, => load it into the View
    private List Images = new List();
    private int Index = 0;

    //Constructor
    public RMI_Implementation() throws RemoteException {

        ID.appendID(1);
        ID.appendID(2);
        ID.appendID(3);
        System.out.println(ID.showID());
        /*
        ID.remove(1);
        System.out.println(ID.show());
        ID.append(4);
        ID.append(5);

        System.out.println(ID.show());
        ID.remove(1);
        ID.append(6);
        System.out.println(ID.show());


        System.out.println("slkdjohfs");
        for (int i = 0; i < 10; i++) {
            //Model.calcData();
            //Data.put(Model.fillData());
            Images.append(Index, 0);
            Index++;
        }


        System.out.println(Images.showImages());
        */

        byte[] b = new byte[10];
        sendData(b);

    }


    //sends data
    @Override
    public byte[] sendData(byte[] DataPaket) throws RemoteException {
        // gets the amount of Threads from the Client
        ByteBuffer buffer = ByteBuffer.wrap(DataPaket);
        int ID = buffer.getInt();
        int place = buffer.getInt();

        Color[][] c = new Color[???][???];

        //getting the Images
        //Extract the Color[][] out of the ByteBuffer
        for(int i = 0; i < ???; i++){
            for(int j = 0; j < ???; j++){
                Color[i][j] = buffer.getInt();
            }
        }

        Images.appendImage(0, 1);s
        Images.appendImage(0,c);
        Images.appendImage(1,c);
        Images.appendImage(2, 2);
        System.out.println(Images.showImages());
        Images.removeImageForce(1); //fails -> correct
        System.out.println(Images.showImages());
        Images.appendImage(2, 2);
        System.out.println(Images.showImages());
        Images.appendImage(3, c); //fails -> correct
        System.out.println(Images.showImages());
        Images.removeImageSecure(1,2); //does not fail -> correct
        System.out.println(Images.showImages());


        return DataPaket; // falsch

    }

    //receives data and working on
    @Override
    public void workOnRequest(byte[] DataPaket) throws RemoteException {

    }

    @Override
    public byte[] getConnection(byte[] DataPaket) throws RemoteException {

        // gets the amount of Threads from the Client
        ByteBuffer buffer = ByteBuffer.wrap(DataPaket);
        int Threads = buffer.getInt();
        ID.appendID(Threads);


        //create Data buffer
        byte[] Data = new byte[Threads];
        ByteBuffer bufferData = ByteBuffer.wrap(Data);

        //fill the buffer with the data
        for (int i = 0; i < Threads; i++) {
            Index++;
          //  Model.calcData();
            //Data.put(Model.fillData());
            Images.appendImage(Index, 0);
        }

        Color[][] c = new Color[1][1];
        //Data.put(c);

        //sends the Data packet back
        return Data;
    }
}