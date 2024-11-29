package UAS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FrameMobil extends JFrame {
    private JTextField merkField, tahunField, hargaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private MobilService mobilService;

    public FrameMobil() {
        mobilService = new MobilService();
        setTitle("CRUD Data Mobil");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        inputPanel.add(new JLabel("Merk:"));
        merkField = new JTextField();
        inputPanel.add(merkField);

        inputPanel.add(new JLabel("Tahun:"));
        tahunField = new JTextField();
        inputPanel.add(tahunField);

        inputPanel.add(new JLabel("Harga:"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Tombol diatur ke kiri

        JButton createButton = new JButton("Create");
        createButton.addActionListener(this::handleCreate);
        buttonPanel.add(createButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::handleUpdate);
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::handleDelete);
        buttonPanel.add(deleteButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        buttonPanel.add(refreshButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Merk", "Tahun", "Harga"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        mobilService.createMobil(
                merkField.getText(),
                Integer.parseInt(tahunField.getText()),
                Double.parseDouble(hargaField.getText())
        );
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idmobil = (int) tableModel.getValueAt(selectedRow, 0);
            mobilService.updateMobil(
                    idmobil,
                    merkField.getText(),
                    Integer.parseInt(tahunField.getText()),
                    Double.parseDouble(hargaField.getText())
            );
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk diupdate.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idmobil = (int) tableModel.getValueAt(selectedRow, 0);
            mobilService.deleteMobil(idmobil);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus.");
        }
    }

    private void refreshTable() {
        List<DataMobil> mobil = mobilService.readMobil();
        tableModel.setRowCount(0);
        for (DataMobil m : mobil) {
            tableModel.addRow(new Object[]{m.getIdmobil(), m.getMerk(), m.getTahun(), m.getHarga()});
        }
    }

    private void clearFields() {
        merkField.setText("");
        tahunField.setText("");
        hargaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameMobil().setVisible(true));
    }
}
