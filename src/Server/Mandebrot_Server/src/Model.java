import java.awt.*;
import java.nio.ByteBuffer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private Presenter presenter;
    public double Zoom;
    private int MidPointX;
    private int MidPointY;
    private ByteBuffer buffer;

    //data for client
    protected int width, height; //screen resolution
    protected double xmin = -1.666;
    protected double xmax = 1.0;
    protected double ymin = -1.0;
    protected double ymax = 1.0;
    protected double cr = -0.743643887036151;
    protected double ci = 0.13182590420533;

    //Image List
    public java.util.List<ArrayList<Object>> Images = new ArrayList<ArrayList<Object>>();
    private int imagesLength = Images.size();
    private int imagesCounter = 0;

    Model(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setZoomAndMidPoints(double Zoom, int X, int Y) {
        this.Zoom = Zoom;
        this.MidPointX = X;
        this.MidPointY = Y;
    }

    public void setScreenResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //checks the Image list all the time
    public void imagesWatchdog(){
        while(true){
            try {
                int length = Images.size();

                if(length > this.imagesLength){
                    this.imagesLength = length;

                    if(Images.get(imagesCounter).get(1) != (Object) 0){
                        System.out.println("Next image came!");

                        this.presenter.setImage((Color[][]) Images.get(imagesCounter).get(1));
                        this.imagesCounter++;
                    }
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}