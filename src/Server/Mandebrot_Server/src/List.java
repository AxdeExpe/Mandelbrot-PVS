import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//own List implementation
public class List {

    //creates normal List Object
    private java.util.List<ArrayList<Integer>> ID = new ArrayList<ArrayList<Integer>>();
    private java.util.List<ArrayList<Object>> Images = new ArrayList<ArrayList<Object>>();


    //corrects the list if an item got removed, => changes the ID of each Client
    private void correctList(){
        for(int i = 0; i < ID.size(); i++){
            if(!ID.get(i).contains(i)){
                ID.get(i).set(0,i);
            }
        }
    }

    //appends the available Threads into the List
    public void append(int threads){
        ID.add(ID.size(),new ArrayList<Integer>(Arrays.asList(ID.size(), threads)));
    }

    public void append(int index, Color[][] image){
        Images.add(index,new ArrayList<Object>(Arrays.asList(Images.size(), image)));
    }

    public void append(int Index, int Null){
        Images.add(Index,new ArrayList<Object>(Arrays.asList(Images.size(), Null)));
    }


    //removes the entry from list
    //Client got disconnected
    //Each client gets another ID
    public void remove(int index){
        ID.remove(index);
        this.correctList();
    }

    //shows all items in List
    public java.util.List showID(){
        return ID;
    }

    public java.util.List showImages(){
        return Images;
    }

}
