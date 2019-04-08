/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author alexey
 */
public class View extends Application {

    public Controller controller;
    public Pagination pagination;
    public TableView<Personal> table;
    ObservableList<Personal> data = FXCollections.observableArrayList();
    int rowsPerPage = 5;

    public static void main(String[] args) {
        launch(args);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
        table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return new VBox(table);
    }

    @Override
    public void start(Stage primaryStage) {
        controller = new Controller(this);
        pagination = new Pagination((data.size() / rowsPerPage + 1), 0);
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
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("file");
        Menu editMenu = new Menu("edit");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML", "*.xml"));
        Image newImage = new Image("image/new.png", 20, 20, true, true);
        MenuItem newItem = new MenuItem("new");
        newItem.setGraphic(new ImageView(newImage));
        newItem.setOnAction((ActionEvent event) -> {
            newFile();
        });
        Image openImage = new Image("image/open.png", 20, 20, true, true);
        MenuItem openItem = new MenuItem("open");
        openItem.setGraphic(new ImageView(openImage));
        openItem.setOnAction((ActionEvent event) -> {
            openFile(fileChooser, primaryStage);

        });
        Image saveImage = new Image("image/save.png", 20, 20, true, true);
        MenuItem saveItem = new MenuItem("save");
        saveItem.setGraphic(new ImageView(saveImage));
        saveItem.setOnAction((ActionEvent event) -> {
            saveFile(fileChooser, primaryStage);
        });
        Image searchImage = new Image("image/search.png", 20, 20, true, true);
        MenuItem searchItem = new MenuItem("search");
        searchItem.setGraphic(new ImageView(searchImage));
        searchItem.setOnAction((ActionEvent event) -> {
            searchData(primaryStage);
        });
        Image deleteImage = new Image("image/delete.png", 20, 20, true, true);
        MenuItem deleteItem = new MenuItem("delete");
        deleteItem.setGraphic(new ImageView(deleteImage));
        deleteItem.setOnAction((ActionEvent event) -> {
            deleteData(primaryStage);
        });
        Menu rowsMenu = new Menu("rows");
        RadioMenuItem fiveRowsForPage = new RadioMenuItem("5");
        fiveRowsForPage.setSelected(true);
        fiveRowsForPage.setOnAction((ActionEvent event) -> {
            rowsPerPage = 5;
            pagination.setPageCount((data.size() / rowsPerPage + 1));
            pagination.setPageFactory(this::createPage);
        });
        RadioMenuItem tenRowsForPage = new RadioMenuItem("10");
        tenRowsForPage.setOnAction((ActionEvent event) -> {
            rowsPerPage = 10;
            pagination.setPageCount((data.size() / rowsPerPage + 1));
            pagination.setPageFactory(this::createPage);
        });
        RadioMenuItem twentyRowsForPage = new RadioMenuItem("20");
        twentyRowsForPage.setOnAction((ActionEvent event) -> {
            rowsPerPage = 20;
            pagination.setPageCount((data.size() / rowsPerPage + 1));
            pagination.setPageFactory(this::createPage);
        });
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(fiveRowsForPage, tenRowsForPage, twentyRowsForPage);
        fileMenu.getItems().addAll(newItem, openItem, saveItem);
        editMenu.getItems().addAll(searchItem, deleteItem);
        rowsMenu.getItems().addAll(fiveRowsForPage, tenRowsForPage, twentyRowsForPage);
        menuBar.getMenus().addAll(fileMenu, editMenu, rowsMenu);
        Button newButton = new Button("", new ImageView(newImage));
        newButton.setOnAction((ActionEvent event) -> {
            newFile();
        });
        Button openButton = new Button("", new ImageView(openImage));
        openButton.setOnAction((ActionEvent event) -> {
            openFile(fileChooser, primaryStage);
        });
        Button saveButton = new Button("", new ImageView(saveImage));
        saveButton.setOnAction((ActionEvent event) -> {
            saveFile(fileChooser, primaryStage);
        });
        Button searchButton = new Button("", new ImageView(searchImage));
        searchButton.setOnAction((ActionEvent event) -> {
            searchData(primaryStage);
        });
        Button deleteButton = new Button("", new ImageView(deleteImage));
        deleteButton.setOnAction((ActionEvent event) -> {
            deleteData(primaryStage);
        });
        FlowPane toolBar = new FlowPane(newButton, openButton, saveButton,
                searchButton, deleteButton);

        Label facultyLabel = new Label("фатультет");
        Label departmentLabel = new Label("кафедра");
        Label nameLabel = new Label("имя");
        Label surnameLabel = new Label("фамилия");
        Label patronymicLabel = new Label("отчество");
        Label academicRankLabel = new Label("учёное звание");
        Label academicDegreeLabel = new Label("учёная степень");
        Label experienceLabel = new Label("стаж");
        TextField facultyField = new TextField();
        TextField departmentField = new TextField();
        TextField nameField = new TextField();
        TextField surnameField = new TextField();
        TextField patronymicField = new TextField();
        TextField academicRankField = new TextField();
        TextField academicDegreeField = new TextField();
        TextField experienceField = new TextField();
        FlowPane facultyPane = new FlowPane(facultyLabel, facultyField);
        FlowPane departmentPane = new FlowPane(departmentLabel, departmentField);
        FlowPane namePane = new FlowPane(nameLabel, nameField);
        FlowPane surnamePane = new FlowPane(surnameLabel, surnameField);
        FlowPane patronymicPane = new FlowPane(patronymicLabel, patronymicField);
        FlowPane academicRankPane = new FlowPane(academicRankLabel, academicRankField);
        FlowPane academicDegreePane = new FlowPane(academicDegreeLabel, academicDegreeField);
        FlowPane experiencePane = new FlowPane(experienceLabel, experienceField);
        Button addNewNoteButton = new Button("Добавить новую запись");
        addNewNoteButton.setOnAction((ActionEvent event) -> {
            if (!facultyField.getText().equals("")
                    && !departmentField.getText().equals("")
                    && !nameField.getText().equals("")
                    && !surnameField.getText().equals("")
                    && !patronymicField.getText().equals("")
                    && !academicRankField.getText().equals("")
                    && !academicDegreeField.getText().equals("")
                    && !experienceField.getText().equals("")) {
                data.add(new Personal(facultyField.getText(),
                        departmentField.getText(),
                        nameField.getText(),
                        surnameField.getText(),
                        patronymicField.getText(),
                        academicRankField.getText(),
                        academicDegreeField.getText(),
                        Integer.parseInt(experienceField.getText())));
                pagination.setPageCount((data.size() / rowsPerPage + 1));
                pagination.setPageFactory(this::createPage);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Вы не заполнили все поля");
                alert.showAndWait();
            }
        });
        VBox addBox = new VBox(facultyPane, departmentPane, namePane, surnamePane,
                patronymicPane, academicRankPane, academicDegreePane,
                experiencePane, addNewNoteButton);
        FlowPane tableAndAdd = new FlowPane(20, 0, pagination, addBox);
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, toolBar, tableAndAdd);
        Scene scene = new Scene(root, 1200, 520);
        primaryStage.setTitle("Лабараторная работа №2");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void openFile(FileChooser fileChooser, Stage primaryStage) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            buffer = controller.openFile(file);
        }
        data.clear();
        for (Personal per : buffer) {
            data.add(per);
        }
        pagination.setPageCount((data.size() / rowsPerPage + 1));
        pagination.setPageFactory(this::createPage);
    }

    public void saveFile(FileChooser fileChooser, Stage primaryStage) {
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            controller.saveFile(file, data);
        }
    }

    public void newFile() {
        data.clear();
    }

    public void searchData(Stage primaryStage) {
        Dialog dialog = new Dialog();
        dialog.addComboBox(data);
        dialog.showDialog(primaryStage);
        dialog.searchFIOofDepartmentButton.setOnAction((ActionEvent event) -> {
            dialog.setPagin(controller.searchDataFirst(data, dialog.nameField.getText(), 
                    dialog.surnameField.getText(), dialog.patronymicField.getText(),
                    (String)dialog.departmentBox.getValue()));
        });
        dialog.searchFacultyAndAcademicRankButton.setOnAction((ActionEvent event) -> {
            dialog.setPagin(controller.searchDataSecond(data, 
                    (String)dialog.academicRankBox.getValue(), 
                    (String)dialog.facultyBox.getValue()));
        });
        dialog.experienceButton.setOnAction((ActionEvent event) -> {
            dialog.data.clear();
            dialog.setPagin(controller.searchDataThird(data, 
                    dialog.heightExperienceField.getText(), 
                    dialog.lowExperienceField.getText()));
        });
    }

    public void deleteData(Stage primaryStage) {
        Dialog dialog = new Dialog();
        dialog.addComboBox(data);
        dialog.showDialog(primaryStage);
        dialog.searchFIOofDepartmentButton.setOnAction((ActionEvent event) -> {
            if (!data.isEmpty()) {
                data = controller.deleteDataFirst(data, dialog.nameField.getText(), 
                    dialog.surnameField.getText(), dialog.patronymicField.getText(),
                    (String)dialog.departmentBox.getValue());
                pagination.setPageCount((data.size() / rowsPerPage + 1));
                pagination.setPageFactory(this::createPage);
            }
        });
        dialog.searchFacultyAndAcademicRankButton.setOnAction((ActionEvent event) -> {
            if (!data.isEmpty()) {
                data = controller.deleteDataSecond(data, 
                        (String)dialog.academicRankBox.getValue(), 
                        (String)dialog.facultyBox.getValue());
                pagination.setPageCount((data.size() / rowsPerPage + 1));
                pagination.setPageFactory(this::createPage);
            }
        });
        dialog.experienceButton.setOnAction((ActionEvent event) -> {
            if (!data.isEmpty()) {
                data = controller.searchDataThird(data, 
                    dialog.heightExperienceField.getText(), 
                    dialog.lowExperienceField.getText());
                pagination.setPageCount((data.size() / rowsPerPage + 1));
                pagination.setPageFactory(this::createPage);
            }
        });
    }
}
