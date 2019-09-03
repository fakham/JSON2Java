package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(Paths.get(".").toAbsolutePath().normalize().toString() + "/src/com/company/example.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject data = (JSONObject) obj;
            //System.out.println(data);

            String className = data.get("name").toString();
            List<String> propsName = new ArrayList<>();
            List<String> types = new ArrayList<>();
            List<Property> properties = new ArrayList<>();
            List<Function> functions = new ArrayList<>();

            JSONObject props = (JSONObject) data.get("properties");
            JSONArray funs = (JSONArray) data.get("functions");

            props.keySet().forEach(key -> propsName.add(key.toString()));
            props.values().forEach(value -> types.add(((JSONObject)value).get("type").toString()));

            for (int i = 0; i < propsName.size() || i < types.size(); i++) {
                Property property = new Property(propsName.get(i), types.get(i));
                properties.add(property);
            }

            funs.forEach(f -> {
                JSONObject fun = (JSONObject) f;
                Function function = new Function();
                List<Argument> arguments = new ArrayList<>();

                function.setName(fun.get("name").toString());
                function.setType(fun.get("returnType").toString());

                JSONArray ars = (JSONArray) fun.get("arguments");
                ars.forEach(a -> {
                    Argument argument = new Argument(((JSONObject)a).get("name").toString(), ((JSONObject)a).get("type").toString());
                    arguments.add(argument);
                });

                function.setArguments(arguments);
                functions.add(function);
            });

            System.out.println("public class " + className + " {\n");

            properties.forEach(p -> {
                System.out.print("\tpublic ");

                if (p.getType().equals("integer"))
                    System.out.print("int ");
                else if (p.getType().equals("character"))
                    System.out.print("char ");
                else
                    System.out.print(p.getType() + " ");

                System.out.println(p.getName() + ";");
            });

            System.out.println();

            functions.forEach(f -> {
                System.out.print("\tpublic " + f.getType() + " " + f.getName() + " (");
                for (int i = 0; i < f.getArguments().size(); i++) {
                    Argument a = f.getArguments().get(i);
                    System.out.print(a.getType() + " " + a.getName());
                    if (i + 1< f.getArguments().size())
                        System.out.print(", ");
                }
                System.out.print(") {\n");
                System.out.println("\t\treturn;");
                System.out.println("\t}");
            });

            System.out.println("}");


//            propsName.forEach(x -> System.out.print(x.toString() + " "));
//            System.out.println();
//            types.forEach(x -> System.out.print(x.toString() + " "));

            //Iterate over employee array
           // employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
