import java.math.*;
import java.util.ArrayList;
import java.util.List;

//ruta linux-terminal: /mnt/c/Documents and Settings/Usuario/Documents/GitHub/CDI/Practica2
public class InterrupcionEj1 {

  public static void main(String args[]) {
    int primerArgumento = Integer.valueOf(args[0]);
    System.out.println(
      "Hello i am number: " + Thread.currentThread().getName()
    );

    List<Thread> myList = new ArrayList<>(primerArgumento);
    for (int i = 0; i < primerArgumento; i++) {
      myList.add(i, new MultithreadThing());
    }

    for (int i = 0; i < primerArgumento; i++) {
      myList.get(i).start();
    }
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      return;
    }

    if(myList.get(0).isInterrupted()){
      System.out.println("Antes");
    }

    if (myList.get(0).isAlive()) {
      System.out.println("Hilo " + myList.get(0).getName() + " interrumpido");
      myList.get(0).interrupt();
    }

    if(myList.get(0).isInterrupted()){
      System.out.println("Despues");
    }

    for (int i = 0; i < primerArgumento; i++) {
      try {
        myList.get(i).join();
      } catch (InterruptedException e) {}
    }

    System.out.println("Bye i am number: " + Thread.currentThread().getName());
  }
}

class MultithreadThing extends Thread {

  //@Override
  public void run() {
   // try {
      boolean negative = false;
      double pi = 0;
      System.out.println("Arrancando hilo " + getName());
      for (int i = 1; i < 1000000000; i += 2) {
        if (negative) {
          pi -= 1.0 / i;
        } else {
          pi += 1.0 / i;
        }
        negative = !negative;
        if(interrupted()){
          System.out.println("I am " + getName() + " interrupted");
          break;
        }
      }
      pi *= 4.0;
    
      System.out.println("Terminado hilo " + getName() + " Numero pi: " + pi);
    //} catch (InterruptedException e) {
     // System.out.println("fhdslfad");
    //}
  }
}
