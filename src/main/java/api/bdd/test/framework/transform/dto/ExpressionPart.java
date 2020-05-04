package api.bdd.test.framework.transform.dto;

import lombok.Getter;

@Getter
public class ExpressionPart {

    private String value;

    private boolean isClosed;

    public ExpressionPart(String value){
        this.value = value;
    }

    public void addChar(char newChar){
        value = value + newChar;
    }

    public void close(){
        isClosed = true;
    }

}
