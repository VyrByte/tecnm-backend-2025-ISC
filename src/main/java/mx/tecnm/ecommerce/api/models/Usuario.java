package mx.tecnm.ecommerce.api.models;

public record Usuario(int id, String nombre, String email, String telefono, Sexo sexo, String fecha_nacimiento, String contrasena, String fecha_registro) {
}