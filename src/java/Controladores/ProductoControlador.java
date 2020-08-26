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
import javax.inject.Inject;

/**
 *
 * @author acer
 */
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    @Inject
    private MensajeControlador mensaje;
    private Producto producto;
    private TipoDeProducto tipoDeProducto;
    private boolean checkdesc;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    TipoDeProductoFacade tipoDeProductoFacade;

    public ProductoControlador() {
        tipoDeProducto = new TipoDeProducto();
        producto = new Producto();
        checkdesc = false;
    }

    public void registrar() {
        producto.setIdTipoproducto(tipoDeProductoFacade.find(tipoDeProducto.getIdTipoproducto()));
        //descuento(producto);
        productoFacade.create(producto);
        /*
        if(checkdesc){
            
        }
         */
        mensaje.setMensaje("MensajeRedirect('./consultar-producto.xhtml','Producto creado','Se ha creado satisfactoriamente el producto " + producto.getNombre() + "','success');");
        producto = new Producto();
        tipoDeProducto = new TipoDeProducto();
    }

    public void descuento(Producto producto) {
        double precioTotal;
        this.producto = producto;
        precioTotal = ((producto.getPreciounidad() % producto.getDescuento()) * 100);
        producto.setPrecioventa(precioTotal);
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

    public boolean isCheckdesc() {
        return checkdesc;
    }

    public void setCheckdesc(boolean checkdesc) {
        this.checkdesc = checkdesc;
    }

}
