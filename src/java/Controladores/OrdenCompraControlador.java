/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Cotizacion;
import Entidades.Inventario;
import Entidades.OrdenCompra;
import Entidades.Producto;
import Entidades.Usuario;
import Facade.CotizacionFacade;
import Facade.InventarioFacade;
import Facade.OrdenCompraFacade;
import Facade.ProductoFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchProviderException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.MessagingException;

/**
 *
 * @author Huertas
 */
@Named(value = "ordenCompraControlador")
@SessionScoped
public class OrdenCompraControlador implements Serializable {

    /**
     * Creates a new instance of OrdenCompraControlador
     */
    @Inject
    private MensajeControlador mensaje;
    private OrdenCompra ordenCompra;
    private Cotizacion cotizacion;
    CorreoControlador correo;
    Inventario inventario;
    Producto producto;
    private Usuario usuario;
    private boolean checked;
    @EJB
    InventarioFacade inventarioFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    OrdenCompraFacade ordenCompraFacade;
    @EJB
    CotizacionFacade cotizacionFacade;

    public OrdenCompraControlador() {
    }

    @PostConstruct
    public void init() {
        correo = new CorreoControlador();
        ordenCompra = new OrdenCompra();
        cotizacion = new Cotizacion();
        usuario = new Usuario();
        producto = new Producto();
        inventario = new Inventario();
        checked = false;
    }

    public void registrar(Cotizacion cotizacion) {
        ordenCompra.setIdCotizacion(cotizacionFacade.find(cotizacion.getIdCotizacion()));
        // Generar fecha actual al momento del pago
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date time = cal.getTime();
        ordenCompra.setFechaCompra(date);
        ordenCompra.setHoraCompra(time);
        ordenCompraFacade.create(ordenCompra);
        this.cotizacion = cotizacion;
        cotizacion.setEstado("Verificación de pago");
        cotizacionFacade.edit(cotizacion);
        this.ordenCompra = new OrdenCompra();
        this.cotizacion = new Cotizacion();
        mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Pago agregado!','Recuerda que tu cotización pasara a estado de verificación por parte del Administrador. Seras notificado cuando se apruebe','success');");
    }

    public String asignarOperario(OrdenCompra ordenCompras) {
        cotizacion = ordenCompras.getIdCotizacion();
        ordenCompra = ordenCompras;
        return "detalle-pago";
    }

    public void actualizar() {
        if (checked) {
            ordenCompra.setIdOperario(usuarioFacade.find(usuario.getIdUsuario()));
            ordenCompra.setIdCotizacion(cotizacionFacade.find(cotizacion.getIdCotizacion()));
            cotizacion.setEstado("Asignada");
            cotizacionFacade.edit(cotizacion);
            mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Se ha asignado la cotizacion al operario!','El estado de este a pasado a Asignada, por lo que pasara a producción.','success');");
        }
        ordenCompraFacade.edit(ordenCompra);
    }

    // Cambiar pedido a estado de produccion
    public void cambiarEstado(OrdenCompra ordenCompra) throws NoSuchProviderException, MessagingException {
        cotizacion = ordenCompra.getIdCotizacion();
        if ("Asignada".equals(cotizacion.getEstado())) {
            cotizacion.setEstado("Proceso de producción");
            cotizacionFacade.edit(cotizacion);
            correo.enviarEmail(cotizacion.getIdUsuario().getCorreoelectronico(), "Su pedido cambio de estado", ""+cotizacion.getNumFactura()+"");
        } else {
            mensaje.setMensaje("Mensaje3('Atención','Esta cotizacion se encuentra en estado: "+cotizacion.getEstado()+"','warning');");
        }
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
        // Igual para poder editar campos
        cotizacion = ordenCompra.getIdCotizacion();
        producto = ordenCompra.getIdCotizacion().getIdProducto();
        inventario = ordenCompra.getIdCotizacion().getIdProducto().getIdInventario();
        if (!"Entregado".equals(cotizacion.getEstado())) {
            cotizacion.setEstado("Entregado");
            cotizacionFacade.edit(cotizacion);
            // Descontar cantidad de inventario
            int cantInventario = cotizacion.getIdProducto().getCantidad();
            int cantCompra = cotizacion.getCantidad();
            int cant = cantInventario - cantCompra;
            producto.setCantidad(cant);
            if (cant <= 0) {
                mensaje.setMensaje("Mensaje3('Atención','El producto " + producto.getNombre() + " se encuentra Sin Stock ya que el pedido del cliente a dejado la cantidad de este en " + cant + ". Se ha notificado la compra.','warning');");
                productoFacade.edit(producto);
                inventario.setEstado("Sin stock");
                inventarioFacade.edit(inventario);
            } else {
                mensaje.setMensaje("Mensaje3('Exito','Se ha notificado satisfactoriamente la compra y han salido " + cant + " " + producto.getNombre() + " del inventario.','success');");
                productoFacade.edit(producto);
            }
        } else {
            mensaje.setMensaje("Mensaje3('Atención','Esta cotizacion ya ha sido entregada','warning');");
        }
    }

    public List<OrdenCompra> cotizacionEstado(String estado) {
        return ordenCompraFacade.cotizacionEstado(estado);
    }

    public List<OrdenCompra> consultarPorAsignado(int idUsuario) {
        return ordenCompraFacade.consultarPorAsignado(idUsuario);
    }

    public List<OrdenCompra> cotizacionUsuario(int idUsuario) {
        return ordenCompraFacade.cotizacionUsuario(idUsuario);
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

}
