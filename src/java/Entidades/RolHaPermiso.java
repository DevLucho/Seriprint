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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "roles_has_permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolHaPermiso.findAll", query = "SELECT r FROM RolHaPermiso r")
    , @NamedQuery(name = "RolHaPermiso.findByIdRoleshaspermisos", query = "SELECT r FROM RolHaPermiso r WHERE r.idRoleshaspermisos = :idRoleshaspermisos")})
public class RolHaPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRoles_has_permisos")
    private Integer idRoleshaspermisos;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol idRol;
    @JoinColumn(name = "idPermisos", referencedColumnName = "idPermisos")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permiso idPermisos;

    public RolHaPermiso() {
    }

    public RolHaPermiso(Integer idRoleshaspermisos) {
        this.idRoleshaspermisos = idRoleshaspermisos;
    }

    public Integer getIdRoleshaspermisos() {
        return idRoleshaspermisos;
    }

    public void setIdRoleshaspermisos(Integer idRoleshaspermisos) {
        this.idRoleshaspermisos = idRoleshaspermisos;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Permiso getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(Permiso idPermisos) {
        this.idPermisos = idPermisos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoleshaspermisos != null ? idRoleshaspermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolHaPermiso)) {
            return false;
        }
        RolHaPermiso other = (RolHaPermiso) object;
        if ((this.idRoleshaspermisos == null && other.idRoleshaspermisos != null) || (this.idRoleshaspermisos != null && !this.idRoleshaspermisos.equals(other.idRoleshaspermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.RolHaPermiso[ idRoleshaspermisos=" + idRoleshaspermisos + " ]";
    }
    
}
