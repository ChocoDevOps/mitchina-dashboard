module me.lorenzo.mitchinadashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens me.lorenzo.mitchinadashboard to javafx.fxml;
    exports me.lorenzo.mitchinadashboard;
    exports me.lorenzo.mitchinadashboard.controller;
    exports me.lorenzo.mitchinadashboard.callback;
    opens me.lorenzo.mitchinadashboard.controller to javafx.fxml;
}