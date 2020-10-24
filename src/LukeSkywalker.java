public class LukeSkywalker extends Karakter {

    private int canSayisi = 3;
    public LukeSkywalker( String karakter, String tur,Lokasyon lokasyon) {
        super( karakter, tur,lokasyon);
    }

    public int getCanSayisi() {
        return canSayisi;
    }

    public void setCanSayisi(int canSayisi) {
        this.canSayisi = canSayisi;
    }
}
