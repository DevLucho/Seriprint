/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Cotizacion;
import Entidades.Estampado;
import Entidades.Producto;
import Entidades.Servicio;
import Entidades.Usuario;
import Facade.CotizacionFacade;
import Facade.EstampadoFacade;
import Facade.ProductoFacade;
import Facade.ServicioFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Huertas
 */
@Named(value = "cotizacionControlador")
@SessionScoped
public class CotizacionControlador implements Serializable {

    /**
     * Creates a new instance of CotizacionControlador
     */
    private Cotizacion cotizacion;
    private Usuario usuario;
    private Producto producto;
    private Servicio servicio;
    private Estampado estampado;
    private List<Integer> cantidad = new ArrayList<>();
    private int op;
    private double precioTotal;

    @EJB
    CotizacionFacade cotizacionFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    ProductoFacade productoFacade;
    @EJB
    ServicioFacade servicioFacade;
    @EJB
    EstampadoFacade estampadoFacade;

    public CotizacionControlador() {
    }

    @PostConstruct
    public void init() {
        cotizacion = new Cotizacion();
        servicio = new Servicio();
        usuario = new Usuario();
        producto = new Producto();
        estampado = new Estampado();
    }

    public void registrar(int tipoCotizacion) {
        cotizacion.setIdUsuario(usuarioFacade.find(4));

        // Generar fecha y hora actual al momento del registro
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date time = cal.getTime();
        cotizacion.setHora(time);
        cotizacion.setFecha(date);
        cotizacion.setEstado("Pendiente");
        cotizacion.setDetalle("eee");

        // Si es 1 Cotiza un producto, Si es 2 cotiza un estampado y sino cotiza un servicio :)
        switch (tipoCotizacion) {
            case 1:
                cotizacion.setIdProducto(productoFacade.find(producto.getIdProducto()));
                break;
            case 2:
                cotizacion.setEstampado(estampadoFacade.find(estampado.getIdEstampado()));
                break;
            default:
                cotizacion.setIdServicio(servicioFacade.find(servicio.getIdServicio()));
                break;
        }

        cotizacionFacade.create(cotizacion);
    }

    public List<Integer> cantidads() {
        for (int i = 1; i <= 200; i++) {
            this.cantidad.add(i);
        }
        return this.cantidad;
    }

    public double calcularPrecio(Producto producto) {
        precioTotal = producto.getPreciounidad();
        for (int i = 1; i <= 200; i++) {
            if (this.cantidad.get(i) == op) {
                precioTotal = precioTotal * op;
            }
        }
        return precioTotal;
    }

    //------------------ Metodos Get y Set -----------------------------
    public Cotizacion getCotizacion() {
        return cotizacion;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Estampado getEstampado() {
        return estampado;
    }

    public void setEstampado(Estampado estampado) {
        this.estampado = estampado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<Integer> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Integer> cantidad) {
        this.cantidad = cantidad;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public double getPrecioUnidad() {
        return precioTotal;
    }

    public void setPrecioUnidad(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
