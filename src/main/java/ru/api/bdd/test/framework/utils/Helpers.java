package ru.api.bdd.test.framework.utils;

public class Helpers {

    public static int multiply(int firstArg, int secondArg){
        return firstArg * secondArg;
    }

    public static int addNumbers(int firstArg, int secondArg){
        return firstArg + secondArg;
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
