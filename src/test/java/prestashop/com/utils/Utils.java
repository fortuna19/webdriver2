package prestashop.com.utils;

import java.io.*;
import java.util.List;

public class Utils {
    public static void serializeObject(Object serializableObject, String filePath) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(serializableObject);
        objectOutputStream.close();
    }

    public static ObjectInputStream deserializeObject(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream;
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cutLongTitles(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > 23) {
                list.set(i, list.get(i).substring(0, 23));
            }
        }
    }

}
