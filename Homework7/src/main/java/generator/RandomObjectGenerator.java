package generator;

import annotations.Max;
import annotations.Min;

import java.lang.reflect.*;
import java.util.Random;

public class RandomObjectGenerator {
    private Random random = new Random();

    public <T> T nextObject(Class<T> clazz) {
        return nextObject(clazz, null);
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) {
        try {
            if (factoryMethodName != null) {
                Method method = clazz.getMethod(factoryMethodName);
                return clazz.cast(method.invoke(null));
            }

            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
            constructor.setAccessible(true);

            Parameter[] params = constructor.getParameters();
            Object[] args = new Object[params.length];

            for (int i = 0; i < params.length; i++) {
                args[i] = generateValue(params[i]);
            }

            return clazz.cast(constructor.newInstance(args));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object generateValue(Parameter parameter) {
        Class<?> type = parameter.getType();
        AnnotatedElement element = parameter;

        if (type == int.class || type == Integer.class) {
            long min = 0, max = 100;
            if (element.isAnnotationPresent(Min.class))
                min = element.getAnnotation(Min.class).value();
            if (element.isAnnotationPresent(Max.class))
                max = element.getAnnotation(Max.class).value();
            return (int)(min + random.nextInt((int)(max - min + 1)));
        } else if (type == String.class) {
            return "str_" + random.nextInt(1000);
        }
        return null;
    }
}
