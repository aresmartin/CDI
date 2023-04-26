import java.util.List;
import java.util.ArrayList;

public class PingPongThread extends Thread{

public String word;
public int delay;

public PingPongThread(String queDecir, int cadaCuantosMs){
    this.word = queDecir;
    this.delay = cadaCuantosMs;
}

@Override    
public void run(){
    System.out.println("Empezando el Thread numero: " + getName());
    while(true){
        System.out.println(word + " ");
        try{
            Thread.sleep(delay);
        }catch(InterruptedException e){return;}

        
    }

}

public static void main(String args[]){
    System.out.println("Empezando el thread: " + Thread.currentThread().getName());
    //Declaracion de 2 threads
    PingPongThread thread1 = new PingPongThread("PING", 33);
    PingPongThread thread2 = new PingPongThread("PONG", 50);
    List<PingPongThread> listahilos = new ArrayList<>();
    listahilos.add(thread2);
    listahilos.add(thread1);
    //Activacion
    thread1.start();
    thread2.start();

    //Esperamos 2 segundos
    try{
        Thread.sleep(2000);

    }catch(InterruptedException e){}

    for (int i = 0; i < listahilos.size(); i++ ){
        try{
            listahilos.get(i).join();
    }catch(InterruptedException e){}

        }
     System.out.println("Terminando el thread: " + Thread.currentThread().getName());
    }
}