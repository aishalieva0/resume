/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao.inter;

import com.company.entity.Country;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface CountryDaoInter {

    public List<Country> getAllCountryByUserId(int userId);

}