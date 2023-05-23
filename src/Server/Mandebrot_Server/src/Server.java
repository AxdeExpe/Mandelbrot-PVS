public class Server extends Thread{

    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
                System.out.println("sheesh");
            }
            catch(InterruptedException e){

                //create new Client
                System.out.println("HALOOOOOOOOOOOOOOOOoo");

                break;
            }
        }
    }

}
