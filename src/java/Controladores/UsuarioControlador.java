package Controladores;

import Entidades.Rol;
import Entidades.Usuario;
import Facade.RolFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author acer
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    private Usuario usuario;
    private Rol rol;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    RolFacade rolFacade;

    public UsuarioControlador() {
        rol = new Rol();
        usuario = new Usuario();
    }

    public void registrar() {
        usuario.setIdRol(rolFacade.find(rol.getIdRol()));
        usuario.setEstado("Activo");
        usuarioFacade.create(usuario);
        rol = new Rol();
        usuario = new Usuario();
    }

    public void cambiarEstado(Usuario usuario, String estado) {
        this.usuario = usuario;
        usuario.setEstado(estado);
        usuarioFacade.edit(usuario);
    }

    public String preactualizar(Usuario usuarioActualizar) {
        rol = usuarioActualizar.getIdRol();
        usuario = usuarioActualizar;
        return "editarUsuario";
    }

    public String actualizar() {
        usuario.setIdRol(rolFacade.find(rol.getIdRol()));
        usuarioFacade.edit(usuario);
        return "Usuario";
    }

    public List<Usuario> consultarTodos() {
        return usuarioFacade.findAll();
    }

    public List<Usuario> consultarEstado(String estado) {
        return usuarioFacade.consultarEstado(estado);
    }

    public void eliminar(Usuario usuario) {
        this.usuario = usuario;
        usuarioFacade.remove(usuarioFacade.find(usuario.getIdUsuario()));
    }
    
    //------------------ Metodos Get y Set -----------------------------

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
