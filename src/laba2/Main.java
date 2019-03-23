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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author alexey
 */
public class Main extends Application{
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("file");
        Menu editMenu = new Menu("edit");
        Image newImage = new Image("image/new.png", 20, 20, true, true);
        MenuItem newItem = new MenuItem("new");
        newItem.setGraphic(new ImageView(newImage));
        Image openImage = new Image("image/open.png", 20, 20, true, true);
        MenuItem openItem = new MenuItem("open");
        openItem.setGraphic(new ImageView(openImage));
        Image saveImage = new Image("image/save.png", 20, 20, true, true);
        MenuItem saveItem = new MenuItem("save");
        saveItem.setGraphic(new ImageView(saveImage));
        Image searchImage = new Image("image/search.png", 20, 20, true, true);
        MenuItem searchItem = new MenuItem("search");
        searchItem.setGraphic(new ImageView(searchImage));
        Image deleteImage = new Image("image/delete.png", 20, 20, true, true);
        MenuItem deleteItem = new MenuItem("delete");
        deleteItem.setGraphic(new ImageView(deleteImage));
        fileMenu.getItems().addAll(newItem, openItem, saveItem);
        editMenu.getItems().addAll(searchItem, deleteItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);
        Button newButton = new Button("", new ImageView(newImage));
        Button openButton = new Button("", new ImageView(openImage));
        Button saveButton = new Button("", new ImageView(saveImage));
        Button searchButton = new Button("", new ImageView(searchImage));
        Button deleteButton = new Button("", new ImageView(deleteImage));
        TableView<Personal> table = new TableView<>();
        ObservableList<Personal> data = FXCollections.observableArrayList();
        TableColumn faculty = new TableColumn("факультет");
        faculty.setCellValueFactory(new PropertyValueFactory("faculty"));
        TableColumn department = new TableColumn("кафедра");
        department.setCellValueFactory(new PropertyValueFactory("department"));
        TableColumn name = new TableColumn("ФИО");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn academicRank = new TableColumn("учёное звание");
        academicRank.setCellValueFactory(new PropertyValueFactory("academicRank"));
        TableColumn academicDegree = new TableColumn("учёная степень");
        academicDegree.setCellValueFactory(new PropertyValueFactory("academicDegree"));
        TableColumn experience = new TableColumn("стаж");
        experience.setCellValueFactory(new PropertyValueFactory("experience"));
        table.setItems(data);
        table.getColumns().addAll(faculty, department, name, academicRank,
                                  academicDegree, experience);
        table.prefWidthProperty().bind(primaryStage.widthProperty());
        FlowPane toolBar = new FlowPane(newButton, openButton, saveButton, 
                                        searchButton, deleteButton);
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, toolBar, table);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Лабараторная работа №2");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
