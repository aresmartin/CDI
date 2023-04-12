class MyThread extends Thread {

  public MyThread(String str) {
    super(str);
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(i + " " + getName());
    }
    System.out.println("Termina thread " + getName());
  }
}

class MyRunnable implements Runnable {

  private String nombre;

  public MyRunnable(String str) {
    nombre = str;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(i + " " + getName());
      System.out.println(i + " " + Thread.currentThread().getName());
    }
    System.out.println("Termina thread " + getName());
  }

  public String getName() {
    return nombre;
  }
}

public class ThreadMiniEjemplo {

  public static void main(String[] args) {
    new MyThread("Pepe").start();
    new MyThread("Juan").start();
    new Thread(new MyRunnable("Maria")).start();
    new Thread(new MyRunnable("Lorenzo")).start();
    System.out.println("Termina thread main");
  }
}
