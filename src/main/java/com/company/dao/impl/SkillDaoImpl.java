package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
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
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        String skillName = rs.getString("name");

        return new Skill(userId, skillName);

    }

    @Override
    public List<Skill> getById(int userId) {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement(" select * from skill where id=? ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill s = getSkill(rs);

                result.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute(" select * from skill");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill u = getSkill(rs);

                result.add(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertSkill(Skill skill) {
        boolean b = true;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" insert skill (name ) values ( ? ) ; ", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            b = stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" update skill set name = ? where id =? ; ");

            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSkill(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" delete from skill where id= ? ");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
