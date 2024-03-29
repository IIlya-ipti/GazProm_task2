package parser;

import engine.UserPath;
import engine.UtilityFunctions;




/**
 * this class contains all information from config file
 * */
public class Config {
    public ConfigPipe       configPipe;
    public ConfigTable      configTable;
    public ConfigCollege    configCollege;
    public ConfigField      configField;

    public static ConfigPipe getConfigPipe(String string){
        ConfigPipe configPipe = new ConfigPipe();
        String[] arStr = string.split(";");
        for(String str: arStr){
            String[] arArStr = str.split("=");
            if(arArStr.length > 1) {
                if (arArStr[0].equals("name")) {
                    configPipe.name = arArStr[1];
                }
                if (arArStr[0].equals("coords")) {
                    configPipe.coords = UtilityFunctions.stringToDoubleDoubleArray(arArStr[1], "!", ",");
                }
            }
        }
        return configPipe;
    }
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
            if(arArStr[0].equals("cordX")){
                configTable.cordX = arArStr[1];
            }
            if(arArStr[0].equals("cordY")){
                configTable.cordY = arArStr[1];
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
    public static ConfigField getConfigField(String string){
        ConfigField configField = new ConfigField();
        String[] arStr = string.split(",");
        for(String str: arStr){
            String[] arArStr = str.split("=");
            if(arArStr[0].equals("path")){
                configField.path = UserPath.valueOf(arArStr[1]);
            }
            if(arArStr[0].equals("cordX")){
                configField.cordX = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("cordY")){
                configField.cordY = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("width")){
                configField.width = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("height")){
                configField.height = Double.parseDouble(arArStr[1]);
            }
            if(arArStr[0].equals("name")){
                configField.name = arArStr[1];
            }
        }
        return configField;
    }
    public String toString(){
        StringBuilder str = new StringBuilder("###\n");
        if(this.configField != null){
            str.
                    append("field_\n");
            str.
                    append("name=").
                    append(this.configField.name).
                    append(",\n");
            str.
                    append("path=").
                    append(this.configField.path).
                    append(",\n");
            str.
                    append("cordX=").
                    append(this.configField.cordX).
                    append(",\n");
            str.
                    append("cordY=").
                    append(this.configField.cordY).
                    append(",\n");
            str.
                    append("width=").
                    append(this.configField.width).
                    append(",\n");
            str.
                    append("height=").
                    append(this.configField.height);
            str.append("__\n");
        }
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
            str.append("__\n");
        }
        if(this.configPipe != null) {
            str.
                    append("pipe_\n");
            str.
                    append("name=").
                    append(this.configPipe.name).
                    append(";\n");
            if (this.configPipe.coords != null) {
                str.
                        append("coords=\n").
                        append(coordsToString(this.configPipe.coords));
            }
            str.append("__\n");
        }
        if (this.configTable != null) {
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
            if (this.configTable.cordX != null) {
                str.append(",\n");
                str.
                        append("cordX=").
                        append(this.configTable.cordX).
                        append(",\n");
                str.
                        append("cordY=").
                        append(this.configTable.cordY).
                        append("__\n");
            } else {
                str.append("__\n");
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
