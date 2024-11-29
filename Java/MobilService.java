package UAS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilService {

    public void createMobil(String merk, int tahun, double harga) {
        // Mengubah nama tabel menjadi showroom.data_mobil
        String query = "INSERT INTO showroom.data_mobil (merk, tahun, harga) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setDouble(3, harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DataMobil> readMobil() {
        List<DataMobil> mobil = new ArrayList<>();
        // Mengubah nama tabel menjadi showroom.data_mobil
        String query = "SELECT * FROM showroom.data_mobil";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                mobil.add(new DataMobil(
                        rs.getInt("idmobil"),
                        rs.getString("merk"),
                        rs.getInt("tahun"),
                        rs.getDouble("harga")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobil;
    }

    public void updateMobil(int idmobil, String merk, int tahun, double harga) {
        // Mengubah nama tabel menjadi showroom.data_mobil
        String query = "UPDATE showroom.data_mobil SET merk = ?, tahun = ?, harga = ? WHERE idmobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setDouble(3, harga);
            stmt.setInt(4, idmobil);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMobil(int idmobil) {
        // Mengubah nama tabel menjadi showroom.data_mobil
        String query = "DELETE FROM showroom.data_mobil WHERE idmobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idmobil);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
