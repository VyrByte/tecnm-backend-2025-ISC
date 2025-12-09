package mx.tecnm.ecommerce.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.ecommerce.api.models. Domicilio;
import mx. tecnm.ecommerce.api.dto.DomicilioDTO;

@Repository
public class DomicilioDAO {

    @Autowired
    private JdbcClient jdbcClient;

    public List<Domicilio> obtenerDomicilios() {
        String sql = """
            SELECT id, calle, numero, colonia, cp, estado, ciudad, usuarios_id 
            FROM domicilios WHERE activo=true
        """;

        return jdbcClient.sql(sql)
                .query(new DomicilioRM())
                .list();
    }

    public Domicilio insertarDomicilio(DomicilioDTO dto) {
        String sql = """
            INSERT INTO domicilios (calle, numero, colonia, cp, ciudad, estado, usuarios_id)
            VALUES (:calle, :numero, :colonia, : cp, :ciudad, :estado, :usuarioId)
            RETURNING id, calle, numero, colonia, cp, ciudad, estado, usuarios_id
        """;

        return jdbcClient.sql(sql)
                .param("calle", dto.calle())
                .param("numero", dto.numero())
                .param("colonia", dto.colonia())
                .param("cp", dto.cp())
                .param("ciudad", dto.ciudad())
                .param("estado", dto.estado())
                .param("usuarioId", dto.usuarios_id())
                .query(new DomicilioRM())
                .single();
    }

    public Domicilio obtenerDomiciliosPorId(int id) {
        String sql = """
            SELECT id, calle, numero, colonia, cp, estado, ciudad, usuarios_id 
            FROM domicilios
            WHERE id = :id AND activo=true
        """;

        List<Domicilio> lista = jdbcClient.sql(sql)
                .param("id", id)
                .query(new DomicilioRM())
                .list();

        return lista.isEmpty() ? null : lista.get(0);
    }

    public Domicilio actualizarDomicilio(int id, DomicilioDTO dto) {
        String sql = """
            UPDATE domicilios SET
                calle = :calle,
                numero = :numero,
                colonia = :colonia,
                cp = :cp,
                ciudad = :ciudad,
                estado = :estado
            WHERE id = :id
            RETURNING id, calle, numero, colonia, cp, ciudad, estado, usuarios_id
        """;

        return jdbcClient.sql(sql)
                .param("id", id)
                .param("calle", dto. calle())
                .param("numero", dto.numero())
                .param("colonia", dto. colonia())
                .param("cp", dto.cp())
                .param("ciudad", dto.ciudad())
                .param("estado", dto.estado())
                .query(new DomicilioRM())
                .single();
    }

    public Domicilio desactivarDomicilio(int id) {
        String sql = """
            UPDATE domicilios SET activo = false
            WHERE id = :id
        """;

        int filas = jdbcClient.sql(sql)
                .param("id", id)
                .update();

        return filas > 0 ? obtenerDomiciliosPorId(id) : null;
    }
}