/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class EditarDuenoController{


    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtApelli;
    @FXML
    private TextField txtDirecc;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox cmbCiu;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCedula;
    @FXML
    private Button btguardar;
    @FXML
    private Button btcancel;
    /**
     * Initializes the controller class.
     */ 
    
    
    @FXML
    private void guardarEdicion() {
        
        int posicion = 0;
        int posBuscad = 0;
        ArrayList<Dueno> duenos = Dueno.cargarDuenos(App.pathDuenos);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        
        System.out.println("Editando empleado");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        Dueno deditado = new Dueno(txtCedula.getText(),txtNom.getText(),txtApelli.getText(),txtDirecc.getText(),txtTelefono.getText(),(Ciudad) cmbCiu.getValue(),txtEmail.getText());
        System.out.println("Empleado editado:" + deditado);
        for(Dueno d:duenos){
            if(deditado.getCedula().equals(d.getCedula())){
                posBuscad=duenos.indexOf(d);
            }
        }
        
        duenos.set(posBuscad, deditado);
        
        
        try {
            //"src/main/resources/"+
            FileWriter writer = new FileWriter(App.pathDuenos);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for(Dueno m:duenos){
                for(Ciudad c:lisCiu){
                    if(m.getCiudad().getNombreC().equals(c.getNombreC())){
                        posicion=lisCiu.indexOf(c);
                    }
                }
                Ciudad ciu = lisCiu.get(posicion);
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(m.getCedula()+","+m.getApellidos()+","+m.getNombres()+","+m.getDireccion()+","+m.getTelefono()+","+ciu+","+m.getEmail());
                bufferedWriter.newLine(); 
            }
            bufferedWriter.close();
            
            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Dueno editado exitosamente");

            alert.showAndWait();            
            App.setRoot("admDuenos");
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    @FXML
    private void switchToAdmDueno() throws IOException {
        App.setRoot("admDuenos");
    }
    
    public void llenarCombo(ArrayList<Ciudad> ciudades) {
        cmbCiu.getItems().setAll(ciudades);
    }
    
    public void llenarCampos(Dueno d){
        lblTitulo.setText("Editar Dueno");
        txtCedula.setEditable(false);
        txtCedula.setText(d.getCedula());
        txtNom.setText(d.getNombres());
        txtApelli.setText(d.getApellidos());
        txtDirecc.setText(d.getDireccion());
        txtTelefono.setText(d.getTelefono());
        txtEmail.setText(d.getEmail());
        cmbCiu.setValue(d.getCiudad());
    }   

}
