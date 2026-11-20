package com.mycompany.aplikasirpl2.model;

public class KaryawanTetap extends Pegawai {
    private Double gajiPokok;

    public KaryawanTetap() {
        super();
    }

    public KaryawanTetap(String idPegawai, String nama, String departemen, Double gajiPokok) {
        super(idPegawai, nama, departemen, gajiPokok);
        this.gajiPokok = gajiPokok;
    }

    @Override
    public Double getGajiPokok() {
        return gajiPokok;
    }

    @Override
    public void setGajiPokok(Double gajiPokok) {
        this.gajiPokok = gajiPokok;
        super.setGajiPokok(gajiPokok);
    }
}