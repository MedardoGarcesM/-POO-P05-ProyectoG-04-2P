/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import com.mycompany.proyectog4parcial2.modelo.Mascota;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class CiudadController{


    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtProvincia;
    @FXML
    private Button botonAggCiudad;
    @FXML
    private Button botonCancelCrea;
    /**
     * Initializes the controller class.
     */ 

    @FXML
    private void cancelCreacion() throws IOException {
        App.setRoot("admCiudad");
    }
    
    @FXML
    private void switchToEditarCiu() throws IOException {
        App.setRoot("editarCiudad");
    }
    
    
    @FXML
    private void guardarCiudad() {
        
        int posicion=0;
        ArrayList<Ciudad> ciudades = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<Integer> idCiudades = new ArrayList<>();
        
        for(Ciudad c:ciudades){
            idCiudades.add((Integer) Integer.parseInt(c.getCodigo()));
        }
     
        System.out.println("Añadiendo ciudad");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        Ciudad ciu = new Ciudad(String.valueOf(Collections.max(idCiudades)+1),txtNombre.getText(),txtProvincia.getText());
        ciudades.add(ciu);//agregar empleado a la lista
        System.out.println("Nueva ciudad:" + ciu);
        
        
        try {
            FileWriter writer = new FileWriter(App.pathCiudades);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Ciudad c1:ciudades){
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(c1.getCodigo()+","+c1.getNombreC()+","+c1.getProvincia());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de confirmacion");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Nueva ciudad agregado correctamente");

            alert.showAndWait();
            App.setRoot("admCiudad");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
