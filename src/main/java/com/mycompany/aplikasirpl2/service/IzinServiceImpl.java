/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Flex 5
 */

package com.mycompany.aplikasirpl2.service;

import com.mycompany.aplikasirpl2.dao.IzinDAO;
import com.mycompany.aplikasirpl2.dao.IzinDAOImpl;
import com.mycompany.aplikasirpl2.model.Izin;
import java.util.List;

public class IzinServiceImpl implements IzinService {

    private final IzinDAO izinDAO = new IzinDAOImpl();

    @Override
    public void ajukanIzin(Izin izin) {
        izinDAO.save(izin);
    }

    @Override
    public void hapusIzin(Integer idIzin) {
        izinDAO.delete(idIzin);
    }

    @Override
    public List<Izin> getAllIzin() {
        return izinDAO.findAll();
    }
}
