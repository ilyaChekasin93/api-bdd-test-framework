package api.bdd.test.framework.transform;

import api.bdd.test.framework.transform.dto.ExpressionPartDto;
import api.bdd.test.framework.transform.dto.ExpressionScopeDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ArgumentParser {

    private List<ExpressionScopeDto> bracketsList;

    public ArgumentParser(TransformRouteManager routeManager) {
        this.bracketsList = routeManager.getRoutScopes();
    }

    public List<String> getArgumentParts(String arg) {
        List<ExpressionPartDto> allParts = getExpressionParts(arg);
        List<ExpressionPartDto> closedParts = getClosedExpressionParts(allParts);
        return closedParts.isEmpty() ? new ArrayList<>() : getPartsValues(closedParts);
    }

    public List<ExpressionPartDto> getExpressionParts(String arg) {
        List<ExpressionPartDto> parts = new ArrayList<>();
        char[] expressionChars = arg.toCharArray();

        for (int i = 0; i < expressionChars.length; i++) {
            char curChar = expressionChars[i];
            parts = addCharInOpenParts(curChar, parts);
            processExpressionChar(curChar, parts);
        }

        return parts;
    }

    private void processExpressionChar(char curChar, List<ExpressionPartDto> parts) {
        if (isOpenChar(curChar)) {
            addNewExpressionPart(curChar, parts);
        } else if (isCloseChar(curChar)) {
            processClosingChar(curChar, parts);
        }
    }

    private void addNewExpressionPart(char curChar, List<ExpressionPartDto> parts) {
        String symbol = String.valueOf(curChar);
        ExpressionPartDto newExpressionPartDto = new ExpressionPartDto(symbol);
        parts.add(newExpressionPartDto);
    }

    private void processClosingChar(char curChar, List<ExpressionPartDto> parts) {
        if (!parts.isEmpty()) {
            char lastOpenChar = getCharLastOpenPart(parts);
            char lastEndChar = getScopeEndChar(lastOpenChar);
            if (curChar == lastEndChar)
                closeLastPart(parts);
        }
    }

    private List<String> getPartsValues(List<ExpressionPartDto> parts) {
        return parts.stream().map(ExpressionPartDto::getValue).collect(Collectors.toList());
    }

    private List<ExpressionPartDto> getClosedExpressionParts(List<ExpressionPartDto> parts) {
        return parts.stream().filter(ExpressionPartDto::isClosed).collect(Collectors.toList());
    }

    private List<ExpressionPartDto> addCharInOpenParts(char newChar, List<ExpressionPartDto> parts) {
        return parts.stream().map(p -> !p.isClosed() ? p.addChar(newChar) : p).collect(Collectors.toList());
    }

    private void closeLastPart(List<ExpressionPartDto> parts) {
        parts.get(parts.size() - 1).close();
    }

    private char getCharLastOpenPart(List<ExpressionPartDto> values) {
        int indexLastOpenChar = values.size() - 1;
        char[] lastValueChars = values.get(indexLastOpenChar).getValue().toCharArray();
        return lastValueChars[0];
    }

    private Character getScopeEndChar(Character startChar) {
        return bracketsList.stream()
                .filter(b -> b.getStartExpressionChar().equals(startChar))
                .findFirst()
                .get()
                .getEndExpressionChar();
    }

    private boolean isOpenChar(Character value) {
        return bracketsList.stream().anyMatch(b -> b.getStartExpressionChar().equals(value));
    }

    private boolean isCloseChar(Character value) {
        return bracketsList.stream().anyMatch(b -> b.getEndExpressionChar().equals(value));
    }

}
