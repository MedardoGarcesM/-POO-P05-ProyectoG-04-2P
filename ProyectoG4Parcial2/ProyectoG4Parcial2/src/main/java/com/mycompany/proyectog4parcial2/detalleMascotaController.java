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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author majo_
 */
public class detalleMascotaController {


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
    
    @FXML
    private void switchToMascota()throws IOException {
        App.setRoot("Mascota");
    }
    
    @FXML
    private void buscarArchivo() throws IOException {
       
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");//para abrir el explorador de archivo

        // Agregar filtros para facilitar la busqueda, solo busca archivos jpg o png
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File imgFile = fileChooser.showOpenDialog(null); //obtengo la foto que escogio el usuario

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            System.out.println(imgFile.getAbsolutePath());//recupero ruta de la imagen
            ivFotoMascota.setImage(image);//muestro la foto en el image view
            Path from = Paths.get(imgFile.toURI()); //copiar la imagen
            Path to = Paths.get("archivos/" + imgFile.getName());
            Files.copy(from, to);
        
} 
    }
}
        
        
    
    

