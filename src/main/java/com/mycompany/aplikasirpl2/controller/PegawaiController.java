package com.mycompany.aplikasirpl2.controller;

import com.mycompany.aplikasirpl2.model.Pegawai;
import com.mycompany.aplikasirpl2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PegawaiController {

    // Simpan Data
    public void simpanPegawai(Pegawai pegawai) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(pegawai);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Ambil Semua Data untuk Dimasukkan ke JTable GUI
    public List<Pegawai> getAllPegawai() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Pegawai", Pegawai.class).list();
        }
    }

    // Hapus Data Berdasarkan ID
    public void hapusPegawai(String idPegawai) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Pegawai p = session.get(Pegawai.class, idPegawai);
            if (p != null) {
                session.delete(p);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}