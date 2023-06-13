import java.awt.*;
import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

public class RMI_Implementation extends UnicastRemoteObject implements RMI{

    //Relation: ID[0][0] = "x","y";
    // x € N0; y € N*;
    // x = "ID"; y = "Threads"
    //[[x,y],[1,8],[2,4],...]

    private java.util.List<ArrayList<Integer>> ID = new ArrayList<ArrayList<Integer>>();

    //[[index, image],[1,image],[2,0],...]
    //index € N*
    //image € N0
    //if image = 0; no image for this index, => need to wait
    //image != 0; image object exists, => load it into the View
    private java.util.List<ArrayList<Object>> Images = new ArrayList<ArrayList<Object>>();



    private java.util.List<ArrayList<Integer>> Queue = new ArrayList<ArrayList<Integer>>();

    private int Index = 0;

    private void checkIDS(){

        int count = 0;

        for(int i = 0; i < Images.size(); i++){


            System.out.println(Images.size() +  "           " + Images.get(i).contains(0));

            if(Images.get(i).contains(0)) {
                int id = (int) Images.get(i).get(0);


                System.out.println("ID: " + id + "        " + i);
                //die ID (client) wurde schon vermerkt
/*
                if ((id == (int) Images.get(i + 1).get(0)) && (count == 0) && i > 0) {
                    System.out.println("sjhgf");
                    continue;
                }
*/

                count = 0;

                for (int j = 0; j < ID.size(); j++) {
                    if (id == ID.get(j).get(0)) {
                        count++;
                    }
                }

                //If Images at [[x,y],[x,y],...] y == 0 and ID wasn't found in ID list, then notice the
                //index and ID from the Images list and append it to the Queue list
                if ((count == 0) && ((int) Images.get(i).get(1) == 0)) {
                    //merke die Stelle und ID, um diese später noch einmal zu bearbeiten
                    Queue.add(Queue.size(), new ArrayList<Integer>(Arrays.asList(id, i)));
                }
            }
        }

        System.out.println(Queue);

    }





    //appends the available Threads into the List
    public void appendID(int threads){
        ID.add(ID.size(),new ArrayList<Integer>(Arrays.asList(ID.size(), threads)));
    }


    //removes the entry from list
    //Client got disconnected
    //Each client gets another ID
    public void removeID(int index){

        //An out-of-range entry cannot be removed
        if (index >= ID.size()) {
            System.out.println("ID: Index out of bound!");
            return;
        }

        ID.remove(index);
        //this.correctListID();
    }

    //shows all items in List
    public java.util.List showID(){
        return ID;
    }

    public int getLastID() { return ID.get(showID().size()).get(0); }



    //shows the image List
    public java.util.List showImages(){
        return Images;
    }

       synchronized public void appendImage(int index, Color[][] image){


            //checks if the placeholder exists and replace it with the image
            if(index < Images.size() && Images.get(index).contains(index) && Images.get(index).get(1) == (Object) 0){
                Images.get(index).set(1,image);
                return;
            }

            //appends the image
            Images.add(index,new ArrayList<Object>(Arrays.asList(Images.size(), image)));

        }

        //Appends a placeholder into List
        public void appendImage(int index, int ID){

            System.out.println("lojuhsdiufhd97z2349732");


            Images.add(index,new ArrayList<Object>(Arrays.asList(ID, 0)));
        }

















    Model m;

    //---------------------------------------RMI--------------------------------------

    //Constructor
    public RMI_Implementation(Model m) throws RemoteException {

        this.m = m;
        //appendID(1);
        //appendID(2);
        //appendID(3);
        System.out.println(showID());
        /*
        ID.remove(1);
        System.out.println(ID.show());
        ID.append(4);
        ID.append(5);

        System.out.println(ID.show());
        ID.remove(1);
        ID.append(6);
        System.out.println(ID.show());


        System.out.println("slkdjohfs");
        for (int i = 0; i < 10; i++) {
            //Model.calcData();
            //Data.put(Model.fillData());
            Images.append(Index, 0);
            Index++;
        }


        System.out.println(Images.showImages());
        */

       // byte[] b = new byte[10];
       // sendData(b);

    }


