package com.mycompany.aplikasirpl2.view;

import com.mycompany.aplikasirpl2.model.Pegawai;
import com.mycompany.aplikasirpl2.model.Izin;
import com.mycompany.aplikasirpl2.controller.PegawaiController;
import com.mycompany.aplikasirpl2.controller.IzinController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

// Import JasperReports
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class AplikasiRPL2GUI extends JFrame {

    private JTextField txtId, txtNama, txtDept, txtGaji, txtCari;
    private JTable tablePegawai;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnCari, btnHapus, btnClear, btnCetak;

    // Komponen Fitur Izin
    private JTextField txtIzinIdPegawai, txtIzinNama, txtIzinTanggal, txtIzinKet;
    private JComboBox<String> cmbJenisIzin;
    private JTable tableIzin;
    private DefaultTableModel izinTableModel;
    private JButton btnAjukanIzin, btnHapusIzin;

    // Inisialisasi Controller untuk Hibernate & Database
    private PegawaiController controller = new PegawaiController();
    private IzinController izinController = new IzinController();

    public AplikasiRPL2GUI() {
        setTitle("Aplikasi Karyawanku - Praktikum RPL 2 (Database Connected)");
        setSize(950, 720);
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

        // Panel Kanan: Tabel Karyawan (Atas) & Panel IZIN (Bawah)
        JPanel panelKanan = new JPanel(new GridLayout(2, 1, 8, 8));

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

        // Panel IZIN
        JPanel panelIzin = new JPanel(new BorderLayout(5, 5));
        panelIzin.setBorder(BorderFactory.createTitledBorder("IZIN"));

        JPanel formIzin = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 3));
        txtIzinIdPegawai = new JTextField(6);
        txtIzinNama = new JTextField(12);
        cmbJenisIzin = new JComboBox<>(new String[]{"Izin", "Sakit", "Cuti"});
        txtIzinTanggal = new JTextField(LocalDate.now().toString(), 9);
        txtIzinKet = new JTextField(12);
        btnAjukanIzin = new JButton("Simpan Izin");
        btnHapusIzin = new JButton("Hapus Izin");

        formIzin.add(new JLabel("ID:"));
        formIzin.add(txtIzinIdPegawai);
        formIzin.add(new JLabel("Nama:"));
        formIzin.add(txtIzinNama);
        formIzin.add(new JLabel("Jenis:"));
        formIzin.add(cmbJenisIzin);
        formIzin.add(new JLabel("Tgl:"));
        formIzin.add(txtIzinTanggal);
        formIzin.add(new JLabel("Ket:"));
        formIzin.add(txtIzinKet);
        formIzin.add(btnAjukanIzin);
        formIzin.add(btnHapusIzin);

        panelIzin.add(formIzin, BorderLayout.NORTH);

        String[] kolomIzin = {"ID Izin", "ID Pegawai", "Nama Pegawai", "Jenis", "Tanggal", "Keterangan"};
        izinTableModel = new DefaultTableModel(kolomIzin, 0);
        tableIzin = new JTable(izinTableModel);
        panelIzin.add(new JScrollPane(tableIzin), BorderLayout.CENTER);

        panelKanan.add(panelTabel);
        panelKanan.add(panelIzin);
        add(panelKanan, BorderLayout.CENTER);

        // Event Handling Tombol Karyawan
        btnTambah.addActionListener(e -> tambahData());
        btnClear.addActionListener(e -> clearForm());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> cariData());
        btnCetak.addActionListener(e -> cetakLaporanJasper());

        // Isi otomatis ID & Nama izin saat baris tabel pegawai diklik
        tablePegawai.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tablePegawai.getSelectedRow();
                if (row != -1) {
                    Object idVal = tableModel.getValueAt(row, 0);
                    Object namaVal = tableModel.getValueAt(row, 1);
                    txtIzinIdPegawai.setText(idVal != null ? idVal.toString() : "");
                    txtIzinNama.setText(namaVal != null ? namaVal.toString() : "");
                }
            }
        });

        // Event Handling Tombol Izin
        btnAjukanIzin.addActionListener(e -> tambahIzin());
        btnHapusIzin.addActionListener(e -> hapusIzin());

        // Load data dari database saat pertama kali dibuka
        loadDataKeTabel();
        loadDataIzinKeTabel();
    }

    private long formatGajiLong(Pegawai p) {
        return (p.getGajiPokok() != null) ? p.getGajiPokok().longValue() : 0L;
    }

    private void loadDataKeTabel() {
        tableModel.setRowCount(0);
        List<Pegawai> list = controller.getAllPegawai();
        for (Pegawai p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPegawai(),
                p.getNama(),
                p.getDepartemen(),
                "Rp " + formatGajiLong(p)
            });
        }
    }

    private void loadDataIzinKeTabel() {
        izinTableModel.setRowCount(0);
        List<Izin> list = izinController.getAllIzin();
        for (Izin iz : list) {
            izinTableModel.addRow(new Object[]{
                iz.getIdIzin(),
                iz.getIdPegawai(),
                iz.getNamaPegawai(),
                iz.getJenisIzin(),
                iz.getTanggalIzin(),
                iz.getKeterangan()
            });
        }
    }

    private void tambahIzin() {
        String idPeg = txtIzinIdPegawai.getText().trim();
        String namaPeg = txtIzinNama.getText().trim();
        String jenis = cmbJenisIzin.getSelectedItem().toString();
        String tanggal = txtIzinTanggal.getText().trim();
        String ket = txtIzinKet.getText().trim();

        if (idPeg.isEmpty() || namaPeg.isEmpty() || tanggal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Pegawai, Nama, dan Tanggal Izin wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Izin izin = new Izin(idPeg, namaPeg, jenis, tanggal, ket);
        izinController.ajukanIzin(izin);
        txtIzinKet.setText("");
        loadDataIzinKeTabel();
        JOptionPane.showMessageDialog(this, "Data izin berhasil disimpan!");
    }

    private void hapusIzin() {
        int selectedRow = tableIzin.getSelectedRow();
        if (selectedRow != -1) {
            Integer idIzin = Integer.valueOf(izinTableModel.getValueAt(selectedRow, 0).toString());
            izinController.hapusIzin(idIzin);
            loadDataIzinKeTabel();
            JOptionPane.showMessageDialog(this, "Data izin berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris data izin yang ingin dihapus terlebih dahulu!");
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
            String id = (p.getIdPegawai() != null) ? p.getIdPegawai() : "";
            String nama = (p.getNama() != null) ? p.getNama() : "";
            if (id.toLowerCase().contains(keyword) || nama.toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{
                    p.getIdPegawai(),
                    p.getNama(),
                    p.getDepartemen(),
                    "Rp " + formatGajiLong(p)
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
            String reportPath = "src/main/java/report/LaporanKaryawanku1.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal mencetak laporan JasperReports.\nDetail: " + ex.getMessage(),
                    "Error JasperReports", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiRPL2GUI().setVisible(true);
        });
    }
}