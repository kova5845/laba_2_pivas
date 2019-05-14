/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author alexey
 */
public class MainDialogWidgets {
    MainDialog mainDialog;
    MenuBar menuBar;
    Menu fileMenu;
    Menu editMenu;
    FileChooser fileChooser;
    Image newImage;
    MenuItem newItem;
    Image openImage;
    MenuItem openItem;
    Image saveImage;
    MenuItem saveItem;
    Image searchImage;
    MenuItem searchItem;
    Image deleteImage;
    MenuItem deleteItem;
    Menu rowsMenu;
    RadioMenuItem fiveRowsForPage;
    RadioMenuItem tenRowsForPage;
    RadioMenuItem twentyRowsForPage;
    ToggleGroup toggleGroup;
    Button newButton;
    Button openButton;
    Button saveButton;
    Button searchButton;
    Button deleteButton;
    FlowPane toolBar;
    Label facultyLabel;
    Label departmentLabel;
    Label nameLabel;
    Label surnameLabel;
    Label patronymicLabel;
    Label academicRankLabel;
    Label academicDegreeLabel;
    Label experienceLabel;
    TextField facultyField;
    TextField departmentField;
    TextField nameField;
    TextField surnameField;
    TextField patronymicField;
    TextField academicRankField;
    TextField academicDegreeField;
    TextField experienceField;
    FlowPane facultyPane;
    FlowPane departmentPane;
    FlowPane namePane;
    FlowPane surnamePane;
    FlowPane patronymicPane;
    FlowPane academicRankPane;
    FlowPane academicDegreePane;
    FlowPane experiencePane;
    Button addNewNoteButton;
    VBox addBox;
    FlowPane tableAndAdd;
    VBox root;
    Table table;
    Label kolAllNotes;

