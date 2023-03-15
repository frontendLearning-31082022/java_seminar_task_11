package patterns.MVP;


import javax.swing.*;
import java.awt.*;

public class View_gui extends JFrame implements View {
    Presenter presenter;
    JTextArea displayInfo;
    JTextArea inputData = new JTextArea("input text Data here");

    View_gui() {


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.displayInfo = new JTextArea("not loaded yet");
        panel.add(displayInfo);

        JButton button = new JButton("DoThis");
        button.addActionListener(e -> presenter.setThis());
        panel.add(button);

        JButton button2 = new JButton("DoThat");
        button2.addActionListener(e -> presenter.changeThat());
        panel.add(button2);

        panel.add(inputData);
        JButton button3 = new JButton("Send text Data");
        button3.addActionListener(e -> presenter.sendData());
        panel.add(button3);

        this.add(panel);
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        presenter = new Presenter(this);
    }

    @Override
    public void setDisplayText(String data) {
        this.displayInfo.setText(data);
    }

    @Override
    public String getDisplayText() {
        return this.displayInfo.getText();
    }

    @Override
    public void setInputTextArea(String data) {
        this.inputData.setText(data);
    }

    @Override
    public String getInputTextArea() {
        return this.inputData.getText();
    }

}
