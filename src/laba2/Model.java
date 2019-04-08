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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author alexey
 */
public class Model {

    public ObservableList<Personal> SaxParser(File file) throws ParserConfigurationException,
            SAXException, IOException {
        ObservableList<Personal> data = FXCollections.observableArrayList();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        DefaultHandler hundler = new DefaultHandler() {
            String lastElement;
            String faculty;
            String department;
            String name;
            String surname;
            String patronymic;
            String academicRank;
            String academicDegree;
            int experience;

            @Override
            public void startDocument() throws SAXException {
                data.clear();
            }

            @Override
            public void startElement(String uri, String localName,
                    String qName, Attributes attributes)
                    throws SAXException {
                lastElement = qName;
            }

            @Override
            public void characters(char[] ch, int start,
                    int length) throws SAXException {
                String information = new String(ch, start, length);
                information = information.replace("\n", "").trim();
                if (!information.isEmpty()) {
                    switch (lastElement) {
                        case "faculty":
                            faculty = information;
                            break;
                        case "department":
                            department = information;
                            break;
                        case "name":
                            name = information;
                            break;
                        case "surname":
                            surname = information;
                            break;
                        case "patronymic":
                            patronymic = information;
                            break;
                        case "academicRank":
                            academicRank = information;
                            break;
                        case "academicDegree":
                            academicDegree = information;
                            break;
                        case "experience":
                            experience = Integer.parseInt(information);
                            break;
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName,
                    String qName) throws SAXException {
                if (qName.equals("person")) {
                    data.add(new Personal(faculty, department, name,
                            surname, patronymic, academicRank,
                            academicDegree, experience));
                }
            }

        };
        parser.parse(file, hundler);
        return data;
    }

    public void DomParser(File file, ObservableList<Personal> data) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("root");
        for (Personal per : data) {
            Element person = document.createElement("person");
            Element faculty = document.createElement("faculty");
            Element department = document.createElement("department");
            Element name = document.createElement("name");
            Element surname = document.createElement("surname");
            Element patronymic = document.createElement("patronymic");
            Element academicRank = document.createElement("academicRank");
            Element academicDegree = document.createElement("academicDegree");
            Element experience = document.createElement("experience");
            Text facultyText = document.createTextNode(per.getFaculty());
            Text departmentText = document.createTextNode(per.getDepartment());
            Text nameText = document.createTextNode(per.getName());
            Text surnameText = document.createTextNode(per.getSurname());
            Text patronymicText = document.createTextNode(per.getPatronymic());
            Text academicRankText = document.createTextNode(per.getAcademicRank());
            Text academicDegreeText = document.createTextNode(per.getAcademicDegree());
            Text experienceText = document.createTextNode(Integer.toString(per.getExperience()));
            faculty.appendChild(facultyText);
            department.appendChild(departmentText);
            name.appendChild(nameText);
            surname.appendChild(surnameText);
            patronymic.appendChild(patronymicText);
            academicRank.appendChild(academicRankText);
            academicDegree.appendChild(academicDegreeText);
            experience.appendChild(experienceText);
            person.appendChild(faculty);
            person.appendChild(department);
            person.appendChild(name);
            person.appendChild(surname);
            person.appendChild(patronymic);
            person.appendChild(academicRank);
            person.appendChild(academicDegree);
            person.appendChild(experience);
            root.appendChild(person);
        }
        document.appendChild(root);
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(file));
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
