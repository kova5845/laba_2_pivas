/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author alexey
 */
public class DataBasePersonal {
    ParserXML parser = new ParserXML();
    ArrayList<Personal> data;
    Controller controller;
    
    public DataBasePersonal(Controller controller) {
        this.controller = controller;
        data = new ArrayList<>();
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
    
    public void sax(File file){
        try {
            parser.saxParser(file, data);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DataBasePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dom(File file){
        try {
            parser.domParser(file, data);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DataBasePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addPerson(Personal per){
        this.data.add(per);
    }

    public ArrayList<Personal> dataClear() {
        this.data.clear();
        return data;
    }
}
