import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Author : 马志彬（120375）
 * Date   : 2017/11/24 0024.
 */
public class CacheTest {

    @Test
    public void test_loadNull2() {
        LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        System.out.println("xx");
                        if (s.equals("hello"))
                            return "world";
                        else
                            return null;
                    }
                });

        try {
            System.out.println(stringCache.get("hello"));

            // get触发load，load返回null则抛出异常：
            // com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key other_key.
            System.out.println(stringCache.get(null));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadNull() {
        LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        System.out.println("xx");
                        if (s.equals("hello"))
                            return "world";
                        else
                            return null;
                    }
                });

        try {
            System.out.println(stringCache.get("hello"));

            // get触发load，load返回null则抛出异常：
            // com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key other_key.
            System.out.println(stringCache.get("other_key"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadNullWhenRefresh() {
        LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    int i = 0;

                    @Override
                    public String load(String s) throws Exception {
                        if (i == 0) {
                            i++;
                            return "world";
                        }
                        return null;
                    }
                });

        try {
            System.out.println(stringCache.get("hello"));
            System.out.println(stringCache.get("hello"));

            // refresh的时候，如果load函数返回null，则refresh抛出异常：
            // Exception thrown during refresh
            // com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key hello.
            stringCache.refresh("hello");

            System.out.println(stringCache.get("hello"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadNullAfterInvalidate() {
        LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    int i = 0;

                    @Override
                    public String load(String s) throws Exception {
                        if (i == 0) {
                            i++;
                            return "world";
                        }
                        return null;
                    }
                });

        try {
            System.out.println(stringCache.get("hello"));
            System.out.println(stringCache.get("hello"));

            // invalidate不会触发load
            stringCache.invalidate("hello");

            // invalidate后，再次get，触发load，抛出异常：
            // com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key hello.
            System.out.println(stringCache.get("hello"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadThrowException() {
        LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        if (s.equals("hello"))
                            return "world";
                        else
                            throw new IllegalArgumentException("only_hello");
                    }
                });

        try {
            System.out.println(stringCache.get("hello"));

            // get触发load，load抛出异常，get也会抛出封装后的异常：
            // com.google.common.util.concurrent.UncheckedExecutionException: java.lang.IllegalArgumentException: only_hello
            System.out.println(stringCache.get("other_key"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_loadUseOptional() {
        LoadingCache<String, Optional<String>> stringCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(new CacheLoader<String, Optional<String>>() {
                    @Override
                    public Optional<String> load(String s) throws Exception {
                        if (s.equals("hello"))
                            return Optional.of("world");
                        else
                            return Optional.absent();
                    }
                });

        try {
            Optional<String> hello = stringCache.get("hello");
            if(hello.isPresent()) {
                System.out.println(hello.get());
            }


            Optional<String> otherKey = stringCache.get("other_key");
            if(otherKey.isPresent()){
                System.out.println(otherKey.get());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
