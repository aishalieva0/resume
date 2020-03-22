/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface UserSkillDaoInter {

    public List<UserSkill> getById(int userId);

    public List<UserSkill> getAll();

    public boolean insertUserSkill(UserSkill u);

    public boolean updateUserSkill(UserSkill u);

    public boolean deleteUserSkill(int id);

}
