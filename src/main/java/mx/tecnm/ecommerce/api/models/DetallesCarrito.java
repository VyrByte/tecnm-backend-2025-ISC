package mx.tecnm.ecommerce.api.models;

public record DetallesCarrito(int id, int cantidad, float precio, int productos_id, int usuarios_id) {
}