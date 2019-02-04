/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package całkowaniemetodaprostokatow;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Lenovo
 */
public class CałkowanieMetodaProstokatow {

    // klasa która przechowuje funkcje do całkowania
     private static double func(double x) {
//     return x*x+3;
//    return 1/x; //wybor 2 funkcji

    return sin(x); // wybor 3 funkcji 
}
    public static void main (String[] args)  throws FileNotFoundException, IOException {
        // inicjowanie zmiennych 
        double   xp, xk, calka, dx, blad, wynik;
    long n, k;
    k = 1; // 2 do k zwieksza dokładnosc 
    long stop , start; // zmienne ktore przechowują czas działania programu 
   
    //wczytanie xp początek przedziału i xk koniec przedziału z klawaitury i dokladnosci podziału 
    Scanner odczyt = new Scanner(System.in);
    System.out.println("Podaj poczatek przedzialu calkowania");
    xp = odczyt.nextDouble();
    System.out.println("Podaj koniec przedzialu calkowania");
    xk = odczyt.nextDouble();

    System.out.println("Podaj dokladnosc calkowania");
    n = odczyt.nextLong();
    start=System.currentTimeMillis();
    //###################################
    //      Pętla która wykonuje się dopuki błąd pomiaru nie bedzię akceptowalny < 0,005 lub program nie trwa powyzej 2minut
    //      i zawieksza dokladność dziesięciokrotnie co każde obejście
    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // ropoczęcie do wykresu
    do{
    dx = (xk - xp) / n;
    // rozpoczęcie calkowania numerycznego 
    calka = 0;
    for (int i=1; i<=n; i++) {
    calka += func(xp + i * dx);
    }
    calka *= dx;
    
    System.out.println("Wartość całki wynosi w przyblizeniu " + calka); // wypisanie wyniku całki metodą prostkątów na ekran
//    wynik = (xk*xk*xk)/3 + 3*xk  - (xp*xp*xp)/3 - 3*xp; // sprawdzenie poprawności obliczen poprzez policzenie funkcji pierwotnej
//     wynik = log(xk) - log(xp); // sprawdzenie poprawności obliczen poprzez policzenie funkcji pierwotnej
    wynik = - cos(xk) + cos(xp);
    System.out.println("sprwadzenie wyniku " + wynik); // wypisanie poprawnego wyniku na ekran
    blad = abs(wynik - calka); // obliczenie błedu pomiarowego 
    
    System.out.println("Blad pomiaru wynosi " + blad); // wyswietlenie błedu
//    double bladTeoretyczny = abs((2*xk * (xk-xp)*dx)/2); // błąd teoretyczny  metody liczącej początki prostokątów  z ksiązki Piotr Modliński całowanie numeryczne
    double bladTeoretyczny = abs((-1 / (xp*xp) *(xk-xp)*dx)/2);  /// funkcji 1/x  
//    double bladTeoretyczny = abs((1*(xk-xp)*dx)/2);
    System.out.println("błąd teoretyczny = " + bladTeoretyczny);
    //######################################################
    // klasa w javie która tworzy plik wyniki.txt i wpisuje przeprowadzone argumenty 
    //##########################################################
        File file = new File("Wyniki.txt");

            try {
                    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
                    PrintWriter zapis = new PrintWriter(out);

                    String tekst = ".\r\n" + "Wyniki z przebiegu doświadczenia.\r\n" + "metoda prostokątów dla współrzędnych xp = " +xp +" xk = " + xk + " n = " + n + ".\r\n" + "wynik całki "  +calka + ".\r\n" + "Prawidłowy wynik  to " + wynik + ".\r\n" + "błąd pomiaru to " + blad + ".\r\n" + "Błąd teoretyczny = " + bladTeoretyczny + ".\r\n";
//                        String wynik1 = "Wyniki z przebiegu doświadczenia.\r\n" + wynik + ".\r\n";
                    zapis.write(tekst);

                    zapis.close();

            } catch (IOException e) {
                    e.printStackTrace();
            }
            
            stop=System.currentTimeMillis(); // koniec odliczania
            if((stop - start) > 12000) // wylącza program gdy trwa powyzej 2 minut 
            {
                System.out.println("Program liczy za dlugo ( powyzej 2 min )");
                break;
            }
            dataset.setValue(blad, "błąd", "k" + n); // dodawanie danych do wykresu
            n =  (long) pow(2, k);
            k++;
//            n = n+2;
    }while(abs(blad) > 0.005); /// konczy gdy blad jest akceptowalny 
        
    // Tworzy wykres typu Bar - słupkowy
		JFreeChart chart = ChartFactory.createBarChart("Wykres bledu pomiarowego od ilosci krokow",
			"Liczba krokow", "Blad pomiarow", dataset, PlotOrientation.VERTICAL,
			false, true, false);
			//parametry podobnie jak w przykładach powyzej
 
		ChartFrame frame1=new ChartFrame("Wykres",chart);
		frame1.setVisible(true);
		frame1.setSize(500,400);
    
    }
    
}
