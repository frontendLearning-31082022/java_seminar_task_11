package patterns.MVC;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements ModelObserver {

    Controller controller;

    JTextArea displayInfo;
    JTextArea inputData=new JTextArea("input text Data here");

    View(Controller controller,Model moduleGeneral){
        this.controller =controller;
        moduleGeneral.registerObserver(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());



        displayInfo=new JTextArea("not loaded yet");
        panel.add(displayInfo);

        JButton button = new JButton("DoThis");
        button.addActionListener(e -> setThis());
        panel.add(button);

        JButton button2 = new JButton("DoThat");
        button2.addActionListener(e -> changeThat());
        panel.add(button2);


        panel.add(inputData);
        JButton button3 = new JButton("Send text Data");
        button3.addActionListener(e -> sendData());
        panel.add(button3);



        this.add(panel);
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    void setThis(){
        this.controller.makeSeveralRequestToDoThis();
    }

    void changeThat(){
        this.controller.makeRequestToDoThat();
    }

    void sendData(){
        this.controller.sendData(this.inputData.getText());
    }


    @Override
    public void update(Object info) {
        this.displayInfo.setText(info.toString());
    }





}
