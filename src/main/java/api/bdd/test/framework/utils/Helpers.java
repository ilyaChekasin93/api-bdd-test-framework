package api.bdd.test.framework.utils;

public class Helpers {

    public static String[] reverse(String[] arr) {
        for (int i = 0, j = arr.length-1; i < j; i++, j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

    public static Object getPojoValue(String pojoPath) {
        Object value = null;

        try {
            value = Class.forName(pojoPath).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return value;
    }

}