    //sends data
    @Override
    public ArrayList<Double> sendData(ArrayList<Object> DataPaket) throws RemoteException {
        // gets the ID and the place where the placeholder of the image is, to replace it with the image
        int ID = (int) DataPaket.get(0);
        int place = (int) DataPaket.get(1);
        Color[][] c = (Color[][]) DataPaket.get(2);

        System.out.println("ID: " + ID + "; Place: " + place + "; Color[][]: " + c);


        //put it into the Queue and show it maybe on the screen
        this.m.getImage(c);
        System.out.println("ICH HABE ES GESCHAFFT");

        //get new place and push it into the List
        calcData();

        //put Data into the second List
        ArrayList<Double> data = new ArrayList<Double>();
        data.add(width);
        data.add(height);
        data.add(xmin);
        data.add(xmax);
        data.add(ymin);
        data.add(ymax);



        /*
        Images.appendImage(0, 1);
        Images.appendImage(0,c);
        Images.appendImage(1,c);
        Images.appendImage(2, 2);
        System.out.println(Images.showImages());
        Images.removeImageForce(1); //fails -> correct
        System.out.println(Images.showImages());
        Images.appendImage(2, 2);
        System.out.println(Images.showImages());
        Images.appendImage(3, c); //fails -> correct
        System.out.println(Images.showImages());
        Images.removeImageSecure(1,2); //does not fail -> correct
        System.out.println(Images.showImages());
*/

        return data; // falsch

    }

    //receives data and working on
    @Override
    public void workOnRequest(byte[] DataPaket) throws RemoteException {

    }


    //Zoom has to be != 1.0
    private double Zoom = 0.1;
    private ByteBuffer buffer;

    //data for client
    private double width = 800; //screen resolution
    private double height = 400; //screen resolution
    private double xmin = -1.666;
    private double xmax = 1.0;
    private double ymin = -1.0;
    private double ymax = 1.0;
    private double cr = -0.743643887036151;
    private double ci = 0.13182590420533;



    public void calcData(){
        double xdim = xmax - xmin;
        double ydim = ymax - ymin;
        xmin = cr - xdim / 2 / Zoom;
        xmax = cr + xdim / 2 / Zoom;
        ymin = ci - ydim / 2 / Zoom;
        ymax = ci + ydim / 2 / Zoom;
    }


    @Override
    public ArrayList<Object> getConnection(int Threads) throws RemoteException {

        //creates List for the necessary Info; [ID, [width, height, xmin, xmax, ymin, ymax]]
        ArrayList<Object> listObj = new ArrayList<Object>();
        System.out.println("New client connected! ID: " + ID.size());

        //add client
        listObj.add(ID.size());
        appendID(Threads);

        //sets a placeholder for the future incoming Color[][] in the list for each thread
        for(int i = 0; i < Threads; i++){

            if (Images.size() == 0) {
                Images.add(Index,new ArrayList<Object>(Arrays.asList(Images.size(), 0)));
            }
            //sets a placeholder for the future incoming Color[][] in the list
            else if(Index < Images.size() && Images.get(Index).contains(Index) && Images.get(Index).get(1) == (Object) 0){
                Images.get(Index).set(1,0);
            }
            Index++;

            Images.add(Index,new ArrayList<Object>(Arrays.asList(Images.size(), 0)));

            //calc the necessary data for each Thread / Image
            calcData();

            //put Data into the second List
            ArrayList<Double> data = new ArrayList<Double>();
            data.add(width);
            data.add(height);
            data.add(xmin);
            data.add(xmax);
            data.add(ymin);
            data.add(ymax);

            listObj.add(data);
        }

        //sends the Data packet back
        return listObj;
    }

}