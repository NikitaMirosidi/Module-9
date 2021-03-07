import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task3 {

    public void processFile() {
        String fileName = "Test3.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            throw new RuntimeException("Файл " + fileName + " не найден");
        }

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            List<String> arr = new ArrayList<>();
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
                    arr.set(i, counter + " " + arr.get(i));
                    continue;
                }

                for (int j = i + 1; j < arr.size(); j++) {
                    if (arr.get(i).equals(arr.get(j)) ) {
                        arr.remove(j);
                        j--;
                        counter++;
                    }
                }
                arr.set(i, counter + " " + arr.get(i));
            }

            // сортировка по добавленному счетчику
            arr.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int a = new Scanner(o1).nextInt();
                    int b = new Scanner(o2).nextInt();
                    return Integer.compare(b, a);
                }
            });

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