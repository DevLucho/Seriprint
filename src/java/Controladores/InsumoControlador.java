/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Insumo;
import Entidades.TipoDeInsumo;
import Facade.InsumoFacade;
import Facade.TipoDeInsumoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author acer
 */
@Named(value = "insumoControlador")
@SessionScoped
public class InsumoControlador implements Serializable {

    private Insumo insumo;
    private TipoDeInsumo tipoDeInsumo;
    @EJB
    InsumoFacade insumoFacade;
    @EJB
    TipoDeInsumoFacade tipoDeInsumoFacade;

    public InsumoControlador() {
        tipoDeInsumo = new TipoDeInsumo();
        insumo = new Insumo();
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public TipoDeInsumo getTipoDeInsumo() {
        return tipoDeInsumo;
    }

    public void setTipoDeInsumo(TipoDeInsumo tipoDeInsumo) {
        this.tipoDeInsumo = tipoDeInsumo;
    }

    public void registrar() {
        insumo.setIdTipoinsumo(tipoDeInsumoFacade.find(tipoDeInsumo.getIdTipoinsumo()));
        insumoFacade.create(insumo);
        tipoDeInsumo = new TipoDeInsumo();
        insumo = new Insumo();
    }

    public String preactualizar(Insumo insumoActualizar) {
        tipoDeInsumo = insumoActualizar.getIdTipoinsumo();
        insumo = insumoActualizar;
        return "editarInsumo";
    }

    public String actualizar() {
        insumo.setIdTipoinsumo(tipoDeInsumoFacade.find(tipoDeInsumo.getIdTipoinsumo()));
        insumoFacade.edit(insumo);
        return "Insumo";
    }

    public void eliminar(Insumo insumo) {
        this.insumo = insumo;
        insumoFacade.remove(insumoFacade.find(insumo.getIdInsumo()));
    }

    public List<Insumo> consultarTodos() {
        return insumoFacade.findAll();
    }
}
