import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public void processFile() {
        String sourceFileName = "Test2.txt";
        File sourceFile = new File(sourceFileName);
        List<User> userList = new ArrayList<>();

        if (!sourceFile.exists()) {
            throw new RuntimeException("Файл " + sourceFileName + " не найден");
        }

        try (BufferedReader toRead = new BufferedReader(new FileReader(sourceFileName))) {
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

        String resultFileName = "Test2_result.txt";
        File resultFile = new File(resultFileName);

        try (BufferedWriter toWrite = new BufferedWriter(new FileWriter(resultFile))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            for (User temp : userList){
                String line = gson.toJson(temp);
                toWrite.append(line);
            }
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