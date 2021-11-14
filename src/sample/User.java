package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    функция сравнивает переданный логин с логинами из файла LogAndPas.txt.
    Возвращает true, если совпадение есть
     */
    public boolean loginExist(){
        BufferedReader br = null;
        String log="";
        String nameOfFile="LogAndPass.txt";
        boolean exist=false;//проверяем существование логина
        try{
            File file = new File(nameOfFile);
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(nameOfFile));
            String line;
            while( ( ( line = br.readLine() ) != null) && !exist){
                int i=0;
                log="";
                while (line.charAt(i)!=':'){
                    log=log+line.charAt(i);
                    i++;
                }
                exist= Objects.equals(getLogin(), log); // проверяем, зарегестрирован ли логин
            }
        } catch(IOException e){
            System.out.println("Error: "+ e);
        } finally {
            try {
                br.close();
            }catch(IOException e) {
                System.out.println("Error: " + e);
            }
        }
        return exist;
    }


    /*
    функция ищет нужный логин, а когда находит его - считывает пароль из файла LogAndPas.txt.
    Возвращает true, если введённый пароль совпадает с паролем из файла
     */
    public boolean passwordCorrect(){
        BufferedReader br = null;
        String log="";
        String pas="";
        String nameOfFile="LogAndPass.txt";
        boolean exist=false;//в функции переменная используется для поиска нужного логина
        int i = 0;
        try{
            File file = new File(nameOfFile);
            if (!file.exists())
                file.createNewFile();
            br = new BufferedReader(new FileReader(nameOfFile));
            String line;
            while( ( ( line = br.readLine() ) != null)&& !exist){//проверяем зарегистрированные логины и пароли
                i=0;
                log="";
                while (line.charAt(i)!=':'){//перебираем логины
                    log=log+line.charAt(i);
                    i++;
                }
                exist= Objects.equals(getLogin(), log); // сравниваем и проверяем, нужный ли логин нашли
                i++;
                while (i<line.length()&& exist){ //если нашли нужный логин, то считываем пароль
                    pas=pas+line.charAt(i);
                    i++;
                }
            }
        } catch(IOException e){
            System.out.println("Error: "+ e);
        } finally {
            try {
                br.close();
            }catch(IOException e) {
                System.out.println("Error: " + e);
            }
        }
        return Objects.equals(getPassword(), pas);//сравниваем пароли
    }
}
