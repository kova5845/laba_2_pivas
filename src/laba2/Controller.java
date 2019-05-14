/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba2;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author alexey
 */
public class Controller {
    DataBasePersonal model;

    public Controller() {
        this.model = new DataBasePersonal(this);
    }
    
    public void addPerson(Personal per){
        model.addPerson(per);
    }

    public ArrayList<Personal> openFile(File file) {
        model.sax(file);
        return model.getData();
    }

    public void saveFile(File file) {
        model.dom(file);
    }
    
    public ArrayList<Personal> getData(){
        return model.getData();
    }
    
    public ArrayList<Personal> setData(ArrayList<Personal> data){
        model.setData(data);
        return data;
    }

    public ArrayList<Personal> searchDataFirst(String name, String surname, String patronymic, String department) {
        ArrayList<Personal> buffer, data;
        buffer = new ArrayList<>();
        data = model.getData();
        for (Personal per : data) {
            if (((name.equals(per.getName())
                    || surname.equals(per.getSurname())
                    || patronymic.equals(per.getPatronymic()))
                    || department.equals(per.getDepartment()))) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ArrayList<Personal> searchDataSecond(String academicRank, String faculty) {
        ArrayList<Personal> buffer, data;
        buffer = new ArrayList<>();
        data = model.getData();
        for (Personal per : data) {
            if (academicRank.equals(per.getAcademicRank())
                    && faculty.equals(per.getFaculty())) {
                buffer.add(per);
            }
        }
        return buffer;
    }

    public ArrayList<Personal> searchDataThird(String heightExperience, String lowExperience) {
        ArrayList<Personal> buffer, data;
        buffer = new ArrayList<>();
        data = model.getData();
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

    public ArrayList<Personal> deleteDataFirst(String name, String surname, String patronymic, String department) {
        ArrayList<Personal> data = model.getData();
        ArrayList<Personal> buffer = searchDataFirst(name, surname, patronymic, department);
        data.removeAll(buffer);
        return data;
    }

    public ArrayList<Personal> deleteDataSecond(String academicRank, String faculty) {
        ArrayList<Personal> data = model.getData();
        ArrayList<Personal> buffer = searchDataSecond(academicRank, faculty);
        data.removeAll(buffer);
        return data;
    }

    public ArrayList<Personal> deleteDataThird(String heightExperience, String lowExperience) {
        ArrayList<Personal> data = model.getData();
        ArrayList<Personal> buffer = searchDataThird(heightExperience, lowExperience);
        data.removeAll(buffer);
        return data;
    }

    public ArrayList<Personal> dataClear() {
        return model.dataClear();
    }
}




//ok //добавить количество записей всех
//ok //постраничный компонент в поиске изменить кол-во записей
//ok //поиск или в первой группе
//ok //постраничный компонент
//ok //парсеры в отдельный класс
//ok //нет слоя Model, удалить data из view и разбить это класс на несколько классов
//ok //переименовать классы
//ok //удалить view из контроллера
//ok //использовать FXCOlections только в слое View

//Запускаем прогу добавляем 1 запись сохраняем смотрим структуру
//Загружаем файл с большим кол-вом записей
//Смотрим постраничный вывод
//Тестируем поиск
//Тестируем удаление