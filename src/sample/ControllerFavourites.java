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

public class ControllerFavourites {
    private final ObservableList<Job> jobData= FXCollections.observableArrayList();

    @FXML
    private TableView<Job> favourites_jobList;

    @FXML
    private Button favourites_buttonDeleteFromFavourites;

    @FXML
    private Button favourites_buttonContactInformation;

    @FXML
    private TableColumn<Job, String> favourites_job;

    @FXML
    private Label favourites_education;

    @FXML
    private Label favourites_workSchedule;

    @FXML
    private Label favourites_workExperience;

    @FXML
    private Label favourites_city;

    @FXML
    private Label favourites_requirements;

    @FXML
    private Label favourites_salary;

    @FXML
    private Button favourites_buttonAllJobsList;

    @FXML
    private Button favourites_buttonJobFilter;

    @FXML
    private Button favourites_buttonExit;

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
        Stage stage1 = (Stage) favourites_buttonAllJobsList.getScene().getWindow();
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
        Stage stage1 = (Stage) favourites_buttonAllJobsList.getScene().getWindow();
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
        Stage stage1 = (Stage) favourites_buttonAllJobsList.getScene().getWindow();
        stage1.close();
    }

    @FXML
    public void initialize (){
        fileToSystem();
        favourites_job.setCellValueFactory(new PropertyValueFactory<>("name"));
        favourites_jobList.setItems(jobData);
        showJobDetails(null);
        favourites_jobList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showJobDetails(newValue));
    }

    private void showJobDetails(Job job){
        if(job!=null){
            favourites_education.setText(job.getEducation());
            favourites_workSchedule.setText(job.getWorkSchedule());
            favourites_workExperience.setText(job.getWorkExperience());
            favourites_city.setText(job.getCity());
            favourites_requirements.setText(job.getRequirements());
            favourites_salary.setText(job.getSalary().toString());
        }else{
            favourites_education.setText("");
            favourites_workSchedule.setText("");
            favourites_workExperience.setText("");
            favourites_city.setText("");
            favourites_requirements.setText("");
            favourites_salary.setText("");
        }
    }

    private void fileToSystem(){
        String path="Favourites.txt";
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

    public void deleteFromFavourites(){//удаление товара из системы и файла
        int selectedIndex=favourites_jobList.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0) {
            favourites_jobList.getItems().remove(selectedIndex);
            File file = new File("Favourites.txt");
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

    public void showContactInformation(){
        if(favourites_city.getText()!="") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //создаём объект alert для показа контактной информации пользователю
            alert.setTitle("Контактная информация");
            alert.setHeaderText("");
            alert.setContentText( favourites_jobList.getSelectionModel().getSelectedItem().getContactInformation() );
            alert.showAndWait();
        }
    }
}//всё работает!!!
