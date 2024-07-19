import java.lang.*;

class SingletonClass extends Object {

    private static SingletonClass instance = new SingletonClass();

    private SingletonClass() {
        super();
    }

    public static SingletonClass getInstance() {
        return instance;
    }

    public void sayHello() {
        System.out.println("Hello");
    }

}

public class SingletonTest {
    public static void main(String[] args) {
        SingletonClass sc1 = SingletonClass.getInstance();
        SingletonClass sc2 = SingletonClass.getInstance();
    }
}