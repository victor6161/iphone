package phone.iphone;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class IphoneJDBCTemplate implements IphoneDAO {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplateObject;
  
    
 
    
    @Override
    public List<Souvenir> getListSouvenir() {
        
       List<Souvenir> listSouvenir = jdbcTemplateObject.query("select * from goods", new SouvenirMapper());
        return listSouvenir;
    }
  
    
    

    @Override
    public Souvenir getSouvenir(String id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        Souvenir iphone = jdbcTemplateObject.queryForObject("select * from goods where id=:id", params, new SouvenirMapper());
        return iphone;
    }
    
    public void setSouvenir(Map namedParameters ){
        String sql="insert into goods(id,title,lacquer,fastening,bevel,length,weight,thickness,price,photo1,photo2,photo3,photo4,description) values (:id,:title,:lacquer,:fastening,:bevel,:length,:weight,:thickness,:price,:photo1,:photo2,:photo3,:photo4,:description) ";    
        jdbcTemplateObject.update(sql, namedParameters);
       
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);
    }
}
