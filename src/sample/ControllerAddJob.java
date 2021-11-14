package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class ControllerAddJob {
    private final ObservableList<String> citys= FXCollections.observableArrayList();
    private final ObservableList<String> jobs= FXCollections.observableArrayList();
    private final ObservableList<String> workExperience= FXCollections.observableArrayList();
    private final ObservableList<String> workSchedule= FXCollections.observableArrayList();

    @FXML
    private TextField addJob_salary;

    @FXML
    private TextField addJob_education;

    @FXML
    private TextField addJob_requirements;

    @FXML
    private TextField addJob_contactInformation;

    @FXML
    private ComboBox<String> addJob_workSchedule;

    @FXML
    private ComboBox<String> addJob_job;

    @FXML
    private ComboBox<String> addJob_workExperience;

    @FXML
    private ComboBox<String> addJob_city;

    @FXML
    private Button addJob_buttonOk;

    @FXML
    private Button addJob_buttonCancel;

    @FXML
    public void initialize (){
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
            addJob_city.setItems(citys);
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
            addJob_job.setItems(jobs);
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
        addJob_workExperience.setItems(workExperience);

        workSchedule.add("Полный день");
        workSchedule.add("Сменный график");
        workSchedule.add("Гибкий график");
        workSchedule.add("Удалённая работа");
        workSchedule.add("Вахтовый метод");
        addJob_workSchedule.setItems(workSchedule);
    }

    public void cancel(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Главное меню.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) addJob_buttonCancel.getScene().getWindow();
        stage1.close();
    }

    public void ok(){
        File file = new File("JobOpenings.txt");
        FileWriter fr = null;
        String jobInfo=addJob_salary.getText()+":"+addJob_job.getValue()+":"+addJob_education.getText()+":"+addJob_workSchedule.getValue()+":"+addJob_workExperience.getValue()+":"+addJob_city.getValue()+":"+addJob_requirements.getText()+":"+addJob_contactInformation.getText();
        try {
            //добавление инфы в файл
            fr = new FileWriter(file,true);
            fr.write(jobInfo+"\n");
            fr.close();
            //переход на другую сцену
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
            Stage stage1 = (Stage) addJob_buttonOk.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
