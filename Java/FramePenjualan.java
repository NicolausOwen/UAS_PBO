package UAS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FramePenjualan extends JFrame {
    private JTextField idPelangganField, idMobilField, totalBiayaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private PenjualanService penjualanService;

    public FramePenjualan() {
        penjualanService = new PenjualanService();
        setTitle("CRUD Data Penjualan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        inputPanel.add(new JLabel("ID Pelanggan:"));
        idPelangganField = new JTextField();
        inputPanel.add(idPelangganField);

        inputPanel.add(new JLabel("ID Mobil:"));
        idMobilField = new JTextField();
        inputPanel.add(idMobilField);

        inputPanel.add(new JLabel("Total Biaya:"));
        totalBiayaField = new JTextField();
        inputPanel.add(totalBiayaField);

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

        tableModel = new DefaultTableModel(new String[]{"ID Penjualan", "ID Pelanggan", "ID Mobil", "Total Biaya"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        penjualanService.createPenjualan(Integer.parseInt(idPelangganField.getText()), Integer.parseInt(idMobilField.getText()), Double.parseDouble(totalBiayaField.getText()));
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idPenjualan = (int) tableModel.getValueAt(selectedRow, 0);
            penjualanService.updatePenjualan(idPenjualan, Integer.parseInt(idPelangganField.getText()), Integer.parseInt(idMobilField.getText()), Double.parseDouble(totalBiayaField.getText()));
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk diupdate.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idPenjualan = (int) tableModel.getValueAt(selectedRow, 0);
            penjualanService.deletePenjualan(idPenjualan);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus.");
        }
    }

    private void refreshTable() {
        List<DataPenjualan> penjualanList = penjualanService.readPenjualan();
        tableModel.setRowCount(0);
        for (DataPenjualan p : penjualanList) {
            tableModel.addRow(new Object[]{p.getIdPenjualan(), p.getIdPelanggan(), p.getIdMobil(), p.getTotalBiaya()});
        }
    }

    private void clearFields() {
        idPelangganField.setText("");
        idMobilField.setText("");
        totalBiayaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FramePenjualan().setVisible(true));
    }
}
