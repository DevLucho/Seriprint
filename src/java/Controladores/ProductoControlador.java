package Controladores;

import Entidades.Producto;
import Entidades.TipoDeProducto;
import Facade.ProductoFacade;
import Facade.TipoDeProductoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author acer
 */
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    private Producto producto;
    private TipoDeProducto tipoDeProducto;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    TipoDeProductoFacade tipoDeProductoFacade;

    public ProductoControlador() {
        tipoDeProducto = new TipoDeProducto();
        producto = new Producto();
    }

    public void registrar() {
        producto.setIdTipoproducto(tipoDeProductoFacade.find(tipoDeProducto.getIdTipoproducto()));
        productoFacade.create(producto);
        producto = new Producto();
        tipoDeProducto = new TipoDeProducto();
    }

    public String preactualizar(Producto productoActualizar) {
        tipoDeProducto = productoActualizar.getIdTipoproducto();
        producto = productoActualizar;
        return "editarProducto";
    }

    public String actualizar() {
        producto.setIdTipoproducto(tipoDeProductoFacade.find(tipoDeProducto.getIdTipoproducto()));
        productoFacade.edit(producto);
        return "Producto";
    }

    public String detalleProducto(Producto detalleProducto) {
        this.tipoDeProducto = detalleProducto.getIdTipoproducto();
        this.producto = detalleProducto;
        return "detalle-producto";
    }

    public void eliminar(Producto producto) {
        this.producto = producto;
        productoFacade.remove(productoFacade.find(producto.getIdProducto()));
    }

    public List<Producto> consultarTodos() {
        return productoFacade.findAll();
    }

    public List<TipoDeProducto> consultarTipoP() {
        return tipoDeProductoFacade.findAll();
    }

    //------------------ Metodos Get y Set -----------------------------
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TipoDeProducto getTipoDeProducto() {
        return tipoDeProducto;
    }

    public void setTipoDeProducto(TipoDeProducto tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }
}
