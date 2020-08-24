/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "agregar_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgregarPago.findAll", query = "SELECT a FROM AgregarPago a")
    , @NamedQuery(name = "AgregarPago.findByIdPedido", query = "SELECT a FROM AgregarPago a WHERE a.idPedido = :idPedido")
    , @NamedQuery(name = "AgregarPago.findByAbona", query = "SELECT a FROM AgregarPago a WHERE a.abona = :abona")
    , @NamedQuery(name = "AgregarPago.findBySaldo", query = "SELECT a FROM AgregarPago a WHERE a.saldo = :saldo")
    , @NamedQuery(name = "AgregarPago.findByObservacion", query = "SELECT a FROM AgregarPago a WHERE a.observacion = :observacion")
    , @NamedQuery(name = "AgregarPago.findByFecha", query = "SELECT a FROM AgregarPago a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AgregarPago.findBySoporte", query = "SELECT a FROM AgregarPago a WHERE a.soporte = :soporte")})
public class AgregarPago implements Serializable {

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
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 100)
    @Column(name = "Soporte")
    private String soporte;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cotizacion idCotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudList;

    public AgregarPago() {
    }

    public AgregarPago(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public AgregarPago(Integer idPedido, String observacion, Date fecha) {
        this.idPedido = idPedido;
        this.observacion = observacion;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
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
        if (!(object instanceof AgregarPago)) {
            return false;
        }
        AgregarPago other = (AgregarPago) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.AgregarPago[ idPedido=" + idPedido + " ]";
    }
    
}
