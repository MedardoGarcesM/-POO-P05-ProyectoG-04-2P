/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class DetalleMascotaController{


    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNacimiento;
    @FXML
    private TextField txtRaza;
    @FXML
    private TextField txtDueno;
    @FXML
    private ImageView imagenMascota;
    @FXML
    private Button botonRegresar;
    /**
     * Initializes the controller class.
     */    
    
    @FXML
    private void switchToAdmMascota() throws IOException {
        App.setRoot("admMascotas");
    }
    
    public void llenarCamposM(Mascota m) throws IOException{
        txtNombre.setEditable(false);
        txtNombre.setText(m.getNombre());
        txtNacimiento.setEditable(false);
        txtNacimiento.setText(m.getFechaNacimiento());
        txtRaza.setEditable(false);
        txtRaza.setText(m.getRaza());
        txtDueno.setEditable(false);
        txtDueno.setText(m.getDueno().getNombres().split(" ")[0]+" "+m.getDueno().getApellidos().split(" ")[0]);
        Image imageP = new Image(MenuController.class.getResource(App.pathImg+""+m.getNombre()+".png").openStream(), 800, 600, false, false);
        imagenMascota.setImage(imageP);
    }

}
