import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    public static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        taskFirst();
        taskSecond();

        int arr[] = {1, 11, 13, 5, 6, 7, 85, 32, 12, 42, 30, 3};
        sort(arr);

        queen();
    }

    public static void taskFirst(){
        Map<Integer, String> heshmap = new HashMap<>();
        heshmap.put(1234567, "Ivanov Ivan");
        heshmap.put(1652435, "Petrov Petr");
        heshmap.put(1652843, "Ivanov Ivan");
        heshmap.put(1954623, "Sokolov Victor");
        heshmap.put(1857245, "Grim Ivan");
        heshmap.put(1239546, "Shah Dzmitry");
        heshmap.put(1854212, "Sidorov Petr");
        heshmap.put(1745213, "Petrov Petr");
        heshmap.put(1236547, "Sidorov Victor");
        heshmap.put(1765432, "Ivanov Ivan");

        for (int key: heshmap.keySet()) {
            logger.info(key + " - " + heshmap.get(key));
        }
        logger.info("Введите: Фамилию и Имя ");
        String name = new Scanner(System.in).nextLine();
        logger.info(String.valueOf(heshmap.keySet().stream().filter(n -> heshmap.get(n).equals(name)).collect(Collectors.toList())));
    }

    public static void taskSecond(){
        List<String> nameList = new LinkedList<>();
        nameList.add("Светлана Петрова");
        nameList.add("Кристина Белова");
        nameList.add("Анна Мусина");
        nameList.add("Анна Крутова");
        nameList.add("Иван Юрин");
        nameList.add("Петр Лыков");
        nameList.add("Павел Чернов");
        nameList.add("Петр Чернышов");
        nameList.add("Петр Чернышов");
        nameList.add("Марина Светлова");
        nameList.add("Мария Савина");
        nameList.add("Марина Лугова");
        nameList.add("Мария Рыкова");
        nameList.add("Анна Владимирова");
        nameList.add("Иван Мечников");
        nameList.add("Петр Петин");
        nameList.add("Иван Ежов");

        Map<String, Integer>nameHash = new HashMap<>();
        int count = 0;
        int max = 1;
        for (String lastname: nameList) {
            boolean hewing = true;
            String[] name = lastname.split(" ");
            for (String key: nameHash.keySet()) {
                if (key.equals(name[0])){
                    count = nameHash.get(key);
                    count += 1;
                    if (max < count){
                        max = count;
                    }
                    nameHash.put(key, count);
                    hewing = false;
                    break;
                }
            }
            if (hewing){
                nameHash.put(name[0], 1);
            }
        }
        for (int i = max; i > 0; i--) {
            int val = i;
            logger.info(String.valueOf(val + ": " + nameHash.keySet().stream().filter(n -> nameHash.get(n).equals(val)).collect(Collectors.toList())));
        }
    }

    public static void sort(int arr[])
    {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }

        String out = "";
        for (int i = 0; i < n - 1; ++i) {
            out += arr[i] + ", ";
        }
        out += arr[n - 1];
        logger.info(out);

    }

    public static void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void queen(){
        boolean[][] s = new boolean[8][8];
        String[] position = new String[8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                s[i][j] = true;
            }
        }
        int max = 8, ind = 0, indI = 0, indJ = 0;
        while (true){
            for (int i = indI; i < 8; i++) {
                for (int j = indJ; j < 8; j++) {
                    if (s[i][j]){
                        s = pole(s, false, i, j);
                        position[ind] = i + " " + j;
                        ind += 1;
                        break;
                    }
                }
                indJ = 0;
            }
            if (ind == max){
                break;
            }
            String[] key = position[ind-1].split(" ");
            indI = Integer.parseInt(key[0]);
            indJ = Integer.parseInt(key[1]) + 1;
            ind -= 1;
            position[ind] = "";
            s = vibor(position, ind);
        }
        for (int i = 0; i < 8; i++) {
            logger.info(position[i]);
        }
    }

    public static boolean[][] pole(boolean[][] doska, boolean value, int i, int j){
        int p = i;
        for (int k = 0; k < 8; k++) {
            doska[k][j] = value;
            doska[i][k] = value;
            if (j - p + k >= 0 && j - p + k < 8){
                doska[k][j - p + k] = value;
            }
            if (j + p - k >= 0 && j + p - k < 8){
                doska[k][j + p - k] = value;
            }
        }
        return doska;
    }

    public static boolean[][] vibor(String[] str, int ind){
        boolean[][] s = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                s[i][j] = true;
            }
        }
        for (int i = 0; i < ind; i++) {
            String[] key = str[i].split(" ");
            int j = Integer.parseInt(key[0]), k = Integer.parseInt(key[1]);
            s = pole(s, false, j, k);
        }
        return s;


    }
}