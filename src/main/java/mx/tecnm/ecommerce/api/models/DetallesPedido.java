package mx.tecnm.ecommerce.api.models;

public record DetallesPedido(int id, int cantidad, double precio, int productos_id, int pedidos_id) {
}