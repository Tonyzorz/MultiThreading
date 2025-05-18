package thread.create1;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Code that will run in a new thread.
                // 1
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        thread.setName("New worker thread");

        thread.setPriority(Thread.MAX_PRIORITY);

        //2
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "before starting ");

        thread.start();

        // 3
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + "after starting ");
    }
}
