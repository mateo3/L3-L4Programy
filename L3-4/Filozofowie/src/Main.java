import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int filozofowieN;
        do{
            System.out.println("Ilość filozofów od 2 do 100:");
            filozofowieN = scanner.nextInt();
        }while(filozofowieN <2 || filozofowieN > 100);
        int rodzaj=0;
        do {
            System.out.println("Wybierz problem:");
            System.out.println("1. Wykorzystanie Semaforów.");
            System.out.println("2. Niesymetryczne sięganie po widelec.");
            System.out.println("3. Wykorzystanie rzutu monetą.");
            rodzaj = scanner.nextInt();
        }while(rodzaj < 1 || rodzaj > 3);
        if(rodzaj == 1)
            new Semafory(filozofowieN).uruchomProblem();
        else if(rodzaj == 2)
            new Niesymetrycznie(filozofowieN).uruchomProblem();
        else if(rodzaj == 3)
            new RzutMoneta(filozofowieN).uruchomProblem();
    }}