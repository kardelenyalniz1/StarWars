import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DarthVader extends Karakter {
    public DarthVader( String karakter, String tur,Lokasyon lokasyon) {
        super(karakter,tur,lokasyon);
    }
    public DarthVader(int x,int y){

    }

    private static final int[][] yonler = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    @Override
    public List<Lokasyon> EnKisaYol(Maze maze) {
        LinkedList<Lokasyon> siradakiDugum = new LinkedList<>();
        Lokasyon baslangic = maze.getEntry();
        //System.out.println(start.getX()+"**"+start.getY());
        siradakiDugum.add(baslangic);

        while (!siradakiDugum.isEmpty()) {
            Lokasyon nokta = siradakiDugum.remove();

            if (!maze.lokasyonKontrol(nokta.getX(), nokta.getY()) || maze.ziyaretEdilmisMi(nokta.getX(), nokta.getY()))
                continue;
            if (maze.cikisMi(nokta.getX(), nokta.getY())) {
                maze.haritaYenile();
                return yolOlusturma(nokta);
            }
            for (int[] direction : yonler) {
                Lokasyon koordinat = new Lokasyon(nokta.getX() + direction[0], nokta.getY() + direction[1], nokta);
                siradakiDugum.add(koordinat);
                maze.setZiyaret(nokta.getX(), nokta.getY(), true);
            }
        }

        return Collections.emptyList();
    }

    @Override
    List<Lokasyon> yolOlusturma(Lokasyon cur) {
        return super.yolOlusturma(cur);
    }
}