    public MainDialogWidgets(Stage primaryStage) {
        table = new Table();
        menuBar = new MenuBar();
        fileMenu = new Menu("file");
        editMenu = new Menu("edit");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML", "*.xml"));
        newImage = new Image("image/new.png", 20, 20, true, true);
        newItem = new MenuItem("new");
        newItem.setGraphic(new ImageView(newImage));
        newItem.setOnAction((ActionEvent event) -> {
            mainDialog.newFile();
        });
        openImage = new Image("image/open.png", 20, 20, true, true);
        openItem = new MenuItem("open");
        openItem.setGraphic(new ImageView(openImage));
        openItem.setOnAction((ActionEvent event) -> {
            mainDialog.openFile(fileChooser, primaryStage);

        });
        saveImage = new Image("image/save.png", 20, 20, true, true);
        saveItem = new MenuItem("save");
        saveItem.setGraphic(new ImageView(saveImage));
        saveItem.setOnAction((ActionEvent event) -> {
            mainDialog.saveFile(fileChooser, primaryStage);
        });
        searchImage = new Image("image/search.png", 20, 20, true, true);
        searchItem = new MenuItem("search");
        searchItem.setGraphic(new ImageView(searchImage));
        searchItem.setOnAction((ActionEvent event) -> {
            mainDialog.searchData(primaryStage);
        });
        deleteImage = new Image("image/delete.png", 20, 20, true, true);
        deleteItem = new MenuItem("delete");
        deleteItem.setGraphic(new ImageView(deleteImage));
        deleteItem.setOnAction((ActionEvent event) -> {
            mainDialog.deleteData(primaryStage);
        });
        rowsMenu = new Menu("rows");
        fiveRowsForPage = new RadioMenuItem("5");
        fiveRowsForPage.setSelected(true);
        fiveRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(5);
            table.setData(table.getData());
        });
        tenRowsForPage = new RadioMenuItem("10");
        tenRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(10);
            table.setData(table.getData());
        });
        twentyRowsForPage = new RadioMenuItem("20");
        twentyRowsForPage.setOnAction((ActionEvent event) -> {
            table.setRowsPerPage(20);
            table.setData(table.getData());
        });
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(fiveRowsForPage, tenRowsForPage, twentyRowsForPage);
        fileMenu.getItems().addAll(newItem, openItem, saveItem);
        editMenu.getItems().addAll(searchItem, deleteItem);
        rowsMenu.getItems().addAll(fiveRowsForPage, tenRowsForPage, twentyRowsForPage);
        menuBar.getMenus().addAll(fileMenu, editMenu, rowsMenu);
        newButton = new Button("", new ImageView(newImage));
        newButton.setOnAction((ActionEvent event) -> {
            mainDialog.newFile();
        });
        openButton = new Button("", new ImageView(openImage));
        openButton.setOnAction((ActionEvent event) -> {
            mainDialog.openFile(fileChooser, primaryStage);
        });
        saveButton = new Button("", new ImageView(saveImage));
        saveButton.setOnAction((ActionEvent event) -> {
            mainDialog.saveFile(fileChooser, primaryStage);
        });
        searchButton = new Button("", new ImageView(searchImage));
        searchButton.setOnAction((ActionEvent event) -> {
            mainDialog.searchData(primaryStage);
        });
        deleteButton = new Button("", new ImageView(deleteImage));
        deleteButton.setOnAction((ActionEvent event) -> {
            mainDialog.deleteData(primaryStage);
        });
        toolBar = new FlowPane(newButton, openButton, saveButton,
                searchButton, deleteButton);

        facultyLabel = new Label("фатультет");
        departmentLabel = new Label("кафедра");
        nameLabel = new Label("имя");
        surnameLabel = new Label("фамилия");
        patronymicLabel = new Label("отчество");
        academicRankLabel = new Label("учёное звание");
        academicDegreeLabel = new Label("учёная степень");
        experienceLabel = new Label("стаж");
        facultyField = new TextField();
        departmentField = new TextField();
        nameField = new TextField();
        surnameField = new TextField();
        patronymicField = new TextField();
        academicRankField = new TextField();
        academicDegreeField = new TextField();
        experienceField = new TextField();
        facultyPane = new FlowPane(facultyLabel, facultyField);
        departmentPane = new FlowPane(departmentLabel, departmentField);
        namePane = new FlowPane(nameLabel, nameField);
        surnamePane = new FlowPane(surnameLabel, surnameField);
        patronymicPane = new FlowPane(patronymicLabel, patronymicField);
        academicRankPane = new FlowPane(academicRankLabel, academicRankField);
        academicDegreePane = new FlowPane(academicDegreeLabel, academicDegreeField);
        experiencePane = new FlowPane(experienceLabel, experienceField);
        addNewNoteButton = new Button("Добавить новую запись");
        addNewNoteButton.setOnAction((ActionEvent event) -> {
            if (!facultyField.getText().equals("")
                    && !departmentField.getText().equals("")
                    && !nameField.getText().equals("")
                    && !surnameField.getText().equals("")
                    && !patronymicField.getText().equals("")
                    && !academicRankField.getText().equals("")
                    && !academicDegreeField.getText().equals("")
                    && !experienceField.getText().equals("")) {
                mainDialog.addPerson(new Personal(facultyField.getText(),
                        departmentField.getText(),
                        nameField.getText(),
                        surnameField.getText(),
                        patronymicField.getText(),
                        academicRankField.getText(),
                        academicDegreeField.getText(),
                        Integer.parseInt(experienceField.getText())));
                table.addPerson(new Personal(facultyField.getText(),
                        departmentField.getText(),
                        nameField.getText(),
                        surnameField.getText(),
                        patronymicField.getText(),
                        academicRankField.getText(),
                        academicDegreeField.getText(),
                        Integer.parseInt(experienceField.getText())));
                kolAllNotes.setText("Количество записей: " + table.data.size());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Вы не заполнили все поля");
                alert.showAndWait();
            }
        });
        kolAllNotes = new Label();
        addBox = new VBox(facultyPane, departmentPane, namePane, surnamePane,
                patronymicPane, academicRankPane, academicDegreePane,
                experiencePane, addNewNoteButton);
        tableAndAdd = new FlowPane(20, 0, table.pagination, addBox);
        root = new VBox();
        root.getChildren().addAll(menuBar, toolBar, tableAndAdd, kolAllNotes);
        Scene scene = new Scene(root, 1200, 520);
        primaryStage.setTitle("Лабараторная работа №2");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void setMainDialog(MainDialog mainDialog) {
        this.mainDialog = mainDialog;
    }
    
    public void setData(ArrayList<Personal> data){
        table.setData(data);
        kolAllNotes.setText("Количество записей: " + data.size());
    }
}
