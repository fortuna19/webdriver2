package prestashop.com.utils;

import java.io.*;

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
}
