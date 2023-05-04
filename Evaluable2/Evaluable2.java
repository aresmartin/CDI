import java.util.*;
import java.util.concurrent.*;

class Consumer extends Thread {
    //se declara el buffer como un objeto BlockingQueue de enteros
    private BlockingQueue<Integer> buffer;

    public Consumer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int t_sleep = (int) (Math.random() * 1000 + 1);
                Thread.sleep(t_sleep);
                read();
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer " + getName() + " interrumpido");
        }
    }
    //este metodo lee un elemento del buffer
    public void read() throws InterruptedException {
        int plato = buffer.take();//quitamos un elemento del buffer utilizando el metodo take()
        System.out.println("Consumer pilla " + plato);//imprimimos el elemento por consola
    }
}

class Producer extends Thread {

    private BlockingQueue<Integer> buffer;

    public Producer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int t_sleep = (int) (Math.random() * 1000 + 1);
                Thread.sleep(t_sleep);
                write();
            }
        } catch (InterruptedException e) {
            System.out.println("Producer " + getName() + " interrumpido");
        }
    }
    //metodo que escribe un elemento en el buffer
    public void write() throws InterruptedException {
        int plato = (int) (Math.random() * 10 + 1); //generamos numero aleatorio entre 1 y 10 
        this.buffer.put(plato);//colocamos el numero generado en el buffer a traves del metodo put
        System.out.println("Producer genera " + plato);//imprimimos el numero generado por consola
    }
}

public class Evaluable2 {

    public static void main(String[] args) {
        System.out.println("Hola soy: " + Thread.currentThread().getName());
        //Pedimos los argumentos por consola
        int capacidad = Integer.valueOf(args[0]);
        int n_consumers = Integer.valueOf(args[1]);
        int n_producers = Integer.valueOf(args[2]);

        //
        BlockingQueue<Integer> buffer = new LinkedBlockingQueue<Integer>(capacidad);

        //creamos las listas de Consumers y Producters
        List<Producer> producers = new ArrayList<Producer>();
        List<Consumer> consumers = new ArrayList<Consumer>();

        //creamos e inicializamos los hilos Consumers
        for (int i = 0; i < n_consumers; i++) {
            consumers.add(new Consumer(buffer));
            consumers.get(i).start();
        }

       //creamos e inicializamos los hilos Producers
        for (int i = 0; i < n_producers; i++) {
            producers.add(new Producer(buffer));
            producers.get(i).start();
        }

        //esperamos 5 segundos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return;
        }

        //interrumpimos luego de esperar tanto los hilos Consumers como los Producers
        for (int i = 0; i < n_consumers; i++) {
            consumers.get(i).interrupt();
        }
        for (int i = 0; i < n_producers; i++) {
            producers.get(i).interrupt();
        }

        //Esperamos al hilo principal para terminal la ejecucion
        try {
            for (int i = 0; i < n_consumers; i++) {
                consumers.get(i).join();
            }
            for (int i = 0; i < n_producers; i++) {
                producers.get(i).join();
            }
        } catch (InterruptedException e) {
            return;
        }

        System.out.println(Thread.currentThread().getName() + " ha terminado.");
    }
}
