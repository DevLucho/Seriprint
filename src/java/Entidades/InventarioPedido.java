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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "inventario_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioPedido.findAll", query = "SELECT i FROM InventarioPedido i")
    , @NamedQuery(name = "InventarioPedido.findByIdInvenSoli", query = "SELECT i FROM InventarioPedido i WHERE i.idInvenSoli = :idInvenSoli")
    , @NamedQuery(name = "InventarioPedido.findByFechaSalida", query = "SELECT i FROM InventarioPedido i WHERE i.fechaSalida = :fechaSalida")})
public class InventarioPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInvenSoli")
    private Integer idInvenSoli;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @JoinColumn(name = "idInventario", referencedColumnName = "idInventario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Inventario idInventario;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrdenCompra idPedido;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public InventarioPedido() {
    }

    public InventarioPedido(Integer idInvenSoli) {
        this.idInvenSoli = idInvenSoli;
    }

    public Integer getIdInvenSoli() {
        return idInvenSoli;
    }

    public void setIdInvenSoli(Integer idInvenSoli) {
        this.idInvenSoli = idInvenSoli;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Inventario getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Inventario idInventario) {
        this.idInventario = idInventario;
    }

    public OrdenCompra getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(OrdenCompra idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof InventarioPedido)) {
            return false;
        }
        InventarioPedido other = (InventarioPedido) object;
        if ((this.idInvenSoli == null && other.idInvenSoli != null) || (this.idInvenSoli != null && !this.idInvenSoli.equals(other.idInvenSoli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.InventarioPedido[ idInvenSoli=" + idInvenSoli + " ]";
    }
    
}
