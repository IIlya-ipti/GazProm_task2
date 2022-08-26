package ConnectedObjects;

/**
 * object connection interface
 * */
public interface ConnectedObject {
    void connect(Information information);
    void connect(Worker worker);
    void connect(UsedObject usedObject, Plan plan);
    void connect(Menu menu);
    void connect(Pipe pipe, Plan plan);

    /**
    * adding connection to map (to then search for connections by key)
    * */
    void connect(Connect connect, ConnectedObject key);

    /**
     * disconnect this and connectedObject objects
     */
    void disConnect(ConnectedObject connectedObject);
}
