/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Bodega;
import Entidades.Insumo;
import Entidades.Inventario;
import Entidades.Producto;
import Facade.BodegaFacade;
import Facade.InsumoFacade;
import Facade.InventarioFacade;
import Facade.ProductoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author acer
 */
@Named(value = "bodegaControlador")
@SessionScoped
public class BodegaControlador implements Serializable {
    
    private Bodega bodega;
    
    @EJB
    BodegaFacade bodegaFacade;
    
    public BodegaControlador() {
        bodega = new Bodega();
    }

    public void crearBodega() {
        bodegaFacade.create(bodega);
        bodega = new Bodega();
    }

    public void eliminar(Bodega bodega) {
        bodegaFacade.remove(bodega);
    }

    public List<Bodega> consultarTodos() {
        return bodegaFacade.findAll();
    }

    public Bodega getBodega() {
        return bodega;
    }
    
    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    } 
}
