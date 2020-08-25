/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.AgregarPago;
import Entidades.Cotizacion;
import Entidades.Solicitud;
import Entidades.Usuario;
import Facade.AgregarPagoFacade;
import Facade.CotizacionFacade;
import Facade.SolicitudFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Huertas
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class SolicitudControlador implements Serializable {

    /**
     * Creates a new instance of SolicitudControlador
     */
    @Inject
    private MensajeControlador mensaje;
    private Solicitud solicitud;
    private Usuario usuario;
    private Cotizacion cotizacion;
    private AgregarPago agregarPago;
    private boolean checked;

    @EJB
    CotizacionFacade cotizacionFacade;
    @EJB
    SolicitudFacade solicitudFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    AgregarPagoFacade agregarPagoFacade;

    public SolicitudControlador() {
    }

    @PostConstruct
    public void init() {
        solicitud = new Solicitud();
        usuario = new Usuario();
        agregarPago = new AgregarPago();
        cotizacion = new Cotizacion();
        checked = false;
    }

    public void registrar(Cotizacion cotizacionE) {
        //agregarPagoFacade.edit(agregarPago);
        if (checked == true) {
            solicitud.setIdUsuario(usuarioFacade.find(usuario.getIdUsuario()));
            solicitud.setIdPedido(agregarPagoFacade.find(agregarPago.getIdPedido()));
            solicitudFacade.create(solicitud);
            cotizacion = cotizacionE;
            
            agregarPago.getIdCotizacion().setEstado("Asignada");
            agregarPagoFacade.edit(agregarPago);
            mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Cotización asignada','Se ha asignado satisfactoriamente la cotización','success');");
        }
        //mensaje.setMensaje("MensajeRedirect('./consultar-cotizacion.xhtml','Se ha actualizado el saldo.','Saldo actualizado','success');");
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AgregarPago getAgregarPago() {
        return agregarPago;
    }

    public void setAgregarPago(AgregarPago agregarPago) {
        this.agregarPago = agregarPago;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

}
