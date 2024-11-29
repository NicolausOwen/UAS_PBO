package UAS;

public class DataPelanggan {
    private int idpelanggan;
    private String nama, nik, notelp, alamat;

    public DataPelanggan(int idpelanggan, String nama, String nik, String notelp, String alamat) {
        this.idpelanggan = idpelanggan;
        this.nama = nama;
        this.nik = nik;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public int getIdpelanggan() { return idpelanggan; }
    public String getNama() { return nama; }
    public String getNik() { return nik; }
    public String getNotelp() { return notelp; }
    public String getAlamat() { return alamat; }
}
