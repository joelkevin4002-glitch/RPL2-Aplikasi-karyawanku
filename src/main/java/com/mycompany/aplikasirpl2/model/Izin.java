/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasirpl2.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_izin")
public class Izin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_izin")
    private Integer idIzin;

    @Column(name = "id_pegawai", length = 20, nullable = false)
    private String idPegawai;

    @Column(name = "nama_pegawai", length = 100, nullable = false)
    private String namaPegawai;

    @Column(name = "jenis_izin", length = 50, nullable = false)
    private String jenisIzin;

    @Column(name = "tanggal_izin", length = 30, nullable = false)
    private String tanggalIzin;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

    public Izin() {}

    public Izin(String idPegawai, String namaPegawai, String jenisIzin, String tanggalIzin, String keterangan) {
        this.idPegawai = idPegawai;
        this.namaPegawai = namaPegawai;
        this.jenisIzin = jenisIzin;
        this.tanggalIzin = tanggalIzin;
        this.keterangan = keterangan;
    }

    public Integer getIdIzin() { return idIzin; }
    public void setIdIzin(Integer idIzin) { this.idIzin = idIzin; }

    public String getIdPegawai() { return idPegawai; }
    public void setIdPegawai(String idPegawai) { this.idPegawai = idPegawai; }

    public String getNamaPegawai() { return namaPegawai; }
    public void setNamaPegawai(String namaPegawai) { this.namaPegawai = namaPegawai; }

    public String getJenisIzin() { return jenisIzin; }
    public void setJenisIzin(String jenisIzin) { this.jenisIzin = jenisIzin; }

    public String getTanggalIzin() { return tanggalIzin; }
    public void setTanggalIzin(String tanggalIzin) { this.tanggalIzin = tanggalIzin; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
}
