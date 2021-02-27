import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public void processFile() {
        String fileName = "Test3.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            throw new RuntimeException("Файл " + fileName + " не найден");
        }

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> arr = new ArrayList<>();
            String line = buffer.readLine(); // построчное считывание потока (файла), разбиение строки на массив слов, запись в общий массив

            while (line != null){
                String[] tempArr = line.split("\\s+");
                arr.addAll(Arrays.asList(tempArr));
                line = buffer.readLine();
            }

            // подсчет повторений с удалением дубликатов и добавлением счетчика к каждому слову
            for (int i = 0; i < arr.size(); i++) {
                int counter = 1;

                if (i == arr.size() - 1) {
                    arr.set(i, arr.get(i) + " " + counter);
                    continue;
                }

                for (int j = i + 1; j < arr.size(); j++) {
                    if (arr.get(i).equals(arr.get(j)) ) {
                        arr.remove(j);
                        j--;
                        counter++;
                    }
                }
                arr.set(i, arr.get(i) + " " + counter);
            }

            // сортировка по добавленному в конец каждого слова счетчику
            for (int i = 0; i < arr.size(); i++) {
                int tempIndex = i;

                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(tempIndex).charAt(arr.get(tempIndex).length() - 1) < arr.get(j).charAt(arr.get(j).length() - 1)) {
                        arr.add(j + 1, arr.get(tempIndex));

                        if (j > tempIndex) {
                            arr.remove(tempIndex);
                            tempIndex = j;
                        }
                        else {
                            arr.remove(tempIndex + 1);
                            tempIndex = j + 1;
                        }
                    }
                }
                if (tempIndex > i){
                    i--;
                }
            }

            // вывод
            for (String s : arr) {
                System.out.println(s);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}