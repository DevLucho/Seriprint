/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.AgregarPago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Huertas
 */
@Stateless
public class AgregarPagoFacade extends AbstractFacade<AgregarPago> {

    @PersistenceContext(unitName = "SeriprintPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgregarPagoFacade() {
        super(AgregarPago.class);
    }
    
}
