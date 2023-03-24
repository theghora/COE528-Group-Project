/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.group.project;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author super
 */
public abstract class singletonWindow {
        //this is really ploopy
        //I'd rather not make everything singleton tbh
        Scene scene;
        Stage stage;
        StackPane window;
        String title;
        public void show(Stage p){
            p.setTitle(title);
            p.setScene(scene);
            stage = p;
        }
}
