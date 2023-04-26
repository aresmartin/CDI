public class PingPongRunnable implements Runnable{

  private String word; // Lo que va a escribir.
  private int delay; // Tiempo entre escrituras

  public PingPongRunnable(String queDecir, int cadaCuantosMs) {
    word = queDecir;
    delay = cadaCuantosMs;
  }

  public void run() {
    while (true) {
      System.out.print(word + " ");
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        return;
      }
    }
  }

  public static void main(String[] args) {
    PingPongRunnable r1 = new PingPongRunnable("PING", 33);
    PingPongRunnable r2 = new PingPongRunnable("PONG", 10);
    //ejemplo de instancia en un solo paso
    Thread t0 = new Thread(new PingPongRunnable("PANG", 666));
    // Se crean los threads
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    // Se activan los threads
    t1.start();
    t2.start();
    t0.start();
  }
}
