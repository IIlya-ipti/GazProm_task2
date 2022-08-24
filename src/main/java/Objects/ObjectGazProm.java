package Objects;


/**
 * class of gazprom object (for init params to second slide)
 * */

@Deprecated
public class ObjectGazProm {
    private String name;
    private String data;
    private int MG;
    private int GPA;
    private int GRC;
    public ObjectGazProm(String name,String data,int MG,int GPA,int GRC){
        this.name = name;
        this.data = data;
        this.MG = MG;
        this.GPA = GPA;
        this.GRC = GRC;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public int getGPA() {
        return GPA;
    }

    public int getGRC() {
        return GRC;
    }

    public int getMG() {
        return MG;
    }

    public String getData() {
        return data;
    }

}
