import java.awt.*;
import java.nio.ByteBuffer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Model extends Thread{
    private Presenter presenter;
    public double Zoom;

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

    public void setZoomAndMidPoints(double Zoom) {
        this.Zoom = Zoom;
    }

    public void setScreenResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //checks the Image list all the time
    public synchronized void run(){
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

                System.out.println("interrupted!");
                int length = Images.size();

                if(length > this.imagesLength){
                    System.out.println("Model Image: "+ Images);
                    this.imagesLength = length;

                    if(Images.get(imagesCounter).get(1) != (Object) 0){
                        System.out.println("Next image came!");
                        System.out.println("Color: " + Images.get(imagesCounter).get(1));

                        this.presenter.setImage((Color[][]) Images.get(imagesCounter).get(1));
                        this.imagesCounter++;
                    }
                }
            }
        }
    }
}