/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Bodega;
import Entidades.Insumo;
import Entidades.Inventario;
import Entidades.Producto;
import Entidades.Usuario;
import Facade.BodegaFacade;
import Facade.InsumoFacade;
import Facade.InventarioFacade;
import Facade.ProductoFacade;
import Facade.UsuarioFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author acer
 */
@Named(value = "inventarioControlador")
@SessionScoped
public class InventarioControlador implements Serializable {

    private Inventario inventario;
    private Producto producto;
    private Insumo insumo;
    private Usuario usuario;
    private Bodega bodega;
    private int opcion;

    @EJB
    InventarioFacade inventarioFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    InsumoFacade insumoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    BodegaFacade bodegaFacade;

    public InventarioControlador() {
        inventario = new Inventario();
        producto = new Producto();
        insumo = new Insumo();
        usuario = new Usuario();
        bodega = new Bodega();
        usuario = new Usuario();
    }

    public void registar() {
        inventario.setIdBodega(bodegaFacade.find(bodega.getIdBodega()));
        inventario.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        inventario.setFechaingreso(date);
        inventario.setEstado("Stock");
        if (this.opcion == 1) {
            inventario.setIdProducto(productoFacade.find(producto.getIdProducto()));
        } else if (this.opcion == 2) {
            inventario.setIdInsumo(insumoFacade.find(insumo.getIdInsumo()));
        }
        inventarioFacade.create(inventario);
        inventario = new Inventario();
        producto = new Producto();
        insumo = new Insumo();
        usuario = new Usuario();
        bodega = new Bodega();
        usuario = new Usuario();
    }

    public List<Inventario> consultarTodos() {
        return inventarioFacade.findAll();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
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

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
