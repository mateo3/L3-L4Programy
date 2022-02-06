import java.util.Random;

public class Samochod extends Thread {
    private String nrRej;
    private int pojZbiornika;
    private int paliwo;
    Random random = new Random();
    public Samochod(String nr, int _pojZbiornika){
        nrRej=nr;
        pojZbiornika=_pojZbiornika;
    }

    public void tankowanie(int _paliwo){
        paliwo+=_paliwo;
        if(paliwo>pojZbiornika){
            paliwo=pojZbiornika;
        }
    }

    public void start(){
        tankowanie(random.nextInt(20));
        super.start();
    } //start samochodu, uruchamiamy wątek zużycia paliwa

    public void Samochodstop(){
        super.stop();
    } //zatrzymanie samochodu, zatrzymujemy wątek zużycia paliwa

    public void run(){
        while(true) {
            try {
                sleep(1000);
                paliwo--;
                System.out.println("Pojazd "+ nrRej + " ma w baku "+ paliwo + "l paliwa");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(paliwo <= 0) {
                Samochodstop();
                System.out.println("Brak paliwa. Samochód "+ nrRej+ " unieruchomiony.");
                return;
            }

        }
    } //kod, który wykonuje się w odrębnym wątku, co 1 s programu zużywany jest 1 litr paliwa

    public static void main(String[] args) {
        new Samochod("WE 123", 50).start();
        new Samochod("BI 1232", 30).start();
        new Samochod("BAU 11A3", 67).start();
    }
}


