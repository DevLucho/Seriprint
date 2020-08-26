
package Controladores;

import Entidades.TipoDeProducto;
import Facade.TipoDeProductoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author acer
 */
@Named(value = "tipoDeProducto")
@SessionScoped
public class TipoDeProductoControlador implements Serializable{

    private TipoDeProducto tipoDeProducto;
    @EJB
    TipoDeProductoFacade tipoDeProductoFacade;
    public TipoDeProductoControlador() {
        tipoDeProducto = new TipoDeProducto();
    }

    public TipoDeProducto getTipoDeProducto() {
        return tipoDeProducto;
    }

    public void setTipoDeProducto(TipoDeProducto tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }
    
    public List<TipoDeProducto> consulta(){
        return tipoDeProductoFacade.findAll();
    }
    
}
