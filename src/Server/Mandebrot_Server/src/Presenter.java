import java.awt.Color;

public class Presenter {
    private Model model;
    private View view;
    private int width;
    private int height;
    private double xmin = -1.666;
    private double xmax = 1.0;
    private double ymin = -1.0;
    private double ymax = 1.0;
    private double cr = -0.743643887036151;
    private double ci = 0.13182590420533;
    private double Zoom;

    Presenter() {

    }

    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setZoom(double zoom) {

        if(zoom == 1.0){
            System.out.println("Zoom has to be larger or lower then 1.0!");
            System.exit(1);
        }

        this.Zoom = zoom;
    }

    public void Start() {
        Color[][] c = new Color[this.width][this.height];
        this.view.UI(this.width, this.height);
        /*
        c = m.apfel_bild(xmin, xmax, ymin, ymax); c = model.getImage();
        view.update(c);
        */


        Model.Handler.waitForClients();

    }
}