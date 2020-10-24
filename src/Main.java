import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Main extends JFrame {
    public  Main(String title) throws HeadlessException{
        super(title);
    }
    static int x2[] = new int[3];
    static int y2[] = new int[3];
    public static void main(String[] args) throws IOException {
        //satır satir okuyup her satiri arrayliste atar
        ArrayList<String> liste = new ArrayList();
        ArrayList<String> kotuKarakter = new ArrayList<>();
        ArrayList<String> kapilar = new ArrayList<>();
        int i=0;
        BufferedReader oku = new BufferedReader(new FileReader("Harita.txt"));
        while (true) {
            String yazi = oku.readLine();
            if (yazi == null)
                break;
            liste.add(yazi);
        }
        oku.close();

        while(liste.get(i).charAt(0)!='0'|| liste.get(i).charAt(0)!='1'){
            if(liste.get(i).charAt(0)=='0'|| liste.get(i).charAt(0)=='1')
                break;
            String s[] = liste.get(i).split(",");
            String s2[] = s[0].split(":");
            kotuKarakter.add(s2[1]);
            String s3[] = s[1].split(":");
            kapilar.add(s3[1]);
            i++;
        }
        ArrayList<String> koordinat=new ArrayList<>();
        // harita bilgilerini arraylistte tutma
        for(int n=i;n<liste.size();n++) {
            liste.set(n, liste.get(n).trim());
            koordinat.add(liste.get(n));
        }
        int s=0;
        int size = liste.size() -(liste.size()-koordinat.size());
        int harita[][] = new int[size][14];
        for(int k=0;k<size;k++){
           // System.out.println(koordinat.get(k));
            for(int l=0;l<koordinat.get(0).length();l++){
                if(koordinat.get(k).charAt(l) == '1' || koordinat.get(k).charAt(l) =='0'){
                    //System.out.println(koordinat.get(k).charAt(l)+" "+koordinat.get(0).length()+"s:"+s);
                    char str = koordinat.get(k).charAt(l);
                    harita[k][s] = Character.getNumericValue(str);
                    s++;
                }
            }
            s = 0;
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("İyi karakter seçiniz(Master Yoda veya Luke Skywalker):");
        String iyiKarakter = scan.nextLine();
        while(!iyiKarakter.equals("Master Yoda") && !iyiKarakter.equals("Luke Skywalker")){
            System.out.println("Hatalı karakter girişi!Tekrar deneyin.");
            System.out.println("İyi karakter seçiniz(Master Yoda veya Luke Skywalker):");
            iyiKarakter = scan.nextLine();
            if(iyiKarakter.equals("Master Yoda") || iyiKarakter.equals("Luke Skywalker"))
                break;
        }

        Vector<Karakter> karakterler = new Vector<>();
        koordinatBul(kapilar);

        for(int j=0;j<kotuKarakter.size();j++){
            if(kotuKarakter.get(j).equals("Kylo Ren")){
                Lokasyon lokasyon = new Lokasyon(x2[j],y2[j]);
                Karakter karakter = new KyloRen(kotuKarakter.get(j),"kotu",lokasyon);
                karakterler.addElement(karakter);
            }
            else if(kotuKarakter.get(j).equals("Stormtrooper")) {
                Lokasyon lokasyon = new Lokasyon(x2[j],y2[j]);
                Karakter karakter = new Stormtrooper( kotuKarakter.get(j), "kotu",lokasyon);
                karakterler.addElement(karakter);
            }
            else if(kotuKarakter.get(j).equals("Darth Vader")) {
                Lokasyon lokasyon = new Lokasyon(x2[j],y2[j]);
                Karakter karakter = new DarthVader(kotuKarakter.get(j), "kotu",lokasyon);
                karakterler.addElement(karakter);
            }
        }
        if(iyiKarakter.equals("Master Yoda")){
            Lokasyon lokasyon = new Lokasyon(6,5);
            Karakter iyikarakter = new MasterYoda(iyiKarakter,"iyi",lokasyon);
            karakterler.addElement(iyikarakter);
        }
        else if (iyiKarakter.equals("Luke Skywalker")){
            Lokasyon lokasyon = new Lokasyon(6,5);
            Karakter iyikarakter = new LukeSkywalker(iyiKarakter,"iyi",lokasyon);
            karakterler.addElement(iyikarakter);
        }
        Main ekran = new Main("Star Wars");

        ekran.setResizable(false);
        ekran.setFocusable(false);

        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Hareket hareket = new Hareket(iyiKarakter,karakterler,kapilar,harita);
        hareket.requestFocus();
        hareket.addKeyListener(hareket);
        hareket.setFocusable(true);
        hareket.setFocusTraversalKeysEnabled(false);

        ekran.add(hareket);
        ekran.setVisible(true);


    }

    public static void koordinatBul(ArrayList<String>kapilar){
        for(int i=0;i<kapilar.size();i++){
            System.out.println(i);
            if(kapilar.get(i).equals("A")){
                x2[i]= 0;
                y2[i] = 5;
            }
            else if(kapilar.get(i).equals("B")){
                x2[i] = 4;
                y2[i] = 0;
            }
            else if(kapilar.get(i).equals("C")){
                x2[i] = 12;
                y2[i] = 0;
            }
            else if(kapilar.get(i).equals("D")){
                x2[i] = 13;
                y2[i] = 5;
            }
            else if(kapilar.get(i).equals("E")){
                x2[i] = 4;
                y2[i] = 10;
            }
        }
    }

}
