import java.util.ArrayList;
import java.util.List;

public class Practica2Ej1 {

  public static void main(String args[]) {
    int primerArgumento = Integer.valueOf(args[0]);
    System.out.println(
      "Hello i am number: " + Thread.currentThread().getName()
    );
    /*for(int i = 0; i < primerArgumento ; i++){
            
            MultithreadThing myThings =  new MultithreadThing();
            myThings.start();
        } */
    List<Thread> myList = new ArrayList<>(primerArgumento);
    for (int i = 0; i < primerArgumento; i++) {
      Thread myThread = new Thread(new PruebaRunnable());
      myList.add(i, myThread);
    }

    for (int i = 0; i < primerArgumento; i++) {
      myList.get(i).start();
    }
    
    for (int i = 0; i < primerArgumento; i++) {
      //while(myList.get(i).isAlive()){}   
      try{
        myList.get(i).join();
      }catch(InterruptedException e){}
    }

    System.out.println("Bye i am number: " + Thread.currentThread().getName());
  }
}

class PruebaRunnable implements Runnable {

  public void run() {
    System.out.println(
      "Hello i am number: " + Thread.currentThread().getName()
    );
    for (int i = 0; i <= 5; i++) {
      System.out.println(i);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
    }
    System.out.println("Bye i am number: " + Thread.currentThread().getName());
  }
}

class MultithreadThing extends Thread {

  //@Override
  public void run() {
    System.out.println("Hello i am number: " + getName());

    for (int i = 0; i <= 5; i++) {
      System.out.println(i);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
    }
    System.out.println("Hello i am number: " + getName());
  }
}
