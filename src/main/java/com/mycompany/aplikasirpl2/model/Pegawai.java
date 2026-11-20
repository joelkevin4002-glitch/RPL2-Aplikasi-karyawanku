package com.mycompany.aplikasirpl2.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_pegawai")
public class Pegawai {

    @Id
    @Column(name = "idPegawai", length = 20)
    private String idPegawai;

    @Column(name = "nama", length = 100, nullable = false)
    private String nama;

    @Column(name = "departemen", length = 50, nullable = false)
    private String departemen;

    @Column(name = "gajiPokok")
    private Double gajiPokok;

    public Pegawai() {}

    public Pegawai(String idPegawai, String nama, String departemen) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.departemen = departemen;
        this.gajiPokok = 0.0;
    }

    public Pegawai(String idPegawai, String nama, String departemen, Double gajiPokok) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.departemen = departemen;
        this.gajiPokok = gajiPokok;
    }

    public String getIdPegawai() { return idPegawai; }
    public void setIdPegawai(String idPegawai) { this.idPegawai = idPegawai; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getDepartemen() { return departemen; }
    public void setDepartemen(String departemen) { this.departemen = departemen; }

    public Double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(Double gajiPokok) { this.gajiPokok = gajiPokok; }
}