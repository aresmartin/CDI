import java.math.*;
import java.util.ArrayList;
import java.util.List;
//ruta linux-terminal: /mnt/c/Documents and Settings/Usuario/Documents/GitHub/CDI/Practica2
public class Practica2Ej2 {

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
    System.out.println("Arrancando");
    for (int i = 0; i < 1000000; ++i) {
      double d = Math.tan(
        Math.atan(
          Math.tan(
            Math.atan(
              Math.tan(
                Math.atan(
                  Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))
                )
              )
            )
          )
        )
      );
      Math.cbrt(d);
    }
    System.out.println("Terminado");
  }
}
