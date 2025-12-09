package mx.tecnm.ecommerce.api.models;

public record Producto(int id, String nombre, float precio, String sku, String color, String marca, String descripcion, float peso, float alto, float ancho, float profundidad, int categorias_id) {
}