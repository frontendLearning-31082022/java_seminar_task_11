package patterns.MVC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ModuleGeneral implements Model{

    List<ModelObserver>observers=new ArrayList<>();
    @Override
    public void on() {}

    @Override
    public void off() {}

    @Override
    public Object getSomething1() {
        notifyObservers("cmd execute getSomething1");
        return null;}
    @Override
    public Object getSomething2() {
        notifyObservers("cmd execute getSomething2");
        return new Object();}

    @Override
    public void setSomething1(Object o) {notifyObservers("setSomething1");}

    @Override
    public void setSomething2(Object o) {
        notifyObservers("setSomething2");}

    @Override
    public void registerObserver(ModelObserver modelObserver) {
        this.observers.add(modelObserver);
    }

    @Override
    public void receiveMessages(String data) {
        notifyObservers("received - "+data);
    }


    void notifyObservers(String data){
        for (ModelObserver observer : observers) {
            observer.update(data+ " (at module)");
        }
    }



    @Override
    public void run() {
        while (true){
            doStuff();
            notifyObservers("nothing matter");
            try {Thread.sleep(5000);} catch (InterruptedException e) {}
        }
    }

    void doStuff(){}
}
