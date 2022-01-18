package com.mycompany.proyectog4parcial2;

import com.mycompany.proyectog4parcial2.modelo.Concurso;
import java.io.BufferedReader;
import java.io.FileReader;
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
    /*
    private static Scene scene;
    public static String pathDuenos="com/mycompany/proyectog4parcial2/files/duenos.csv";
    public static String pathCiudades="com/mycompany/proyectog4parcial2/files/ciudades.csv";
    public static String pathMascotas="com/mycompany/proyectog4parcial2/files/mascotas.csv";//com/mycompany/proyectog4parcial2/files/mascotas.csv
    public static String pathAuspiciantes="com/mycompany/proyectog4parcial2/files/auspiciantes.csv";
    public static String pathConcurso="com/mycompany/proyectog4parcial2/files/concurso.txt";
    */
    public static String pathDuenos="documentos/duenos.csv";
    public static String pathCiudades="documentos/ciudades.csv";
    public static String pathMascotas="documentos/mascotas.csv";//com/mycompany/proyectog4parcial2/files/mascotas.csv
    public static String pathAuspiciantes="documentos/auspiciantes.csv";
    public static String pathConcurso="documentos/concurso.txt";
    public static String pathImg="ImagenesMascotas/";//

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"),600,400);
        //scene.getStylesheets().add(App.class.getResource("css/estilos.css").toExternalForm());//le agrega estilos a las interfaces
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
        
        for(Concurso con:Concurso.cargarArchivo(App.pathConcurso)){
            System.out.println(con);
        }
        launch();
    }
    
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }
    
    

}