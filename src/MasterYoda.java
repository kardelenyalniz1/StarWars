public class MasterYoda extends Karakter {
    private int canSayisi = 6;
    public MasterYoda( String karakter, String tur,Lokasyon lokasyon) {
        super( karakter, tur,lokasyon);
    }

    public int getCanSayisi() {
        return canSayisi;
    }

    public void setCanSayisi(int canSayisi) {
        this.canSayisi = canSayisi;
    }
}
