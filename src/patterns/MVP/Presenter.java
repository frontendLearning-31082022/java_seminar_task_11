package patterns.MVP;

import patterns.MVC.Model;
import patterns.MVC.ModelObserver;
import patterns.MVC.ModuleGeneral;

import javax.swing.*;

public class Presenter implements ModelObserver {

    View view;
    Model moduleGeneral;
    Presenter(View view){
        this.view=view;
        this.moduleGeneral=new ModuleGeneral();
        Thread thread2=new Thread(this.moduleGeneral,"moduleGeneral");
        thread2.start();

        moduleGeneral.registerObserver(this);

    }

    public void setThis() {
        // chain of requests to model
        Object asd= this.moduleGeneral.getSomething1();
        this.moduleGeneral.setSomething1(asd);
    }

    public void changeThat() {
        this.moduleGeneral.setSomething2(null);
    }

    public void sendData() {
        String inputField=view.getInputTextArea();
        this.moduleGeneral.receiveMessages(inputField);
    }

    private void logicGates(Object info){
        // if if if
        this.view.setDisplayText(info.toString());
    }

    private void errorUserInput(Exception error){
        this.view.setInputTextArea(error.getMessage());
    }

    @Override
    public void update(Object info) {
        logicGates(info); //logic
    }
}
