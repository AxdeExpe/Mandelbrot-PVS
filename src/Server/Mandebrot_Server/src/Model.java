import java.nio.ByteBuffer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Model {
    private Presenter presenter;
    private double Zoom;
    private int MidPointX;
    private int MidPointY;
    private ByteBuffer buffer;

    //data for client
    private int width, height; //screen resolution
    private double xmin = -1.666;
    private double xmax = 1.0;
    private double ymin = -1.0;
    private double ymax = 1.0;
    private double cr = -0.743643887036151;
    private double ci = 0.13182590420533;

    Model(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setZoomAndMidPoints(double Zoom, int X, int Y) {
        this.Zoom = Zoom;
        this.MidPointX = X;
        this.MidPointY = Y;
    }

    public void setScreenResolution(int width, int height){
        this.width = width;
        this.height = height;
    }


    //calc the necessary data for the client
    public void calcData(){
        double xdim = xmax - xmin;
        double ydim = ymax - ymin;
        xmin = cr - xdim / 2 / Zoom;
        xmax = cr + xdim / 2 / Zoom;
        ymin = ci - ydim / 2 / Zoom;
        ymax = ci + ydim / 2 / Zoom;
    }

    //filling up the ByteBuffer
    private ByteBuffer fillData(){
        this.buffer = ByteBuffer.allocate(6);

        //screen resolution
        this.buffer.putInt(this.width);
        this.buffer.putInt(this.height);

        //image data
        this.buffer.putDouble(this.xmin);
        this.buffer.putDouble(this.xmax);
        this.buffer.putDouble(this.ymin);
        this.buffer.putDouble(this.xmax);

        return this.buffer;
    }


    //RMI class
    static class Handler{

        public static void waitForClients(){

            try {
                RMI remoteObject = new RMI_Implementation();

                //export remote object and listen at the port
                if (UnicastRemoteObject.unexportObject(remoteObject, false)) {
                    remoteObject = (RMI) UnicastRemoteObject.exportObject(remoteObject, 8888); //don't need sudo rights
                }

                //create RMI-Registry and bind remote object
                Registry registry = LocateRegistry.createRegistry(1099);
                registry.rebind("rmi", remoteObject);

                System.out.println("Server started!");
            }
            catch(Exception e){
                System.err.println("Couldn't start server: " + e.toString());
            }
        }
    }
}