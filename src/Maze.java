import java.util.Arrays;

public class Maze {
    private int[][] maze;
    private boolean[][] ziyaret;
    private Lokasyon baslangic;
    private Lokasyon cikis;

    public Maze(int[][] maze) {
        this.maze = maze;
        for(int i=0;i< maze.length;i++){
            for (int j = 0; j < maze[0].length; j++) {
                 if (maze[i][j] == 2) {
                    baslangic = new Lokasyon(i, j);
                } else if (maze[i][j] == 9) {
                    cikis = new Lokasyon(i, j);
                }
            }
        }
        ziyaret = new boolean[maze.length][maze[0].length];
    }

    public Lokasyon getEntry() {
        return baslangic;
    }

    public Lokasyon getCikis() {
        return cikis;
    }

    public boolean cikisMi(int x, int y) {
        return x == cikis.getX() && y == cikis.getY();
    }

    public boolean baslangic(int x, int y) {
        return x == baslangic.getX() && y == baslangic.getY();
    }

    public boolean ziyaretEdilmisMi(int row, int col) {
        return ziyaret[row][col];
    }

    public boolean duvar_kontrol(int row, int col) {
        return maze[row][col] == 0;
    }

    public void setZiyaret(int row, int col, boolean value) {
        ziyaret[row][col] = value;
    }

    public boolean lokasyonKontrol(int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i < ziyaret.length; i++)
            Arrays.fill(ziyaret[i], false);
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    public void haritaYenile(){
        //maze = harita;
    }
}
