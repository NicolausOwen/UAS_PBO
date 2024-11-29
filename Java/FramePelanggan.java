package UAS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class FramePelanggan extends JFrame {
    private JTextField namaField, nikField, notelpField, alamatField;
    private JTable table;
    private DefaultTableModel tableModel;
    private PelangganService pelangganService;

    public FramePelanggan() {
        pelangganService = new PelangganService();
        setTitle("CRUD Data Pelanggan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("NIK:"));
        nikField = new JTextField();
        inputPanel.add(nikField);

        inputPanel.add(new JLabel("No. Telp:"));
        notelpField = new JTextField();
        inputPanel.add(notelpField);

        inputPanel.add(new JLabel("Alamat:"));
        alamatField = new JTextField();
        inputPanel.add(alamatField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 

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

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "NIK", "No. Telp", "Alamat"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        pelangganService.createPelanggan(namaField.getText(), nikField.getText(), notelpField.getText(), alamatField.getText());
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idpelanggan = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.updatePelanggan(idpelanggan, namaField.getText(), nikField.getText(), notelpField.getText(), alamatField.getText());
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk diupdate.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idpelanggan = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.deletePelanggan(idpelanggan);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris untuk dihapus.");
        }
    }
    
    private void refreshTable() {
        List<DataPelanggan> pelanggan = pelangganService.readPelanggan();
        tableModel.setRowCount(0);
        for (DataPelanggan p : pelanggan) {
            tableModel.addRow(new Object[]{p.getIdpelanggan(), p.getNama(), p.getNik(), p.getNotelp(), p.getAlamat()});
        }
    }

    private void clearFields() {
        namaField.setText("");
        nikField.setText("");
        notelpField.setText("");
        alamatField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FramePelanggan().setVisible(true));
    }
}
