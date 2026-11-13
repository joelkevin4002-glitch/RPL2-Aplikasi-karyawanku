package com.mycompany.aplikasirpl2.service;

import com.mycompany.aplikasirpl2.dao.PegawaiDAO;
import com.mycompany.aplikasirpl2.model.Pegawai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

    @Autowired
    private PegawaiDAO pegawaiDAO;

    public void setPegawaiDAO(PegawaiDAO pegawaiDAO) {
        this.pegawaiDAO = pegawaiDAO;
    }

    @Override
    public void save(Pegawai pegawai) {
        pegawaiDAO.save(pegawai);
    }

    @Override
    public void update(Pegawai pegawai) {
        pegawaiDAO.update(pegawai);
    }

    @Override
    public void delete(Pegawai pegawai) {
        pegawaiDAO.delete(pegawai);
    }

    @Override
    public Pegawai getById(String idPegawai) {
        return pegawaiDAO.getById(idPegawai);
    }

    @Override
    public List<Pegawai> getAll() {
        return pegawaiDAO.getAll();
    }
}