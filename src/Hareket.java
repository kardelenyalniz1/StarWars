import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hareket extends JPanel implements KeyListener,ActionListener {

    List<Lokasyon> yol = new ArrayList<>();
    private   int array[][];
    private BufferedImage resim, kalpresim1,kupa;
    private ArrayList<BufferedImage> resimler = new ArrayList<>();
    private Vector<Karakter> karakterler = new Vector<>();
    private ArrayList<String> kapilar = new ArrayList<String>();
    private ArrayList<Lokasyon> kKoordinat = new ArrayList<Lokasyon>(); //kotu karakter koordinatlari
    private String karakter;
    private int hareketX = 40; //sag-sol oynatmak için birim sayısı
    private int hareketY = 40; //yukarı-asagı oynatmak icin birim sayisi
    private int karakterX = 240; //karakterin x konumu
    private int karakterY =200; //karakterin y konumu
    private static int x = 6,y = 5, tmp=0,canW=120,canH=60,kontrol = 0;
    private int yenilgi = 0,canX=600, CanY=0;
    ArrayList<BufferedImage> Lcanresimler = new ArrayList();
    ArrayList<BufferedImage> Mcanresimler = new ArrayList();



    public Hareket(String karakter, Vector<Karakter>karakterler,ArrayList<String>kapilar,int[][] array){
        this.karakter = karakter;
        this.karakterler = karakterler;
        this.kapilar = kapilar;
        this.array = array;

        //iyi karakter resim
        try{
            if(karakter.equals("Luke Skywalker")){
                resim = ImageIO.read( new FileImageInputStream(new File("image/LukeSkyWalker.png")));
            }
            else  if(karakter.equals("Master Yoda")){
                resim = ImageIO.read( new FileImageInputStream(new File("image/MasterYoda.png")));
            }
        }
        catch(IOException ex){
            Logger.getLogger(Hareket.class.getName()).log(Level.SEVERE,null,ex);
        }

        //kotu karakter resim
        for(int i=0;i<karakterler.size()-1;i++){
            try{
                kupa = ImageIO.read(new FileImageInputStream(new File("image/Kupa.png")));
                if(karakterler.get(i).getKarakter().equals("Kylo Ren")){
                    resimler.add(ImageIO.read( new FileImageInputStream(new File("image/KyloRen.png"))));
                }
                else  if(karakterler.get(i).getKarakter().equals("Darth Vader")){
                    resimler.add(ImageIO.read( new FileImageInputStream(new File("image/DarthVader.png"))));
                }
                else  if(karakterler.get(i).getKarakter().equals("Stormtrooper")){
                    resimler.add(ImageIO.read( new FileImageInputStream(new File("image/Stormtrooper.png"))));
                }
            }
            catch(IOException ex){
                Logger.getLogger(Hareket.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        try{
            //luke sky için can
            Lcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/2t.png"))));
            Lcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/1t.png"))));

            //master y. için cam
            Mcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/2t1y.png"))));
            Mcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/2t.png"))));
            Mcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/1t1y.png"))));
            Mcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/1t.png"))));
            Mcanresimler.add(ImageIO.read( new FileImageInputStream(new File("image/1y.png"))));
        }
        catch(IOException ex){
            Logger.getLogger(Hareket.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(kupa,560,360,40,40,this);
        try{
            if(tmp==0){
                kalpresim1= ImageIO.read( new FileImageInputStream(new File("image/3kalp.png")));
                g.drawImage(kalpresim1,canX, CanY,canW,canH, this);
            }
        }  catch (IOException ex) {
            Logger.getLogger(Hareket.class.getName()).log(Level.SEVERE, null, ex);}

        setBackground(Color.black);
        g.setColor(Color.BLUE);

        for (int i = 0; i < array.length; i++) {
            Color color;
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j]==0)
                    color = Color.BLUE;
                else
                    color = Color.WHITE;

                g.setColor(color);
                g.fillRect(40 * j, 40 * i, 40, 40);
                g.setColor(Color.BLACK);
                g.drawRect(40 * j, 40 * i, 40, 40);
            }
        }
        koordinatbul();
        for (int i = 1; i < yol.size() - 1; i++) { // yol çizimi
            g.setColor(Color.green);
            g.fillRect( 40 * yol.get(i).getY(),  40 * yol.get(i).getX(), 40, 40);
            g.drawRect( 40 * yol.get(i).getY(),  40 * yol.get(i).getX(), 40, 40);
        }
        yol.clear();
        g.drawImage(resim, karakterX, karakterY, 40, 40, this);
        for (int i = 0; i < karakterler.size() - 1; i++) {//kotu karakterlerin çizimi
            g.drawImage(resimler.get(i), kKoordinat.get(i).getX() * 40, kKoordinat.get(i).getY() * 40, 40, 40, this);
        }

        if(yenilgi==1 &&karakter.equals("Master Yoda")){

            if(tmp==5) {
                JOptionPane.showMessageDialog(null, "GAME OVER", "Mesaj -1", -1);
                System.out.println("GAME OVER!");
                System.exit(0);
            }
            g.drawImage(Mcanresimler.get(tmp), canX,CanY,canW,canH, this);
            yenilgi=0;
            tmp++;
            canW-=15;
            canH-=5;
        }
        if(yenilgi==1 && karakter.equals("Luke Skywalker")){
            if(tmp==2){
                JOptionPane.showMessageDialog(null, "GAME OVER", "Mesaj -1", -1);
                System.out.println("GAME OVER!.");
                System.exit(0);
            }
            g.drawImage(Lcanresimler.get(tmp),canX,CanY,120,60, this);
            yenilgi = 0;
            tmp++;
            canW-=15;
            canH-=5;
        }


    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            x = x - 1;
            if(duvarKontrol(x,y)==0){
                x++;
            }
            else {
                karakterX -= hareketX;
                kontrol = 1;

            }
        }
        else if(c == KeyEvent.VK_RIGHT){
            x++;
            if(x==14 && y==9){
                System.out.println("Kazandınız.");
                System.exit(0);
            }
            if(duvarKontrol(x,y)==0){
                //System.out.println("x:"+loc.getX()+loc.getY());
                x--;
            }
            else {
                karakterX += hareketX;
                kontrol = 1;
            }
        }
        else if(c == KeyEvent.VK_UP){
            y--;
            if(duvarKontrol(x,y)==0){
                //System.out.println("x:"+loc.getX()+loc.getY());
                y++;
            }
            else {
                karakterY -= hareketY;
                kontrol = 1;
            }
        }
        else if(c == KeyEvent.VK_DOWN){
            y++;
            if(duvarKontrol(x,y)==0){
                y--;
            }
            else {
                karakterY += hareketY;
                kontrol = 1;
            }
        }
        if(kontrol == 1){
            for(int i=0;i<karakterler.size()-1;i++){
                /*System.out.println("i:"+i);
                System.out.println(karakterler.size());
                System.out.println(kKoordinat.get(i).getY()+" "+kKoordinat.get(i).getX()+" "+x+" "+y);*/
                if(x==kKoordinat.get(i).getX() && y ==kKoordinat.get(i).getY()){
                   // System.out.println("Yenildiniz.");
                    karakterX = 240;
                    karakterY = 200;
                    x = 6;
                    y = 5;
                    kKoordinat.clear();
                    koordinatbul();
                    yenilgi = 1;
                    break;
                }
                int oncekiX = array[y][x];
                int oncekiY =  array[kKoordinat.get(i).getY()][kKoordinat.get(i).getX()];
                array[y][x] = 2;
                array[kKoordinat.get(i).getY()][kKoordinat.get(i).getX()] = 9;
                Maze maze = new Maze(array);
                List<Lokasyon>  path =  bfs(maze,karakterler.get(i).getKarakter());
                System.out.println("Uzunluk:"+(path.size()-2));
                for(int j=1;j<path.size();j++){
                    yol.add(path.get(j));
                }
                array[y][x] = oncekiX;
                array[kKoordinat.get(i).getY()][kKoordinat.get(i).getX()] = oncekiY;
                //System.out.println("11 "+path.get(1).getY()+" "+path.get(1).getX());

                if(karakterler.get(i).getKarakter().equals("Kylo Ren") && path.size()>2){
                    kKoordinat.get(i).setY(path.get(2).getX());
                    kKoordinat.get(i).setX(path.get(2).getY());
                   // System.out.println("kylo ren:"+(path.get(2).getX()) +" "+(path.get(2).getY()));
                }
                else{
                    kKoordinat.get(i).setY(path.get(1).getX());
                    kKoordinat.get(i).setX(path.get(1).getY());
                }
                if(x==kKoordinat.get(i).getX() && y ==kKoordinat.get(i).getY()){
                    System.out.println("Yenildiniz.");
                    karakterX = 240;
                    karakterY = 200;
                    x = 6;
                    y = 5;
                    kKoordinat.clear();
                    koordinatbul();
                    yenilgi = 1;
                    break;
                }

            }
        }
        repaint();
        kontrol = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int duvarKontrol(int x1,int y1){
        //System.out.println("x:"+x1+"y:"+y1+" "+array[y1][x1]);
        if(array[y1][x1]==0)
            return 0;

        return 1;
    }
    private  List<Lokasyon> bfs(Maze maze,String karakterAdi){
        List<Lokasyon> path = new ArrayList<>();
        if(karakterAdi.equals("Darth Vader")){
            Karakter bfs = new DarthVader(6,5);
            path = bfs.EnKisaYol(maze);
        }
        else if(karakterAdi.equals("Kylo Ren")){
            Karakter bfs = new KyloRen(6,5);
            path = bfs.EnKisaYol(maze);
        }
        else if(karakterAdi.equals("Stormtrooper")){
            Karakter bfs = new Stormtrooper(6,5);
            path = bfs.EnKisaYol(maze);
        }
        maze.reset();
        return path;
    }
    public void koordinatbul(){
        for(int i=0;i<karakterler.size()-1;i++){
            if(kapilar.get(i).equals("A")){
                Lokasyon lokasyon = new Lokasyon(0,5);
                karakterler.get(i).setLokasyon(lokasyon);
                kKoordinat.add(lokasyon);
            }
            else if(kapilar.get(i).equals("B")){
                Lokasyon lokasyon = new Lokasyon(4,0);
                karakterler.get(i).setLokasyon(lokasyon);
                kKoordinat.add(lokasyon);
            }
            else if(kapilar.get(i).equals("C")){
                Lokasyon lokasyon = new Lokasyon(12,0);
                karakterler.get(i).setLokasyon(lokasyon);
                kKoordinat.add(lokasyon);

            }
            else  if(kapilar.get(i).equals("D")){
                Lokasyon lokasyon = new Lokasyon(13,5);
                karakterler.get(i).setLokasyon(lokasyon);
                kKoordinat.add(lokasyon);
            }
            else  if(kapilar.get(i).equals("E")){
                Lokasyon lokasyon = new Lokasyon(4,11);
                karakterler.get(i).setLokasyon(lokasyon);
                kKoordinat.add(lokasyon);
            }
        }
    }

}
