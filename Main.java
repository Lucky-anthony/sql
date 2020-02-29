import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    VBox root = new VBox();
    HBox buttons = new HBox();
    Label status = new Label("STATUS:");
    TextField name = new TextField();
    TextField second_name = new TextField();
    TextField email = new TextField();
    Button ok = new Button("ADD USER");
    TextField number = new TextField();
    Button generate = new Button("GENERATE");

    @Override
    public void start(Stage primaryStage) throws Exception
    {


        root.getChildren().addAll(status, name, second_name, email);
        buttons.getChildren().addAll(ok, number, generate);
        root.getChildren().add(buttons);
        init();
        primaryStage.setTitle("Database example program");
        primaryStage.setScene(new Scene(root, 300, 110));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e ->
        {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }

    public void init()
    {
        DAO.createTables();

        number.setPromptText("NUMBER");
        name.setPromptText("NAME");
        second_name.setPromptText("SECOND NAME");
        email.setPromptText("EMAIL");

        number.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("^[1-9]$|^[1-9][0-9]$|^(100)$"))
                {
                    number.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        ok.setOnAction(e ->
        {
            boolean result = DAO.add_user(name.getText(), second_name.getText(), email.getText());
            if(result)
            {
                status.setText("STATUS: USER ADDED!");
                name.setText("");
                second_name.setText("");
                email.setText("");
            }
            else
            {
                status.setText("STATUS: USER WAS NOT ADDED!");
            }
        });

        generate.setOnAction(e ->
        {
            long n = Long.parseLong(number.getText());
            boolean result = DAO.add_user_list(User.generate_users(n));

            if(result)
            {
                status.setText("STATUS: " + n + " USERS ADDED!");
            }
            else
            {
                status.setText("STATUS: " + 1 + "USER ADDED DUE TO ERROR!");
            }
        });
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
