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
@Table(name = "tipo_de_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeProducto.findAll", query = "SELECT t FROM TipoDeProducto t")
    , @NamedQuery(name = "TipoDeProducto.findByIdTipoproducto", query = "SELECT t FROM TipoDeProducto t WHERE t.idTipoproducto = :idTipoproducto")
    , @NamedQuery(name = "TipoDeProducto.findByNombres", query = "SELECT t FROM TipoDeProducto t WHERE t.nombres = :nombres")
    , @NamedQuery(name = "TipoDeProducto.findByDescripcion", query = "SELECT t FROM TipoDeProducto t WHERE t.descripcion = :descripcion")})
public class TipoDeProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_producto")
    private Integer idTipoproducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 106)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoproducto", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public TipoDeProducto() {
    }

    public TipoDeProducto(Integer idTipoproducto) {
        this.idTipoproducto = idTipoproducto;
    }

    public TipoDeProducto(Integer idTipoproducto, String nombres, String descripcion) {
        this.idTipoproducto = idTipoproducto;
        this.nombres = nombres;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoproducto() {
        return idTipoproducto;
    }

    public void setIdTipoproducto(Integer idTipoproducto) {
        this.idTipoproducto = idTipoproducto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoproducto != null ? idTipoproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDeProducto)) {
            return false;
        }
        TipoDeProducto other = (TipoDeProducto) object;
        if ((this.idTipoproducto == null && other.idTipoproducto != null) || (this.idTipoproducto != null && !this.idTipoproducto.equals(other.idTipoproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoDeProducto[ idTipoproducto=" + idTipoproducto + " ]";
    }
    
}
