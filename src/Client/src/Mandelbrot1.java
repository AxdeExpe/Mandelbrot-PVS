import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Mandelbrot1 {
    public static void main(String[] args) {
        ApfelPresenter p = new ApfelPresenter();
        ApfelView v = new ApfelView(p);
        ApfelModel m = new ApfelModel(v);
        p.setModelAndView(m, v);
        p.apfel();
    }
}