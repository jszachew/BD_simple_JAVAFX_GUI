package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class MenuController {
    private Controller mainController;

    @FXML
    public void openPracownicy(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Pracownicy.fxml"));
        Pane pane = loader.load();

        PracownicyController pracownicyController = loader.getController();
        pracownicyController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void openDzialy(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Dzialy.fxml"));
        Pane pane = loader.load();

        DzialyController dzialyController = loader.getController();
        dzialyController.setMainController(mainController);
        mainController.setScreen(pane);

    }

    public void setMainController(Controller mainController) {

        this.mainController = mainController;
    }

    public void dodaj10000Prac(ActionEvent actionEvent)
    {
        PracownicyController.dodaj1000();
    }

    public void dodaj10000dzialow(ActionEvent actionEvent) throws SQLException {
        DzialyController.dodaj10000();
    }
}
