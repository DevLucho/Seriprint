package Controladores;

import Entidades.Bodega;
import Entidades.Inventario;
import Entidades.Producto;
import Entidades.TipoDeProducto;
import Entidades.Usuario;
import Facade.BodegaFacade;
import Facade.InventarioFacade;
import Facade.ProductoFacade;
import Facade.TipoDeProductoFacade;
import Facade.UsuarioFacade;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    @Inject
    private MensajeControlador mensaje;
    private Producto producto;
    private TipoDeProducto tipoDeProducto;
    private Inventario inventario;
    private Usuario usuario;
    private Bodega bodega;
    private boolean checkdesc;
    private double precioUnidad;
    private double precioVenta;
    private double descuento;
    // Generar fecha y hora del sistema
    private Calendar cal = Calendar.getInstance();
    private Date date = cal.getTime();

    @EJB
    ProductoFacade productoFacade;
    @EJB
    InventarioFacade inventarioFacade;
    @EJB
    TipoDeProductoFacade tipoDeProductoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    BodegaFacade bodegaFacade;

    public ProductoControlador() {
        tipoDeProducto = new TipoDeProducto();
        producto = new Producto();
        inventario = new Inventario();
        bodega = new Bodega();
        usuario = new Usuario();
        checkdesc = false;
    }

    public void registrar() {
        inventario.setIdBodega(bodegaFacade.find(bodega.getIdBodega()));
        inventario.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
        inventario.setFechaingreso(date);
        inventario.setEstado("Stock");
        inventarioFacade.create(inventario);
        producto.setIdInventario(inventario);
        producto.setIdTipoproducto(tipoDeProductoFacade.find(tipoDeProducto.getIdTipoproducto()));
        producto.setPreciounidad(precioUnidad);
        // Calcular precio venta
        if (checkdesc == false) {
            producto.setPrecioventa(precioUnidad);
        } else {
            double descuentoTotal = ((descuento / 100) * precioUnidad);
            producto.setPrecioventa(precioUnidad - descuentoTotal);
            producto.setDescuento(descuento);
        }
        productoFacade.create(producto);
        mensaje.setMensaje("MensajeRedirect('./consultar-producto.xhtml','Producto creado','Se ha creado satisfactoriamente el producto " + producto.getNombre() + "','success');");
        producto = new Producto();
        tipoDeProducto = new TipoDeProducto();
        inventario = new Inventario();
        bodega = new Bodega();
        usuario = new Usuario();
        precioUnidad = 0.0;
        descuento = 0.0;
        checkdesc = false;
    }

    public String getDescuentoFinal(double descuento) {
        DecimalFormat formato = new DecimalFormat("#");
        return formato.format(descuento)+"%";
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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
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

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

}
