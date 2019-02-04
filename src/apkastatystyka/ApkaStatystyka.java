/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apkastatystyka;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Lenovo
 */
public class ApkaStatystyka extends JFrame {
     

    public  ApkaStatystyka() throws FileNotFoundException, IOException
    {
        super("Statystyka");
        this.setBounds(400, 400, 400, 400);
        this.setDefaultCloseOperation(3); // wylacza ramke gdy X
        Container kontener = this.getContentPane(); // panel przechowujacy przyciski wyswietlenia itp
        

         FileDialog fd =new FileDialog(this,"Wczytaj",FileDialog.LOAD);
       // Ewentualnie: FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
         fd.setVisible(true);
         
         String katalog=fd.getDirectory();
         String plik=fd.getFile();
         System.out.println("Wybrano plik: " + plik);
         System.out.println("w katalogu: "+ katalog);
         System.out.println("Ścieżka: "+ katalog + plik);
                 int iloscWierszy = 0;
        Scanner odczyt4 = new Scanner(System.in);
                File plikWygrane = new File(plik);
        Scanner odczytWygrane = new Scanner(plikWygrane);
        if(plikWygrane.exists())
        {
            FileReader fr = new FileReader(plikWygrane);
            LineNumberReader lnr = new LineNumberReader(fr);
    		    
            while (lnr.readLine() != null)
            {
                iloscWierszy++;
            }
    	 
            System.out.println("ilosc wczytanych wierszy to : " + iloscWierszy);
            lnr.close();
        }
         int daneWygrane; // zmienna w ktorej bedziemy przechowywac kolejne wiersze tabeli76
        int i9 = 0; //zmienna do numerowanie indeksu tablicy
        double[] tabWygrane = new double[iloscWierszy]; // tablica w ktorej bedą przetrzymywane dane z pliku
        ///////////////////////////////////////////////////////////////////////
        //////// petla while wpisująca kolejne wiersze pliku do tablicy
        while (odczytWygrane.hasNextInt()) // petla while bedzie sie wykonywac do konca pliku
        {
            
            daneWygrane = odczytWygrane.nextInt(); // zapisanie wiersza do zmiennej dane
            tabWygrane[i9] = daneWygrane;           /// zapisanie zmiennej danej do tablicy
            i9++;                     /// zmiana indeksu tablicy
        }
        
        //Przeszukaj tablice
        System.out.println();
        int n = 10;
        int [] liczby = new int[iloscWierszy];
        int [] ilosc = new int[iloscWierszy];
        int indeks = 0;
        int ileLiczb = 0;
        
        for (int i = 0; i < iloscWierszy; i++) 
        {
            int temp = (int) tabWygrane[i];

            for (int j = 0; j < iloscWierszy; j++) 
            {
                if (liczby[j] == temp) 
                {
                    ++ilosc[j];
                    break;
                }
                else if (j == iloscWierszy-1) 
                {
                    liczby[indeks] = temp;
                    ilosc[indeks] = 1;
                    ++indeks;
                    ++ileLiczb;
                }
            }
         }
        // wyswietlenie szeregu punktowego
        
        for ( int i2 = 0; i2 < liczby.length; i2++)
            {   
                if (liczby[i2] == 0 && ilosc[i2] == 0)
                    break;
                System.out.println("wygranych" + " " + liczby[i2] + " " + "ile drużyn " + ilosc[i2]);
                
                    if(i2>30)
                    {   
                        System.out.println("Za dużo liczb punktowy możę być nieczytelny skorzystaj z przedziałowego");
                        break;
                    }
            } 
        int c = 0;
         for ( int i2 = 0; i2 < liczby.length; i2++)
            {   
                if (liczby[i2] == 0 && ilosc[i2] == 0)
                    break;
                
                c++;
                                if (liczby[i2] == 0 && ilosc[i2] == 0)
                    break;
            } 
         /////////////wczytywanie wierszy do zewnętrznej bilbioteki
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int i = 0; i < iloscWierszy; i++) 
           {
                   stats.addValue(tabWygrane[i]);
           }
        ///////////////////
        
