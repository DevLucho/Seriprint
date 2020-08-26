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
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author acer
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    @Inject
    private MensajeControlador mensaje;
    private Usuario usuario;
    private Usuario user = null;
    private Rol rol;
    Rol rolSeleccionado;
    // Datos inicio sesion
    private String documentoc;
    private int documento;
    private String contrasenia;
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

    public void eliminar(Usuario usuario) {
        this.usuario = usuario;
        usuarioFacade.remove(usuarioFacade.find(usuario.getIdUsuario()));
    }

    public List<Usuario> consultarTodos() {
        return usuarioFacade.findAll();
    }

    public List<Usuario> consultarEstado(String estado) {
        return usuarioFacade.consultarEstado(estado);
    }
    
    public List<Usuario> consultarUsuarioPorRol(int idRol){
        return usuarioFacade.consultarUsuarioPorRol(idRol);
    }

    // Iniciar sesion
    public String iniciarSesion() {
        user = usuarioFacade.iniciarSesion(documento = Integer.parseInt(documentoc), contrasenia);
        FacesContext fc = FacesContext.getCurrentInstance();
        if (user.getNdocumento() != 0 && user.getNdocumento() > 0 && contrasenia != null && !contrasenia.equals("")) {
            rolSeleccionado = user.getIdRol();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado", user);
            if ("Activo".equals(user.getEstado())) {
                if (rolSeleccionado.getIdRol() == 1) {
                    return "/pagos/consultar-cotizacion?faces-redirect=true";
                }
                if (rolSeleccionado.getIdRol() == 2) {
                    return "/pagos/consultar-cotizacion?faces-redirect=true";
                }
                // Si es cliente:
                return "/productos/consultar-producto?faces-redirect=true";
            } else {
                // Si no es activo ...
                mensaje.setMensaje("Mensaje2('Usuario bloqueado.','error');");
                return "";
            }

        } else {
            // Si no exite el usuario ...
            mensaje.setMensaje("Mensaje2('Usuario y/o contraseña incorrecto.','error');");
            return "";
        }
    }

    public String cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            this.usuario = null;
            this.documento = 0;
            this.contrasenia = "";
            //mensaje.setMensaje("MensajeAlertify('Logout exitoso','success');");
            //Thread.sleep(5 * 1000);
        } catch (Exception e) {
            System.out.println("Error en logout revisar: " + e.getMessage());
        }
        return "/index.xhtml?faces-redirect=true";
    }

    //Metodo para consultar usuario en la sesion
    public List<Usuario> sesionUsuario(int idUsuario) {
        return usuarioFacade.sesionUsuario(idUsuario);
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getDocumentoc() {
        return documentoc;
    }

    public void setDocumentoc(String documentoc) {
        this.documentoc = documentoc;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
