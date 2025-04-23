package com.nhnacademy.day04.init;

import com.nhnacademy.day04.factory.ControllerFactory;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@HandlesTypes(
        value = {
                com.nhnacademy.student.controller.Command.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(c);
        ctx.setAttribute(ControllerFactory.CONTROLLER_FACTORY_NAME, controllerFactory);

    }

}