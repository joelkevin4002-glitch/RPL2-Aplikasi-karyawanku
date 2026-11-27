/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Flex 5
 */

package com.mycompany.aplikasirpl2.controller;

import com.mycompany.aplikasirpl2.model.Izin;
import com.mycompany.aplikasirpl2.service.IzinService;
import com.mycompany.aplikasirpl2.service.IzinServiceImpl;
import java.util.List;

public class IzinController {

    private final IzinService izinService = new IzinServiceImpl();

    public void ajukanIzin(Izin izin) {
        izinService.ajukanIzin(izin);
    }

    public void hapusIzin(Integer idIzin) {
        izinService.hapusIzin(idIzin);
    }

    public List<Izin> getAllIzin() {
        return izinService.getAllIzin();
    }
}