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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huertas
 */
@Entity
@Table(name = "anuncios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a")
    , @NamedQuery(name = "Anuncio.findByIdAnuncio", query = "SELECT a FROM Anuncio a WHERE a.idAnuncio = :idAnuncio")
    , @NamedQuery(name = "Anuncio.findByTitulo", query = "SELECT a FROM Anuncio a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "Anuncio.findByDescripcion", query = "SELECT a FROM Anuncio a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Anuncio.findByImg", query = "SELECT a FROM Anuncio a WHERE a.img = :img")})
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnuncio")
    private Integer idAnuncio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "img")
    private String img;

    public Anuncio() {
    }

    public Anuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Anuncio(Integer idAnuncio, String titulo, String descripcion, String img) {
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
    }

    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnuncio != null ? idAnuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anuncio)) {
            return false;
        }
        Anuncio other = (Anuncio) object;
        if ((this.idAnuncio == null && other.idAnuncio != null) || (this.idAnuncio != null && !this.idAnuncio.equals(other.idAnuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Anuncio[ idAnuncio=" + idAnuncio + " ]";
    }
    
}
