/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Cotizacion;
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
public class CotizacionFacade extends AbstractFacade<Cotizacion> {

    @PersistenceContext(unitName = "SeriprintPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionFacade() {
        super(Cotizacion.class);
    }

    public List<Cotizacion> cotizacionUsuario(int idUsuario) {
        Query query;
        query = em.createQuery("SELECT u FROM Cotizacion u WHERE u.idUsuario.idUsuario=:idUsuario");
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    public List<Cotizacion> cotizacionEstado(String estado) {
        Query query = em.createQuery("SELECT u FROM Cotizacion u WHERE u.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

}
