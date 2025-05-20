module edu.miracosta.cs112.finalproject.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens edu.miracosta.cs112.finalproject.finalproject to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject;
    exports edu.miracosta.cs112.finalproject.finalproject.Items;
    opens edu.miracosta.cs112.finalproject.finalproject.Items to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.Entities;
    opens edu.miracosta.cs112.finalproject.finalproject.Entities to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.lib;
    opens edu.miracosta.cs112.finalproject.finalproject.lib to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.controllers;
    opens edu.miracosta.cs112.finalproject.finalproject.controllers to javafx.fxml;
}