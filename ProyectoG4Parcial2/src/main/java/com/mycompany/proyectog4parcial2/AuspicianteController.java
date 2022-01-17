/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
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
public class AuspicianteController{


    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDirecc;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPagWeb;
    @FXML
    private Button botonCancAgg;
    @FXML
    private Button botonMenuP;
    /**
     * Initializes the controller class.
     */ 
    
    @FXML
    private void guardarAuspiciante() {
        
        int posicion=0;
        ArrayList<Auspiciante> auspiciantes = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
        ArrayList<Integer> idAuspiciantes = new ArrayList<>();
        
        for(Auspiciante a:auspiciantes){
            idAuspiciantes.add((Integer) Integer.parseInt(a.getCodigoA()));
        }
     
        System.out.println("Añadiendo auspiciante");
        //String codigoA,String nombreA, String direccionA, String telefonoA, String ciudadA, String emailA, String webPage
        Auspiciante auspNuevo = new Auspiciante(String.valueOf(Collections.max(idAuspiciantes)+1),txtNom.getText(),txtDirecc.getText(),txtTel.getText(),txtCiudad.getText(),txtEmail.getText(),txtPagWeb.getText());
        auspiciantes.add(auspNuevo);//agregar empleado a la lista
        System.out.println("Nuevo auspiciante:" + auspNuevo);
        
        
        try {
            //FileWriter writer = new FileWriter("src/main/resources/"+App.pathAuspiciantes);
            FileWriter writer = new FileWriter(App.pathAuspiciantes);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Auspiciante a1:auspiciantes){
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(a1.getCodigoA()+","+a1.getNombreA()+","+a1.getDireccionA()+","+a1.getTelefonoA()+","+a1.getCiudadA()+","+a1.getEmailA()+","+a1.getWebPage());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de confirmacion");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Nuevo auspiciante agregado correctamente");

            alert.showAndWait();
            App.setRoot("admAuspiciante");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void switchToAdmAusp() throws IOException {
        App.setRoot("admAuspiciante");
    }

    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

}
