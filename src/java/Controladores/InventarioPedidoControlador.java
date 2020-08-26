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
    Inventario inventario;
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
            ordenCompraFacade.edit(ordenCompra);
            mensaje.setMensaje("Mensaje3('Exito!','Se ha agregado el abono del cliente.','success');");
        }
        this.cotizacion = new Cotizacion();
        ordenCompra = new OrdenCompra();
        inventarioPedido = new InventarioPedido();
        usuario = new Usuario();
        inventario = new Inventario();
        checked = false;
    }
    
    //Consultar por operario asignado
    public List<InventarioPedido> consultarPorAsignado(int idUsuario){
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

}
