package patterns.MVC;

public class MVC {
    public static void main(String[] args) {
        ModuleGeneral moduleGeneral=new ModuleGeneral();
        Thread thread2=new Thread(moduleGeneral,"moduleGeneral");
        thread2.start();

        ControllerModuleView controller=new ControllerModuleView(moduleGeneral);
    }
}
