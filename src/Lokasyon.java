public class Lokasyon {
     private int x = 6;
     private int y = 5;
    Lokasyon parent;

    public Lokasyon(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Lokasyon(int x, int y, Lokasyon parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    Lokasyon getParent() {
        return parent;
    }
}
