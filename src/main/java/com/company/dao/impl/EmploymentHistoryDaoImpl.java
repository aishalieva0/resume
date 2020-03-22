package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Hp
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        String header = rs.getString("header");
        String jobDecsription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDecsription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getById(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement(" select * from employment_history where user_id = ? ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT "
                    + " header, "
                    + " begin_date, "
                    + " end_date, "
                    + " job_description, "
                    +" user_id"
                    + " FROM "
                    + " employment_history ;");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                EmploymentHistory e = getEmploymentHistory(rs);
                result.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertEmploymentHistory(EmploymentHistory emp) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" insert into employment_history (header, begin_date, end_date, job_description) values (?,?,?,?) ");
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, emp.getBeginDate());
            stmt.setDate(3, emp.getEndDate());
            stmt.setString(4, emp.getJobDescription());
            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory emp) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" update employment_history set header=?, begin_date=?, end_date=?, job_description=? where=? ");
            stmt.setString(1, emp.getHeader());
            stmt.setDate(2, emp.getBeginDate());
            stmt.setDate(3, emp.getEndDate());
            stmt.setString(4, emp.getJobDescription());
            stmt.setInt(5, emp.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmploymentHistory(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute(" delete from employment_history where id= " + id);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
