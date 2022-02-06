import java.util.concurrent.Semaphore;

public class Semafory {
    int iloscFilozofow;
    Semafory(int ilosc) {
        iloscFilozofow = ilosc;
    }
    public void uruchomProblem() {
        Semafora.usawIlosWidelcow(iloscFilozofow);
        for (int i = 0; i < iloscFilozofow; i++) {
            new Semafora(i).start();
        }
    }
}
class Semafora extends Thread {
    public static Semaphore[] widelce;
    private int mojNum;
    public Semafora(int nr) {
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
        widelce[widelecLewy].acquireUninterruptibly();
        widelce[widelecPrawy].acquireUninterruptibly();
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
