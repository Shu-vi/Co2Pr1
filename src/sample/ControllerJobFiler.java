package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class ControllerJobFiler {
    private final ObservableList<String> citys= FXCollections.observableArrayList();
    private final ObservableList<String> jobs= FXCollections.observableArrayList();
    private final ObservableList<String> workExperience= FXCollections.observableArrayList();
    private final ObservableList<String> workSchedule= FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> jobFilter_job;

    @FXML
    private ComboBox<String> jobFilter_city;

    @FXML
    private TextField jobFilter_salary;

    @FXML
    private Button jobFilter_buttonToFind;

    @FXML
    private ComboBox<String> jobFilter_workExperience;

    @FXML
    private ComboBox<String> jobFilter_workSchedule;

    @FXML
    private Button jobSearch_buttonAllJobsList;

    @FXML
    private Button jobSearch_buttonExit;

    @FXML
    private Button jobSearch_buttonFavourites;

    public void allJobsList(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Главное меню.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возврат на первую форму
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) jobSearch_buttonAllJobsList.getScene().getWindow();
        stage1.close();
    }

    public void exit(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Вход в систему.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возврат на первую форму
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) jobSearch_buttonAllJobsList.getScene().getWindow();
        stage1.close();
    }

    public void favourites(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Избранное.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возврат на первую форму
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) jobSearch_buttonAllJobsList.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void initialize() {
        BufferedReader br = null;
        String nameOfFile="Citys.txt";
        //загружаем список городов
        try{
            File file = new File(nameOfFile);
            br = new BufferedReader(new FileReader(nameOfFile));
            String city;
            while( ( ( city = br.readLine() ) != null) ){
                citys.add(city);
            }
            jobFilter_city.setItems(citys);
        } catch(IOException e){
            System.out.println("Error: "+ e);
        } finally {
            try {
                br.close();
            }catch(IOException e) {
                System.out.println("Error: " + e);
            }
        }
        nameOfFile="ProfessionalField.txt";
        //загружаем список областей профессий
        try{
            File file = new File(nameOfFile);
            br = new BufferedReader(new FileReader(nameOfFile));
            String job;
            while( ( ( job = br.readLine() ) != null) ){
                jobs.add(job);
            }
            jobFilter_job.setItems(jobs);
        } catch(IOException e){
            System.out.println("Error: "+ e);
        } finally {
            try {
                br.close();
            }catch(IOException e) {
                System.out.println("Error: " + e);
            }
        }

        workExperience.add("Нет опыта");
        workExperience.add("От 1 года до 3 лет");
        workExperience.add("От 3 лет до 6 лет");
        workExperience.add("Более 6 лет");
        jobFilter_workExperience.setItems(workExperience);

        workSchedule.add("Полный день");
        workSchedule.add("Сменный график");
        workSchedule.add("Гибкий график");
        workSchedule.add("Удалённая работа");
        workSchedule.add("Вахтовый метод");
        jobFilter_workSchedule.setItems(workSchedule);

        try {
            File file = new File("FoundProfessions.txt");
            FileWriter fr = null;
            fr = new FileWriter(file);
            fr.write("");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //в файл записывает отфильтрованный список вакансий и вызывает новую сцену, где выводится список
    public void toFindJob(){
        if (jobFilter_job.getValue()!=null&& jobFilter_city.getValue()!=null&& jobFilter_workExperience.getValue()!=null&&jobFilter_salary.getText()!=null&&jobFilter_workSchedule.getValue()!=null) {
            String jobCall = jobFilter_job.getValue();
            String cityCall = jobFilter_city.getValue();
            String workExperienceCall = jobFilter_workExperience.getValue();
            String salaryCall = jobFilter_salary.getText();
            String workScheduleCall = jobFilter_workSchedule.getValue();
            String path = "JobOpenings.txt";
            String salary = "";
            String name = "";
            String education = "";
            String workSchedule = "";
            String workExperience = "";
            String city = "";
            String requirements = "";
            String contactInformation = "";
            int i = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while (((line = br.readLine()) != null)) {//Считываем построчно информацию о товарах и обрабатываем каждую строку отдельно
                    i = 0;
                    salary = "";
                    name = "";
                    education = "";
                    workSchedule = "";
                    workExperience = "";
                    city = "";
                    requirements = "";
                    contactInformation = "";
                    while (line.charAt(i) != ':') {//считываем зарплату
                        salary = salary + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем название вакансии
                    while (line.charAt(i) != ':') {
                        name = name + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем образование
                    while (line.charAt(i) != ':') {
                        education = education + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем график работы
                    while (line.charAt(i) != ':') {
                        workSchedule = workSchedule + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем опыт работы
                    while (line.charAt(i) != ':') {
                        workExperience = workExperience + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем город
                    while (line.charAt(i) != ':') {
                        city = city + line.charAt(i);
                        i++;
                    }
                    i++;
                    //считываем требования
                    while (line.charAt(i) != ':') {
                        requirements = requirements + line.charAt(i);
                        i++;
                    }
                    i++;
                    while (i < line.length()) { // считываем контактную информацию
                        contactInformation = contactInformation + line.charAt(i);
                        i++;
                    }
                    if (Objects.equals(name, jobCall) && Objects.equals(cityCall, city) && Objects.equals(workExperienceCall, workExperience) && Objects.equals(workScheduleCall, workSchedule) && Long.parseLong(salary) >= Long.parseLong(salaryCall)) {//проверяем , совпадает ли вакансия с запросом
                        printToFile(salary, name, education, workSchedule, workExperience, city, requirements, contactInformation);
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            Stage stage = new Stage();
            stage.setTitle("Поиск работы");
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Список вакансий.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //возврат на первую форму
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Stage stage1 = (Stage) jobSearch_buttonAllJobsList.getScene().getWindow();
            stage1.close();
        }
    }
    //печатает подходящие профессии файлы(в дальнейшем на новой форме будет считывать из него)
    private void printToFile(String salary, String name, String education, String workSchedule, String workExperience, String city, String requirements, String contactInformation){
        File file = new File("FoundProfessions.txt");
        FileWriter fr = null;
        String profInfo=salary+":"+name+":"+education+":"+workSchedule+":"+workExperience+":"+city+":"+requirements+":"+contactInformation;
        try {
            fr = new FileWriter(file,true);
            fr.write(profInfo+"\n");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
