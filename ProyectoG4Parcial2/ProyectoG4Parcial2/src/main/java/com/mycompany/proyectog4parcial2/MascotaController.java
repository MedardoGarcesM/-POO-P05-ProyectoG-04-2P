/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MascotaController{

    @FXML
    private Button guardarM;
    @FXML
    private Button cancelarM;
    @FXML
    private Button menuP;
    @FXML
    private TextField NombreM;
    @FXML
    private TextField razaM;
    @FXML
    private ComboBox<?> dueñoM;
    @FXML
    private RadioButton perroM;
    @FXML
    private ToggleGroup tipoMascota;
    @FXML
    private RadioButton gatoM;
    @FXML
    private DatePicker nacimientoM;
    @FXML
    private Button fotoM;

    /**
     * Initializes the controller class.
     */ 

    @FXML
    private void switchToMenuPrincipal() throws IOException{
        App.setRoot("menu");
    }
    
    @FXML
    private void switchToDetalleMascota() throws IOException{
        App.setRoot("detalleMascota");
    }
    
    @FXML
    private void switchToAdmMascota() throws IOException{
        App.setRoot("admMascotas");
    }

    @FXML
    private void buscarArchivo(ActionEvent event) {
    }
}
