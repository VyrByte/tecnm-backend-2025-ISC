//mx/tecnm/ecommerce/api/repository/ProductoDAO.java
package mx.tecnm.ecommerce.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.ecommerce.api. dto.PUTProductoDTO;
import mx. tecnm.ecommerce.api.dto.ProductoDTO;
import mx.tecnm.ecommerce.api. models.Producto;

@Repository
public class ProductoDAO {
    @Autowired
    private JdbcClient jdbcClient;

    public List<Producto> obtenerProductos() {
        String sql = "SELECT id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id FROM productos";
        return jdbcClient.sql(sql)
                .query(new ProductoRM())
                .list();
    }

    public Producto obtenerProductoPorId(int id) {
        String sql = "SELECT id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id FROM productos WHERE id = : id";
        List<Producto> productos = jdbcClient.sql(sql)
                .param("id", id)
                .query(new ProductoRM())
                .list();
        return productos.isEmpty() ? null : productos. get(0);
    }

    public Producto insertarProducto(ProductoDTO producto) {
        int nuevoId = jdbcClient.sql("INSERT INTO productos(nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id) VALUES (:nombre, :precio, : sku, :color, :marca, :descripcion, :peso, : alto, :ancho, :profundidad, :categorias_id) RETURNING id")
            .param("nombre", producto.nombre())
            .param("precio", producto.precio())
            .param("sku", producto.sku())
            .param("color", producto.color())
            .param("marca", producto.marca())
            .param("descripcion", producto.descripcion())
            .param("peso", producto.peso())
            .param("alto", producto. alto())
            .param("ancho", producto.ancho())
            .param("profundidad", producto.profundidad())
            .param("categorias_id", producto.categorias_id())
            .query((rs, rowNum) -> rs.getInt("id"))
            .single();
        return obtenerProductoPorId(nuevoId);
    }

    public boolean eliminarProducto(int id) {
        int filas = jdbcClient.sql("DELETE FROM productos WHERE id=:id")
            .param("id", id)
            .update();
        return filas > 0;
    }

    public Producto actualizarProducto(int id, PUTProductoDTO producto) {
        int filas = jdbcClient.sql("UPDATE productos SET nombre=:nombre, precio=:precio, sku=:sku, color=: color, marca=:marca, descripcion=:descripcion, peso=:peso, alto=:alto, ancho=:ancho, profundidad=:profundidad WHERE id=:id")
            .param("id", id)
            .param("nombre", producto.nombre())
            .param("precio", producto.precio())
            .param("sku", producto.sku())
            .param("color", producto.color())
            .param("marca", producto.marca())
            .param("descripcion", producto.descripcion())
            .param("peso", producto.peso())
            .param("alto", producto.alto())
            .param("ancho", producto.ancho())
            .param("profundidad", producto.profundidad())
            .update();
        return filas > 0 ? obtenerProductoPorId(id) : null;
    }
}