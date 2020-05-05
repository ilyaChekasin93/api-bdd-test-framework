package api.bdd.test.framework.transform;

import api.bdd.test.framework.transform.dto.ExpressionPart;
import api.bdd.test.framework.transform.dto.ExpressionScope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ArgumentParser {

    private List<ExpressionScope> bracketsList;

    public ArgumentParser(TransformRouteManager routeManager){
        this.bracketsList = routeManager.getRoutScopes();
    }

    public List<String> getArgumentParts(String arg){
        List<ExpressionPart> allParts = getExpressionParts(arg);
        List<ExpressionPart> closedParts = getClosedExpressionParts(allParts);
        return closedParts.isEmpty() ? new ArrayList<>() : getPartsValues(closedParts);
    }

    private List<String> getPartsValues(List<ExpressionPart> parts){
        return parts.stream().map(p -> p.getValue()).collect(Collectors.toList());
    }

    private List<ExpressionPart> getClosedExpressionParts(List<ExpressionPart> parts){
        return parts.stream().filter(p -> p.isClosed()).collect(Collectors.toList());
    }

    public List<ExpressionPart> getExpressionParts(String arg) {
        List<ExpressionPart> parts = new ArrayList<>();

        char[] expressionChars = arg.toCharArray();

        for (int i = 0; i < expressionChars.length; i++) {
            char curChar = expressionChars[i];

            parts = addCharInOpenParts(parts, curChar);

            if (isOpenChar(curChar)) {
                String symbol = String.valueOf(curChar);
                ExpressionPart newExpressionPart = new ExpressionPart(symbol);
                parts.add(newExpressionPart);

            } else if (isCloseChar(curChar)) {
                if(!parts.isEmpty()) {
                    char lastOpenChar = getCharLastOpenPart(parts);
                    char lastEndChar = getScopeEndChar(lastOpenChar);
                    if (curChar == lastEndChar)
                        closeLastPart(parts);
                }
            }
        }

        return parts;
    }

    private List<ExpressionPart> addCharInOpenParts(List<ExpressionPart> parts, char newChar) {
        return parts.stream().map(p -> !p.isClosed() ? p.addChar(newChar) : p).collect(Collectors.toList());
    }

    private void closeLastPart(List<ExpressionPart> parts){
        parts.get(parts.size() - 1).close();
    }

    private char getCharLastOpenPart(List<ExpressionPart> values){
        int indexLastOpenChar = values.size() - 1;
        char[] lastValueChars = values.get(indexLastOpenChar).getValue().toCharArray();
        return lastValueChars[0];
    }

    private Character getScopeEndChar(Character startChar){
        return bracketsList.stream().filter(b -> b.getStartExpressionChar().equals(startChar)).findFirst().get().getEndExpressionChar();
    }

    private boolean isOpenChar(Character value){
        return bracketsList.stream().filter(b -> b.getStartExpressionChar().equals(value)).findFirst().isPresent();
    }

    private boolean isCloseChar(Character value){
        return bracketsList.stream().filter(b -> b.getEndExpressionChar().equals(value)).findFirst().isPresent();
    }

}
