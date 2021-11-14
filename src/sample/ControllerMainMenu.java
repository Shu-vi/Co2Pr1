package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class ControllerMainMenu {
    private final ObservableList<Job> jobData= FXCollections.observableArrayList();

    @FXML
    private TableView<Job> mainMenu_jobList;

    @FXML
    private TableColumn<Job, String> mainMenu_job;

    @FXML
    private Label mainMenu_education;

    @FXML
    private Label mainMenu_workSchedule;

    @FXML
    private Label mainMenu_workExperience;

    @FXML
    private Label mainMenu_city;

    @FXML
    private Label mainMenu_requirements;

    @FXML
    private Label mainMenu_salary;

    @FXML
    private Button mainMenu_buttonAddToFavourites;

    @FXML
    private Button mainMenu_buttonAddJob;


    @FXML
    private Button mainMenu_buttonDelete;

    @FXML
    private Button mainMenu_buttonShowContactingInformation;

    @FXML
    private Button mainMenu_ButtonJobSearch;

    @FXML
    private Button mainMenu_buttonExit;

    @FXML
    private Button mainMenu_buttonFavourites;

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
        Stage stage1 = (Stage) mainMenu_buttonExit.getScene().getWindow();
        stage1.close();
    }

    public void jobSearch(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Фильтр вакансий.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возврат на первую форму
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) mainMenu_ButtonJobSearch.getScene().getWindow();
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
        Stage stage1 = (Stage) mainMenu_buttonFavourites.getScene().getWindow();
        stage1.close();
    }

    @FXML
    public void initialize (){
        fileToSystem();
        mainMenu_job.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainMenu_jobList.setItems(jobData);
        showJobDetails(null);
        mainMenu_jobList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showJobDetails(newValue));
    }

    private void showJobDetails(Job job){
        if(job!=null){
            mainMenu_education.setText(job.getEducation());
            mainMenu_workSchedule.setText(job.getWorkSchedule());
            mainMenu_workExperience.setText(job.getWorkExperience());
            mainMenu_city.setText(job.getCity());
            mainMenu_requirements.setText(job.getRequirements());
            mainMenu_salary.setText(job.getSalary().toString());
        }else{
            mainMenu_education.setText("");
            mainMenu_workSchedule.setText("");
            mainMenu_workExperience.setText("");
            mainMenu_city.setText("");
            mainMenu_requirements.setText("");
            mainMenu_salary.setText("");
        }
    }

    private void fileToSystem(){
        String path="JobOpenings.txt";
        String salary="";
        String name="";
        String education="";
        String workSchedule="";
        String workExperience="";
        String city="";
        String requirements="";
        String contactInformation="";
        int i = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while( ( ( line = br.readLine() ) != null )){//Считываем построчно информацию о товарах и обрабатываем каждую строку отдельно
                i=0;
                salary="";
                name="";
                education="";
                workSchedule="";
                workExperience="";
                city="";
                requirements="";
                contactInformation="";
                while (line.charAt(i)!=':'){//считываем зарплату
                    salary=salary+line.charAt(i);
                    i++;
                } i++;
                //считываем название вакансии
                while (line.charAt(i)!=':'){
                    name=name+line.charAt(i);
                    i++;
                } i++;
                //считываем образование
                while (line.charAt(i)!=':'){
                    education=education+line.charAt(i);
                    i++;
                } i++;
                //считываем график работы
                while (line.charAt(i)!=':'){
                    workSchedule=workSchedule+line.charAt(i);
                    i++;
                } i++;
                //считываем опыт работы
                while (line.charAt(i)!=':'){
                    workExperience=workExperience+line.charAt(i);
                    i++;
                } i++;
                //считываем город
                while (line.charAt(i)!=':'){
                    city=city+line.charAt(i);
                    i++;
                } i++;
                //считываем требования
                while (line.charAt(i)!=':'){
                    requirements=requirements+line.charAt(i);
                    i++;
                }i++;
                while (i<line.length()){ // считываем контактную информацию
                    contactInformation=contactInformation+line.charAt(i);
                    i++;
                }
                jobData.add( new Job(Long.parseLong(salary), education, workSchedule, workExperience, city, requirements, name, contactInformation) );
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addToFavourites(){
        if(mainMenu_city.getText()!=""){
            File file = new File("Favourites.txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file, true);
                fr.write(Long.parseLong(mainMenu_salary.getText())+":"+mainMenu_jobList.getSelectionModel().getSelectedItem().getName()+":"+mainMenu_education.getText()+":"+mainMenu_workSchedule.getText()+":"+mainMenu_workExperience.getText()+":"+mainMenu_city.getText()+":"+mainMenu_requirements.getText()+":"+mainMenu_jobList.getSelectionModel().getSelectedItem().getContactInformation()+"\n");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showContactInformation(){
        if(mainMenu_city.getText()!="") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //создаём объект alert для показа контактной информации пользователю
            alert.setTitle("Контактная информация");
            alert.setHeaderText("");
            alert.setContentText( mainMenu_jobList.getSelectionModel().getSelectedItem().getContactInformation() );
            alert.showAndWait();
        }
    }

    public void deleteFromSystem(){//удаление товара из системы и файла
        int selectedIndex=mainMenu_jobList.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0) {
            mainMenu_jobList.getItems().remove(selectedIndex);
            File file = new File("JobOpenings.txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                fr.write("");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(Job i : jobData){
                String info=i.getSalary()+":"+i.getName()+":"+i.getEducation()+":"+i.getWorkSchedule()+":"+i.getWorkExperience()+":"+i.getCity()+":"+i.getRequirements()+":"+i.getContactInformation();
                try {
                    fr = new FileWriter(file, true);
                    fr.write(info+"\n");
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addJob(){
        Stage stage = new Stage();
        stage.setTitle("Поиск работы");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("Добавить вакансию.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возврат на первую форму
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) mainMenu_ButtonJobSearch.getScene().getWindow();
        stage1.close();
    }

}//Всё работает из присутствующего