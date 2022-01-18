package com.mycompany.proyectog4parcial2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.proyectog4parcial2.modelo.Auspiciante;
import com.mycompany.proyectog4parcial2.modelo.Ciudad;
import com.mycompany.proyectog4parcial2.modelo.Concurso;
import com.mycompany.proyectog4parcial2.modelo.Dueno;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class editarConcursoController {

    @FXML
    private Label labelTitulo;

    @FXML
    private TextField txtCodigo;

    @FXML
    private ComboBox CCDirigidoa;

    @FXML
    private TextField CCnombre;

    @FXML
    private TextField CClugar;

    @FXML
    private DatePicker CCinicioInscripcion;

    @FXML
    private DatePicker CCcierreInscripcion;
    @FXML
    private DatePicker CCfechaEvento;
    @FXML
    private ComboBox CCciudad;

    @FXML
    private SplitMenuButton CCPremiosLugar;

    @FXML
    private MenuButton CCPremioDescripcion;

    @FXML
    private MenuButton CCPremiosAuspiciantes;

    @FXML
    private ComboBox CCAuspiciantes;

    @FXML
    private TextField CChora;

    @FXML
    private Button btguardar;

    @FXML
    private Button btcancel;

    @FXML
    void guardarEdicion() {

    }

    @FXML
    void switchToAdmDueno() throws IOException {
        App.setRoot("AdmConcurso");

    }

    public void llenarCombo(ArrayList<Ciudad> ciudades) {
        CCciudad.getItems().setAll(ciudades);
    }

    public void llenarComboa(ArrayList<Auspiciante> auspiciantes) {
        for (Auspiciante a : Auspiciante.generarAus()) {
            CCAuspiciantes.getItems().addAll(a.getNombreA());
        }
    }

    public void llenarCombod() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Perro", "Gato");
        CCDirigidoa.getItems().setAll(items);
    }

    public void llenarCampos(Concurso d) {

        txtCodigo.setEditable(false);
        txtCodigo.setText(d.getCodigo());
        CCciudad.setValue(d.getCiudad());
        CCAuspiciantes.setValue(d.getAuspiciantes().getNombreA());
        CCnombre.setText(d.getNombre());
        CClugar.setText(d.getLugar());
        CCDirigidoa.setValue(d.getDirigido());
        CCinicioInscripcion.setValue(d.getFechaInicioInscripción());
        CCcierreInscripcion.setValue(d.getFechaCierreInscripción());
        CCfechaEvento.setValue(d.getFechaEvento());
        CChora.setText(d.getHoraEvento().toString());

    }

    /*private void guardarEdit() {

        int posicion = 0;
        int posBuscad = 0;
        ArrayList<Concurso> conc = Concurso.cargarArchivo(App.pathConcurso);//cargar la lista del archivo
        ArrayList<Ciudad> lisCiu = Ciudad.cargarCiudades(App.pathCiudades);
        ArrayList<Auspiciante> auspiciante= Auspiciante.generarAus();

        System.out.println("Editando Concurso");
        //Dueno(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
        String[] st = CChora.getText().split(":");
        LocalTime hora = LocalTime.of(Integer.parseInt(st[0]), Integer.parseInt(st[1]));
        for (Auspiciante a : auspiciante) {
            if (CCAuspiciantes.getValue().toString().equals(a.getNombreA())) {
                posicion = auspiciante.indexOf(a);
            }
        }
        Auspiciante aaa = auspiciante.get(posicion);
        

        System.out.println("Creando concurso");
        //Concurso(String nombre, LocalDate fechaEvento, LocalTime horaEvento, LocalDate fechaInicioInscripción, LocalDate fechaCierreInscripción, Ciudad ciudad, String lugar, String[] premios, Auspiciante auspiciantes, String dirigido, String codigo, boolean concursoAbierto, ArrayList<Mascota> mascotasInscri, ArrayList<String> ganadores)
        Concurso ceditado = new Concurso(CCnombre.getText(), LocalDate.of(2022, 1, 15), hora, CCinicioInscripcion.getValue(), CCcierreInscripcion.getValue(), (Ciudad) CCciudad.getValue(), CClugar.getText(), premio, aaa, CCDirigidoa.getValue(), txtCodigo.getText(), true, mas, gana);
        conc.add(ceditado);
        System.out.println(" concurso editador " + ceditado);
        for (Dueno d : duenos) {
            if (deditado.getCedula().equals(d.getCedula())) {
                posBuscad = duenos.indexOf(d);
            }
        }

        duenos.set(posBuscad, deditado);

        try {
            FileWriter writer = new FileWriter("src/main/resources/" + App.pathDuenos);//true significa que escribe al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Dueno m : duenos) {
                for (Ciudad c : lisCiu) {
                    if (m.getCiudad().getNombreC().equals(c.getNombreC())) {
                        posicion = lisCiu.indexOf(c);
                    }
                }
                Ciudad ciu = lisCiu.get(posicion);
                //(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email)
                bufferedWriter.write(m.getCedula() + "," + m.getApellidos() + "," + m.getNombres() + "," + m.getDireccion() + "," + m.getTelefono() + "," + ciu + "," + m.getEmail());
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

    }*/

}