         ///####################################################
         /// Tworzenie okienka z szeregiem punktowym 
         /// ###################################################
         System.out.println("Potrzebna dlugość przedziału punktowego to " + c);
//        JFrame ramka = new JFrame ("Witaj Java" );
        this.setLayout(new GridLayout(c + 12,1));
        kontener.add(new JLabel("Wartość    " + "ilość wystąpień ",JLabel.LEFT));
        for(int i=0;i<c;i++)
        {
            kontener.add(new JLabel("   " +  liczby[i] + "              " + ilosc[i] ,JLabel.LEFT));
            if(i >20)
                break;
            
        }
//            kontener.add(new JLabel("Średnia: "+StatUtils.mean(tabWygrane)),JLabel.BOTTOM_ALIGNMENT);
        JLabel suma = new JLabel ("Suma liczb = " + podajSumeLiczb(tabWygrane));
        suma.setLocation(100, 400);
        kontener.add(suma, JLabel.BOTTOM_ALIGNMENT);
        JLabel srednia = new JLabel ("Srednia = " + podajSredniaArytmetyczna(tabWygrane));
        srednia.setLocation(100, 400);
        kontener.add(srednia, JLabel.BOTTOM_ALIGNMENT);
        JLabel najmniejsza = new JLabel ("Najmniejsza Wartość = " + podajNajmniejszaLiczbe(tabWygrane));
        najmniejsza.setLocation(100, 400);
        kontener.add(najmniejsza, JLabel.BOTTOM_ALIGNMENT);
        JLabel najwieksza = new JLabel ("Najwieksza wartość  = " + podajNajwiekszaLiczbe(tabWygrane));
        najwieksza.setLocation(100, 400);
        kontener.add(najwieksza, JLabel.BOTTOM_ALIGNMENT);
        JLabel wariancja = new JLabel ("Wariancja = " + podajSredniaArytmetyczna(tabWygrane));
        srednia.setLocation(100, 400);
        kontener.add(wariancja, JLabel.BOTTOM_ALIGNMENT);
        JLabel sumaKwadratow = new JLabel ("Suma kwadratów = "+StatUtils.sumSq(tabWygrane));
        sumaKwadratow.setLocation(100, 400);
        kontener.add(sumaKwadratow, JLabel.BOTTOM_ALIGNMENT);
        JLabel odchylenie = new JLabel ("Odch. standardowe = " + stats.getStandardDeviation());
        odchylenie.setLocation(100, 400);
        kontener.add(odchylenie, JLabel.BOTTOM_ALIGNMENT);
        JLabel mediana = new JLabel ("Mediana = " + mediana(tabWygrane));
        mediana.setLocation(100, 400);
        kontener.add(mediana, JLabel.BOTTOM_ALIGNMENT);
        JLabel dominanta = new JLabel ("Dominanta = " + dominanta(tabWygrane));
        dominanta.setLocation(100, 400);
        kontener.add(dominanta, JLabel.BOTTOM_ALIGNMENT);
        //ramka.getContentPane().add( napis);
        JButton wykres = new JButton("Wykres");
                wykres.setLocation(100, 400);
            

//                addActionListener
        kontener.add(wykres, JButton.BOTTOM_ALIGNMENT);
        ////////////////////////////////////////////////////////////////
  //##############################################
  //       rysowanie  Tworzenie wykresu kołowego  do punktowego
  //##############################################
 DefaultPieDataset dane = new DefaultPieDataset();
 /////////// pętla wczytujące dane i ilosć ich wystąpień /////
        for ( int i2 = 0; i2 < liczby.length; i2++)
        {
            if (liczby[i2] == 0 && ilosc[i2] == 0)
                break;
            if(i2>30)
                break;
            dane.setValue("zwycięstwa " + liczby[i2],ilosc[i2]); //wartosci
        }
 
	//Tworzymy wykres JFreeChart typu PieChart
	JFreeChart chart = ChartFactory.createPieChart
		("Wykres ilości zwycieśtw w sezonie Leauge of Legends ", // Tytuł
		dane, // Dane
		true, // Flaga - Legendy
		true, // Tultips – male opisy
		false // Configure chart to generate URLs?
	);
 
	ChartFrame frame1=new ChartFrame("statystyka ilosci wygranych",chart);
	frame1.setVisible(true);
	frame1.setSize(700,700);
         //###################################
         /// wykres słupkowy
          //////////// początek wykresu slupkowego
        ///////////// podanie danych do wykresu
        
