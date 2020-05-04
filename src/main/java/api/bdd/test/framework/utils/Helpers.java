package api.bdd.test.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static final int FIRST_INT = 1;

    public static String[] reverse(String[] arr) {
        for (int i = 0, j = arr.length-1; i < j; i++, j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

    public static Object getPojoValue(String pojoPath) {
        try {
            return Class.forName(pojoPath).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(String.format("Error: '%s' get pojo with name '%s'", e.getMessage(), pojoPath));
        }
    }

    public static String getFullMath(String value, String patternValue){
        final Pattern pattern = Pattern.compile(patternValue);
        final Matcher matcher = pattern.matcher(value);

        String result = "";
        if (matcher.find())
            result = matcher.group();

        return result;
    }

    public static String getGroupOneMath(String value, String patternValue){
        final Pattern pattern = Pattern.compile(patternValue);
        final Matcher matcher = pattern.matcher(value);

        String result = "";
        if (matcher.find())
            result = matcher.group(FIRST_INT);

        return result;
    }

    public static boolean findMath(String value, String patternValue){
        final Pattern pattern = Pattern.compile(patternValue);
        final Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
