//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Model {
    private Presenter presenter;
    private double Zoom;
    private int MidPointX;
    private int MidPointY;

    Model(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setZoomAndMidPoints(double Zoom, int X, int Y) {
        this.Zoom = Zoom;
        this.MidPointX = X;
        this.MidPointY = Y;
    }
}

