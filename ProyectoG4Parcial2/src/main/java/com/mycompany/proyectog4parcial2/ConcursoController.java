package com.mycompany.proyectog4parcial2;

import Main.Sistema;
import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class ConcursoController {

    @FXML
    private ComboBox< String> CCDirigidoa;

    @FXML
    private TextField CCnombre;

    @FXML
    private TextField CChora;

    @FXML
    private TextField CClugar;

    @FXML
    private DatePicker CCinicioInscripcion;

    @FXML
    private DatePicker CCcierreInscripcion;

    @FXML
    private ComboBox CCciudad;

    @FXML
    private ComboBox CCAuspiciantes;

    @FXML
    private SplitMenuButton CCPremiosLugar;
    @FXML
    private TextField CCcod;

    @FXML
    private MenuButton CCPremioDescripcion;

    @FXML
    private MenuButton CCPremiosAuspiciantes;

    @FXML
    private Button CCAñadirPremio;

    @FXML
    private Button CCguardar;

    @FXML
    private Button CCCancelar;

    @FXML
    private Button menuP;

    @FXML
    private void initialize() {

// setea las ciudades en el cmb
        CCciudad.getItems().setAll(Ciudad.cargarCiudades(App.pathCiudades));

// setea a quien va dirigido en el cmb
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Perro", "Gato");
        CCDirigidoa.getItems().setAll(items);

// Setea a los auspiciantes recorriendo la lista de auspiciantes
        for (Auspiciante a : Auspiciante.generarAus()) {
            CCAuspiciantes.getItems().addAll(a.getNombreA());
            MenuItem A1 = new MenuItem(a.getNombreA());
            CCPremiosAuspiciantes.getItems().add(A1);
        }

//Setea los premios
        MenuItem m1 = new MenuItem("Primer lugar");
        MenuItem m2 = new MenuItem("Segundo lugar");
        MenuItem m3 = new MenuItem("Tercer lugar");
        CCPremiosLugar.getItems().add(m1);
        CCPremiosLugar.getItems().add(m2);
        CCPremiosLugar.getItems().add(m3);

        // setea la descricion
        MenuItem n1 = new MenuItem("$1500");
        MenuItem n2 = new MenuItem("$750");
        MenuItem n3 = new MenuItem("$500");
        CCPremioDescripcion.getItems().add(n1);
        CCPremioDescripcion.getItems().add(n2);
        CCPremioDescripcion.getItems().add(n3);

        // setea los auspiciantes
    }

    @FXML
    void switchToAdmConcurso() throws IOException {
        App.setRoot("admConcurso");

    }

    @FXML
    void switchToAdmPremios() throws IOException {
        App.setRoot("premio");

    }

    @FXML
    void switchToMenuPrincipal() throws IOException {
        App.setRoot("menu");

    }

    @FXML
    private void guardarConcurso() {
        int posicion = 0;
        ArrayList<Concurso> concurso = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<String> gana = new ArrayList<>();
        ArrayList<Mascota> mas = new ArrayList<>();
        ArrayList<Auspiciante> auspiciante=Auspiciante.generarAus();
        String[] premio = new String[]{CCPremiosLugar.getText(), CCPremioDescripcion.getText(), CCPremiosAuspiciantes.getText()};
        String[] st = CChora.getText().split(":");
        LocalTime hora = LocalTime.of(Integer.parseInt(st[0]), Integer.parseInt(st[1]));
       for(Auspiciante a : auspiciante){
           if (CCAuspiciantes.getValue().equals(a.getNombreA())){
               posicion=auspiciante.indexOf(a);
           }
       }Auspiciante aaa = auspiciante.get(posicion);

        System.out.println("Creando concurso");
        //Concurso(String nombre, LocalDate fechaEvento, LocalTime horaEvento, LocalDate fechaInicioInscripción, LocalDate fechaCierreInscripción, Ciudad ciudad, String lugar, String[] premios, Auspiciante auspiciantes, String dirigido, String codigo, boolean concursoAbierto, ArrayList<Mascota> mascotasInscri, ArrayList<String> ganadores)
        Concurso c = new Concurso(CCnombre.getText(), LocalDate.of(2022, 1, 15), hora, CCinicioInscripcion.getValue(), CCcierreInscripcion.getValue(), (Ciudad) CCciudad.getValue(), CClugar.getText(), premio, aaa, CCDirigidoa.getValue(), CCcod.getText(), true, mas, gana);
        concurso.add(c);
        System.out.println("nuevo concurso " + c);

        try {
            FileWriter writer = new FileWriter("src/main/resources/"+App.pathConcurso, true);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
            bufferedWriter.write(CCcod.getText()+";"+CCnombre.getText()+";"+ LocalDate.of(2022, 1, 15)+";"+ hora+";"+ CCinicioInscripcion.getValue()+";"+CCcierreInscripcion.getValue()+";"+ (Ciudad) CCciudad.getValue()+";"+ CClugar.getText()+";"+ premio+";"+ aaa.getNombreA() +";"+ CCDirigidoa.getValue()+";"+  true+";"+ mas+";"+ gana);
            bufferedWriter.newLine();

            bufferedWriter.close();

            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje de confirmacion");
            alert.setHeaderText("Resultado de la operacion");
            alert.setContentText("Nuevo Concurso agregado correctamente");

            alert.showAndWait();
            App.setRoot("admConcurso");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
