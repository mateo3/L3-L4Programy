import java.util.Random;
import java.util.concurrent.Semaphore;

public class RzutMoneta {
    private int iloscFilozofow;
    RzutMoneta(int ilosc) {
        iloscFilozofow = ilosc;
    }
    public void uruchomProblem() {
        RzutThread.usawIlosWidelcow(iloscFilozofow);
        for (int i = 0; i < iloscFilozofow; i++) {
            new RzutThread(i).start();
        }
    }
}

class RzutThread extends Thread {
    public static Semaphore[] widelce;
    private int mojNum;
    private Random random = new Random();
    public RzutThread(int nr) {
        mojNum = nr;
    }
    public static void usawIlosWidelcow(int ilosc) {
        widelce = new Semaphore[ilosc];
        for (int i = 0; i < widelce.length; i++)
            widelce[i] = new Semaphore(1);
    }
    public void run() {
        while (true) {
            System.out.println("Myslę: " + mojNum);
            Threadsleep();
            podniesWidelce();
            System.out.println("Zaczynam jeść: " + mojNum);
            Threadsleep();
            System.out.println("Konczę jeść: " + mojNum);
            odlozWidelce();
        }
    }
    private void podniesWidelce() {
        int widelecLewy = mojNum;
        int widelecPrawy = (mojNum + 1) % widelce.length;

        boolean najpierwLewy = random.nextBoolean();
        boolean podnioslDwaWidelce = false;
        do {
            if (najpierwLewy)
                podnioslDwaWidelce = podniesNajpierwPotem(widelecLewy, widelecPrawy);
            else
                podnioslDwaWidelce = podniesNajpierwPotem(widelecPrawy, widelecLewy);
        } while (!podnioslDwaWidelce);
    }
    private boolean podniesNajpierwPotem(int najpierw, int potem) {
        widelce[najpierw].acquireUninterruptibly();

        if (!widelce[potem].tryAcquire()) {
            widelce[najpierw].release();
        } else {
            return true;
        }

        return false;
    }
    private void odlozWidelce() {
        int widelecLewy = mojNum;
        int widelecPrawy = (mojNum + 1) % widelce.length;
        widelce[widelecLewy].release();
        widelce[widelecPrawy].release();
    }
    private void Threadsleep() {
        try {
            Thread.sleep((long) (5000 * Math.random()));
        } catch (InterruptedException e) {
        }
    }
}