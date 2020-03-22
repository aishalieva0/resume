/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface EmploymentHistoryDaoInter {

    public List<EmploymentHistory> getById(int userId);

    public List<EmploymentHistory> getAll();

    public boolean insertEmploymentHistory(EmploymentHistory emp);

    public boolean updateEmploymentHistory(EmploymentHistory emp);

    public boolean deleteEmploymentHistory(int id);

}
