package jp.nagua.npractice.utils;

import com.google.common.reflect.ClassPath;
import jp.nagua.npractice.Main;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassHandler {
    public static void loadClasses(String packageName) {
        try {
            Set<Class<?>> classes = ClassPath.from(Main.getPluginClassLoader()).getTopLevelClasses(packageName).stream().map(info -> info.load()).collect(Collectors.toSet());
            for(Class<?> loadclass : classes) {
                Class.forName(loadclass.getName(), true, Main.getPluginClassLoader());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
