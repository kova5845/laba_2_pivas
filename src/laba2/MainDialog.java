/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author alexey
 */
public class MainDialog extends Application {

    Controller controller = new Controller();
    MainDialogWidgets mainDialogWidgets;
    //ObservableList<Personal> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainDialogWidgets = new MainDialogWidgets(primaryStage);
        mainDialogWidgets.setMainDialog(this);
        
    }

    public void openFile(FileChooser fileChooser, Stage primaryStage) {
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            mainDialogWidgets.setData(controller.openFile(file));
        }
        
    }

    public void saveFile(FileChooser fileChooser, Stage primaryStage) {
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            controller.saveFile(file);
        }
    }

    public void newFile() {
        mainDialogWidgets.setData(controller.dataClear());
    }

    public void searchData(Stage primaryStage) {
        Dialog dialog = new Dialog();
        dialog.addComboBox(FXCollections.observableArrayList(controller.getData()));
        dialog.showDialog(primaryStage);
        dialog.searchFIOofDepartmentButton.setOnAction((ActionEvent event) -> {
            dialog.setPagin(controller.searchDataFirst(dialog.nameField.getText(), 
                    dialog.surnameField.getText(), dialog.patronymicField.getText(),
                    (String)dialog.departmentBox.getValue()));
        });
        dialog.searchFacultyAndAcademicRankButton.setOnAction((ActionEvent event) -> {
            dialog.setPagin(controller.searchDataSecond((String)dialog.academicRankBox.getValue(), 
                    (String)dialog.facultyBox.getValue()));
        });
        dialog.experienceButton.setOnAction((ActionEvent event) -> {
            dialog.setPagin(controller.searchDataThird(dialog.heightExperienceField.getText(), 
                    dialog.lowExperienceField.getText()));
        });
    }

    public void deleteData(Stage primaryStage) {
        Dialog dialog = new Dialog();
        dialog.table.pagination.setVisible(false);
        dialog.addComboBox(FXCollections.observableArrayList(controller.getData()));
        dialog.showDialog(primaryStage);
        dialog.searchFIOofDepartmentButton.setOnAction((ActionEvent event) -> {
            ArrayList<Personal> data = controller.getData();
            int kol = data.size();
            data = controller.deleteDataFirst(dialog.nameField.getText(), 
                dialog.surnameField.getText(), dialog.patronymicField.getText(),
                (String)dialog.departmentBox.getValue());
            dialog.showAlert(kol -= data.size());
            mainDialogWidgets.setData(data);
        });
        dialog.searchFacultyAndAcademicRankButton.setOnAction((ActionEvent event) -> {
            ArrayList<Personal> data = controller.getData();
            int kol = data.size();
                data = controller.deleteDataSecond((String)dialog.academicRankBox.getValue(), 
                        (String)dialog.facultyBox.getValue());
                dialog.showAlert(kol -= data.size());
                mainDialogWidgets.setData(data);
        });
        dialog.experienceButton.setOnAction((ActionEvent event) -> {
            ArrayList<Personal> data = controller.getData();
            int kol = data.size();
                data = controller.deleteDataThird(dialog.heightExperienceField.getText(), 
                    dialog.lowExperienceField.getText());
                dialog.showAlert(kol -= data.size());
                mainDialogWidgets.setData(data);
        });
    }
    
    public void addPerson(Personal per){
        controller.addPerson(per);
    }
}
