/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author alexey
 */
public class Controller {

    View view;
    Model model;

    public Controller(View view) {
        this.view = view;
        this.model = new Model();
    }

    public ObservableList<Personal> openFile(File file) {
        try {
            return model.SaxParser(file);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void saveFile(File file, ObservableList<Personal> data) {
        try {
            model.DomParser(file, data);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Personal> searchDataFirst(ObservableList<Personal> data,
            String name, String surname, String patronymic, String department) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if ((name.equals(per.getName())
                    || name.equals(""))
                    && (surname.equals(per.getSurname())
                    || surname.equals(""))
                    && (patronymic.equals(per.getPatronymic())
                    || patronymic.equals(""))
                    && (department.equals(per.getDepartment())
                    || department.equals(""))
                    && !(name.equals("")
                    && surname.equals("")
                    && patronymic.equals("")
                    && department.equals(""))) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ObservableList<Personal> searchDataSecond(ObservableList<Personal> data,
            String academicRank, String faculty) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if (academicRank.equals(per.getAcademicRank())
                    && faculty.equals(per.getFaculty())) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ObservableList<Personal> searchDataThird(ObservableList<Personal> data,
            String heightExperience, String lowExperience) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if ((Integer.parseInt(heightExperience)
                    >= per.getExperience())
                    && (Integer.parseInt(lowExperience)
                    <= per.getExperience())) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ObservableList<Personal> deleteDataFirst(ObservableList<Personal> data,
            String name, String surname, String patronymic, String department) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if (!((name.equals(per.getName())
                    || name.equals(""))
                    && (surname.equals(per.getSurname())
                    || surname.equals(""))
                    && (patronymic.equals(per.getPatronymic())
                    || patronymic.equals(""))
                    && (department.equals(per.getDepartment())
                    || department.equals("")))
                    && !(name.equals("")
                    && surname.equals("")
                    && patronymic.equals("")
                    && department.equals(""))) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ObservableList<Personal> deleteDataSecond(ObservableList<Personal> data,
            String academicRank, String faculty) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if (!(academicRank.equals(per.getAcademicRank())
                    && faculty.equals(per.getFaculty()))
                    && !(academicRank.equals("") 
                    && faculty.equals(""))) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ObservableList<Personal> deleteDataThird(ObservableList<Personal> data,
            String heightExperience, String lowExperience) {
        ObservableList<Personal> buffer = FXCollections.observableArrayList();
        for (Personal per : data) {
            if (!((Integer.parseInt(heightExperience)
                    >= per.getExperience())
                    && (Integer.parseInt(lowExperience)
                    <= per.getExperience()))) {
                buffer.add(per);
            }
        }
        return buffer;
    }
}
