import java.awt.*;
import java.nio.ByteBuffer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMI extends Remote {

    ArrayList<Double> sendData(ArrayList<Object> DataPaket) throws RemoteException; //Server -> Client

    void workOnRequest(byte[] DataPaket) throws RemoteException; //Client -> Server

    ArrayList<Object> getConnection(int Threads) throws RemoteException; //Client -> Server, necessary for the first connection request
}
