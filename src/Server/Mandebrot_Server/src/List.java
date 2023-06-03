import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//own List implementation
public class List {

    //creates normal List Object
    private java.util.List<ArrayList<Integer>> ID = new ArrayList<ArrayList<Integer>>();
    private java.util.List<ArrayList<Object>> Images = new ArrayList<ArrayList<Object>>();


    //corrects the list if an item got removed, => changes the ID of each Client
    /*
    private void correctListID(){
        for(int i = 0; i < ID.size(); i++){
            if(!ID.get(i).contains(i)){
                ID.get(i).set(0,i);
            }
        }
    }
     */

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
            System.out.println("Index out of bound!");
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
            System.out.println("Index out of bound!");
            return;
        }

        Images.remove(index);
    }

    //removes entry safely
    public void removeImageSecure(int index, int ID){

        //An out-of-range entry cannot be removed
        if (index >= Images.size()) {
            System.out.println("Index out of bound!");
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
