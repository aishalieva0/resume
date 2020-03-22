/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author Hp
 */
public class Main {

    public static void main(String[] args) throws Exception {
        EmploymentHistoryDaoInter emp = Context.instanceEmploymentHistoryDao();
        CountryDaoInter c = Context.instanceCountryDao();
        SkillDaoInter s = Context.instanceSkillDao();
        UserSkillDaoInter us = Context.instanceUserSkillDao();
        UserDaoInter u = Context.instanceUserDao();

    }
}
