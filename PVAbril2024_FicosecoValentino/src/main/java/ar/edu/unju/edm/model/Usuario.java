package ar.edu.unju.edm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Usuario {
	@Id
    @NotNull(message = "dni es nescesario")
    private Integer dni;

    @NotBlank(message = "apellido es nescesario")
    @Size(max = 20)
    private String apellido;

    @NotBlank(message = "nombre es nescesario")
    @Size(max = 30)
    private String nombre;
    
    @NotBlank(message = "contraseña es nescesario")
    private String password;
    
    @NotNull(message = "tipo es nescesario")
    private char tipoUsuario;
    
	private Boolean estado;

    public Usuario(Integer dni, String apellido, String nombre, char tipoUsuario, String password,Boolean estado) {
        super();
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
        this.setEstado(estado);
    }

    public Usuario() {
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(char tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
    }