import java.util.Random;

class Montecarlo extends Thread {
    double xStart, yStart, xStop, yStop;
    int N;
    double wynik;
    Random r;
    public Montecarlo(double xStart, double yStart, double xStop, double yStop, int liczbaStrzalow) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.wynik = 0;
        this.N = liczbaStrzalow;
        this.r = new Random();
    }
    public void run() {
        int temp = 0;
        for (int i = 0; i < this.N; i++) {
            double x = Math.random();
            double y = Math.random();
            if ((x * x + y * y) <= 1)
                temp++;
        }
        this.wynik = temp;
    }
    public static void main(String[] args) {
        Montecarlo m1, thread2, thread3, thread4;
        int liczbaStrzalow = 654;
        double a = 5;
        m1 = new Montecarlo(0,0, a/2, a/2, liczbaStrzalow);
        thread2 = new Montecarlo(a/2,0, 1, a/2, liczbaStrzalow);
        thread3 = new Montecarlo(0, a/2, a/2, a, liczbaStrzalow);
        thread4 = new Montecarlo(a/2,a/2, a, a, liczbaStrzalow);
        m1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        try {
            m1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }catch (Exception e){
        }
        double pole = m1.wynik + thread2.wynik + thread3.wynik + thread4.wynik;
        pole = pole / ((double)liczbaStrzalow * 4) * (a * a);
        System.out.println("Pole kola = " + pole);
    }
}
