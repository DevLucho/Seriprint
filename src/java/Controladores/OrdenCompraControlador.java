/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Cotizacion;
import Entidades.OrdenCompra;
import Facade.CotizacionFacade;
import Facade.OrdenCompraFacade;
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
    @EJB
    OrdenCompraFacade ordenCompraFacade;
    @EJB
    CotizacionFacade cotizacionFacade;

    public OrdenCompraControlador() {
    }

    @PostConstruct
    public void init() {
        ordenCompra = new OrdenCompra();
        cotizacion = new Cotizacion();
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

    public List<OrdenCompra> cotizacionEstado(String estado) {
        return ordenCompraFacade.cotizacionEstado(estado);
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

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

}
