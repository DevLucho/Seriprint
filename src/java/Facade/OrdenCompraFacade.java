/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.OrdenCompra;
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
public class OrdenCompraFacade extends AbstractFacade<OrdenCompra> {

    @PersistenceContext(unitName = "SeriprintPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenCompraFacade() {
        super(OrdenCompra.class);
    }

    public List<OrdenCompra> cotizacionEstado(String estado) {
        Query query = em.createQuery("SELECT u FROM OrdenCompra u WHERE u.idCotizacion.estado=:estado");
        query.setParameter("estado", estado);
        return query.getResultList();
    }

}
