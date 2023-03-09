package com.example.theardpanelfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class MainCont {
    Thread t;
    @FXML
    Circle c1, c2;

    @FXML
    Polygon gun;

    @FXML
    Label cn;
    @FXML
    Label points;
    boolean run = false;
    boolean move_big_up = false;
    boolean move_small_up = true;
    boolean move_gun = false;

    public void MoveBigCircle(){
        double y = c1.getLayoutY();
        double r = c1.getRadius();
        if (y - r >= 0 && move_big_up == false){
            y -= 5;
            if (y - r<=0) move_big_up = true;
            c1.setLayoutY(y);
        }
        else if (y - (2*r) < 250.0 && move_big_up == true){
            y += 5;
            if (y - (2*r)>=250.0) move_big_up = false;
            c1.setLayoutY(y);
        }
    }

    public void MoveSmallCircle(){
        double y = c2.getLayoutY();
        double r = c2.getRadius();
        if (y - r > 0 && move_small_up == false){
            y -= 10;
            if (y - r<=0) move_small_up = true;
            c2.setLayoutY(y);
        }
        else if (y - (2*r) < 350.0 && move_small_up == true){
            y += 10;
            if (y - (2*r)>=350.0) move_small_up = false;
            c2.setLayoutY(y);
        }
    }
    public void MoveGun(){
        double x = gun.getLayoutX();
        double y = gun.getLayoutY();
        double y_c1 = c1.getLayoutY();
        double y_c2 = c2.getLayoutY();
        double x_c1 = c1.getLayoutX();
        double x_c2 = c2.getLayoutX();
        double r_c1 = c1.getRadius();
        double r_c2 = c2.getRadius();

        //(y <= y_c1+r_c1) && (y_c1 - r_c1 <= y)
        //(x <= x_c1 + r_c1) && (x_c1 - r_c1 <= x)
        x += 30;
        if ((y <= y_c1+r_c1) && (y_c1 - r_c1 <= y) && (x-30 <= x_c1 + r_c1) && (x_c1 - r_c1 <= x-30)){
            move_gun = false;
            Platform.runLater(
                    ()->{
                        int count = Integer.parseInt(points.getText());
                        count += 1;
                        points.setText(String.valueOf(count));
                        int count_all = Integer.parseInt(cn.getText());
                        count_all += 1;
                        cn.setText(String.valueOf(count_all));
                        gun.setLayoutX(141.0);
                    }
            );
        }
        if ((y <= y_c2+r_c2) && (y_c2 - r_c2 <= y) && (x-15 <= x_c2 + r_c2) && (x_c2 - r_c2 <= x-15)){
            move_gun = false;
            Platform.runLater(
                    ()->{
                        int count = Integer.parseInt(points.getText());
                        count += 2;
                        points.setText(String.valueOf(count));
                        int count_all = Integer.parseInt(cn.getText());
                        count_all += 1;
                        cn.setText(String.valueOf(count_all));
                        gun.setLayoutX(141.0);
                    }
            );
        }
        if (x >= 600) {
            move_gun = false;
            Platform.runLater(
                    () -> {
                        int count_all = Integer.parseInt(cn.getText());
                        count_all += 1;
                        cn.setText(String.valueOf(count_all));
                        gun.setLayoutX(141.0);
                    }
            );
        }

        gun.setLayoutX(x);
        }

    @FXML
    protected void runCircle(){
        if (t!=null) return;
        t = new Thread(
            ()->
            {
                Platform.runLater(
                        () -> {
                            gun.setLayoutX(141.0);
                            c1.setLayoutX(371.0);
                            c1.setLayoutY(202.0);
                            c2.setLayoutX(505.0);
                            c2.setLayoutY(202.0);
                            points.setText("0");
                            cn.setText("0");
                        }
                );
                run = true;
                while(run)
                {

                    if(move_gun == true) MoveGun();
                    MoveBigCircle();
                    MoveSmallCircle();
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        run = false;
                    }
                }

            }
        );

        t.start();

    }
    @FXML
    protected void onShot(){
        if (t!=null)
        {
            move_gun = true;
        }
    }
    @FXML
    protected void stopGame(){
        if (t!=null){
            t.interrupt();
            t = null;
            move_gun = false;

        }
    }
}
