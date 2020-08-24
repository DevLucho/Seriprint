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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

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
    private List<SelectItem> cantidad; // lista para almacenar num 1-200
    // Generar numero de factura aleatorio
    Random rnd = new Random();
    String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numfactura = "";
    int pos = 0, num;

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

    // Generar cotizacion
    public void registrar(int tipoCotizacion, double precioUnidad) {
        // Generar fecha y hora actual al momento del registro
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Date time = cal.getTime();
        // Calcular precio total
        double precioTotalP = cotizacion.getCantidad() * precioUnidad;
        // Generar num factura
        pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
        num = (int) (rnd.nextDouble() * 9999 + 1000);
        // Estructura num factura
        this.numfactura = this.numfactura + this.abecedario.charAt(pos)
                + this.abecedario.charAt(pos + 2) + num + this.abecedario.charAt(pos - 1)
                + this.abecedario.charAt(pos - 2) + this.abecedario.charAt(pos);
        // Setear valores
        cotizacion.setIdUsuario(usuarioFacade.find(4));
        cotizacion.setHora(time);
        cotizacion.setFecha(date);
        cotizacion.setEstado("Pendiente");
        cotizacion.setPrecioCompra(precioTotalP);
        cotizacion.setNumFactura(numfactura);
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

    // Metodo para registrar y mostrar 200 numeros 
    public List<SelectItem> cantidadProducto() {
        this.cantidad = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            this.cantidad.add(new SelectItem(i, "" + i + ""));
        }
        return this.cantidad;
    }

    // Calcular precio dependiendo de la cantidad elegida
    public String calcularPrecio(int cantidadProducto, double precioUnidad) {
        double precioF = precioUnidad;
        for (int i = 1; i <= 200; i++) {
            precioF = cantidadProducto * precioUnidad;
        }
        return String.format("$%,.2f", precioF);
    }

    // Metodo para mostrar precio en pesos.
    public String getPrecioUnidad(Double precioUnidad) {
        return String.format("$%,.2f", precioUnidad);
    }

    // Consultar cotizacion
    public List<Cotizacion> consultarTodos() {
        return cotizacionFacade.findAll();
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

}
