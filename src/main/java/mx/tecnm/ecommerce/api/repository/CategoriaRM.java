package mx.tecnm.ecommerce. api.repository;

import org.springframework.jdbc.core. RowMapper;
import org.springframework.lang.NonNull;
import mx.tecnm.ecommerce. api.models.Categoria;

public class CategoriaRM implements RowMapper<Categoria> {
    @Override
    public Categoria mapRow(@NonNull java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return new Categoria(
                rs.getInt("id"),
                rs.getString("nombre"));
    }
}