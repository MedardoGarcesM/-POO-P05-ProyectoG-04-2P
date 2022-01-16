/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MenuController{

    @FXML
    private Button admCon;
    @FXML
    private Button admDue;
    @FXML
    private Button admMas;
    
    @FXML
    private Button admCiu;
    @FXML
    private Button admAuspiciantes;

    @FXML
    void switchToAdmCiudad() throws IOException {
        App.setRoot("AdmCiudad");

    }
    @FXML
    private void switchToAdmConcurso() throws IOException {
        App.setRoot("admConcurso");
    }

    @FXML
    private void switchToAdmDueno() throws IOException{
        App.setRoot("admDuenos");
    }

    @FXML
    private void switchToAdmMascota() throws IOException{
        App.setRoot("admMascotas");
    }
    
    @FXML
    private void switchToAdmAuspiciantes() throws IOException{
        App.setRoot("admAuspiciante");
    }
    
}
