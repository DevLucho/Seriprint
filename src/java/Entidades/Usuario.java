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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNdocumento", query = "SELECT u FROM Usuario u WHERE u.ndocumento = :ndocumento")
    , @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByCorreoelectronico", query = "SELECT u FROM Usuario u WHERE u.correoelectronico = :correoelectronico")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByFecha", query = "SELECT u FROM Usuario u WHERE u.fecha = :fecha")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ndocumento")
    private int ndocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono")
    private int telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Correo_electronico")
    private String correoelectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "Contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol idRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<Cotizacion> cotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<InventarioPedido> inventarioPedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<Inventario> inventarioList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, int ndocumento, String nombres, String apellidos, String direccion, int telefono, String correoelectronico, String contrasena, Date fecha, String estado) {
        this.idUsuario = idUsuario;
        this.ndocumento = ndocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(int ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @XmlTransient
    public List<InventarioPedido> getInventarioPedidoList() {
        return inventarioPedidoList;
    }

    public void setInventarioPedidoList(List<InventarioPedido> inventarioPedidoList) {
        this.inventarioPedidoList = inventarioPedidoList;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
