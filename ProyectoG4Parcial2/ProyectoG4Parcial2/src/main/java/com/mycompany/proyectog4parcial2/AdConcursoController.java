/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;
import com.mycompany.proyectog4parcial2.modelo.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Correo;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdConcursoController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Concurso> adConcurso;

    @FXML
    private TableColumn<Concurso, String> colCod;

    @FXML
    private TableColumn<Concurso, String> colNombres;

    @FXML
    private TableColumn<Concurso, String> colFecha;
    @FXML
    private TableColumn<Concurso, String> colCiudad;
    @FXML
    private TableColumn<Concurso, String> colCorreo;
    
    private Button crearconcursoadC;
    private Button editarConcurso;
    private Button elimConcurso;
    private Button enviarCorreo;
    private Button menuP;

    public void initialize(){
       
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEvento"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        //colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        adConcurso.getItems().setAll(Concurso.crearArchivo());    }    
    @FXML
    private void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void switchToCrearConcurso() throws IOException {
        App.setRoot("concurso");
    }

    @FXML
    private void probarCorreo() {
        System.out.println("Enviar correo");
        String destinatario = "mjmoyano@espol.edu.ec";//agregar a todos los usuarios tipo dueño
        String asunto = "Invitacion a un nuevo concurso";
        String cuerpo = "Cuerpo de email.  Saludos";
        Correo.enviarCorreo(destinatario, asunto, cuerpo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);//mostrar informacion sobre el correo a enviar
        alert.setTitle("Informacion del correo");
        alert.setHeaderText("Enviar correo");
        alert.setContentText("Enviando correo....");
        alert.setContentText("Correo enviado!");
        alert.showAndWait();///

        System.out.println("Correo enviado!");
    }
}
