package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;

public class ControllerJobList {
    private final ObservableList<Job> jobData= FXCollections.observableArrayList();

    @FXML
    private TableView<Job> jobList_jobList;

    @FXML
    private TableColumn<Job, String> jobList_job;

    @FXML
    private Label jobList_education;

    @FXML
    private Label jobList_workSchedule;

    @FXML
    private Label jobList_workExperience;

    @FXML
    private Label jobList_city;

    @FXML
    private Label jobList_requirements;

    @FXML
    private Label jobList_salary;

    @FXML
    private Button jobList_buttonAddToFavourites;

    @FXML
    private Button jobList_buttonShowContactingInformation;

    @FXML
    private Button jobList_buttonToBack;

    @FXML
    void initialize(){
        fileToSystem();
        jobList_job.setCellValueFactory(new PropertyValueFactory<>("name"));
        jobList_jobList.setItems(jobData);
        showJobDetails(null);
        jobList_jobList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showJobDetails(newValue));
    }

    private void fileToSystem(){
        String path="FoundProfessions.txt";
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

    private void showJobDetails(Job job){
        if(job!=null){
            jobList_education.setText(job.getEducation());
            jobList_workSchedule.setText(job.getWorkSchedule());
            jobList_workExperience.setText(job.getWorkExperience());
            jobList_city.setText(job.getCity());
            jobList_requirements.setText(job.getRequirements());
            jobList_salary.setText(job.getSalary().toString());
        }else{
            jobList_education.setText("");
            jobList_workSchedule.setText("");
            jobList_workExperience.setText("");
            jobList_city.setText("");
            jobList_requirements.setText("");
            jobList_salary.setText("");
        }
    }

    public void toBack(){
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
        Stage stage1 = (Stage) jobList_buttonToBack.getScene().getWindow();
        stage1.close();
    }

    public void showContactInfo(){
        if(jobList_city.getText()!="") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //создаём объект alert для показа контактной информации пользователю
            alert.setTitle("Контактная информация");
            alert.setHeaderText("");
            alert.setContentText( jobList_jobList.getSelectionModel().getSelectedItem().getContactInformation() );
            alert.showAndWait();
        }
    }

    public void addToFavourites(){
        if(jobList_city.getText()!=""){
            File file = new File("Favourites.txt");
            FileWriter fr = null;
            try {
                fr = new FileWriter(file, true);
                fr.write(Long.parseLong(jobList_salary.getText())+":"+jobList_jobList.getSelectionModel().getSelectedItem().getName()+":"+jobList_education.getText()+":"+jobList_workSchedule.getText()+":"+jobList_workExperience.getText()+":"+jobList_city.getText()+":"+jobList_requirements.getText()+":"+jobList_jobList.getSelectionModel().getSelectedItem().getContactInformation()+"\n");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
