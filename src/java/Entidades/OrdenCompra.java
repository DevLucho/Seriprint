/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findByIdPedido", query = "SELECT o FROM OrdenCompra o WHERE o.idPedido = :idPedido")
    , @NamedQuery(name = "OrdenCompra.findByAbona", query = "SELECT o FROM OrdenCompra o WHERE o.abona = :abona")
    , @NamedQuery(name = "OrdenCompra.findBySaldo", query = "SELECT o FROM OrdenCompra o WHERE o.saldo = :saldo")
    , @NamedQuery(name = "OrdenCompra.findByObservacion", query = "SELECT o FROM OrdenCompra o WHERE o.observacion = :observacion")
    , @NamedQuery(name = "OrdenCompra.findByFechaCompra", query = "SELECT o FROM OrdenCompra o WHERE o.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "OrdenCompra.findByHoraCompra", query = "SELECT o FROM OrdenCompra o WHERE o.horaCompra = :horaCompra")
    , @NamedQuery(name = "OrdenCompra.findByFechaEntrega", query = "SELECT o FROM OrdenCompra o WHERE o.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "OrdenCompra.findByHoraEntrega", query = "SELECT o FROM OrdenCompra o WHERE o.horaEntrega = :horaEntrega")
    , @NamedQuery(name = "OrdenCompra.findBySoporte", query = "SELECT o FROM OrdenCompra o WHERE o.soporte = :soporte")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Abona")
    private Double abona;
    @Column(name = "Saldo")
    private Double saldo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaCompra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaCompra")
    @Temporal(TemporalType.TIME)
    private Date horaCompra;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "horaEntrega")
    @Temporal(TemporalType.TIME)
    private Date horaEntrega;
    @Size(max = 100)
    @Column(name = "Soporte")
    private String soporte;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cotizacion idCotizacion;
    @JoinColumn(name = "idOperario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idOperario;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public OrdenCompra(Integer idPedido, String observacion, Date fechaCompra, Date horaCompra) {
        this.idPedido = idPedido;
        this.observacion = observacion;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Double getAbona() {
        return abona;
    }

    public void setAbona(Double abona) {
        this.abona = abona;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(Date horaCompra) {
        this.horaCompra = horaCompra;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Usuario getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(Usuario idOperario) {
        this.idOperario = idOperario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.OrdenCompra[ idPedido=" + idPedido + " ]";
    }
    
}
