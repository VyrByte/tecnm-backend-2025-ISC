package mx.tecnm.ecommerce.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.ecommerce.api.models.DetallesPedido;
import mx.tecnm.ecommerce.api.dto.DetallesPedidoDTO;
import mx.tecnm.ecommerce.api.dto.PUTDetallesPedidoDTO;

@Repository
public class DetallesPedidoDAO {
    @Autowired
    private JdbcClient jdbcClient;

    public List<DetallesPedido> obtenerDetallesPedido() {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE activo=true";
        return jdbcClient.sql(sql)
                .query(new DetallesPedidoRM())
                .list();
    }

    public DetallesPedido obtenerDetallesPedidoPorId(int id) {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE id = :id AND activo=true";
        List<DetallesPedido> detalles = jdbcClient.sql(sql)
                .param("id", id)
                .query(new DetallesPedidoRM())
                .list();
        return detalles.isEmpty() ? null : detalles.get(0);
    }

    public List<DetallesPedido> obtenerDetallesPorPedido(int pedidoId) {
        String sql = "SELECT id, cantidad, precio, productos_id, pedidos_id FROM detalles_pedido WHERE pedidos_id = :pedidos_id AND activo=true";
        return jdbcClient.sql(sql)
                .param("pedidos_id", pedidoId)
                .query(new DetallesPedidoRM())
                .list();
    }

    public DetallesPedido insertarDetallePedido(DetallesPedidoDTO dto) {
        int nuevoId = jdbcClient.sql("""
                INSERT INTO detalles_pedido(pedidos_id, productos_id, cantidad, precio)
                VALUES (:pedidos_id, :productos_id, :cantidad, :precio)
                RETURNING id
            """)
            .param("pedidos_id", dto.pedidos_id())
            .param("productos_id", dto.productos_id())
            .param("cantidad", dto.cantidad())
            .param("precio", dto.precio())
            .query((rs, rowNum) -> rs.getInt("id"))
            .single();

        return obtenerDetallesPedidoPorId(nuevoId);
    }

    public DetallesPedido actualizarDetallePedido(int id, PUTDetallesPedidoDTO dto) {
        int filas = jdbcClient.sql("""
                UPDATE detalles_pedido
                SET cantidad = :cantidad,
                    precio = :precio
                WHERE id = :id
            """)
            .param("id", id)
            .param("cantidad", dto.cantidad())
            .param("precio", dto.precio())
            .update();

        return filas > 0 ? obtenerDetallesPedidoPorId(id) : null;
    }

    public DetallesPedido desactivarDetallePedido(int id) {
        int filas = jdbcClient.sql("UPDATE detalles_pedido SET activo=false WHERE id=:id")
            .param("id", id)
            .update();
        return filas > 0 ? obtenerDetallesPedidoPorId(id) : null;
    }
}