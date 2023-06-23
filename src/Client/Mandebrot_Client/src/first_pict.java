import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class first_pict extends Thread{
    ArrayList<Object> list;
    //private Main main;
    first_pict (ArrayList<Object> list){
        this.list = list;
        //this.main = main;
    }
    void berechnen(int i, int ID, RMI remoteObjekt) throws RemoteException {
        int place;
        ArrayList<Object> list2 = new ArrayList<Object>();

        if(i == 0){
            place = (int) list.get(1);
        }
        else{
            System.out.println(list  + "\n"+ i);
            place = (int) list.get(2*i+1);
        }

        System.out.println(list);
        list2 = (ArrayList<Object>) list.get(2*i+2);

        double width = (Double) list2.get(0);
        double height = (Double) list2.get(1);
        double xmin = (Double) list2.get(2);
        double xmax = (Double) list2.get(3);
        double ymin = (Double) list2.get(4);
        double ymax = (Double) list2.get(5);
        int ypix = (int) Math.round((double)list2.get(1));
        int xpix = (int) Math.round((double)list2.get(0));

        System.out.println("Vergleichsdaten:" + list2);
        System.out.println("Color[][]: " + list2);
        System.out.println("ID: " + ID + "\nplace: " + place + "\nWidth: " + width + "\nHeight: " + height + "\nxmin: " + xmin + "\nxmax: " + xmax + "\nymin: " + ymin + "\nymax: " + ymax);

        Color[][] c = new Color[(int) width][(int) height];
        ApfelWorker worker = new ApfelWorker(0, ypix,  xmin,  xmax,  ymin,  ymax,  ypix,  xpix, place, ID, remoteObjekt);
        worker.start();
        //c = result;
        //System.out.println(c);

                /*for (int y = 0; y < height; ++y) {
                    for (int x = 0; x < width; ++x) {
                        if (c[x][y] == null) {
                            c[x][y] = Color.BLUE;
                            //if(place%2 == 0 || place%4 == 0){
                            //    c[x][y] = Color.BLUE;
                            //}
                            ////   c[x][y] = Color.RED;
                            //}
                        }
                    }
                }*/



        /*ArrayList<Object> data = new ArrayList<Object>();
        data.add(ID);
        data.add(place);
        data.add(c);

        ArrayList<Double> dataReturn = remoteObjekt.sendData(data);
        System.out.println("HIER SIND DIE DATEN: " + dataReturn);*/

    }
}

