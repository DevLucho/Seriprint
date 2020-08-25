/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.AgregarPago;
import Entidades.Cotizacion;
import Facade.AgregarPagoFacade;
import Facade.CotizacionFacade;
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
@Named(value = "agregarPagoControlador")
@SessionScoped
public class AgregarPagoControlador implements Serializable {

    /**
     * Creates a new instance of AgregarPagoControlador
     */
    @Inject
    private MensajeControlador mensaje;
    private AgregarPago agregarPago;
    private Cotizacion cotizacion;
    @EJB
    AgregarPagoFacade agregarPagoFacade;
    @EJB
    CotizacionFacade cotizacionFacade;

    public AgregarPagoControlador() {
    }

    @PostConstruct
    public void init() {
        agregarPago = new AgregarPago();
        cotizacion = new Cotizacion();
    }

    public void registrar(Cotizacion cotizacion) {
        agregarPago.setIdCotizacion(cotizacionFacade.find(cotizacion.getIdCotizacion()));
        // Generar fecha actual al momento del pago
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        agregarPago.setFecha(date);
        agregarPagoFacade.create(agregarPago);
        this.cotizacion = cotizacion;
        cotizacion.setEstado("Verificación de pago");
        cotizacionFacade.edit(cotizacion);
        this.agregarPago = new AgregarPago();
        this.cotizacion = new Cotizacion();
        mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Pago agregado!','Recuerda que tu cotización pasara a estado de verificación por parte del Administrador. Seras notificado cuando se apruebe','success');");

    }
    
    public String asignarOperario(AgregarPago agregarPagos){
        cotizacion = agregarPagos.getIdCotizacion();
        agregarPago = agregarPagos;
        return "detalle-pago";
    }

    public List<AgregarPago> cotizacionEstado(String estado) {
        return agregarPagoFacade.cotizacionEstado(estado);
    }

    public AgregarPago getAgregarPago() {
        return agregarPago;
    }

    public void setAgregarPago(AgregarPago agregarPago) {
        this.agregarPago = agregarPago;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

}
