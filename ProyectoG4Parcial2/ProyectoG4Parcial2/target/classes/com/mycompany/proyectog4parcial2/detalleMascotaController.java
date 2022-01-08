/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author majo_
 */
public class detalleMascotaController implements Initializable {


    @FXML
    private Button regresarDetalleMascota;
    @FXML
    private TextField nombreDetalleM;
    @FXML
    private TextField nacimientoDetalleM;
    @FXML
    private TextField razaDetalleM;
    @FXML
    private TextField dueñoDetalleM;
    @FXML
    private ImageView ivFotoMascota;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void switchToMascota()throws IOException {
        App.setRoot("Mascota");
    }

    File imgFile = fileChooser.showOpenDialog(null); //muestro la foto que escogio el usuario

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            System.out.println(imgFile.getAbsolutePath());
            ivFotoMascota.setImage(image);//muestro la foto en el image view
            //copiar la imagen
            Path from = Paths.get(imgFile.toURI());
            Path to = Paths.get("archivos/" + imgFile.getName());
            Files.copy(from, to);
        }
    }
    

