package mx.tecnm.ecommerce.api.models;

public record Domicilio(int id, String calle, String numero, String colonia, String cp, String estado, String ciudad, int usuarios_id) {
}