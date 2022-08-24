package parser;

import engine.UserPath;

public class Config {
    public String name;
    public double[][] coords;
    public ConfigTable configTable;
    public ConfigCollege configCollege;
    public static ConfigTable getConfigTable(String string){
        ConfigTable configTable = new ConfigTable();
        String[] arStr = string.split(",");
        for(String str: arStr){
            String[] arArStr = str.split("=");
            if(arArStr[0].equals("name")){
                configTable.name = arArStr[1];
            }
            if(arArStr[0].equals("longProject")){
                configTable.longProject = arArStr[1];
            }
            if(arArStr[0].equals("shortProject")){
                configTable.shortProject = arArStr[1];
            }
            if(arArStr[0].equals("totalWorkers")){
                configTable.totalWorkers = arArStr[1];
            }
            if(arArStr[0].equals("period")){
                configTable.period = arArStr[1];
            }
            if(arArStr[0].equals("vlA")){
                configTable.vlA = arArStr[1];
            }
            if(arArStr[0].equals("vlB")){
                configTable.vlB = arArStr[1];
            }
        }
        return configTable;
    }
    public static ConfigCollege getConfigCollege(String string){
        ConfigCollege configCollege = new ConfigCollege();
        String[] arStr = string.split(",");
        for(String str: arStr){
            String[] arArStr = str.split("=");
            if(arArStr[0].equals("path")){
                configCollege.path = UserPath.valueOf(arArStr[1]);
            }
            if(arArStr[0].equals("cordX")){
                configCollege.cordX = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("cordY")){
                configCollege.cordY = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("width")){
                configCollege.width = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("height")){
                configCollege.height = Double.parseDouble(arArStr[1]);
            }
        }
        return configCollege;
    }
    public String toString(){
        StringBuilder str = new StringBuilder("###\n");
        if(this.configCollege != null){
            str.
                    append("college_\n");
            str.
                    append("path=").
                    append(this.configCollege.path).
                    append(",\n");
            str.
                    append("cordX=").
                    append(this.configCollege.cordX).
                    append(",\n");
            str.
                    append("cordY=").
                    append(this.configCollege.cordY).
                    append(",\n");
            str.
                    append("width=").
                    append(this.configCollege.width).
                    append(",\n");
            str.
                    append("height=").
                    append(this.configCollege.height);
            str.append(";\n");
        }
        if(this.name != null) {
            str.
                    append("name_\n").
                    append(this.name).
                    append(";\n");
            if (this.coords != null) {
                str.
                        append("coords_\n").
                        append(coordsToString(this.coords)).
                        append(";\n");
            }
            if (configTable != null) {
                str.
                        append("table_\n");
                str.
                        append("name=").
                        append(this.configTable.name).
                        append(",\n");
                str.
                        append("longProject=").
                        append(this.configTable.longProject).
                        append(",\n");
                str.
                        append("shortProject=").
                        append(this.configTable.shortProject).
                        append(",\n");
                str.
                        append("totalWorkers=").
                        append(this.configTable.totalWorkers).
                        append(",\n");
                str.
                        append("period=").
                        append(this.configTable.period);
                if (this.configTable.vlA != null) {
                    str.append(",\n");
                    str.
                            append("vlA=").
                            append(this.configTable.vlA).
                            append(",\n");
                    str.
                            append("vlB=").
                            append(this.configTable.vlB).
                            append(";\n");
                } else {
                    str.append(";\n");
                }
            }
        }
        return str.toString();

    }
    public static String coordsToString(double[] values){
        StringBuilder str = new StringBuilder("");
        int ind = 0;
        for(double val: values){
            str.append(val).append(",");
            if(++ind % 2 == 0){
                str.append("\n");
            }
        }
        if(str.length() > 0) {
            str = new StringBuilder(str.substring(0, str.length() - 2));
        }
        return str.toString();
    }
    public static String coordsToString(double[][] values){
        StringBuilder str = new StringBuilder("");
        for(double[] arVal : values){
            str.append(coordsToString(arVal));
            str.append("!\n");
        }
        return str.toString();
    }

}
