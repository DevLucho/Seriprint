/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;

/**
 *
 * @author Huertas
 */
@Named(value = "mensajeControlador")
@RequestScoped
public class MensajeControlador implements Serializable {

    /**
     * Creates a new instance of MensajeControlador
     */
    private String mensaje;

    public MensajeControlador() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
