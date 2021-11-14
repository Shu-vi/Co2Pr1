package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerRegistrationInSystem {

    @FXML
    private TextField registrationSystem_login;

    @FXML
    private TextField registrationSystem_password;

    @FXML
    private Button registrationSystem_buttonRegistration;

    public void registration(){
        User user= new User(registrationSystem_login.getText(), registrationSystem_password.getText());
        String path = "LogAndPass.txt";
        if (user.loginExist()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //создаём объект alert для вывода сообщений пользователю
            alert.setTitle("Регистрация");
            alert.setHeaderText("");
            alert.setContentText("Данный логин уже занят.");
            alert.showAndWait();
        }else{
            File file = new File(path);
            FileWriter fr = null;
            String logPas=user.getLogin()+':'+user.getPassword();
            try {
                fr = new FileWriter(file,true);
                fr.write(logPas+"\n");
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
            Stage stage1 = (Stage) registrationSystem_buttonRegistration.getScene().getWindow();
            stage1.close();
        }
    }

}//всё работает
