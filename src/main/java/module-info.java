module gui.ija_projekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui.ija_projekt to javafx.fxml;
    exports gui.ija_projekt;
}