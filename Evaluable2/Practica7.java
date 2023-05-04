import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.*;

class Buffer {

    public final Lock lock = new ReentrantLock();
    public final Condition isFull = lock.newCondition();
    public final Condition isEmpty = lock.newCondition();
    LinkedList<Integer> myList = new LinkedList<Integer>();
    int capacidadMax;

    public Buffer(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public boolean estaLleno() {
        return myList.size() >= capacidadMax;
    }

    public boolean estaVacio() {
        return myList.size() <= 0;
    }

    public void generar(int plato) throws InterruptedException {
        lock.lock();

        while (estaLleno()) {
            System.out.println("Buffer lleno esperando");
            isFull.await();
        }

        myList.add(plato);

        isEmpty.signal();
        lock.unlock();
    }

    public int coger() throws InterruptedException {
        lock.lock();

        while (estaVacio()) {
            System.out.println("Buffer vacio esperando");
            isEmpty.await();
        }
        int plato = myList.get(0);
        myList.remove(0);

        isFull.signal();
        lock.unlock();

        return plato;
    }
}

class Consumer extends Thread {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
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

    public void read() throws InterruptedException {
        int plato = this.buffer.coger();
        System.out.println("Consumer pilla " + plato);
    }
}

class Producer extends Thread {

    private Buffer buffer;

    public Producer(Buffer buffer) {
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

    public void write() throws InterruptedException {
        int plato = (int) (Math.random() * 10 + 1);
        this.buffer.generar(plato);
        System.out.println("Producer genera " + plato);
    }
}

public class Evaluable2 {

    public static void main(String[] args) {
        int capacidad = Integer.valueOf(args[0]);
        int n_consumers = Integer.valueOf(args[1]);
        int n_producers = Integer.valueOf(args[2]);

        Buffer buffer = new Buffer(capacidad);

        List<Consumer> consumers = new ArrayList<Consumer>();
        for (int i = 0; i < n_consumers; i++) {
            consumers.add(new Consumer(buffer));
            consumers.get(i).start();
        }

        List<Producer> producers = new ArrayList<Producer>();
        for (int i = 0; i < n_producers; i++) {
            producers.add(new Producer(buffer));
            producers.get(i).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return;
        }
        for (int i = 0; i < n_consumers; i++) {
            consumers.get(i).interrupt();
        }
        for (int i = 0; i < n_producers; i++) {

            producers.get(i).interrupt();
        }

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
    }
}