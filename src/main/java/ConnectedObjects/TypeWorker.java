package ConnectedObjects;

public enum TypeWorker {
    EMPLOYEES,
    WORKERS,
    MANAGERS;

    static TypeWorker getType(String typeName) {
        if (typeName.equalsIgnoreCase("один")) {
            return TypeWorker.EMPLOYEES;
        }
        if (typeName.equalsIgnoreCase("два")) {
            return TypeWorker.WORKERS;
        }
        if (typeName.equalsIgnoreCase("три")) {
            return TypeWorker.MANAGERS;
        }
        return null;
    }
}
