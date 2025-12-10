package mx.tecnm.ecommerce.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.ecommerce.api.models.DetallesCarrito;
import mx.tecnm.ecommerce.api.dto.DetallesCarritoDTO;
import mx.tecnm.ecommerce.api.dto.PUTDetallesCarritoDTO;

@Repository
public class DetallesCarritoDAO {
    @Autowired
    private JdbcClient jdbcClient;

    public List<DetallesCarrito> obtenerDetallesCarrito() {
        String sql = "SELECT id, cantidad, precio, productos_id, usuarios_id FROM detalles_carrito WHERE activo=true";
        return jdbcClient.sql(sql)
                .query(new DetallesCarritoRM())
                .list();
    }

    public DetallesCarrito obtenerDetallesCarritoPorId(int id) {
        String sql = "SELECT id, cantidad, precio, productos_id, usuarios_id FROM detalles_carrito WHERE id = :id AND activo=true";
        List<DetallesCarrito> detalles = jdbcClient.sql(sql)
                .param("id", id)
                .query(new DetallesCarritoRM())
                .list();
        return detalles.isEmpty() ? null :detalles.get(0);
    }

    public List<DetallesCarrito> obtenerDetallesCarritoPorUsuario(int usuarioId) {
        String sql = "SELECT id, cantidad, precio, productos_id, usuarios_id FROM detalles_carrito WHERE usuarios_id = :usuarios_id AND activo=true";
        return jdbcClient.sql(sql)
                .param("usuarios_id", usuarioId)
                .query(new DetallesCarritoRM())
                .list();
    }

    public DetallesCarrito insertarDetalleCarrito(DetallesCarritoDTO dto, float precio) {
        int nuevoId = jdbcClient.sql("""
                INSERT INTO detalles_carrito(productos_id, usuarios_id, cantidad, precio)
                VALUES (:productos_id, :usuarios_id, :cantidad, :precio)
                RETURNING id
            """)
            .param("productos_id", dto.productos_id())
            .param("usuarios_id", dto.usuarios_id())
            .param("cantidad", dto.cantidad())
            .param("precio", precio)
            .query((rs, rowNum) -> rs.getInt("id"))
            .single();

        return obtenerDetallesCarritoPorId(nuevoId);
    }

    public DetallesCarrito actualizarDetalleCarrito(int id, PUTDetallesCarritoDTO dto) {
        int filas = jdbcClient.sql("""
                UPDATE detalles_carrito
                SET cantidad = :cantidad
                WHERE id = :id
            """)
            .param("id", id)
            .param("cantidad", dto.cantidad())
            .update();

        return filas > 0 ? obtenerDetallesCarritoPorId(id) :null;
    }

    public DetallesCarrito desactivarDetalleCarrito(int id) {
        int filas = jdbcClient.sql("UPDATE detalles_carrito SET activo=false WHERE id=:id")
            .param("id", id)
            .update();
        return filas > 0 ? obtenerDetallesCarritoPorId(id) :null;
    }

    public void limpiarCarritoPorUsuario(int usuarioId) {
        jdbcClient.sql("UPDATE detalles_carrito SET activo=false WHERE usuarios_id=:usuarios_id")
            .param("usuarios_id", usuarioId)
            .update();
    }
}