package mx. tecnm.ecommerce.api.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import mx. tecnm.ecommerce.api.models.Domicilio;

public class DomicilioRM implements RowMapper<Domicilio> {
    @Override
    public Domicilio mapRow(@NonNull java.sql. ResultSet rs, int rowNum) throws java.sql.SQLException {
        return new Domicilio(
                rs.getInt("id"),
                rs.getString("calle"),
                rs.getString("numero"),
                rs.getString("colonia"),
                rs.getString("cp"),
                rs.getString("estado"),
                rs.getString("ciudad"),
                rs.getInt("usuarios_id"));
    }
}