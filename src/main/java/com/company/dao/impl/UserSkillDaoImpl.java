package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("user_skill_id"); //id
        int userId = rs.getInt("user_id");
        int skillId = rs.getInt("skill_id"); // user skillden skill_id
        String skillName = rs.getString("skill_name"); //skillden name
        int power = rs.getInt("power"); //power

        return new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);

    }

    @Override
    public List<UserSkill> getById(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement(" SELECT " + "us.id as user_skill_id,"
                    + "	u.*, "
                    + "	us.skill_id, "
                    + "	s.NAME AS skill_name, "
                    + "	us.power  "
                    + "FROM "
                    + "	user_skill us "
                    + "	LEFT JOIN USER u ON us.user_id = u.id "
                    + "	LEFT JOIN skill s ON us.skill_id = s.id  "
                    + "WHERE "
                    + "	us.user_id = ?; ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set skill_id =? , user_id =? , power = ? where id =?");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            stmt.setInt(4, u.getId());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserSkill(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" delete from user_skill where id= ? ");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" insert into user_skill (skill_id , user_id , power ) values(? , ? , ? ) ");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserSkill> getAll() {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute(" SELECT "
                    + "	s.NAME AS skill_name, "
                    + "	us.id AS user_skill_id, "
                    + "	us.user_id, "
                    + "	us.skill_id, "
                    + "	us.power  "
                    + "FROM "
                    + "	user_skill us "
                    + "	LEFT JOIN skill s ON us.skill_id = s.id; ");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);

                result.add(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
