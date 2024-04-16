package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Entity
public class CanchaDeFutbol {
	@Id
    @NotNull(message = "Codigo es nescesario")
    private Integer Codigo;

    @NotBlank(message = "Precios es nescesario")
    private String Precios;

    @NotBlank(message = "horario1 es nescesario")
    private String Horario1;
    
    @NotBlank(message = "horario3 es nescesario")
    private String Horario3;

    @NotBlank(message = "horario2 es nescesario")
    private String Horario2;
    
    @NotNull(message = "Capacidad es nescesario")
    private char Capacidad;
    
    public CanchaDeFutbol(Integer Codigo, String Precios, String Horario1, String Horario2, String Horario3, char Capacidad) {
        super();
        this.Codigo = Codigo;
        this.Precios = Precios;
        this.Horario1 = Horario1;
        this.Horario2 = Horario2;
        this.Horario3 = Horario3;
        this.Capacidad = Capacidad;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
    }

    public String getPrecios() {
        return Precios;
    }

    public void setPrecios(String Precios) {
        this.Precios = Precios;
    }

    public String getHorario2() {
        return Horario2;
    }

    public void setHorario2(String Horario2) {
        this.Horario2 = Horario2;
    }
    public String getHorario1() {
        return Horario1;
    }

    public void setHorario1(String Horario1) {
        this.Horario1 = Horario1;
    }
    public String getHorario3() {
        return Horario3;
    }

    public void setHorario3(String Horario3) {
        this.Horario3 = Horario3;
    }

    public char getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(char Capacidad) {
        this.Capacidad = Capacidad;
    }
    }