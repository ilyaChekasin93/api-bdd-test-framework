package api.bdd.test.framework.client.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Header {

    private String name;

    private List<String> value;


    public Header(){
        value = new ArrayList<>();
    }

}