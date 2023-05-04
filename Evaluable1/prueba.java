import java.math.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

//ruta linux-terminal: /mnt/c/Documents and Settings/Usuario/Documents/GitHub/CDI/Practica2
public class prueba {

/*
 *  El hilo principal, en principio, actua como hasta ahora:
    crea cierto número de hilos, cuya cantidad se para via línea de comando,
    guarda todas las referencias en una estructura de un array adecuada,
    lanza todos los hilos para que ejecuten sus métodos run() mencionados
    arriba,
    y espera en un bucle final con los join() correspondientes para que terminen
    todos los hilos.

    1.- crear cierto numero de hilos OK
    2.- cantidad de hilos via linea de comando OK
    3.- guardar todos los hilos en un array struct OK
    4.- lanzar todos los hilos OK
    5.- esperar al bucle final con los join() OK
 */
  public static void main(String args[]) {
    System.out.println("¡HOLA! soy el hilo: " + Thread.currentThread().getName());
    Guardian guardian = new Guardian(Thread.currentThread());
    int primerArgumento = Integer.valueOf(args[0]);
    List <Thread> myList = new ArrayList<>();

    //Creamos los hilos correspondientes y los añadimos a la lista
    for(int i = 0; i < primerArgumento; i++){
        myList.add(i, new MultithreadThing());
    }
    
    //lanzamos guardian antes que a los hilos creados
    guardian.start();

    //lanzamos todos los hilos
    for(int i = 0; i < primerArgumento; i++){
        myList.get(i).start();
    }

    //esperar al hilo main con join
    for(int i = 0; i < primerArgumento; i++){
        try{
            myList.get(i).join();
        }catch(InterruptedException e){
            System.out.println("Main interrumpido");
            for(int j = i; j < primerArgumento; j++){
                if(myList.get(j).isAlive()){
                    myList.get(j).interrupt();
                }
            }

            try{
                myList.get(i).join();
            }catch(InterruptedException ee){
                
            }
        }
    }

    System.out.println("bye from " + Thread.currentThread().getName() + " :(");

  }
}

/*
 * 1. Cambia la tarea de los hilos de las prácticas pasadas por un simple bucle do-while
        con una condición booleana generada al azar (p.ej. un número aleatoria flotante
        entre 0 y 1 es menor o mayor que 0.5,) que o bien es un bucle infinito o bien es un
        bucle de una sola vuelta. Dentro del bucle realiza un sleep de un segundo. Cuando
        captas una posible interrupción de tal sleep(...) sal del bucle con un break

        1.- hacer un do-while en el run  OK
        2.- generar un numero random entre 0 y 1 OK
        3.- poner la condicion en el while  OK
        4.- bucle infinito      OK
        5.- bucle una sola vuelta OK
        6.- dentro del bucle realizar un sleep de un segundo OK
        7.- salir del bucle cuando se capta una interrupcion OK
 */

class MultithreadThing extends Thread {

  //@Override
  public void run() {
    System.out.println("Arranca el hilo " + getName());
    float numeroRandom = (float) (Math.random());
    System.out.println(getName() + " numero: " + numeroRandom);

    do {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        break;
      }
    } while (numeroRandom > 0.5);

    System.out.println("Terminado hilo " + getName());
  }
}


/*
 * 5.   Crea una clase Guardian que extiende de Thread, que
        recibe la referencia al hilo principal (busca információn como obtener tal
        referencia),
        y ejecuta en su método run() una espera de unos 10 segundos y a acabar
        interrumpe el hilo principal.
        Crea y lanza tal guardián en el programa principal en un sitio adecuado.
        El hilo principal saldrá del bucle (que contiene los join()) por interrupción (si el
        temporizador del guardián haya terminando) y puede mandar ahora interrupciones a
        todos los hilos que todavía están ejecutando su bucle infinito.

        1.- crear clase guardian OK
        2.- añadir referencia del hilo principal OK
        3.- metodo run: esperar 10 segundos OK
        4.- al acabar sleep interrumpir bucle principal OK
        5.- crear y lanzar guardian en main OK
        6.- mandar en el bucle del join interrupciones a todos los hilos
 */

class Guardian extends Thread{

    Thread referenciaMain = new Thread();

    public Guardian(Thread referenciaMain){
        this.referenciaMain = referenciaMain;
    }

    public void run(){
        System.out.println("Guardian esta listo para empezar: " + getName());
        try{
            Thread.sleep(10000);
        }catch(InterruptedException e){
            System.out.println(getName() + " se ha interrumpido.");
        }
        referenciaMain.interrupt();

        System.out.println("Guardian ha terminado su ejecucion: " + getName());
    }

}