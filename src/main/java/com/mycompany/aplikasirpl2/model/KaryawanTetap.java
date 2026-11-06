package com.mycompany.aplikasirpl2.model;

public class KaryawanTetap extends Pegawai {
    private double gajiPokok;

    public KaryawanTetap(String idPegawai, String nama, String departemen, double gajiPokok) {
        super(idPegawai, nama, departemen);
        this.gajiPokok = gajiPokok;
    }

    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) { this.gajiPokok = gajiPokok; }
}