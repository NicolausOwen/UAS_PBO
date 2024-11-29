package UAS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Main Frame - Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton pelangganButton = new JButton("CRUD Data Pelanggan");
        pelangganButton.addActionListener(this::openPelangganFrame);
        pelangganButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton mobilButton = new JButton("CRUD Data Mobil");
        mobilButton.addActionListener(this::openMobilFrame);
        mobilButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton penjualanButton = new JButton("CRUD Data Penjualan");
        penjualanButton.addActionListener(this::openPenjualanFrame);
        penjualanButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(pelangganButton);
        panel.add(Box.createVerticalStrut(10)); 
        panel.add(mobilButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(penjualanButton);
        add(panel);

        setVisible(true);
    }

    private void openPelangganFrame(ActionEvent e) {
        new FramePelanggan().setVisible(true);
    }

    private void openMobilFrame(ActionEvent e) {
        new FrameMobil().setVisible(true);
    }

    private void openPenjualanFrame(ActionEvent e) {
        new FramePenjualan().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
