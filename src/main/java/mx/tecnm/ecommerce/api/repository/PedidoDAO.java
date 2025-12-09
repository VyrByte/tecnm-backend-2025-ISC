package mx.tecnm. ecommerce.api.repository;

import java.util.List;
import org.springframework.beans. factory.annotation.Autowired;
import org.springframework.jdbc. core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.ecommerce.api. models.Pedido;
import mx.tecnm.ecommerce.api.dto.AgregarPedidoDTO;
import mx.tecnm.ecommerce.api.dto.PUTPedidoDTO;

@Repository
public class PedidoDAO {
    @Autowired
    private JdbcClient jdbcClient;

    public List<Pedido> obtenerPedidos() {
        String sql = "SELECT id, fecha, importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha_hora_pago, importe_iva, total, numero FROM pedidos WHERE activo=true";
        return jdbcClient.sql(sql)
                .query(new PedidoRM())
                .list();
    }

    public Pedido obtenerPedidoPorId(int id) {
        String sql = "SELECT id, fecha, importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha_hora_pago, importe_iva, total, numero FROM pedidos WHERE id = :id AND activo=true";
        List<Pedido> pedidos = jdbcClient.sql(sql)
                .param("id", id)
                .query(new PedidoRM())
                .list();
        return pedidos.isEmpty() ? null : pedidos.get(0);
    }

    public Pedido insertarPedido(AgregarPedidoDTO dto, double importe_productos) {
        int nuevoId = jdbcClient.sql("""
                INSERT INTO pedidos(usuarios_id, importe_productos, importe_envio, metodos_pago_id, fecha_hora_pago)
                VALUES (:usuarios_id, :importe_productos, :importe_envio, :metodos_pago_id, NOW())
                RETURNING id
            """)
            .param("usuarios_id", dto.usuarios_id())
            .param("importe_productos", importe_productos)
            .param("importe_envio", dto.importe_envio())
            .param("metodos_pago_id", dto.metodos_pago_id())
            .query((rs, rowNum) -> rs.getInt("id"))
            .single();

        return obtenerPedidoPorId(nuevoId);
    }

    public Pedido actualizarPedido(int id, PUTPedidoDTO dto) {
        int filas = jdbcClient.sql("""
                UPDATE pedidos
                SET importe_productos = :importe_productos,
                    importe_envio = :importe_envio
                WHERE id = :id
            """)
            .param("id", id)
            .param("importe_productos", dto.importe_productos())
            .param("importe_envio", dto.importe_envio())
            .update();

        return filas > 0 ? obtenerPedidoPorId(id) : null;
    }

    public Pedido desactivarPedido(int id) {
        int filas = jdbcClient.sql("UPDATE pedidos SET activo=false WHERE id=: id")
            .param("id", id)
            .update();
        return filas > 0 ? obtenerPedidoPorId(id) : null;
    }
}