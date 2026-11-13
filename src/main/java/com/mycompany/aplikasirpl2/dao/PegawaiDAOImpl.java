package com.mycompany.aplikasirpl2.dao;

import com.mycompany.aplikasirpl2.model.Pegawai;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PegawaiDAOImpl implements PegawaiDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Pegawai pegawai) {
        sessionFactory.getCurrentSession().save(pegawai);
    }

    @Override
    public void update(Pegawai pegawai) {
        sessionFactory.getCurrentSession().update(pegawai);
    }

    @Override
    public void delete(Pegawai pegawai) {
        sessionFactory.getCurrentSession().delete(pegawai);
    }

    @Override
    public Pegawai getById(String idPegawai) {
        return sessionFactory.getCurrentSession().get(Pegawai.class, idPegawai);
    }

    @Override
    public List<Pegawai> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Pegawai", Pegawai.class).list();
    }
}