package Singleton;

public class Singleton {
    // static variable for the only instance of this class
    private static Singleton singleton;

    /**
     * private constructor so no new instance can be initialized
     */
    private Singleton(){ }

    /**
     * if no there is no class instance yet initialise one
     * @return class instace
     */
    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    /**
     * instance method defining the use of the singleton
     * @param s
     */
    public void instanceMethod(String s){
        System.out.println(s);
    }
}
