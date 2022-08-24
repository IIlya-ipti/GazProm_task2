package ConnectedObjects;
/*
* some abstraction for working with animations of turning objects on and off
* */
public interface Animation {
    /**
     * enable object
     */
    void on();
    /**
     * disable object
     */
    void off();
    /**
    * a function to check if an object is active
    * */
    boolean isActive();
}
