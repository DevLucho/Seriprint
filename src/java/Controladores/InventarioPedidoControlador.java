/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Cotizacion;
import Entidades.Inventario;
import Entidades.InventarioPedido;
import Entidades.OrdenCompra;
import Entidades.Usuario;
import Facade.CotizacionFacade;
import Facade.InventarioFacade;
import Facade.InventarioPedidoFacade;
import Facade.OrdenCompraFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Huertas
 */
@Named(value = "inventarioPedidoControlador")
@SessionScoped
public class InventarioPedidoControlador implements Serializable {

    /**
     * Creates a new instance of InventarioPedidoControlador
     */
    @Inject
    private MensajeControlador mensaje;
    private OrdenCompra ordenCompra;
    private InventarioPedido inventarioPedido;
    private Cotizacion cotizacion;
    private Usuario usuario;
    private Inventario inventario;
    private boolean checked;

    @EJB
    CotizacionFacade cotizacionFacade;
    @EJB
    OrdenCompraFacade ordenCompraFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    InventarioFacade invetarioFacade;
    @EJB
    InventarioPedidoFacade inventarioPedidoFacade;

    public InventarioPedidoControlador() {
    }

    @PostConstruct
    public void init() {
        cotizacion = new Cotizacion();
        ordenCompra = new OrdenCompra();
        inventarioPedido = new InventarioPedido();
        usuario = new Usuario();
        inventario = new Inventario();
        checked = false;
    }

    // Asignar operario a cotizacion
    public void registrar(Cotizacion cotizacion) {
        if (checked) {
            inventarioPedido.setIdPedido(ordenCompraFacade.find(ordenCompra.getIdPedido()));
            inventarioPedido.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
            inventarioPedidoFacade.create(inventarioPedido);
            this.cotizacion = cotizacion;
            cotizacion.setEstado("Asignada");
            cotizacionFacade.edit(cotizacion);
            mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Se ha asignado la cotizacion al operario!','El estado de este a pasado a Asignada, por lo que pasara a producción.','success');");
        } else {
            //ordenCompraFacade.edit(ordenCompra);
            mensaje.setMensaje("Mensaje3('Exito!','Se ha agregado el abono del cliente.','success');");
        }
        this.cotizacion = new Cotizacion();
        ordenCompra = new OrdenCompra();
        inventarioPedido = new InventarioPedido();
        usuario = new Usuario();
        inventario = new Inventario();
        checked = false;
    }

    // Metodo para validar si esta en stock el producto cotizado por el cliente
    public void validarProductoStock() {
        int cantidad;
        List<Inventario> productos;
        productos = invetarioFacade.findAll();
        int productoInvetario = inventario.getIdProducto().getIdProducto();
        int productoCotizacion = inventarioPedido.getIdPedido().getIdCotizacion().getIdProducto().getIdProducto();

        for (int i = 0; i < productos.size(); i++) {
            if (productoInvetario == productoCotizacion) {
                int cantidadCotizacion = inventarioPedido.getIdPedido().getIdCotizacion().getCantidad();
                int cantidadProducto = inventario.getIdProducto().getCantidad();
                if (cantidadCotizacion >= cantidadProducto) {
                    cantidad = (cantidadCotizacion - cantidadProducto);
                } else {
                    cantidad = (cantidadProducto - cantidadCotizacion);
                }
            }

        }
    }
    
    // Cambiar pedido a estado de produccion
    public void cambiarEstado(OrdenCompra ordenCompra) {
        cotizacion = ordenCompra.getIdCotizacion();
        cotizacion.setEstado("Proceso de producción");
        cotizacionFacade.edit(cotizacion);
    }
    
    // Metodo para generar fechas de salida y notificar el pedido
    public void notificarEntrega(OrdenCompra ordenCompra) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date time = cal.getTime();
        /*
        inventarioPedido.setFechaSalida(time);
        inventarioPedidoFacade.edit(inventarioPedido);
         */
        this.ordenCompra = ordenCompra;
        this.ordenCompra.setFechaEntrega(date);
        this.ordenCompra.setHoraEntrega(time);
        ordenCompraFacade.edit(this.ordenCompra);
        cotizacion = ordenCompra.getIdCotizacion();
        if (!"Entregado".equals(cotizacion.getEstado())) {
            cotizacion.setEstado("Entregado");
            cotizacionFacade.edit(cotizacion);
            mensaje.setMensaje("Mensaje3('Exito','Se ha notificado satisfactoriamente la compra.','warning');");
        } else {
            mensaje.setMensaje("Mensaje3('Atención','Esta cotizacion ya ha sido entregada','warning');");
        }
    }

    //Consultar por operario asignado
    public List<InventarioPedido> consultarPorAsignado(int idUsuario) {
        return inventarioPedidoFacade.consultarPorAsignado(idUsuario);
    }

    // Metodos get y set
    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public InventarioPedido getInventarioPedido() {
        return inventarioPedido;
    }

    public void setInventarioPedido(InventarioPedido inventarioPedido) {
        this.inventarioPedido = inventarioPedido;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

}
