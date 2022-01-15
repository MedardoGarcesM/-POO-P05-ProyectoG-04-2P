/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;


import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class PremioController{

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label LugarP;

    @FXML
    private Label descripcionP;

    @FXML
    private Label AuspiciantesP;

    @FXML
    private TextField PLugar;

    @FXML
    private TextField PDesc;

    @FXML
    private ComboBox PAuspiciante;

    @FXML
    private Button GuardarP;

    @FXML
    private Button cancelarP;
 @FXML
    private void initialize() {
         for (Auspiciante a : Auspiciante.generarAus()) {
            PAuspiciante.getItems().addAll(a.getNombreA());}
    }
    @FXML
    private void switchToCrearConcurso() throws IOException {
        App.setRoot("concurso");
    }
}
