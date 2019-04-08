/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alexey
 */
public class Dialog {

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
    TableView<Personal> table;
    ObservableList<Personal> data = FXCollections.observableArrayList();
    Pagination pagination;
    int rowsPerPage = 5;

    public Dialog() {
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
        table = new TableView<>();
        table.setMinSize(711, 300);
        TableColumn faculty = new TableColumn<>("факультет");
        faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        TableColumn department = new TableColumn<>("кафедра");
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        TableColumn name = new TableColumn("имя");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn surname = new TableColumn("фамилия");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn patronymic = new TableColumn("отчество");
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        TableColumn fio = new TableColumn("ФИО");
        fio.getColumns().addAll(surname, name, patronymic);
        TableColumn academicRank = new TableColumn("учёное звание");
        academicRank.setCellValueFactory(new PropertyValueFactory<>("academicRank"));
        TableColumn academicDegree = new TableColumn("учёная степень");
        academicDegree.setCellValueFactory(new PropertyValueFactory<>("academicDegree"));
        TableColumn experience = new TableColumn("стаж");
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        table.getColumns().addAll(faculty, department, fio, academicRank,
                academicDegree, experience);
        vBox = new VBox();
        vBox.getChildren().addAll(searchFIOorDepartment,
                searchFacultyAndAcademicRank, searchExperience);
        pagination = new Pagination((data.size() / rowsPerPage+ 1), 0);
        vBox.getChildren().add(pagination);
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
        facultyBox.setItems(FXCollections.observableArrayList(facultyList));
        academicRankBox.setItems(FXCollections.observableArrayList(academicRankList));
    }
    
     private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return new VBox(table);
    }

    public void setPagin(ObservableList<Personal> data) {
        this.data = data;
        if (data.size() != 0) {
            pagination.setPageCount((data.size() / rowsPerPage + 1));
            pagination.setPageFactory(this::createPage);
        } else {
            pagination.setPageCount(1);
        }
    }

    public void showDialog(Stage primaryStage) {

        window.setTitle("Поиск");
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(primaryStage);
        window.show();
    }
}
