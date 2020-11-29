package api.bdd.test.framework.transform;

import api.bdd.test.framework.exception.PatternNotFoundException;
import api.bdd.test.framework.transform.dto.ExpressionScopeDto;
import api.bdd.test.framework.transform.route.TransformRoute;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static api.bdd.test.framework.utils.Helpers.findMath;


@Component
public class TransformRouteManager {

    private List<TransformRoute> transformRoutes;


    public TransformRouteManager(ListableBeanFactory beanFactory) {
        Collection<TransformRoute> expressionStrategyCollection = beanFactory.getBeansOfType(TransformRoute.class).values();
        transformRoutes = new ArrayList<>(expressionStrategyCollection);
    }

    public TransformRoute getRoute(String value) {
        TransformRoute route = transformRoutes.stream()
                .filter(e -> findMath(value, e.getPattern())).findFirst()
                .orElseThrow(() -> new PatternNotFoundException(value));

        route.setExpression(value);

        return route;
    }

    public List<ExpressionScopeDto> getRoutScopes() {
        return transformRoutes.stream().map(r -> {
            Character startChar = r.getStartPatternChar();
            Character endChar = r.getEndPatternChar();
            return new ExpressionScopeDto(startChar, endChar);
        }).collect(Collectors.toList());
    }

}
