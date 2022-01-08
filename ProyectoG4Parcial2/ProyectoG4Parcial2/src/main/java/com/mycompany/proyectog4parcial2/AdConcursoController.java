/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectog4parcial2;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import utils.Correo;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class AdConcursoController{

    /**
     * Initializes the controller class.
     */
    @FXML
    private void switchToMenu() throws IOException {
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
