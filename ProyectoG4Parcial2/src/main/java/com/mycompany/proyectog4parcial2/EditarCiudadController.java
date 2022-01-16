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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class EditarCiudadController{


    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtProvincia;
    @FXML
    private Button botonGuardarEdit;
    @FXML
    private Button botonCancelarEdit;
    @FXML
    private Label labelTitulo;
    @FXML
    private TextField txtCodigo;
    /**
     * Initializes the controller class.
     */    

    @FXML
    private void switchToAdmCiudad() throws IOException {
        App.setRoot("admCiudad");
    }
    
    public void llenarCampos(Ciudad c){
        labelTitulo.setText("Editar Ciudad");
        txtCodigo.setEditable(false);
        txtCodigo.setText(c.getCodigo());
        txtNombre.setText(c.getNombreC());
        txtProvincia.setText(c.getProvincia());
    } 

    @FXML
    private void guardarEdicion() {
        
        int posicion = 0;
        int posBuscad = 0;
        ArrayList<Ciudad> ciudades = Ciudad.cargarCiudades(App.pathCiudades);
        
        System.out.println("Editando ciudad");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        Ciudad ciuEditada = new Ciudad(txtCodigo.getText(),txtNombre.getText(),txtProvincia.getText());
        System.out.println("Empleado editado:" + ciuEditada);
        
        for(Ciudad c:ciudades){
            if(ciuEditada.getCodigo().equals(c.getCodigo())){
                posBuscad=ciudades.indexOf(c);
            }
        }
        
        ciudades.set(posBuscad, ciuEditada);
        
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/"+App.pathCiudades);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Ciudad c1:ciudades){
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(c1.getCodigo()+","+c1.getNombreC()+","+c1.getProvincia());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Ciudad editado exitosamente");

            alert.showAndWait();            
            App.setRoot("admCiudad");
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

}
