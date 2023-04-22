class MyThread extends Thread {

    Runnable my_runnable = null;
  
    public MyThread(String str) {
      super(str);
    }
  
    public MyThread(Runnable run, String name) {
      super(name);
    }
  
    public void run() {
      if (my_runnable != null) {
        for (int i = 0; i < 10; i++) {
          System.out.println(i + " " + getName());
        }
        System.out.println("Termina thread " + getName());
      } else {
        my_runnable.run();
      }
    }
  }
  
  class MyRunnable implements Runnable {
  
    private String nombre;
    private int numSegundos;
  
    public MyRunnable(String str, int numSegundos) {
      nombre = str;
      this.numSegundos = numSegundos;
    }
  
    public void run() {
      
      System.out.println("Hello word, I am the java thread number " + getName() + " waiting: " + getSeconds());
      try {
        Thread.sleep(getSeconds() * 1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      System.out.println("Bye from thread number " + getName());
    }
  
    public String getName() {
      return nombre;
    }
  
    public int getSeconds() {
      return numSegundos;
    }
  }
  
  public class ThreadEjemploEjercicio4 {
  /*Extiende tu programa del apartado anterior para que en el método main de la clase
    principal se cree una lista (o un vector) de hilos e inícialos. Inmediatamente después
    de iniciar los hilos, desde el hilo principal, imprime a la pantalla un mensaje, p.ej.,
    el programa ha terminado. Para crear una lista de hilos debes tener una variable
    number_of_threads que recoge el numero de hilos especificado por línea de
    comando:
    
    int number_of_threads;
    List<Thread> threadList =
    new ArrayList<Thread>(number_of_threads);
    for(int i=1; i<=number_of_threads; ++i) {
    //...
} */
    public static void main(String[] args) {
      int numHilos = 0;
      int numSegundos = 0;
  
      if (args.length == 2) {
        numHilos = Integer.parseInt(args[0]);
        numSegundos = Integer.parseInt(args[1]);
      }
      System.out.println("Corriendo numHilos = " + numHilos + " Segundos: " + numSegundos);
  
      for (int i = 0; i < numHilos; i++) {
        //convertimos a string el valor de i para poder crear tantos nombres como hilos
        String nombreHilo = String.valueOf(i);
        //sirve para que ahora a MyRunnable le podamos pasar un string de nombre
        new Thread(new MyRunnable(nombreHilo, numSegundos)).start();
        
      }
    }
  }
  
  