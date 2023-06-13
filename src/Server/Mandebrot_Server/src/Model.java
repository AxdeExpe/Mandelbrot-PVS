import java.awt.*;
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

    public void setScreenResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void getImage(Color[][] image){
        this.presenter.setImage(image);
    }
}