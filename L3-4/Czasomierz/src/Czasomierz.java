class Czasomierz extends Thread {
    int sekundy, minuty;
    int temp_sekundy, temp_minuty;
    public Czasomierz(int _minuty, int _sekundy) {
        minuty = _minuty;
        sekundy = _sekundy;
    }
    public void run() {
        while(true) {

            try {
                Thread.sleep(1000);
                temp_sekundy++;
                System.out.println("Czas: " + temp_minuty + ":"+ temp_sekundy);
            }catch(Exception e) {
            }
            if(temp_sekundy==60){
                temp_sekundy=0;
                temp_minuty++;
            }
            if(temp_minuty==minuty && temp_sekundy==sekundy){
                return;
            }
    }
}
    public static void main(String[] args) {
        Czasomierz czasomierz = new Czasomierz(1, 5);
        czasomierz.start();
    }
}
