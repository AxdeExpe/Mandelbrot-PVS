import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
class ApfelModel {
    ApfelView v;
    boolean farbe = false;
    final int max_iter = 5000;
    final double max_betrag2 = 4;
    int xpix, ypix;
    double xmin, xmax, ymin, ymax;
    int[][] bildIter; // Matrix der Iterationszahl, t.b.d.
    Color[][] bild;


    public ApfelModel(ApfelView v) {
        this.v = v;
    }

    public void setParameter(int xpix, int ypix) {
        this.xpix = xpix;
        this.ypix = ypix;
        bildIter = new int[xpix][ypix]; // Matrix der Iterationszahl, t.b.d.
        bild = new Color[xpix][ypix];
    }

    /** Erzeuge ein komplettes Bild mittles Threads */
    Color[][] apfel_bild(double xmin, double xmax, double ymin, double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;

        ApfelWorker worker = new ApfelWorker(0, ypix);
        worker.work();
        return bild;
    }

    // Threads and writing to arrays
    // http://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.6

    /** @author jvogt lokale Klasse zum Thread-Handling */
    class ApfelWorker  {
        int y_sta, y_sto;

        public ApfelWorker(int y_start, int y_stopp) {
            this.y_sta = y_start;
            this.y_sto = y_stopp;
        }

        public void work() {
            double c_re, c_im;
            for (int y = y_sta; y < y_sto; y++) {
                c_im = ymin + (ymax - ymin) * y / ypix;

                for (int x = 0; x < xpix; x++) {
                    c_re = xmin + (xmax - xmin) * x / xpix;
                    int iter = calc(c_re, c_im);
                    bildIter[x][y] = iter;
                    Color pix = farbwert(iter); // Farbberechnung
                    // if (iter == max_iter) pix = Color.RED; else pix = Color.WHITE;
                    // v.image.setRGB(x, y, pix.getRGB()); // rgb
                    bild[x][y] = pix;
                }
            }
        }

        /**
         * @param cr Realteil
         * @param ci Imaginärteil
         * @return Iterationen
         */
        public int calc(double cr, double ci) {
            int iter;
            double zr, zi, zr2 = 0, zi2 = 0, zri = 0, betrag2 = 0;
            //  z_{n+1} = z²_n + c
            //  z²  = x² - y² + i(2xy)
            // |z|² = x² + y²
            for (iter = 0; iter < max_iter && betrag2 <= max_betrag2; iter++) {
                zr = zr2 - zi2 + cr;
                zi = zri + zri + ci;

                zr2 = zr * zr;
                zi2 = zi * zi;
                zri = zr * zi;
                betrag2 = zr2 + zi2;
            }
            return iter;
        }

        /**
         * @param iter Iterationszahl
         * @return Farbwert nsmooth = n + 1 - Math.log(Math.log(zn.abs()))/Math.log(2)
         *     Color.HSBtoRGB(0.95f + 10 * smoothcolor ,0.6f,1.0f);
         */
        Color farbwert(int iter) {
            if (!farbe) {
                if (iter == max_iter) return Color.BLACK;
                else return Color.RED;
            }
            return Color.BLACK;
        }
    } // ApfelThread
}