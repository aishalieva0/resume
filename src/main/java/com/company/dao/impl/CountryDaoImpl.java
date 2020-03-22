package com.company.dao.impl;

import com.company.entity.Country;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
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
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int userId = rs.getInt("id");
        String country = rs.getString("name");
        String nationality = rs.getString("nationality");

        return new Country(userId, country, nationality);

    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {

            Statement stmt = c.createStatement();
            stmt.execute("select * from country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country u = getCountry(rs);

                result.add(u);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Country> getById(int userId) {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement(" select * from country where id=? ");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country country = getCountry(rs);

                result.add(country);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into country (name,nationality) values(?,?) ");
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getNationality());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateCountry(Country country) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(" update country set name=?, nationality=? where id=? ");
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getNationality());

            return stmt.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCountry(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute(" delete from country where id= " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
