import java.util.ArrayList;
import java.util.List;

public class KyloRen extends Karakter {
    public KyloRen(String karakter, String tur,Lokasyon lokasyon) {
        super(karakter,tur,lokasyon);
    }

    public KyloRen(int x, int y){
    }

    @Override
    public List<Lokasyon> EnKisaYol(Maze maze) {
        return super.EnKisaYol(maze);
    }
    @Override
    List<Lokasyon> yolOlusturma(Lokasyon c) {
        List<Lokasyon> yol = new ArrayList<>();
        List<Lokasyon> yedekleme = new ArrayList<>();
        Lokasyon iter = c;


        while (iter != null) {
            yol.add(iter);
            iter = iter.parent;
        }
        /*for(int i=0;i<yol.size();i+=2){
            yedekleme.add(yol.get(i));
        }
        for(int i=0;i<yedekleme.size();i++)
            System.out.println("aaa"+yedekleme.get(i).getX()+" "+yedekleme.get(i).getY());
        System.out.println(yol.size());*/
        return yol;
    }
}
