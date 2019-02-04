/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statstykaligi;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/**
 *
 * @author Lenovo
 */
public class StatstykaLigi {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
                     Frame ramka = new Frame ("Okno macierzyste");
     ramka.setBounds(20,20,400,500);
     ramka.setVisible(true);

     FileDialog fd =new FileDialog(ramka,"Wczytaj",FileDialog.LOAD);
   // Ewentualnie: FileDialog fd =new FileDialog(a,"Zapisz",FileDialog.SAVE);
     fd.setVisible(true);
     String katalog=fd.getDirectory();
     String plik=fd.getFile();
     System.out.println("Wybrano plik: " + plik);
     System.out.println("w katalogu: "+ katalog);
     System.out.println("Ścieżka: "+ katalog + plik); 
  
        
         Scanner odczyt4 = new Scanner(System.in);
//            System.out.println("Podaj nazwe pliku i upewnij się ze znajduje sie w folderze z programem i dopisz rozszrzecznie .txt");
//    String nazwaPliku = odczyt4.nextLine();
                         ///////Otwieranie pliku //////////////////////
        File plikWygrane = new File(plik);
        Scanner odczytWygrane = new Scanner(plikWygrane);
        ///////////////////////////////////////////
        int iloscWierszy = 0;
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
//        System.out.println(tabWygrane[9]);
//        System.out.println(tabWygrane.length);
//        System.out.println("FNC" + " " + tabWygrane[0]);
//        System.out.println("VIT" + " " + tabWygrane[1]);
//        System.out.println("S04" + " " + tabWygrane[2]);
//        System.out.println("G2" + " " + tabWygrane[3]);
//        System.out.println("MSF" + " " + tabWygrane[4]);
//        System.out.println("SPY" + " " + tabWygrane[5]);
//        System.out.println("ROC" + " " + tabWygrane[6]);
//        System.out.println("UOL" + " " + tabWygrane[7]);
//        System.out.println("GIA" + " " + tabWygrane[8]);
//        System.out.println("H2K" + " " + tabWygrane[9]);
        /// obliczenie sumy wygranych
        int SumaWygrane = 0;
        for(int i2 = 0; i2 < tabWygrane.length - 1; i2++ )
            SumaWygrane += tabWygrane[i2];
        System.out.println("Średnia ilosć wygranych to" + " " + SumaWygrane / tabWygrane.length);
        int x = 0;
        
        int suma = 0;
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
            } 
        int c = 0;
         for ( int i2 = 0; i2 < liczby.length; i2++)
            {   
                if (liczby[i2] == 0 && ilosc[i2] == 0)
                    break;
                
                c++;
            } 
         ///####################################################
         /// Tworzenie okienka z szeregiem punktowym 
         /// ###################################################
         System.out.println("Potrzebna dlugość przedziału punktowego to " + c);
