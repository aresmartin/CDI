import java.math.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 2. El hilo principal, en principio, actua como hasta ahora:
        1.- crea cierto número de hilos, cuya cantidad se para via línea de comando, OK
        2.- guarda todas las referencias en una estructura de un array adecuada, OK
        3.- lanza todos los hilos para que ejecuten sus métodos run() mencionados OK
            arriba,
        4.- y espera en un bucle final con los join() correspondientes para que terminen
            todos los hilos. OK
 */
/*
 * 3. Si ejecutas el hilo principal del programa, observarás que, dependiendo si algún hilo
      al final realiza un bucle infinito o no, se quedará también esperando sin acabar
 */
public class Evaluable {

  public static void main(String args[]) {
    Guardian guardian = new Guardian(Thread.currentThread());
    int primerArgumento = Integer.valueOf(args[0]);
    System.out.println(
      "Hello i am number: " + Thread.currentThread().getName()
    );

    List<Thread> myList = new ArrayList<>(primerArgumento);
    for (int i = 0; i < primerArgumento; i++) {
      myList.add(i, new MultithreadThing());
    }
    //Aqui arranco el guardian
    guardian.start();

    for (int i = 0; i < primerArgumento; i++) {
      myList.get(i).start();
    }
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      return;
    }

    for (int i = 0; i < primerArgumento; i++) {
      try {
        myList.get(i).join();
      } catch (InterruptedException e) {
        System.out.println("Main interrumpido");
        for (int j = i; j < primerArgumento; j++) {
          if (myList.get(j).isAlive()) {
            myList.get(j).interrupt();
          }
        }   
        try{
            myList.get(i).join();
            guardian.join();
        }catch(InterruptedException ee){}
        
        //main espera a que terminen sus hijos
        //guardian a interrumpido a main
        //main tiene que interrumpir a los hijos que queden
      }
    }

    
    System.out.println("Bye i am number: " + Thread.currentThread().getName());
  }
}

class MultithreadThing extends Thread {

  /*
 * 1. Cambia la tarea de los hilos de las prácticas pasadas por un simple bucle do-while
        con una condición booleana generada al azar (p.ej. un número aleatoria flotante
        entre 0 y 1 es menor o mayor que 0.5,) que o bien es un bucle infinito o bien es un
        bucle de una sola vuelta. Dentro del bucle realiza un sleep de un segundo. Cuando
        captas una posible interrupción de tal sleep(...) sal del bucle con un break
 *  1.- Bucle do-while OK
 *  2.- Generar numero al azar OK
 *  3.- Generar la condicion en while OK
 *  4.- Ejecucion una sola vez OK
 *  5.- Ejecucion infinita OK
 *  6.- Sleep de un segundo en el bucle OK
 *  7.- Interrupcion en el sleep (break en el catch) OK
 *  8.-
 */
  @Override
  public void run() {
    float numeroRandom = (float) (Math.random());
    System.out.println(
      "Hilo empieza ejecucion " + getName() + " " + numeroRandom
    );
    do {
      System.out.println("Ejecucion hilo " + getName());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.out.println("Hilo " + getName() + " interrumpido");
        break;
      }
    } while (numeroRandom >= 0.5);

    System.out.println("El hilo " + getName() + " ha terminado");
  }
}

/*
 * 5. Crea una clase Guardian que extiende de Thread, que
       1.-  recibe la referencia al hilo principal (busca információn como obtener tal
            referencia), OK
        2.- y ejecuta en su método run() una espera de unos 10 segundos y a acabar
            interrumpe el hilo principal. OK
       3.-  Crea y lanza tal guardián en el programa principal en un sitio adecuado. OK
        
        El hilo principal saldrá del bucle (que contiene los join()) por interrupción (si el
        temporizador del guardián haya terminando) y puede mandar ahora interrupciones a
        todos los hilos que todavía están ejecutando su bucle infinito
 */

class Guardian extends Thread {

  private Thread principal;

  public Guardian(Thread principal) {
    this.principal = principal;
  }

  @Override
  public void run() {
    System.out.println(
      "Hola soy " +
      getName() +
      " y este es mi hilo ppal: " +
      principal.getName()
    );
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      System.out.println("El hilo " + getName() + " se ha interrumpido.");
    }
    System.out.println("Interrumpiendo: " + principal.getName());
    principal.interrupt();

    System.out.println("Terminado guardian: " + getName());
  }
}
