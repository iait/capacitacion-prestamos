package com.eiv.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eiv.enums.SistemaAmortizacionEnum;
import com.eiv.enums.UnidadAmortizacionEnum;

@Entity
@Table(name = "prestamos")
public class PrestamoEntity {

    @Id
    @Column(name = "prestamo_id", nullable = false)
    private long id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(
                    name = "id_tipodocumento", referencedColumnName = "id_tipodocumento", 
                    nullable = false),
            @JoinColumn(
                    name = "numero_documento", referencedColumnName = "numero_documento", 
                    nullable = false) })
    private PersonaEntity persona;
    
    @OneToOne
    @JoinColumn(name = "linea_id", referencedColumnName = "linea_id", nullable = false)
    private LineaEntity linea;

    @Column(name = "sistema_amortizacion", nullable = false, length = 1)
    private SistemaAmortizacionEnum sistemaAmortizacion;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "fecha_primer_vto", nullable = false)
    private LocalDate fechaPrimerVto;

    @Column(name = "tasa_efectiva", nullable = false)
    private BigDecimal tasaEfectiva;
    
    @Column(name = "tasa_modulo", nullable = false)
    private Integer tasaModulo;

    @Column(name = "amortizacion_periodo", nullable = false)
    private Integer amortizacionPeriodo;

    @Column(name = "amortizacion_unidad", nullable = false)
    private UnidadAmortizacionEnum amortizacionUnidad;

    @Column(name = "capital_prestado", nullable = false)
    private BigDecimal capitalPrestado;
    
    @Column(name = "total_intereses", nullable = false)
    private BigDecimal totalIntereses;

    @Column(name = "total_cuotas", nullable = false)
    private Integer totalCuotas;

    @OneToOne
    @JoinColumns({
            @JoinColumn(
                    name = "usuario_tipo_documento_id", referencedColumnName = "id_tipodocumento", 
                    nullable = false),
            @JoinColumn(
                    name = "usuario_numero_documento", referencedColumnName = "numero_documento", 
                    nullable = false) })
    private UsuarioEntity usuario;
    
    public PrestamoEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public LineaEntity getLinea() {
        return linea;
    }

    public void setLinea(LineaEntity linea) {
        this.linea = linea;
    }

    public SistemaAmortizacionEnum getSistemaAmortizacion() {
        return sistemaAmortizacion;
    }

    public void setSistemaAmortizacion(SistemaAmortizacionEnum sistemaAmortizacion) {
        this.sistemaAmortizacion = sistemaAmortizacion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaPrimerVto() {
        return fechaPrimerVto;
    }

    public void setFechaPrimerVto(LocalDate fechaPrimerVto) {
        this.fechaPrimerVto = fechaPrimerVto;
    }

    public BigDecimal getTasaEfectiva() {
        return tasaEfectiva;
    }

    public void setTasaEfectiva(BigDecimal tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    public Integer getTasaModulo() {
        return tasaModulo;
    }

    public void setTasaModulo(Integer tasaModulo) {
        this.tasaModulo = tasaModulo;
    }

    public Integer getAmortizacionPeriodo() {
        return amortizacionPeriodo;
    }

    public void setAmortizacionPeriodo(Integer amortizacionPeriodo) {
        this.amortizacionPeriodo = amortizacionPeriodo;
    }

    public UnidadAmortizacionEnum getAmortizacionUnidad() {
        return amortizacionUnidad;
    }

    public void setAmortizacionUnidad(UnidadAmortizacionEnum amortizacionUnidad) {
        this.amortizacionUnidad = amortizacionUnidad;
    }

    public BigDecimal getCapitalPrestado() {
        return capitalPrestado;
    }

    public void setCapitalPrestado(BigDecimal capitalPrestado) {
        this.capitalPrestado = capitalPrestado;
    }

    public BigDecimal getTotalIntereses() {
        return totalIntereses;
    }

    public void setTotalIntereses(BigDecimal totalIntereses) {
        this.totalIntereses = totalIntereses;
    }

    public Integer getTotalCuotas() {
        return totalCuotas;
    }

    public void setTotalCuotas(Integer totalCuotas) {
        this.totalCuotas = totalCuotas;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PrestamoEntity other = (PrestamoEntity) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrestamoEntity [id=" + id + ", linea=" + linea + "]";
    }    
}