        DefaultCategoryDataset dataset = new
        DefaultCategoryDataset();
        for ( int i2 = 0; i2 < liczby.length; i2++)
        {
            if (liczby[i2] == 0 && ilosc[i2] == 0)
                break;
            if(i2>30)
                break;
           dataset.setValue(ilosc[i2], "Seria1", " "+ liczby[i2]);
        }
        // Tworzy wykres typu Bar - słupkowy
        JFreeChart chart2 = ChartFactory.createBarChart("Wykres słupkowych Sezon Leauge of Legends", "Ilość wygranych w sezonie", "Ilość drużyn którę osiągneły dany pułap wygranych", dataset, PlotOrientation.VERTICAL, false, true, false);
        //parametry podobnie jak w poprzednich przykladach
        ChartFrame frame2=new ChartFrame("Bar Chart",chart2);
        frame2.setVisible(true);
        frame2.setSize(900,700);
       
        
    }
  

    private static class szPunktowy implements ActionListener
    {

        public szPunktowy() 
        {
            
        }

        
        public void actionPerformed(ActionEvent e)
        {
            try {
                new ApkaStatystyka().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ApkaStatystyka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static class szPrzedziałowy  extends JFrame implements ActionListener {

        public szPrzedziałowy() {
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try {
                new wyświetlPrzedziałowy();
            } catch (IOException ex) {
                Logger.getLogger(ApkaStatystyka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

       
    }

    ///#####################################
    //                MAIN
    public static void main(String[] args) throws IOException {
        
        JFrame okno = new JFrame("Statysyka");
        okno.setDefaultCloseOperation(3);
        okno.setBounds(400,400,400,400);
        okno.setVisible(true);
        Container kontener = okno.getContentPane();
        JButton szeregPunktowy = new JButton("Szereg Punktowy");
        szeregPunktowy.addActionListener(new szPunktowy());
        szeregPunktowy.setLocation(50,20);
        szeregPunktowy.setSize(200,25);
        kontener.add(szeregPunktowy);
        JButton szeregPrzedziałowy = new JButton("Szereg przedziałowy");
        szeregPrzedziałowy.addActionListener(new szPrzedziałowy());
        szeregPrzedziałowy.setLocation(50, 60);
        szeregPrzedziałowy.setSize(200,25);
        kontener.add(szeregPrzedziałowy);
        
    kontener.setLayout(null);
       
        
//        new ApkaStatystyka().setVisible(true);
        
        

    }
   
        
       private static double podajSumeLiczb(double[] liczby) {

        double sum = 0;

        for (double l : liczby) {

            sum=sum+l;

        }

        return sum;

    }

    
     private static double podajSredniaArytmetyczna(double[] liczby) {

        double srednia = podajSumeLiczb(liczby)/liczby.length;

        return srednia;

    }
     //##########################################################
     // funkcja licząca WARIANCJE
     //#####################################################
    private static double podajWariancje(double[] liczby) {

        double wariancja;

        double srednia = podajSredniaArytmetyczna(liczby);

        double sum=0.0;

        for (double x : liczby) {

            sum=sum+(x-srednia)*(x-srednia);

        }

        wariancja = sum/(liczby.length-1);

        return wariancja;

    }
    

    public static double podajNajmniejszaLiczbe(double[] liczby) {

        double min = liczby[0];

        for (double l : liczby) 
            {

                if (l < min)
                {
                    min = l;

                }
            }
        return min;

    }
	    private static double podajNajwiekszaLiczbe(double[] liczby) 
            {

                double max = liczby[0];

                for (double l : liczby) {

                    if (l > max)
                    {
                        max = l;
                    }

                }
                return max;

            } 
            
            private static double mediana(double [] liczby) {
		double mediana = 0;
		Arrays.sort(liczby);
		if (liczby.length % 2 == 0) {
			mediana = (liczby[liczby.length / 2] + liczby[(liczby.length / 2) + 1]) / 2.0;
		} else {
			mediana = liczby[liczby.length / 2];
		}
 
		return mediana;
	}
        private static double dominanta(double[] liczby) {
		double dominanta = 0;
		int maks = 0;
		int licznik = 0;
 
		for (int i = 0; i < liczby.length; i++) {
			licznik = 0;
			for (int k = 0; k < liczby.length; k++) {
				if (liczby[i] == liczby[k]) {
					licznik++;
					if (licznik > maks) {
						dominanta = liczby[i];
						maks = licznik;
					}
				}
 
			}
		}
		return dominanta;
	}   
        


    public static class wyświetlPrzedziałowy extends JFrame {

        public wyświetlPrzedziałowy() throws FileNotFoundException, IOException {
     
             super("Statystyka"); //s
        this.setBounds(400, 400, 400, 400);
        this.setDefaultCloseOperation(3); // wylacza ramke gdy X
        Container kontener = this.getContentPane(); // panel przechowujacy przyciski wyswietlenia itp
        

         FileDialog fd =new FileDialog(this,"Wczytaj",FileDialog.LOAD);
       // Ewentualnie: FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
         fd.setVisible(true);
         
         String katalog=fd.getDirectory();
         String plik=fd.getFile();
         System.out.println("Wybrano plik: " + plik);
         System.out.println("w katalogu: "+ katalog);
         System.out.println("Ścieżka: "+ katalog + plik);
                 int iloscWierszy = 0;
        Scanner odczyt4 = new Scanner(System.in);
                File plikWygrane = new File(plik);
        Scanner odczytWygrane = new Scanner(plikWygrane);
        if(plikWygrane.exists())
        {
            FileReader fr = new FileReader(plikWygrane);
            LineNumberReader lnr = new LineNumberReader(fr);
    		    
            while (lnr.readLine() != null)
            {
                iloscWierszy++;
            }
    	 
            System.out.println("ilosc wczytanych wierszy to : " + iloscWierszy);
            lnr.close();
        }
         int daneWygrane; // zmienna w ktorej bedziemy przechowywac kolejne wiersze tabeli76
        int i9 = 0; //zmienna do numerowanie indeksu tablicy
        double[] tabWygrane = new double[iloscWierszy]; // tablica w ktorej bedą przetrzymywane dane z pliku
        ///////////////////////////////////////////////////////////////////////
        //////// petla while wpisująca kolejne wiersze pliku do tablicy
        while (odczytWygrane.hasNextInt()) // petla while bedzie sie wykonywac do konca pliku
        {
            
            daneWygrane = odczytWygrane.nextInt(); // zapisanie wiersza do zmiennej dane
            tabWygrane[i9] = daneWygrane;           /// zapisanie zmiennej danej do tablicy
            i9++;                     /// zmiana indeksu tablicy
        }
            System.out.println("Statystyki do danych surowych");
            System.out.println("Najmniejsza Wartość = " + podajNajmniejszaLiczbe(tabWygrane));
            System.out.println("Najwieksza wartość  = " + podajNajwiekszaLiczbe(tabWygrane));
            System.out.println("Wariancja = " + podajSredniaArytmetyczna(tabWygrane));
            System.out.println("Mediana = " + mediana(tabWygrane));
                //####################################################
        ///////// tworzenie szeregu przedziałowego
        //####################################################
        int k;
        ArrayList<Integer> przedział03 = new ArrayList<Integer>();
        ArrayList<Integer> przedział46 = new ArrayList<Integer>();
        ArrayList<Integer> p79 = new ArrayList<Integer>();
        ArrayList<Integer> p1013 = new ArrayList<Integer>();
        ArrayList<Integer> p1420 = new ArrayList<Integer>();
            System.out.println("Podaj koniec przedziału (0,...)");
        int ik1 = odczyt4.nextInt();
            System.out.println("podaj koniec przedziału(" + ik1 + ",...)");
            int ik2 = odczyt4.nextInt();
            System.out.println("podaj koniec przedziału(" + ik2 + ",...)");
            int ik3 = odczyt4.nextInt();
            System.out.println("podaj koniec przedziału(" + ik3 + ",...)");
            int ik4 = odczyt4.nextInt();
            System.out.println("podaj koniec przedziału(" + ik4 + ",...)");
            int ik5 = odczyt4.nextInt();            
        for(int i =0; i<tabWygrane.length; i++) // pętla wczytująca dane do arraylist
        {
            if(tabWygrane[i] < ik1) /// tworzenie przedziału od [0,4)
            {
                k = (int) tabWygrane[i];
                przedział03.add(k);
                
            }
            if(tabWygrane[i] >= ik1 && tabWygrane[i] < ik2) // od [4,7)
            {
                k = (int) tabWygrane[i];
                przedział46.add(k);
            }
            if(tabWygrane[i] >= ik2 && tabWygrane[i] <ik3) // [7,10)
            {
                k = (int) tabWygrane[i];
                p79.add(k);
            }
            if(tabWygrane[i] >=ik3 && tabWygrane[i] <ik4) // [10,14)
            {
                k = (int) tabWygrane[i];
                p1013.add(k);
            }
            if(tabWygrane[i] >=ik4 && tabWygrane[i] <= ik5) // [14,20]
            {
                k = (int) tabWygrane[i];
                p1420.add(k);
            }
            
        }

        
        ///////// słupkowy do przedziałowego
        DefaultCategoryDataset dataset2 = new
        DefaultCategoryDataset();
        dataset2.setValue(przedział03.size(), "Seria1", "wygrane [0," + ik1 +")"); /// zbieranie danych
        dataset2.setValue(przedział46.size(), "Seria1", "wygrane [" + ik1 +"," + ik2 + ")");
        dataset2.setValue(p79.size(), "Seria1", "wygrane [" + ik2 +"," + ik3 + ")");
        dataset2.setValue(p1013.size(), "Seria1", "wygrane [" + ik3 +"," + ik4 + ")");
        dataset2.setValue(p1420.size(), "Seria1", "wygrane [" + ik4 +"," + ik5 + ")");
        // Tworzy wykres typu Bar - słupkowy
        JFreeChart chart3 = ChartFactory.createBarChart("Wykres słupkowych przedziałowy Sezon Leauge of Legends", "Ilość wygranych w sezonie", "Ilość drużyn którę osiągneły dany pułap wygranych", dataset2, PlotOrientation.VERTICAL, false, true, false);
        //parametry podobnie jak w poprzednich przykladach
        ChartFrame frame3=new ChartFrame("słupkowy",chart3);
        frame3.setVisible(true);
        frame3.setSize(900,700);
        
                System.out.println();    
         //#########################################################
        ///// Pętle pokazujące statystyki dla wybranych przedziałów
        //#########################################################
        int koniec;


                System.out.println();
                System.out.println("Statystyki dla przedziału [0," + ik1 +")" );
                statystyki(przedział03);
              

                System.out.println();
                System.out.println("Statystyki dla przedziału [" + ik1 +"," + ik2 + ")");
                statystyki(przedział46);  /// korzystanie z funkcji 

                System.out.println();
                System.out.println("Statystyki dla przedziału [" + ik2 +"," + ik3 + ")");
                statystyki(p79);  /// korzystanie z funkcji 

                System.out.println();
                System.out.println("Statystyki dla przedziału [" + ik3 +"," + ik4 + ")");
                statystyki(p1013);  /// korzystanie z funkcji 
         

                System.out.println();
                System.out.println("Statystyki dla przedziału [" + ik4 +"," + ik5 + ")");
                statystyki(p1420);  /// korzystanie z funkcji 


         

        }

        private void statystyki(ArrayList<Integer> p)
        {
                    DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int i = 0; i < p.size(); i++) 
           {
                   stats.addValue(p.get(i));
           }
        
        System.out.println("Średnia: "+stats.getMean());
        System.out.println("W. najwięsza: "+stats.getMax());
        System.out.println("W. najmniejsza: "+stats.getMin());
        System.out.println("Odch. standardowe: "+stats.getStandardDeviation());
        System.out.println("Suma: "+stats.getSum());
        System.out.println("Wariancja: "+stats.getVariance());
        }
        
        
    }

    public static abstract class Wykres implements ActionListener {
    
        void Wykres1(int[] ilosc, int[] liczby) {
             /// wykres słupkowy
          //////////// początek wykresu slupkowego
        ///////////// podanie danych do wykresu
        
        DefaultCategoryDataset dataset = new
        DefaultCategoryDataset();
        for ( int i2 = 0; i2 < liczby.length; i2++)
        {
            if (liczby[i2] == 0 && ilosc[i2] == 0)
                break;
            if(i2>30)
                break;
           dataset.setValue(ilosc[i2], "Seria1", " "+ liczby[i2]);
        }
        // Tworzy wykres typu Bar - słupkowy
        JFreeChart chart2 = ChartFactory.createBarChart("Wykres słupkowych Sezon Leauge of Legends", "Ilość wygranych w sezonie", "Ilość drużyn którę osiągneły dany pułap wygranych", dataset, PlotOrientation.VERTICAL, false, true, false);
        //parametry podobnie jak w poprzednich przykladach
        ChartFrame frame2=new ChartFrame("Bar Chart",chart2);
        frame2.setVisible(true);
        frame2.setSize(900,700);
        }
    }
    
}
