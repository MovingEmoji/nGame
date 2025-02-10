package jp.nagua.nGame.utils;

import com.google.common.reflect.ClassPath;
import jp.nagua.nGame.Main;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassHandler {
    public static void loadClasses(String packageName) {
        try {
            Set<Class<?>> classes = ClassPath.from(Main.getPluginClassLoader()).getTopLevelClasses(packageName).stream().map(info -> info.load()).collect(Collectors.toSet());
            for(Class<?> commandclass : classes) {
                Class.forName(commandclass.getName(), true, Main.getPluginClassLoader());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
