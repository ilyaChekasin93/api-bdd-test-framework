package ru.api.bdd.test.framework.utils.json.mapper;


public interface Mapper {

    boolean isValid(final String json);

    String jsonObiect2JsonString(Object json);

}
