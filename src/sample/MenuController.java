package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

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
}
