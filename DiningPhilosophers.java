class Philosopher implements Runnable {
  private Object leftFork;
  private Object rightFork;
  public Philosopher(Object leftFork, Object rightFork) {
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }
 private void doAction(String action) throws InterruptedException {
   final int timesec=1000;
   System.out.println(
   Thread.currentThread().getName() + " " + action);
   Thread.sleep(((int) (Math.random() * timesec)));
  }
 public void run() {
   try {
     while(true) {
       doAction(": Thinking");
       synchronized (leftFork) {
         doAction(": Has Picked up left fork");
         synchronized (rightFork) {
           doAction(": Has Picked up right fork - Now he is eating");
           doAction(": Has Put down right fork - Now he is not eating");
          }
          doAction(": Has Put down left fork. Back to thinking");
        }
      }
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return;
    }
  }
}
public class DiningPhilosophers {
  public static void main(String[] args) throws Exception {
    Philosopher[] philosophers = new Philosopher[5];
    Object[] forks = new Object[philosophers.length];
    for(int count = 0; count < forks.length; count++) {
      forks[count] = new Object();
    }
    for(int count = 0; count< philosophers.length; count++) {
     Object leftFork = forks[count];
     Object rightFork = forks[(count + 1) % forks.length];
     if (count== philosophers.length - 1) {
        philosophers[count] = new Philosopher(rightFork, leftFork);
      }
     else {
       philosophers[count] = new Philosopher(leftFork, rightFork);
      }
      Thread philosopherthreadobj = new Thread(philosophers[count], "Philosopher " + (count + 1));
      philosopherthreadobj.start();
    }
  }
}