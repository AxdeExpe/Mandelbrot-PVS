import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
class ApfelPresenter {
    protected ApfelModel m;
    protected ApfelView v;

    double xmin = -1.666, xmax = 1, ymin = -1, ymax = 1; // Parameter des Ausschnitts
    // static double  cr = -0.3735,  ci = 0.655;
    double cr = -0.743643887036151, ci = 0.131825904205330;
    double zoomRate = 1.5;
    int xpix = 640, ypix = 480;

    public void setModelAndView(ApfelModel m, ApfelView v) {
        this.m = m;
        this.v = v;
        v.setDim(xpix, ypix);
        m.setParameter(xpix, ypix);
    }

    /** Komplette Berechnung aller Bilder */
    void apfel() {
        Color[][] c = new Color[xpix][ypix];
        for (int i = 1; i < 65; i++) { // Iterationen bis zum Endpunkt
            System.out.println(i + " Vergrößerung: " + 2.6 / (xmax - xmin) + " xmin: " + xmin + " xmax: " + xmax);
            c = m.apfel_bild(xmin, xmax, ymin, ymax);
            v.update(c);
            double xdim = xmax - xmin;
            double ydim = ymax - ymin;
            xmin = cr - xdim / 2 / zoomRate;
            xmax = cr + xdim / 2 / zoomRate;
            ymin = ci - ydim / 2 / zoomRate;
            ymax = ci + ydim / 2 / zoomRate;
        }
    }

}