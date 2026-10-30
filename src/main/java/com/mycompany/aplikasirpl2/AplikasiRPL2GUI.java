package com.mycompany.aplikasirpl2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// Import JasperReports
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class AplikasiRPL2GUI extends JFrame {

    private JTextField txtId, txtNama, txtDept, txtGaji, txtCari;
    private JTable tablePegawai;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnCari, btnHapus, btnClear, btnCetak;

    private static ArrayList<KaryawanTetap> listPegawai = new ArrayList<>();

    public AplikasiRPL2GUI() {
        setTitle("Aplikasi Karyawanku - Praktikum RPL 2");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Header
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(41, 128, 185));
        JLabel lblJudul = new JLabel("APLIKASI ");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblJudul.setForeground(Color.WHITE);
        panelHeader.add(lblJudul);
        add(panelHeader, BorderLayout.NORTH);

        // Panel Form Input
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Input Data Karyawan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("ID Pegawai:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        panelForm.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(15);
        panelForm.add(txtNama, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Departemen:"), gbc);
        gbc.gridx = 1;
        txtDept = new JTextField(15);
        panelForm.add(txtDept, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(new JLabel("Gaji Pokok:"), gbc);
        gbc.gridx = 1;
        txtGaji = new JTextField(15);
        panelForm.add(txtGaji, gbc);

        JPanel panelTombolForm = new JPanel(new GridLayout(2, 2, 5, 5));
        btnTambah = new JButton("Tambah Data");
        btnClear = new JButton("Clear Form");
        btnHapus = new JButton("Hapus Data");
        btnCetak = new JButton("Cetak Laporan (.jrxml)");
        btnCetak.setBackground(new Color(39, 174, 96));
        btnCetak.setForeground(Color.WHITE);

        panelTombolForm.add(btnTambah);
        panelTombolForm.add(btnClear);
        panelTombolForm.add(btnHapus);
        panelTombolForm.add(btnCetak);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelForm.add(panelTombolForm, gbc);

        add(panelForm, BorderLayout.WEST);

        // Panel Tabel Database
        JPanel panelTabel = new JPanel(new BorderLayout(5, 5));
        panelTabel.setBorder(BorderFactory.createTitledBorder("Database Karyawan"));

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari ID/Nama:"));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelTabel.add(panelCari, BorderLayout.NORTH);

        String[] kolom = {"ID Pegawai", "Nama Lengkap", "Departemen", "Gaji Pokok"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePegawai = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePegawai);
        panelTabel.add(scrollPane, BorderLayout.CENTER);

        add(panelTabel, BorderLayout.CENTER);

        // Event Handling Tombol
        btnTambah.addActionListener(e -> tambahData());
        btnClear.addActionListener(e -> clearForm());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> cariData());
        btnCetak.addActionListener(e -> cetakLaporanJasper());
    }

    private void tambahData() {
        try {
            String id = txtId.getText().trim();
            String nama = txtNama.getText().trim();
            String dept = txtDept.getText().trim();
            double gaji = Double.parseDouble(txtGaji.getText().trim());

            if (id.isEmpty() || nama.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            KaryawanTetap kt = new KaryawanTetap(id, nama, dept, gaji);
            listPegawai.add(kt);

            tableModel.addRow(new Object[]{kt.getIdPegawai(), kt.getNama(), kt.getDepartemen(), "Rp " + (long)kt.getGajiPokok()});
            clearForm();
            JOptionPane.showMessageDialog(this, "Data Karyawan Berhasil Ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Gaji harus berupa angka!", "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtDept.setText("");
        txtGaji.setText("");
        txtCari.setText("");
        refreshTabel(listPegawai);
    }

    private void hapusData() {
        int selectedRow = tablePegawai.getSelectedRow();
        if (selectedRow != -1) {
            String idTarget = tableModel.getValueAt(selectedRow, 0).toString();
            listPegawai.removeIf(p -> p.getIdPegawai().equals(idTarget));
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris tabel yang ingin dihapus terlebih dahulu!");
        }
    }

    private void cariData() {
        String keyword = txtCari.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            refreshTabel(listPegawai);
            return;
        }

        ArrayList<KaryawanTetap> hasilCari = new ArrayList<>();
        for (KaryawanTetap p : listPegawai) {
            if (p.getIdPegawai().toLowerCase().contains(keyword) || p.getNama().toLowerCase().contains(keyword)) {
                hasilCari.add(p);
            }
        }
        refreshTabel(hasilCari);
    }

    private void refreshTabel(ArrayList<KaryawanTetap> list) {
        tableModel.setRowCount(0);
        for (KaryawanTetap kt : list) {
            tableModel.addRow(new Object[]{kt.getIdPegawai(), kt.getNama(), kt.getDepartemen(), "Rp " + (long)kt.getGajiPokok()});
        }
    }

    private void cetakLaporanJasper() {
        if (listPegawai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Database masih kosong, tidak ada data untuk dicetak!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String reportPath = "src/main/java/com/mycompany/aplikasirpl2/LaporanKaryawan.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listPegawai);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "File 'LaporanKaryawan.jrxml' belum ditemukan di folder project.\nDetail: " + ex.getMessage(), 
                    "Info Jaspersoft Studio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiRPL2GUI().setVisible(true);
        });
    }
}