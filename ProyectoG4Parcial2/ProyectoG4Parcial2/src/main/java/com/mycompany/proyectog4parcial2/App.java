package com.mycompany.proyectog4parcial2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathDuenos="com/mycompany/proyectog4parcial2/files/duenos.csv";
    public static String pathCiudades="com/mycompany/proyectog4parcial2/files/ciudades.csv";
    public static String pathMascotas="com/mycompany/proyectog4parcial2/files/mascotas.csv";//com/mycompany/proyectog4parcial2/files/mascotas.csv
    public static String pathConcurso="ProyectoG4Parcial2/concursos.csv";
    public static String pathImg="ImagenesMascotas/";//

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"),600,400);
        scene.getStylesheets().add(App.class.getResource("css/estilos.css").toExternalForm());//le agrega estilos a las interfaces
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        System.out.println("hola");
        launch();
    }
    
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }
    
    

}