package UAS;

public class DataMobil {
    private int idmobil, tahun;
    private String merk;
    private double harga;

    public DataMobil(int idmobil, String merk, int tahun, double harga) {
        this.idmobil = idmobil;
        this.merk = merk;
        this.tahun = tahun;
        this.harga = harga;
    }

    public int getIdmobil() { return idmobil; }
    public String getMerk() { return merk; }
    public int getTahun() { return tahun; }
    public double getHarga() { return harga; }
}
