package com.mycompany.aplikasirpl2.dao;

import com.mycompany.aplikasirpl2.model.Pegawai;
import java.util.List;

public interface PegawaiDAO {
    void save(Pegawai pegawai);
    void update(Pegawai pegawai);
    void delete(Pegawai pegawai);
    Pegawai getById(String idPegawai);
    List<Pegawai> getAll();
}
/**
 *
 * @author Lenovo Flex 5
 */
