import java.rmi.*;
import java.rmi.server.*;

public class Main{

    private static double Zoom;
    private static int midPointX, midPointY;

    public static void main(String[] args) {

        //usage check
        if(args.length != 3){
            System.out.println("Usage: <Zoomfactor> <MidPointX> <MidPointY>");
            System.exit(1);
        }

        //initialize
        Zoom = Double.parseDouble(args[0]);
        midPointX = Integer.parseInt(args[1]);
        midPointY = Integer.parseInt(args[2]);


        //wait for Clients



        while(true){

            Server t1 = new Server();
            t1.start();

            try
            {
                Thread.sleep(2000);
                t1.interrupt(); //interrupt nur wenn neuer Client sich connecten will
            }catch(Exception e){
                System.out.println("Exception handled "+e);
            }
        }
    }
}