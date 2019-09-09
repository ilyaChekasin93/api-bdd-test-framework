package ru.api.bdd.test.framework.assertion;

import org.springframework.util.Assert;

import java.util.Collection;

public class Checker {

    public static void isNotEmpty(Object value){

        Assert.notNull(
                value,
                new StringBuilder("The value ").append(value).append(" must not be null").toString()
        );

        String message = new StringBuilder("The value ").append(value).append(" must not be empty").toString();

        if(value instanceof Collection) {
            Assert.isTrue(!((Collection) value).isEmpty(), message);
        }
        else {
            Assert.isTrue(!value.toString().isEmpty(), message);
        }
    }

    public static void isAboveZero(Object value){

        String message = new StringBuilder("The value ").append(value).append(" must be greater than zero").toString();

        if (value instanceof String) {
            Assert.isTrue(Integer.valueOf(value.toString()) > 0, message);
        } else {
            Assert.isTrue((Integer) value > 0, message);
        }
    }

    public static void isNotEquals(Object firstValue, Object secondValue){

        String message = new StringBuilder("The first value ")
                .append(firstValue).append(" must not be equals second value ").append(secondValue).toString();

        if(firstValue.getClass().getName().equals(secondValue.getClass().getName())) {
            Assert.isTrue(!firstValue.equals(secondValue), message);
        }else {
            Assert.isTrue(!firstValue.toString().equals(secondValue.toString()), message);
        }
    }

    public static void isEquals(Object firstValue, Object secondValue){

        String message = new StringBuilder("The first value ")
                .append(firstValue)
                .append(" must be equal second value ")
                .append(secondValue).toString();

        if(firstValue.getClass().getName().equals(secondValue.getClass().getName())) {
            Assert.isTrue(firstValue.equals(secondValue), message);
        }else {
            Assert.isTrue(firstValue.toString().equals(secondValue.toString()), message);
        }
    }

    public static void isContains(Object firstValue, Object secondValue){

        String message = new StringBuilder("The first value ")
                .append(firstValue)
                .append(" must not be contains second value ")
                .append(secondValue).toString();

        if(firstValue instanceof Collection){
            Assert.isTrue(((Collection) firstValue).contains(secondValue), message);
        }
        else {
            Assert.isTrue(firstValue.toString().contains(secondValue.toString()), message);
        }
    }

    public static void isNotContains(Object firstValue, Object secondValue){

        String message = new StringBuilder("The first value ")
                .append(firstValue).append(" must not be contains second value ")
                .append(secondValue).toString();

        if(firstValue instanceof Collection){
            Assert.isTrue(!((Collection) firstValue).contains(secondValue), message);
        }
        else {
            Assert.isTrue(!firstValue.toString().contains(secondValue.toString()), message);
        }
    }

    public static void isExist(Object value){
        Assert.notNull(
                value,
                new StringBuilder("The value ").append(value).append(" must not be null").toString()
        );
    }

    public static void isNotExist(Object value){
        Assert.isNull(
                value,
                new StringBuilder("The value ").append(value).append(" must not be null").toString()
        );
    }

    public static void isTrue(boolean value){
        Assert.isTrue(value, "The value must be true");
    }

    public static void isCollection(Object value){
        Assert.isTrue(
                value instanceof Collection,
                new StringBuilder("The value ").append(value).append(" must be collection").toString()
        );
    }

    public static void isNumber(Object value){

        String messsage = new StringBuilder("The value ").append(value).append(" must be number").toString();

        if (value instanceof String) {
            boolean isNumber;

            try {
                Integer.parseInt(value.toString());
                isNumber = true;
            } catch (NumberFormatException e) {
                isNumber = false;
            }

            Assert.isTrue(isNumber, messsage);

        }else {

            Assert.isTrue(
                    value instanceof Integer,
                    messsage
            );

        }
    }

    public static void sizeIs(Object value, Object length) {

        isNumber(length);

        int lengthVal = Integer.valueOf(length.toString());

        String message = new StringBuilder("Size must be ").append(length).toString();

        if(value instanceof Collection) {
            Assert.isTrue(((Collection) value).size() == lengthVal, message);
        } else {
            Assert.isTrue(value.toString().length() == lengthVal, message);
        }
    }

}
