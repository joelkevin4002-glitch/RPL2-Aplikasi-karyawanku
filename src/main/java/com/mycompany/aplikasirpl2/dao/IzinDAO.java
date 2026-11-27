

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Flex 5
 */
package com.mycompany.aplikasirpl2.dao;

import com.mycompany.aplikasirpl2.model.Izin;
import java.util.List;

public interface IzinDAO {
    void save(Izin izin);
    void delete(Integer idIzin);
    List<Izin> findAll();
}