/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Flex 5
 */

package com.mycompany.aplikasirpl2.service;

import com.mycompany.aplikasirpl2.model.Izin;
import java.util.List;

public interface IzinService {
    void ajukanIzin(Izin izin);
    void hapusIzin(Integer idIzin);
    List<Izin> getAllIzin();
}
