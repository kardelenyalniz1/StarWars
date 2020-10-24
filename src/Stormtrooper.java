import java.util.List;

public class Stormtrooper extends Karakter {
    public Stormtrooper( String karakter, String tur,Lokasyon lokasyon) {
        super(karakter, tur,lokasyon);
    }

    public Stormtrooper(int x, int y) {
    }

    @Override
    public List<Lokasyon> EnKisaYol(Maze maze) {
        return super.EnKisaYol(maze);
    }
}
