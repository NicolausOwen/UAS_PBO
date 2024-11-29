package UAS;

public class DataPenjualan {
    private int idPenjualan;
    private int idPelanggan;
    private int idMobil;
    private double totalBiaya;

    // Konstruktor
    public DataPenjualan(int idPenjualan, int idPelanggan, int idMobil, double totalBiaya) {
        this.idPenjualan = idPenjualan;
        this.idPelanggan = idPelanggan;
        this.idMobil = idMobil;
        this.totalBiaya = totalBiaya;
    }

    // Getter dan Setter
    public int getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
}
