module edu.miracosta.cs112.finalproject.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens edu.miracosta.cs112.finalproject.finalproject to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject;
    exports edu.miracosta.cs112.finalproject.finalproject.Items;
    opens edu.miracosta.cs112.finalproject.finalproject.Items to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.Entities;
    opens edu.miracosta.cs112.finalproject.finalproject.Entities to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.GameRooms;
    opens edu.miracosta.cs112.finalproject.finalproject.GameRooms to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject.RewardItems;
    opens edu.miracosta.cs112.finalproject.finalproject.RewardItems to javafx.fxml;
}