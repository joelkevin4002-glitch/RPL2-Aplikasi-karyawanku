package com.mycompany.aplikasirpl2.view;

import com.mycompany.aplikasirpl2.model.Pegawai;
import com.mycompany.aplikasirpl2.controller.PegawaiController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Import JasperReports
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class AplikasiRPL2GUI extends JFrame {

    private JTextField txtId, txtNama, txtDept, txtGaji, txtCari;
    private JTable tablePegawai;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnCari, btnHapus, btnClear, btnCetak;

    // Inisialisasi Controller untuk Hibernate & Database
    private PegawaiController controller = new PegawaiController();

    public AplikasiRPL2GUI() {
        setTitle("Aplikasi Karyawanku - Praktikum RPL 2 (Database Connected)");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Header
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(41, 128, 185));
        JLabel lblJudul = new JLabel("APLIKASI CAPITAL HR - DATABASE CONNECTED");
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 20));
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

        // Load data dari database saat pertama kali dibuka
        loadDataKeTabel();
    }

    private void loadDataKeTabel() {
        tableModel.setRowCount(0);
        List<Pegawai> list = controller.getAllPegawai();
        for (Pegawai p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPegawai(), 
                p.getNama(), 
                p.getDepartemen(), 
                "Rp " + (long)p.getGajiPokok()
            });
        }
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

            // Simpan ke database MySQL via Hibernate
            Pegawai pegawai = new Pegawai(id, nama, dept, gaji);
            controller.simpanPegawai(pegawai);

            loadDataKeTabel();
            clearForm();
            JOptionPane.showMessageDialog(this, "Data Karyawan Berhasil Disimpan ke Database!");
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
        loadDataKeTabel();
    }

    private void hapusData() {
        int selectedRow = tablePegawai.getSelectedRow();
        if (selectedRow != -1) {
            String idTarget = tableModel.getValueAt(selectedRow, 0).toString();
            controller.hapusPegawai(idTarget);
            loadDataKeTabel();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus dari database!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris tabel yang ingin dihapus terlebih dahulu!");
        }
    }

    private void cariData() {
        String keyword = txtCari.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            loadDataKeTabel();
            return;
        }

        tableModel.setRowCount(0);
        List<Pegawai> list = controller.getAllPegawai();
        for (Pegawai p : list) {
            if (p.getIdPegawai().toLowerCase().contains(keyword) || p.getNama().toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                    p.getIdPegawai(), 
                    p.getNama(), 
                    p.getDepartemen(), 
                    "Rp " + (long)p.getGajiPokok()
                });
            }
        }
    }

    private void cetakLaporanJasper() {
        List<Pegawai> list = controller.getAllPegawai();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Database masih kosong, tidak ada data untuk dicetak!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String reportPath = "src/main/java/com/mycompany/aplikasirpl2/LaporanKaryawan.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
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