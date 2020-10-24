import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Karakter   {
    private String karakter;
    private String tur;
    private Lokasyon lokasyon;

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public Karakter(String karakter, String tur, Lokasyon lokasyon) {
        this.karakter = karakter;
        this.tur = tur;
        this.lokasyon = lokasyon;
    }

    public Karakter() {
    }

    private static final int[][] yonler = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public List<Lokasyon> EnKisaYol(Maze maze){
        LinkedList<Lokasyon> siradakiDugum = new LinkedList<>();
        Lokasyon baslangic = maze.getEntry();
       // System.out.println(start.getX()+"**"+start.getY());
        siradakiDugum.add(baslangic);

        while (!siradakiDugum.isEmpty()) {
            Lokasyon lokasyon = siradakiDugum.remove();
            //System.out.println("cur:"+cur.getX()+" "+cur.getY());

            if (!maze.lokasyonKontrol(lokasyon.getX(), lokasyon.getY()) || maze.ziyaretEdilmisMi(lokasyon.getX(), lokasyon.getY()))
                continue;
            if (maze.duvar_kontrol(lokasyon.getX(), lokasyon.getY())) {
                maze.setZiyaret(lokasyon.getX(), lokasyon.getY(), true);
                continue;
            }
            if (maze.cikisMi(lokasyon.getX(), lokasyon.getY())) {
                maze.haritaYenile();
                return yolOlusturma(lokasyon);
            }
            for (int[] direction : yonler) {
                Lokasyon koordinat = new Lokasyon(lokasyon.getX() + direction[0], lokasyon.getY() + direction[1], lokasyon);
                siradakiDugum.add(koordinat);
                maze.setZiyaret(lokasyon.getX(), lokasyon.getY(), true);
            }
        }

        return Collections.emptyList();

    }
     List<Lokasyon> yolOlusturma(Lokasyon c) {
        List<Lokasyon> yol = new ArrayList<>();
        Lokasyon iter = c;

        while (iter != null) {
            yol.add(iter);
            iter = iter.parent;
        }
        /*for(int i=0;i<yol.size();i++)
            System.out.println(yol.get(i).getX()+" "+yol.get(i).getY());
        System.out.println(path.size());*/
        return yol;
    }
}
