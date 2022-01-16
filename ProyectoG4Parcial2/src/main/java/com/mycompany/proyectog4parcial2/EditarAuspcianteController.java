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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class EditarAuspcianteController{

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
    private TextField txtCodigo;
    @FXML
    private Button botonGuardAu;
    @FXML
    private Button botonCancAgg;
    @FXML
    private Label labelTitulo;
    
    @FXML
    private void guardarAuspiciante() {
        
        int posicion = 0;
        int posBuscad = 0;
        ArrayList<Auspiciante> auspiciantes = Auspiciante.cargarAuspiciantes(App.pathAuspiciantes);
        
        System.out.println("Editando auspiciante");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        Auspiciante auspEditado = new Auspiciante(txtCodigo.getText(),txtNom.getText(),txtDirecc.getText(),txtTel.getText(),txtCiudad.getText(),txtEmail.getText(),txtPagWeb.getText());
        System.out.println("Auspiciante editado:" + auspEditado);
        
        for(Auspiciante au:auspiciantes){
            if(auspEditado.getCodigoA().equals(au.getCodigoA())){
                posBuscad=auspiciantes.indexOf(au);
            }
        }
        
        auspiciantes.set(posBuscad, auspEditado);
        
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/"+App.pathAuspiciantes);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Auspiciante a1:auspiciantes){
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(a1.getCodigoA()+","+a1.getNombreA()+","+a1.getDireccionA()+","+a1.getTelefonoA()+","+a1.getCiudadA()+","+a1.getEmailA()+","+a1.getWebPage());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Auspiciante editado exitosamente");

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

    /**
     * Initializes the controller class.
     */
    
    public void llenarCampos(Auspiciante auspiciante){
        labelTitulo.setText("Editar Auspciante");
        txtCodigo.setEditable(false);
        txtCodigo.setText(auspiciante.getCodigoA());
        txtNom.setText(auspiciante.getNombreA());
        txtDirecc.setText(auspiciante.getDireccionA());
        txtTel.setText(auspiciante.getTelefonoA());
        txtCiudad.setText(auspiciante.getCiudadA());
        txtEmail.setText(auspiciante.getEmailA());
        txtPagWeb.setText(auspiciante.getWebPage());
    }
    
}
