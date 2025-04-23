package com.nhnacademy.day04.factory;

import com.nhnacademy.student.controller.Command;
import com.nhnacademy.day04.controller.StopwatchControllerProxy;
import com.nhnacademy.day04.annotation.RequestMapping;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory  {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public static final String CONTROLLER_FACTORY_NAME = "controllerFactory";

    public void init(Set<Class<?>> c){
        //todo beanMap에 key = method + servletPath, value = Controller instance
        for (Class<?> clazz : c) {
            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
            if(Objects.isNull(annotation)) {
                continue;
            }
            String method = annotation.method().name();
            String path = annotation.value();
            String key = getKey(method, path);

            try {
                Object command = clazz.getConstructor().newInstance();
                beanMap.put(key, new StopwatchControllerProxy((Command) command));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getKey(String method, String path) {
        return "%s-%s".formatted(method, path);

    }

    public Object getBean(String method, String path){
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        return beanMap.get(getKey(method, path));
    }
}