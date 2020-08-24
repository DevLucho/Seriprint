/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "tipo_de_insumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeInsumo.findAll", query = "SELECT t FROM TipoDeInsumo t")
    , @NamedQuery(name = "TipoDeInsumo.findByIdTipoinsumo", query = "SELECT t FROM TipoDeInsumo t WHERE t.idTipoinsumo = :idTipoinsumo")
    , @NamedQuery(name = "TipoDeInsumo.findByNombre", query = "SELECT t FROM TipoDeInsumo t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDeInsumo.findByDescripcion", query = "SELECT t FROM TipoDeInsumo t WHERE t.descripcion = :descripcion")})
public class TipoDeInsumo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_insumo")
    private Integer idTipoinsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoinsumo", fetch = FetchType.LAZY)
    private List<Insumo> insumoList;

    public TipoDeInsumo() {
    }

    public TipoDeInsumo(Integer idTipoinsumo) {
        this.idTipoinsumo = idTipoinsumo;
    }

    public TipoDeInsumo(Integer idTipoinsumo, String nombre, String descripcion) {
        this.idTipoinsumo = idTipoinsumo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoinsumo() {
        return idTipoinsumo;
    }

    public void setIdTipoinsumo(Integer idTipoinsumo) {
        this.idTipoinsumo = idTipoinsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Insumo> getInsumoList() {
        return insumoList;
    }

    public void setInsumoList(List<Insumo> insumoList) {
        this.insumoList = insumoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoinsumo != null ? idTipoinsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDeInsumo)) {
            return false;
        }
        TipoDeInsumo other = (TipoDeInsumo) object;
        if ((this.idTipoinsumo == null && other.idTipoinsumo != null) || (this.idTipoinsumo != null && !this.idTipoinsumo.equals(other.idTipoinsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoDeInsumo[ idTipoinsumo=" + idTipoinsumo + " ]";
    }
    
}
