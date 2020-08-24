/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "inventario-solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioSolicitud.findAll", query = "SELECT i FROM InventarioSolicitud i")
    , @NamedQuery(name = "InventarioSolicitud.findByIdInvenSoli", query = "SELECT i FROM InventarioSolicitud i WHERE i.idInvenSoli = :idInvenSoli")})
public class InventarioSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInvenSoli")
    private Integer idInvenSoli;
    @JoinColumn(name = "idInventario", referencedColumnName = "idInventario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventario idInventario;
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitud idSolicitud;

    public InventarioSolicitud() {
    }

    public InventarioSolicitud(Integer idInvenSoli) {
        this.idInvenSoli = idInvenSoli;
    }

    public Integer getIdInvenSoli() {
        return idInvenSoli;
    }

    public void setIdInvenSoli(Integer idInvenSoli) {
        this.idInvenSoli = idInvenSoli;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvenSoli != null ? idInvenSoli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioSolicitud)) {
            return false;
        }
        InventarioSolicitud other = (InventarioSolicitud) object;
        if ((this.idInvenSoli == null && other.idInvenSoli != null) || (this.idInvenSoli != null && !this.idInvenSoli.equals(other.idInvenSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.InventarioSolicitud[ idInvenSoli=" + idInvenSoli + " ]";
    }
    
}
