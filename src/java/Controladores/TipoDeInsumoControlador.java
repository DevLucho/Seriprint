/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.TipoDeInsumo;
import Facade.TipoDeInsumoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author acer
 */
@Named(value = "tipoDeInsumoControlador")
@Dependent
public class TipoDeInsumoControlador implements Serializable {

    private TipoDeInsumo tipoDeInsumo;
    @EJB
    TipoDeInsumoFacade tipoDeInsumoFacade;
    public TipoDeInsumoControlador () {
        tipoDeInsumo = new TipoDeInsumo();
    }

    public TipoDeInsumo getTipoDeInsumo() {
        return tipoDeInsumo;
    }

    public void setTipoDeInsumo(TipoDeInsumo tipoDeInsumo) {
        this.tipoDeInsumo = tipoDeInsumo;
    }
    public List<TipoDeInsumo> consulta(){
        return tipoDeInsumoFacade.findAll();
    }
}
