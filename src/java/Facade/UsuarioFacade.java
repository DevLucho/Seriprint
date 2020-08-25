/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Huertas
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SeriprintPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> consultarEstado(String estado) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    // Login
    public Usuario iniciarSesion(int ndocumento, String contrasena) {
        Usuario usuario = new Usuario();
        try {
            Query query;
            query = em.createQuery("SELECT u FROM Usuario u WHERE u.ndocumento=:ndocumento and u.contrasena=:contrasena");
            query.setParameter("ndocumento", ndocumento);
            query.setParameter("contrasena", contrasena);
            usuario = (Usuario) query.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("Error en login revisar: " + e.getMessage());
        }
        return usuario;
    }

    // Variable de sesion
    public List<Usuario> sesionUsuario(int idUsuario) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.idUsuario=:idUsuario");
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    // Consultar usuario por rol
    public List<Usuario> consultarUsuarioPorRol(int idRol) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.idRol.idRol=:idRol");
        query.setParameter("idRol", idRol);
        return query.getResultList();
    }

}
