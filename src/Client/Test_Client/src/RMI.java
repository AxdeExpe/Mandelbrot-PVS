import java.nio.ByteBuffer;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote {

    ByteBuffer sendData(ByteBuffer DataPaket) throws RemoteException; //Server -> Client

    void workOnRequest(ByteBuffer DataPaket) throws RemoteException; //Client -> Server

    ByteBuffer getConnection(ByteBuffer DataPaket) throws RemoteException; //Client -> Server, necessary for the first connection request
}
