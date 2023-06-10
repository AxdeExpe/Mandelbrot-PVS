import java.nio.ByteBuffer;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {

    byte[] sendData(byte[] DataPaket) throws RemoteException; //Server -> Client

    void workOnRequest(byte[] DataPaket) throws RemoteException; //Client -> Server

    byte[] getConnection(byte[] DataPaket) throws RemoteException; //Client -> Server, necessary for the first connection request
}
