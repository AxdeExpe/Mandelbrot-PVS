import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//own List implementation
public class List {

    //creates normal List Object
    private java.util.List<ArrayList<Integer>> ID = new ArrayList<ArrayList<Integer>>();
    private java.util.List<ArrayList<Object>> Images = new ArrayList<ArrayList<Object>>();

    //muss während Paketabgabe gechekt werden und anderen client zugewiesen werden
    //[[ID,index from Images],[...],...]
    private java.util.List<ArrayList<Integer>> Queue = new ArrayList<ArrayList<Integer>>();


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





    //shows the image List
    public java.util.List showImages(){
        return Images;
    }

    //appends Image into List
    public void appendImage(int index, Color[][] image){


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

        if(index < Images.size() && Images.get(index).contains(index) && Images.get(index).get(1) != null){
            System.out.println("This index is already filled!");
            return;
        }

        Images.add(index,new ArrayList<Object>(Arrays.asList(ID, 0)));
    }

    //removes an entry
    public void removeImageForce(int index){

        //An out-of-range entry cannot be removed
        if (index >= Images.size()) {
            System.out.println("Image_Force: Index out of bound!");
            return;
        }

        Images.remove(index);
    }

    //removes entry safely
    public void removeImageSecure(int index, int ID){

        //removeID(2);
        //System.out.println(this.ID + " " + this.ID.size());

        //checkIDS();


        //An out-of-range entry cannot be removed
        if (index >= Images.size()) {
            System.out.println("Image_Secure: Index out of bound!");
            return;
        }

        //if the ID is not the right or the image is not '0', decline the request
        if(!(((Integer)Images.get(index).get(0)).intValue() == ID) || !((Images.get(index).get(1)) == (Object) 0)){
            System.out.println("Can't delete the entry! ");
            return;
        }

        Images.remove(index);


    }
}
