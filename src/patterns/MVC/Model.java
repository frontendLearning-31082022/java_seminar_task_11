package patterns.MVC;

public interface Model extends Runnable{

    void on();
    void off();

    Object getSomething1();
    Object getSomething2();

    void setSomething1(Object o);
    void setSomething2(Object o);

    void registerObserver(ModelObserver modelObserver);

    void receiveMessages(String data);
}
