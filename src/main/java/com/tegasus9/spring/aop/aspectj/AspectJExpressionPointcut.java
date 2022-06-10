package com.tegasus9.spring.aop.aspectj;

import com.tegasus9.spring.aop.ClassFilter;
import com.tegasus9.spring.aop.MethodMatcher;
import com.tegasus9.spring.aop.PointCut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author XiongYiGe
 * @date 2022/6/10
 * @description 切点表达式
 */
public class AspectJExpressionPointcut implements ClassFilter, MethodMatcher, PointCut {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }


    @Override
    public boolean matches(Class<?> targetClass, Method method) {
        return this.pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return this.pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
