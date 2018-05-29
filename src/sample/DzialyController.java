package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class DzialyController {

    public Button updateButton;
    public TextField updateIDKierText;
    public TextField updateNazwaDzialuText;
    public TextField updateIDDzialu;
    private Controller mainController = null;
    ObservableList list = FXCollections.observableArrayList();
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list11 = FXCollections.observableArrayList();
    static DBConnect connect = new DBConnect();
    @FXML ListView<String> FXList;
    @FXML ListView<String> FXList1;
    @FXML ListView<String> FXList11;
    static PracownicyController PController = new PracownicyController();

    public  void initialize() throws SQLException {

        list.removeAll(list);
        ResultSet rs = connect.getData("dzialy");
        while (rs.next())
        {

            String idd = rs.getString("iddzialy");
            String name = rs.getString("Nazwa_dzialu");
            String kierownik = rs.getString("pracownicy_idPracownicy_kierownik");

            list.addAll(idd);
            list1.addAll( name);
            list11.addAll( kierownik);

        }

        FXList.getItems().addAll(list);
        FXList1.getItems().addAll(list1);
        FXList11.getItems().addAll(list11);

    }

    public void back(ActionEvent actionEvent) {
    mainController.loadMenuScreen();
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void updateInfo(ActionEvent actionEvent) throws SQLException {
        String Nazwa = updateNazwaDzialuText.getText();
        int IDKierowinika=Integer.parseInt(updateIDKierText.getText());
        int IDtoUpdate = Integer.parseInt(updateIDDzialu.getText());
        connect.updateDepartments(IDtoUpdate,Nazwa,IDKierowinika);
    }

    public static void dodaj10000() throws SQLException {
        String nazwa;
        int idKierownika;

        for(int i=0; i<10000; i++)
        {
            nazwa = RandomString.generate(10);
            idKierownika = PController.getRandromKierownik();
            connect.addDepartment(nazwa,idKierownika);
        }


    }
}
