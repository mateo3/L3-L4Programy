
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Fraktal extends Thread {
    final static int N = 4096;
    final static int CUTOFF = 100;
    static int[][] set = new int[N][N];
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Fraktal thread0 = new Fraktal(0);
        Fraktal thread1 = new Fraktal(1);
        Fraktal thread2 = new Fraktal(2);
        Fraktal thread3 = new Fraktal(3);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zako�czone w czasie " + (endTime - startTime) + " millisekund");
        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = set[i][j];
                float level;
                if (k < CUTOFF) {
                    level = (float) k / CUTOFF;
                } else {
                    level = 0;
                }
                Color c = new Color(0, level, 0);
                img.setRGB(i, j, c.getRGB());
            }
        }
        ImageIO.write(img, "PNG", new File("Julia.png"));
    }
    int me;
    int begin;
    int end;
    static final double ratioY = (1.25 - -1.25) / N;
    static final double ratioX = (1.25 - -1.25) / N;
    public Fraktal(int me) {
        this.me = me;
        this.begin = (N/4) * me;
        this.end = (N/4) * (me+1);
    }
    public void run() {
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
                double rzeczywista = i*ratioY + -1.25;
                double urojona = j*ratioX + -1.25;

                LiczbaZespolona c = new LiczbaZespolona(-0.123, 0.745);
                LiczbaZespolona z = new LiczbaZespolona(rzeczywista, urojona);
                int k = 0;

                while (k < CUTOFF && z.modul() < 2.0) {
                    // z = c + z * z
                    z = c.suma(z.kwadrat());
                    k++;
                }
                set[i][j] = k;
            }
        }
    }
}
class LiczbaZespolona {
    private double real;
    private double urojona;
    public LiczbaZespolona(double r, double u){
        this.real = r;
        this.urojona = u;
    }
    LiczbaZespolona suma(LiczbaZespolona inna){
        return new LiczbaZespolona(real + inna.real, urojona +inna.urojona);
    }
    LiczbaZespolona iloczyn(LiczbaZespolona inna){
        double rzeczywista = real * inna.real - urojona * inna.urojona;
        double urojona = real *inna.urojona + this.urojona *inna.real;
        return new LiczbaZespolona(rzeczywista, urojona);
    }
    double modul(){
        return Math.sqrt(real * real + urojona * urojona);
    }
    LiczbaZespolona kwadrat(){
        return this.iloczyn(this);
    }
}