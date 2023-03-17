package patterns;

public class Prototype {
    static class ObjectConstructor implements Cloneable{
        int num=0;
        ObjectConstructor(){
            deserizlizeSQL10gb();
            makeLongCalculations();
        }
        @Override
        public ObjectConstructor clone() {
            try {return (ObjectConstructor) super.clone();
            } catch (CloneNotSupportedException e) {throw new AssertionError();}
        }
        public void analyzeRefactorObjectData() {}
        public void doProcess() {}
        public void designNewLogic() {}
    }

    private static void makeLongCalculations() {}
    private static void deserizlizeSQL10gb() {}

    public static void main(String[] args) {
        ObjectConstructor objectConstructor=new ObjectConstructor();

        ObjectConstructor objectConstructorVer2=objectConstructor.clone();
        objectConstructorVer2.analyzeRefactorObjectData();
        objectConstructorVer2.doProcess();

        objectConstructorVer2.num=1;
        objectConstructorVer2.designNewLogic();
    }
}
