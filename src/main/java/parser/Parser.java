package parser;

import engine.UtilityFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Parser {
    private final String path;
    private String[] parseArray;
    private List<Config> configs;
    public Parser(String path){
        this.path = path;
        parse(path);
        StringArToConfigClass(parseArray);
    }
    public void parse(String path){
        StringBuilder arrayString = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                arrayString.append(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        String regex = "###";
        parseArray = arrayString.toString().split(regex);
    }
    public void StringArToConfigClass(String[] array){
        configs = new ArrayList<>();
        for(String str: array){
            Config config = StringToConfig(str);
            configs.add(config);
        }
    }

    /*
    * [INP] val -
    * */
    public Config StringToConfig(String val){
        Config config = new Config();
        String[] arStr = val.split(";");
        for(String vl: arStr) {
            String[] vl_arr = vl.split("_");
            if(vl_arr.length > 1) {
                if (vl_arr[0].equals("name")) {
                    config.name = vl_arr[1];
                }
                if (vl_arr[0].equals("coords")) {
                    config.coords = UtilityFunctions.stringToDoubleDoubleArray(vl_arr[1],"!",",");
                }
                if(vl_arr[0].equals("table")){
                    config.configTable = Config.getConfigTable(vl_arr[1]);
                }
                if(vl_arr[0].equals("college")){
                    config.configCollege = Config.getConfigCollege(vl_arr[1]);
                    System.out.println("College!");
                }
            }

        }
        return config;
    }
    public List<Config> getConfigs() {
        return configs;
    }
    public String getPath() {
        return path;
    }
}

