/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.InventarioPedido;
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
public class InventarioPedidoFacade extends AbstractFacade<InventarioPedido> {

    @PersistenceContext(unitName = "SeriprintPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioPedidoFacade() {
        super(InventarioPedido.class);
    }

    public List<InventarioPedido> consultarPorAsignado(int idUsuario) {
        Query query = em.createQuery("SELECT u FROM InventarioPedido u WHERE u.idUsuario.idUsuario=:idUsuario");
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

}
