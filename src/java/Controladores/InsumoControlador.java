/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Bodega;
import Entidades.Insumo;
import Entidades.Inventario;
import Entidades.TipoDeInsumo;
import Entidades.Usuario;
import Facade.BodegaFacade;
import Facade.InsumoFacade;
import Facade.InventarioFacade;
import Facade.TipoDeInsumoFacade;
import Facade.UsuarioFacade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author acer
 */
@Named(value = "insumoControlador")
@SessionScoped
public class InsumoControlador implements Serializable {

    @Inject
    private MensajeControlador mensaje;
    private Insumo insumo;
    private TipoDeInsumo tipoDeInsumo;
    private Inventario inventario;
    private Usuario usuario;
    private Bodega bodega;
    // Generar fecha y hora del sistema
    private Calendar cal = Calendar.getInstance();
    private Date date = cal.getTime();
    @EJB
    InsumoFacade insumoFacade;
    @EJB
    TipoDeInsumoFacade tipoDeInsumoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    BodegaFacade bodegaFacade;
    @EJB
    InventarioFacade inventarioFacade;

    public InsumoControlador() {
        tipoDeInsumo = new TipoDeInsumo();
        insumo = new Insumo();
        inventario = new Inventario();
        bodega = new Bodega();
        usuario = new Usuario();
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public TipoDeInsumo getTipoDeInsumo() {
        return tipoDeInsumo;
    }

    public void setTipoDeInsumo(TipoDeInsumo tipoDeInsumo) {
        this.tipoDeInsumo = tipoDeInsumo;
    }

    public void registrar() {
        inventario.setIdBodega(bodegaFacade.find(bodega.getIdBodega()));
        inventario.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
        inventario.setFechaingreso(date);
        inventario.setEstado("Stock");
        inventarioFacade.create(inventario);
        insumo.setIdInventario(inventario);
        insumo.setIdTipoinsumo(tipoDeInsumoFacade.find(tipoDeInsumo.getIdTipoinsumo()));
        insumoFacade.create(insumo);
        mensaje.setMensaje("Mensaje('Insumo agregado!','Se ha adicionado el insumo al invetario.','success');");
        tipoDeInsumo = new TipoDeInsumo();
        insumo = new Insumo();
        inventario = new Inventario();
        bodega = new Bodega();
        usuario = new Usuario();
    }

    public String preactualizar(Insumo insumoActualizar) {
        tipoDeInsumo = insumoActualizar.getIdTipoinsumo();
        insumo = insumoActualizar;
        return "editarInsumo";
    }

    public void actualizar() {
        insumo.setIdTipoinsumo(tipoDeInsumoFacade.find(tipoDeInsumo.getIdTipoinsumo()));
        insumoFacade.edit(insumo);
        mensaje.setMensaje("MensajeRedirect('./Insumo.xhtml','Insumo modificado!','Se ha actualizado satisfactoriamente el insumo: "+insumo.getNombre()+"','success');");
    }

    public void eliminar(Insumo insumo) {
        this.insumo = insumo;
        insumoFacade.remove(insumoFacade.find(insumo.getIdInsumo()));
    }

    public List<Insumo> consultarTodos() {
        return insumoFacade.findAll();
    }

    public List<TipoDeInsumo> consultarTipoInsumo() {
        return tipoDeInsumoFacade.findAll();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
