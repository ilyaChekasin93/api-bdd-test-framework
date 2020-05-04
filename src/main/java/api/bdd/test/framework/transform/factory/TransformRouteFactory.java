package api.bdd.test.framework.transform.factory;

import api.bdd.test.framework.transform.dto.ExpressionScope;
import api.bdd.test.framework.transform.route.TransformRoute;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static api.bdd.test.framework.utils.Helpers.findMuth;

@Component
public class TransformRouteFactory {

    private List<TransformRoute> transformRoutes;


    public TransformRouteFactory(ListableBeanFactory beanFactory){
        Collection<TransformRoute> expressionStrategyCollection = beanFactory.getBeansOfType(TransformRoute.class).values();
        transformRoutes = expressionStrategyCollection.stream().collect(Collectors.toList());
    }

    public TransformRoute getRoute(String value){
        TransformRoute route = transformRoutes.stream()
                .filter(e -> findMuth(value, e.getPattern())).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Pattern for %s not found", value)));

        route.setExpression(value);

        return route;
    }

    public List<ExpressionScope> getBrackets(){
        return transformRoutes.stream().map(r -> {
            Character startChar = r.getStartPatternChar();
            Character endChar = r.getEndPatternChar();
            return new ExpressionScope(startChar, endChar);
        }).collect(Collectors.toList());
    }

}
