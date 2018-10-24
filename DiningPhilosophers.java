 class Philosopher implements Runnable {


  private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

   private void doAction(String action) throws InterruptedException {
        System.out.println(
          Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 1000)));
    }

    public void run() {
        try {
            while (true) {


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
        } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               return;
           }
    }
}



public class DiningPhilosophers {

    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {

                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                    philosophers[i] = new Philosopher(leftFork, rightFork);
                }

            Thread t
              = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}