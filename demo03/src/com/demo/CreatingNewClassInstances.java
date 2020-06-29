package com.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/**
 * @ClassName CreatingNewClassInstances
 * @Description TODO
 * @Author lktbz
 * @Date 2020/6/28
 */
public class CreatingNewClassInstances {
    public static void main(String... args) {
        Constructor[] ctors = CreatingNewClassInstances.class.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }

        try {
            ctor.setAccessible(true);
            CreatingNewClassInstances c = (CreatingNewClassInstances)ctor.newInstance();
            Field f = c.getClass().getDeclaredField("cs");
            f.setAccessible(true);
            System.out.println(f.get(c));
            System.out.println( Charset.defaultCharset());
            // production code should handle these exceptions more gracefully
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