//        JFrame ramka = new JFrame ("Witaj Java" );
        ramka.setLayout(new GridLayout(c + 1,1));
        for(int i=1;i<c;i++)
        {
            ramka.add(new JLabel("wygrane" + " " + liczby[i] + " " + "ilość drużyn " + ilosc[i] ,JLabel.CENTER));
        }
            ramka.add(new JLabel("Średnia: "+StatUtils.mean(tabWygrane)),JLabel.BOTTOM_ALIGNMENT);
        //JLabel napis = new JLabel ("Witaj Java!", JLabel.CENTER );
        //ramka.getContentPane().add( napis);
        //ramka.add(new JLabel("<html>jeden<br>dwa</html>")); //tak możesz stworzyc etykietę wielowierszową
        ramka.setSize( 300, 300);
        ramka.setVisible( true );
  ////////////////////////////////////////////////////////////////
  //##############################################
  //       rysowanie  Tworzenie wykresu do punktowego
  //##############################################
 DefaultPieDataset dane = new DefaultPieDataset();
 /////////// pętla wczytujące dane i ilosć ich wystąpień /////
        for ( int i2 = 0; i2 < liczby.length; i2++)
        {
            if (liczby[i2] == 0 && ilosc[i2] == 0)
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
        
        
        //######################################
        //////////// początek wykresu slupkowego
        ///////////// podanie danych do wykresu
        //######################################
        DefaultCategoryDataset dataset = new
        DefaultCategoryDataset();
        for ( int i2 = 0; i2 < liczby.length; i2++)
        {
            if (liczby[i2] == 0 && ilosc[i2] == 0)
                break;
           dataset.setValue(ilosc[i2], "Seria1", " "+ liczby[i2]);
        }
        // Tworzy wykres typu Bar - słupkowy
        JFreeChart chart2 = ChartFactory.createBarChart("Wykres słupkowych Sezon Leauge of Legends", "Ilość wygranych w sezonie", "Ilość drużyn którę osiągneły dany pułap wygranych", dataset, PlotOrientation.VERTICAL, false, true, false);
        //parametry podobnie jak w poprzednich przykladach
        ChartFrame frame2=new ChartFrame("Bar Chart",chart2);
        frame2.setVisible(true);
        frame2.setSize(900,700);
 
        
        
        
       
        
        
        ////////// wykorzystanie bilbioteki org.apache.commons.math
        System.out.println("Średnia:           "+StatUtils.mean(tabWygrane));
        System.out.println("W. najwięsza:      "+StatUtils.max(tabWygrane));
        System.out.println("W. najmniejsza:    "+StatUtils.min(tabWygrane));
        System.out.println("Suma kwadratów:    "+StatUtils.sumSq(tabWygrane));
////////////////////// inna metoda
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int i = 0; i < iloscWierszy; i++) 
           {
                   stats.addValue(tabWygrane[i]);
           }

        
        System.out.println("Średnia: "+stats.getMean());
        System.out.println("W. najwięsza: "+stats.getMax());
        System.out.println("W. najmniejsza: "+stats.getMin());
        System.out.println("Odch. standardowe: "+stats.getStandardDeviation());
        System.out.println("Suma: "+stats.getSum());
        System.out.println("Wariancja: "+stats.getVariance());
        
        //####################################################
        //// tworzenie okna
        //####################################################
         JFrame ramkaS = new JFrame("ladna ramka");
        ramkaS.setBounds(500, 300, 400, 500);
        ramkaS.setDefaultCloseOperation(3);
        JLabel srednia = new JLabel("Średnia:           "+StatUtils.mean(tabWygrane));
        ramkaS.add(srednia);
        JLabel wNajwieksza = new JLabel("W. najwięsza:      "+StatUtils.max(tabWygrane));
        ramkaS.add(wNajwieksza);
        JLabel wNajmniejsza = new JLabel("W. najmniejsza:    "+StatUtils.min(tabWygrane));
        ramkaS.add(wNajmniejsza);
        JLabel sumaKwadratow = new JLabel("Suma kwadratów:    "+StatUtils.sumSq(tabWygrane));
        ramkaS.add(sumaKwadratow);
        ramkaS.setVisible(true);
        
                JFrame ramkaStatystyk = new JFrame ("Witaj Java" );
        ramkaStatystyk.setLayout(new GridLayout(10,1));

        ramkaStatystyk.add(new JLabel("Średnia:           "+StatUtils.mean(tabWygrane)),JLabel.CENTER);
        ramkaStatystyk.add(new JLabel("wiersz ",JLabel.CENTER));

        //JLabel napis = new JLabel ("Witaj Java!", JLabel.CENTER );
        //ramka.getContentPane().add( napis);
        //ramka.add(new JLabel("<html>jeden<br>dwa</html>")); //tak możesz stworzyc etykietę wielowierszową
        ramkaStatystyk.setSize( 300, 300);
        ramkaStatystyk.setVisible( true );
        ramkaStatystyk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // tego ci brakowało
        
        //####################################################
        ///////// tworzenie szeregu przedziałowego
        //####################################################
        int k;
        ArrayList<Integer> przedział03 = new ArrayList<Integer>();
        ArrayList<Integer> przedział46 = new ArrayList<Integer>();
        ArrayList<Integer> p79 = new ArrayList<Integer>();
        ArrayList<Integer> p1013 = new ArrayList<Integer>();
        ArrayList<Integer> p1420 = new ArrayList<Integer>();
        for(int i =0; i<tabWygrane.length; i++) // pętla wczytująca dane do arraylist
        {
            if(tabWygrane[i] < 4) /// tworzenie przedziału od [0,4)
            {
                k = (int) tabWygrane[i];
                przedział03.add(k);
            }
            if(tabWygrane[i] >= 4 && tabWygrane[i] < 7) // od [4,7)
            {
                k = (int) tabWygrane[i];
                przedział46.add(k);
            }
            if(tabWygrane[i] >=7 && tabWygrane[i] <10) // [7,10)
            {
                k = (int) tabWygrane[i];
                p79.add(k);
            }
            if(tabWygrane[i] >=10 && tabWygrane[i] <14) // [10,14)
            {
                k = (int) tabWygrane[i];
                p1013.add(k);
            }
            if(tabWygrane[i] >=14 && tabWygrane[i] <= 20) // [14,20]
            {
                k = (int) tabWygrane[i];
                p1420.add(k);
            }
            
        }

        
        ///////// słupkowy do przedziałowego
        DefaultCategoryDataset dataset2 = new
        DefaultCategoryDataset();
        dataset2.setValue(przedział03.size(), "Seria1", "wygrane [0,4)"); /// zbieranie danych
        dataset2.setValue(przedział46.size(), "Seria1", "wygrane [4,7)");
        dataset2.setValue(p79.size(), "Seria1", "wygrane [7,10)");
        dataset2.setValue(p1013.size(), "Seria1", "wygrane [10,14)");
        dataset2.setValue(p1420.size(), "Seria1", "wygrane [14,20]");
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
        do
        {
            System.out.println();
            System.out.println("Podaj z jakiego przedziału wyświetlić statystyki");
            System.out.println(" 1 - [0,4)  2 - [4,7)   3 - [7,10)  4 - [10,14) 5 - [14,20] 6 - koniec");
            koniec = odczyt4.nextInt();
            if(koniec ==1)
            {
                System.out.println();
                System.out.println("Statystyki dla przedziału [0,4)");
                statystyki p = new statystyki(przedział03);  /// korzystanie z funkcji 
            }
            if(koniec ==2)
            {
                System.out.println();
                System.out.println("Statystyki dla przedziału [4,7)");
                statystyki p = new statystyki(przedział46);  /// korzystanie z funkcji 
            }
            if(koniec ==3)
            {
                System.out.println();
                System.out.println("Statystyki dla przedziału [7,10)");
                statystyki p = new statystyki(p79);  /// korzystanie z funkcji 
            }
            if(koniec ==4)
            {
                System.out.println();
                System.out.println("Statystyki dla przedziału [10,14)");
                statystyki p = new statystyki(p1013);  /// korzystanie z funkcji 
            }
            if(koniec ==5)
            {
                System.out.println();
                System.out.println("Statystyki dla przedziału [14,20]");
                statystyki p = new statystyki(p1420);  /// korzystanie z funkcji 
            }
        } while(koniec !=6);
        
        
         System.out.println("Średnia arytmetyczna liczb to: "+podajSredniaArytmetyczna(tabWygrane));
        System.out.println("Wariancja dla podanych liczb to: "+podajWariancje(tabWygrane));
        podajNajmniejszaLiczbe(tabWygrane);
        podajNajwiekszaLiczbe(tabWygrane);
    }

    private Container getContentPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
       private static double[] wprowadzNoweDane() {

        Scanner skaner = new Scanner(System.in);

        System.out.println("Podaj ile liczb chcesz wpisać ");

        String liczbaLiczb = skaner.nextLine();

        int dlTabeli = Integer.parseInt(liczbaLiczb);

        double[] liczby = new double[dlTabeli];

        for (int i=0; i<liczby.length; i++) {       

            System.out.println("Podaj "+ (i+1) +" liczbę z "+dlTabeli);

            String liczba = skaner.nextLine();

            liczby[i] = Double.parseDouble(liczba);

        }

        return liczby;
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
     private static void podajNajmniejszaLiczbe(double[] liczby) {

        double min = liczby[0];

        for (double l : liczby) 
            {

                if (l < min)
                {
                    min = l;

                }
            }
        System.out.println("Najmniejsza liczba to: "+min);

    }
	    private static void podajNajwiekszaLiczbe(double[] liczby) 
            {

                double max = liczby[0];

                for (double l : liczby) {

                    if (l > max)
                    {
                        max = l;
                    }

                }
                System.out.println("Największa liczba to: "+max);

            } 
}


class statystyki
{
    statystyki (ArrayList<Integer> p)
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
