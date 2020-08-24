/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "estampado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estampado.findAll", query = "SELECT e FROM Estampado e")
    , @NamedQuery(name = "Estampado.findByIdEstampado", query = "SELECT e FROM Estampado e WHERE e.idEstampado = :idEstampado")
    , @NamedQuery(name = "Estampado.findByNombre", query = "SELECT e FROM Estampado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estampado.findByImg", query = "SELECT e FROM Estampado e WHERE e.img = :img")
    , @NamedQuery(name = "Estampado.findByPrecio", query = "SELECT e FROM Estampado e WHERE e.precio = :precio")})
public class Estampado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstampado")
    private Integer idEstampado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "img")
    private String img;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @OneToMany(mappedBy = "estampado", fetch = FetchType.LAZY)
    private List<Cotizacion> cotizacionList;

    public Estampado() {
    }

    public Estampado(Integer idEstampado) {
        this.idEstampado = idEstampado;
    }

    public Estampado(Integer idEstampado, String nombre, String img) {
        this.idEstampado = idEstampado;
        this.nombre = nombre;
        this.img = img;
    }

    public Integer getIdEstampado() {
        return idEstampado;
    }

    public void setIdEstampado(Integer idEstampado) {
        this.idEstampado = idEstampado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstampado != null ? idEstampado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estampado)) {
            return false;
        }
        Estampado other = (Estampado) object;
        if ((this.idEstampado == null && other.idEstampado != null) || (this.idEstampado != null && !this.idEstampado.equals(other.idEstampado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Estampado[ idEstampado=" + idEstampado + " ]";
    }
    
}
