package sample;


import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PracownicyController {

    public Label labelAvgr = null;
    public Button delBttn;
    public TextField toDelText;
    private Controller mainController = null;
    private int avg;
    private int nOfEmp;


    static ObservableList listID = FXCollections.observableArrayList();
    ObservableList listImie = FXCollections.observableArrayList();
    ObservableList listNazwisko = FXCollections.observableArrayList();
    ObservableList listPensja = FXCollections.observableArrayList();
    ObservableList listStaz = FXCollections.observableArrayList();
    ObservableList listStanowisko = FXCollections.observableArrayList();
   static DBConnect connect = new DBConnect();
    @FXML
   static ListView<String> FXListID;
    @FXML ListView<String> FXListImie;
    @FXML ListView<String> FXListNazwisko;
    @FXML ListView<String> FXListPensja;
    @FXML ListView<String> FXListStaz;
    @FXML ListView<String> FXListStanowsko;
   @FXML private TextField dodajImie;
   @FXML private TextField dodajNazwisko;
   @FXML private TextField dodajPensja;
   @FXML private TextField dodajStaz;
   @FXML private TextField dodajStanowisko;

    public void initialize() throws SQLException {
        listID.removeAll(listID);
        avg=0;
        nOfEmp=0;
        ResultSet rs = connect.getData("pracownicy");
        while (rs.next())
        {

            String idd = rs.getString("idPracownicy");
            String imie = rs.getString("Imie");
            String nazwisko = rs.getString("Nazwisko");
            String pensja = rs.getString("Pensja");
            String staz = rs.getString("Staz_pracy");
            String stanowsko = rs.getString("Stanowsko");

            listID.addAll(idd);
            listImie.addAll(imie);
            listNazwisko.addAll( nazwisko);
            listPensja.addAll(pensja);
            listStanowisko.addAll( stanowsko);
            listStaz.addAll( staz);
            nOfEmp++;
            avg += Integer.parseInt(pensja);
        }
        int sum=avg;
        listNazwisko.addAll("Srednia:");
        listNazwisko.addAll("Laczny koszt:");
        avg=(avg/nOfEmp);
        String toShowAvg= Integer.toString(avg);
        String toShowSum= Integer.toString(sum);
        listPensja.addAll(toShowAvg);
        listPensja.addAll(toShowSum);

        FXListID.getItems().addAll(listID);
        FXListImie.getItems().addAll(listImie);
        FXListNazwisko.getItems().addAll(listNazwisko);
        FXListPensja.getItems().addAll(listPensja);
        FXListStaz.getItems().addAll(listStaz);
        FXListStanowsko.getItems().addAll(listStanowisko);


    }
    
    

    public void back(ActionEvent actionEvent) {
        mainController.loadMenuScreen();
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }


    public void dodajPrac(ActionEvent actionEvent) throws SQLException {
        String Imie, Nazwisko, Stanowisko;
        int Staz, Pensja;
        Imie = dodajImie.getText();
        Nazwisko = dodajNazwisko.getText();
         Stanowisko = dodajStanowisko.getText();
        Staz = Integer.parseInt(dodajStaz.getText());
        Pensja = Integer.parseInt(dodajPensja.getText());

        connect.addEmployee(Imie,Nazwisko,Pensja,Staz,Stanowisko);
    }

    public int getRandromKierownik() throws SQLException {
        ResultSet rs = connect.getData("pracownicy");
        Random r=new Random();
        int size=0;
        int ID,rowManager;
        while(rs.next())
        {
            size++;
        }

        rowManager=r.nextInt(size);
        int a=1;
        ResultSet as=connect.getData("pracownicy");

        while(a<rowManager)
        {
            as.next();
            a++;
        }

        String idd = as.getString("idPracownicy");
        ID=Integer.parseInt(idd);
        System.out.println(ID);
        return ID;
    }

    public void usunPrac(ActionEvent actionEvent) throws SQLException {
        int toDel = Integer.parseInt(toDelText.getText());
        try
        {
            connect.removeEmployee(toDel);
        }
        catch (Exception e)
        {
            System.out.println("Foreign key");
            toDelText.setText("!KLUCZ OBCY!");
        }
        
    }

    public static void dodaj1000()
    {
        Random r=new Random();
        for(int i=0; i<10000; i++)
        {
            String Imie, Nazwisko, Stanowisko;
            int Pensja, Staz;
            Pensja=r.nextInt(10000);
            Staz=r.nextInt(50);
            Imie = RandomString.generate(7);
            Nazwisko = RandomString.generate(10);
            Stanowisko = RandomString.generate(10);

            try {
                connect.addEmployee(Imie,Nazwisko,Pensja,Staz,Stanowisko);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
