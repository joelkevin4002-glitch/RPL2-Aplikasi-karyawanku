package com.mycompany.aplikasirpl2.service;

import com.mycompany.aplikasirpl2.model.Pegawai;
import java.util.List;

public interface PegawaiService {
    void save(Pegawai pegawai);
    void update(Pegawai pegawai);
    void delete(Pegawai pegawai);
    Pegawai getById(String idPegawai);
    List<Pegawai> getAll();
}