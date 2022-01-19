/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView imagen;
    @FXML
    private ImageView imagenPrincipal;
    
    @FXML
    private void switchToAdmCiudad() throws IOException {
        App.setRoot("admCiudad");
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
    
    public void initialize() throws IOException{
        Image imageP = new Image(MenuController.class.getResource("ImagenesMascotas/fundacion.jpeg").openStream(), 1000, 600, false, false);
        imagenPrincipal.setImage(imageP);
        //imagen
        Image imageS = new Image(MenuController.class.getResource("ImagenesMascotas/anuncio.jpeg").openStream(), 1500,1000, false, false);
        imagen.setImage(imageS);
    }
    
}
