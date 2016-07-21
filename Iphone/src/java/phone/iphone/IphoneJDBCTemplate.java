/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phone.iphone;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author Admin
 */
public class IphoneJDBCTemplate implements IphoneDAO {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;

    @Override
    public List<Iphone> getListIphone() {
        List<Iphone> listIphone = jdbcTemplateObject.query("select * from iphone", new IphoneMapper());
        return listIphone;
    }

    @Override
    public Iphone getIphone(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        Iphone iphone = jdbcTemplateObject.queryForObject("select * from iphone where id=:id", params, new IphoneMapper());
        return iphone;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }

}
