/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class ConcursoController{

    /**
     * Initializes the controller class.
     */
    @FXML
    private void switchToAdmPremios() throws IOException {
        App.setRoot("premio");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menu");
    }
    
    @FXML
    private void switchToAdmConcurso() throws IOException {
        App.setRoot("admConcurso");
    }
    
}
