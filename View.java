import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class View extends Stage
{
    public ArrayList<User> users = new ArrayList<>();
    ScrollPane sp;
    VBox main = new VBox();
    HBox data = new HBox();
    VBox id = new VBox();
    VBox name = new VBox();
    VBox second_name = new VBox();
    VBox email = new VBox();
    Button reload = new Button("RELOAD");
    Scene sc;
    public View()
    {
        this.setResizable(false);
        this.setTitle("VIEW DATA");
        this.initModality(Modality.NONE);
        sc = new Scene(main, 800, 800);
        this.setScene(sc);
        reload.setOnAction(e -> reload());
        main.getChildren().add(reload);

        //main.getChildren().add(data);
        data.getChildren().add(id);
        data.getChildren().add(name);
        data.getChildren().add(second_name);
        data.getChildren().add(email);

        sp = new ScrollPane(data);
        sp.setFitToWidth(true);
        main.getChildren().add(sp);
    }

    public void reload()
    {
        users = (ArrayList<User>) DAO.select_all().clone();
        id.getChildren().clear();
        name.getChildren().clear();
        second_name.getChildren().clear();
        email.getChildren().clear();

        for(User u : users)
        {
            id.getChildren().add(new Label(String.valueOf(u.id)));
            name.getChildren().add(new Label("     " + u.name));
            second_name.getChildren().add(new Label("     " + u.second_name));
            email.getChildren().add(new Label("     " + u.email));
        }
    }
}
