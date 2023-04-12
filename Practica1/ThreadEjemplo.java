class MyThread extends Thread {
  Runnable my_runnable = null;
    public MyThread(String str) {
      super(str);
    }
  
    public MyThread(Runnable run, String name) {
      super(name);
    }
  
    public void run() {
      if (my_runnable != null){
          for (int i = 0; i < 10; i++) {
          System.out.println(i + " " + getName());
          }
          System.out.println("Termina thread " + getName());
          }
      else {
          my_runnable.run();
      }
    }
  }
  
  class MyRunnable implements Runnable{
  
      private String nombre;
      public MyRunnable(String str) {
          nombre = str;
        }
      public void run(){
          for (int i = 0; i < 10; i++) {
              System.out.println(i + " " + getName());
             // System.out.println(i + " " +          Thread.currentThread().getName());
            }
            System.out.println("Termina thread " + getName());
  
      }
  
      public String getName(){
          return nombre;
      }
  }
  
  public class ThreadEjemplo {
  
    public static void main(String[] args) {
      /*new MyThread("Pepe").start();
      new MyThread("Juan").start();
      //Runnable runnable = new MyRunnable("Jose");
      new Thread (new MyRunnable("Maria")).start();
      new Thread (new MyRunnable("Lorenzo")).start();
      new MyThread(new MyRunnable("Carlos"), "Carlos").start();
      Runnable run = new MyRunnable("hola");
      run.run();
      System.out.println("Termina thread main");*/
      int numHilos = 0; 
      
      if(args.length == 1){
        numHilos = Integer.parseInt (args[0]);
      }
      System.out.println("Corriendo numHilos = " + numHilos);

      for(int i = 0; i<numHilos; i++){
        //convertimos a string el valor de i para poder crear tantos nombres como hilos
        String nombreHilo= String.valueOf(i);
        //sirve para que ahora a MyRunnable le podamos pasar un string de nombre
        new Thread (new MyRunnable(nombreHilo)).start();
        //System.out.println("Hilo numero " + i);
      }
    }
  }




/*class MyThread extends Thread {

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

class MyRunnable implements Runnable{

    private String nombre;
    public MyRunnable(String str) {
        nombre = str;
      }
    
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
            System.out.println(i + " " +          Thread.currentThread().getName());                       
          }
          System.out.println("Termina thread " + getName());

    }

    public String getName(){
        return nombre;
    }
    
}

public class ThreadEjemplo {

  public static void main(String[] args) {
    new MyThread("Pepe").start();
    new MyThread("Juan").start();
    new Thread (new MyRunnable("Maria")).start();
    new Thread (new MyRunnable("Lorenzo")).start();
    System.out.println("Termina thread main");
  }
}
*/