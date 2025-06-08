package proxy;

import annotations.Cache;

import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy {
    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<?> interf) {
        return (T) Proxy.newProxyInstance(
                interf.getClassLoader(),
                new Class<?>[]{interf},
                new CacheHandler(target)
        );
    }

    private static class CacheHandler implements InvocationHandler {
        private final Object target;
        private final Map<String, Object> cache = new HashMap<>();

        public CacheHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!method.isAnnotationPresent(Cache.class))
                return method.invoke(target, args);

            String key = method.getName() + "#" + (args != null ? args[0] : "null");
            Cache cacheAnn = method.getAnnotation(Cache.class);

            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            File file = new File("cache_" + key + ".ser");
            if (cacheAnn.persist() && file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Object result = ois.readObject();
                    cache.put(key, result);
                    return result;
                }
            }

            Object result = method.invoke(target, args);
            cache.put(key, result);

            if (cacheAnn.persist()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(result);
                }
            }

            return result;
        }
    }
}
