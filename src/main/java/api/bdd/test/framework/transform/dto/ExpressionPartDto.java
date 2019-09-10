package api.bdd.test.framework.transform.dto;

import lombok.Getter;

@Getter
public class ExpressionPartDto {

    private String value;

    private boolean isClosed;

    public ExpressionPartDto(String value){
        this.value = value;
    }

    public ExpressionPartDto addChar(char newChar){
        value = value + newChar;

        return this;
    }

    public void close(){
        isClosed = true;
    }

}
