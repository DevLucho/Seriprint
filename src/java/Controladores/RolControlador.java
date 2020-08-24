
package Controladores;

import Entidades.Rol;
import Facade.RolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author acer
 */
@Named(value = "rolControlador")
@SessionScoped
public class RolControlador implements Serializable {

    private Rol rol;
    @EJB
    RolFacade rolFacade;
    public RolControlador() { 
        rol = new Rol();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public List<Rol> consulta(){
    return rolFacade.findAll();
    }
    
}
