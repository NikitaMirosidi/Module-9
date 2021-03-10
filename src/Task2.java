import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Task2 {
    public void processFile(String fileName) {
        File sourceFile = new File(fileName);
        List<User> userList = new ArrayList<>();

        if (!sourceFile.exists()) {
            throw new RuntimeException("Файл " + fileName + " не найден");
        }

        try (BufferedReader toRead = new BufferedReader(new FileReader(fileName))) {
            String line = toRead.readLine();

            while (line != null){
                String[] temp = line.split(" ");
                userList.add(new User(temp[0], temp[1]));
                line = toRead.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String resultFileName = "Test2_result.json";
        File resultFile = new File(resultFileName);

        try (BufferedWriter toWrite = new BufferedWriter(new FileWriter(resultFile))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<String> jsonArr = userList.stream().
                    map(gson::toJson)
                    .collect(Collectors.toList());
            toWrite.append(jsonArr.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class User {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}