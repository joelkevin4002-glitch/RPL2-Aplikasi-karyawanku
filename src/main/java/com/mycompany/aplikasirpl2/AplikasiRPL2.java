package com.mycompany.aplikasirpl2;

import javax.swing.SwingUtilities;

class Pegawai {
    private String idPegawai;
    private String nama;
    private String departemen;

    public Pegawai(String idPegawai, String nama, String departemen) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.departemen = departemen;
    }

    public String getIdPegawai() { return idPegawai; }
    public void setIdPegawai(String idPegawai) { this.idPegawai = idPegawai; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getDepartemen() { return departemen; }
    public void setDepartemen(String departemen) { this.departemen = departemen; }

    public void tampilkanInfo() {
        System.out.printf("ID: %-8s | Nama: %-15s | Dept: %-12s", idPegawai, nama, departemen);
    }
}

class KaryawanTetap extends Pegawai {
    private double gajiPokok;

    public KaryawanTetap(String idPegawai, String nama, String departemen, double gajiPokok) {
        super(idPegawai, nama, departemen);
        this.gajiPokok = gajiPokok;
    }

    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) { this.gajiPokok = gajiPokok; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf(" | Status: Tetap    | Gaji: Rp%.0f\n", gajiPokok);
    }
}

public class AplikasiRPL2 {
    public static void main(String[] args) {
        // Menjalankan aplikasi GUI utama Capital HR
        SwingUtilities.invokeLater(() -> {
            new AplikasiRPL2GUI().setVisible(true);
        });
    }
}