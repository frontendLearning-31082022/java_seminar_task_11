package patterns.MVC;

public class ControllerModuleView implements Controller{
    Model model;
    View view;

    public ControllerModuleView(Model model) {
        this.model = model;
        this.view=new View(this,model);
    }

    @Override
    public void makeSeveralRequestToDoThis() {
        // chain of requests to model
        Object asd= this.model.getSomething1();
        this.model.setSomething1(asd);
    }

    @Override
    public void makeRequestToDoThat() {
        this.model.setSomething2(null);
    }

    @Override
    public void sendData(String data) {
        this.model.setSomething2(new Object());
        this.model.receiveMessages(data);
    }
}
