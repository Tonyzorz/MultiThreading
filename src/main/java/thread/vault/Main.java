package thread.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    // 금고
    private static class Vault {
        private int password;

        public Vault(int password) {
            System.out.println("password is = " + password);
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return this.password == guess;
        }
    }

    // 해커 클래스
    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    // 해커 클래스 금고 비번 맞추기 ++
    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {

                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the passworwd " + guess);
                    System.exit(0);
                }
            }
        }
    }

    // 해커 클래스 금고 비번 맞추기 --
    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {

                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the passworwd " + guess);
                    System.exit(0);
                }
            }
        }

    }

    // 경찰 클래스
    private static class PoliceThread extends Thread{

        @Override
        public void run() {

            for (int i = 10; i > 0; i--) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("seconds = " + i);
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }

}
