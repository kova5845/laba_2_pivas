/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alexey
 */
public class Dialog {
    MenuBar menuBar;
    Menu rows;
    RadioMenuItem fiveRowsForPage;
    RadioMenuItem tenRowsForPage;
    RadioMenuItem twentyRowsForPage;
    Label nameLabel;
    Label surnameLabel;
    Label patronymicLabel;
    Label departmentLabel;
    TextField nameField;
    TextField surnameField;
    TextField patronymicField;
    ComboBox departmentBox;
    Button searchFIOofDepartmentButton;
    FlowPane searchFIOorDepartment;
    Label academicRankLabel;
    ComboBox academicRankBox;
    Label facultyLabel;
    ComboBox facultyBox;
    Button searchFacultyAndAcademicRankButton;
    FlowPane searchFacultyAndAcademicRank;
    Label experienceLabel;
    Label lowExperienceLabel;
    TextField lowExperienceField;
    Label heightExperienceLabel;
    TextField heightExperienceField;
    Button experienceButton;
    FlowPane searchExperience;
    VBox vBox;
    Scene scene;
    Stage window;
    Table table;
    Label kolAllNotes;

    
    public Dialog() {
        fiveRowsForPage = new RadioMenuItem("5");
        fiveRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(5);
        });
        tenRowsForPage = new RadioMenuItem("10");
        tenRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(10);
        });
        twentyRowsForPage = new RadioMenuItem("20");
        twentyRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(20);
        });
        rows = new Menu("rows");
        rows.getItems().addAll(fiveRowsForPage, tenRowsForPage, twentyRowsForPage);
        menuBar = new MenuBar(rows);
        nameLabel = new Label("имя");
        surnameLabel = new Label("фамилия");
        patronymicLabel = new Label("отчество");
        departmentLabel = new Label("кафедра");
        nameField = new TextField();
        surnameField = new TextField();
        patronymicField = new TextField();
        departmentBox = new ComboBox();
        searchFIOofDepartmentButton = new Button("поиск");
        searchFIOorDepartment = new FlowPane();
        searchFIOorDepartment.getChildren().addAll(nameLabel,
                nameField, surnameLabel, surnameField, patronymicLabel,
                patronymicField, departmentLabel, departmentBox,
                searchFIOofDepartmentButton);
        academicRankLabel = new Label("учёное звание");
        academicRankBox = new ComboBox();
        facultyLabel = new Label("факультет");
        facultyBox = new ComboBox();
        searchFacultyAndAcademicRankButton = new Button("поиск");
        searchFacultyAndAcademicRank = new FlowPane();
        searchFacultyAndAcademicRank.getChildren().addAll(
                facultyLabel, facultyBox, academicRankLabel, academicRankBox,
                searchFacultyAndAcademicRankButton);
        experienceLabel = new Label("опыт работы  ");
        lowExperienceLabel = new Label("от");
        lowExperienceField = new TextField();
        heightExperienceLabel = new Label("до");
        heightExperienceField = new TextField();
        experienceButton = new Button("поиск");
        searchExperience = new FlowPane();
        searchExperience.getChildren().addAll(experienceLabel,
                lowExperienceLabel, lowExperienceField, heightExperienceLabel,
                heightExperienceField, experienceButton);
        table = new Table();
        kolAllNotes = new Label();
        vBox = new VBox();
        vBox.getChildren().add(menuBar);
        vBox.getChildren().addAll(searchFIOorDepartment,
                searchFacultyAndAcademicRank, searchExperience);
        vBox.getChildren().addAll(table.pagination, kolAllNotes);
        scene = new Scene(vBox, 1000, 500);
        window = new Stage();
        window.setScene(scene);
    }
    
    public void addComboBox(ObservableList<Personal> data){
        ObservableSet<String> departmentList = FXCollections.observableSet();
        ObservableSet<String> facultyList = FXCollections.observableSet();
        ObservableSet<String> academicRankList = FXCollections.observableSet();
        for(Personal per : data){
            departmentList.add(per.getDepartment());
            facultyList.add(per.getFaculty());
            academicRankList.add(per.getAcademicRank());
        }
        departmentBox.setItems(FXCollections.observableArrayList(departmentList));
        departmentBox.setValue(departmentBox.getItems().get(0));
        facultyBox.setItems(FXCollections.observableArrayList(facultyList));
        facultyBox.setValue(facultyBox.getItems().get(0));
        academicRankBox.setItems(FXCollections.observableArrayList(academicRankList));
        academicRankBox.setValue(academicRankBox.getItems().get(0));
    }

    public void setPagin(ArrayList<Personal> data) {
        table.setData(data);
        kolAllNotes.setText("Количество записей: " + data.size());
    }

    public void showDialog(Stage primaryStage) {

        window.setTitle("Поиск");
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(primaryStage);
        window.show();
    }
    
    public void showAlert(int kol) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Было удалено "  + Integer.toString(kol) + " записей");
        alert.show();
    }
}
