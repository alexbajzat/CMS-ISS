package com.frasinu.iss.view;

import com.frasinu.iss.main.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.frasinu.iss.view.controllers.BaseController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Paul on 5/7/17.
 */
@ComponentScan(basePackages = "com.frasinu.iss")
public class FrasinuApplication extends Application {

    private static Stage primaryStage;
    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FrasinuApplication.class);

    public static void changeScreen(Screen screen) {
        changeScreen(screen, null);
    }

    public static void createScreen(Screen screen){
        createScreen(screen,null);
    }

    public static void createScreen(Screen screen,HashMap<String, Object> data){
        Stage stage=new Stage();
        stage.setScene(createSceneFromFXML(screen.getName(), data, screen.getController()));
        stage.setTitle(screen.getTitle());
        stage.show();
    }

    public static void changeScreen(Screen screen, HashMap<String, Object> data) {
        primaryStage.setScene(createSceneFromFXML(screen.getName(), data, screen.getController()));
        primaryStage.setTitle(screen.getTitle());
        primaryStage.show();
    }

    private static Scene createSceneFromFXML(String name, HashMap<String, Object> data, String classController) {
        try {
            FXMLLoader loader = new FXMLLoader(FrasinuApplication.class.getResource("/layouts/" + name));

            BaseController baseController= null;
            try {
                baseController = (BaseController)applicationContext.getBean(classController);
            } catch (BeansException e) {
                e.printStackTrace();
            }
            loader.setController(baseController);
            Parent root = loader.load();
            //BaseController baseController = loader.getController();
            if (baseController == null) {
                throw new IllegalArgumentException("The " + name + " layout doesn't have a controller!");
            }
            baseController.setData(data);
            baseController.start();
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FrasinuApplication.primaryStage = primaryStage;
        changeScreen(Screen.LOGIN);
    }
}
