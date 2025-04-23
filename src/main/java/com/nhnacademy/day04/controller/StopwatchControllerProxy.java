package com.nhnacademy.day04.controller;

import com.nhnacademy.student.controller.Command;
import com.nhnacademy.day04.annotation.StopWatch;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StopwatchControllerProxy implements Command {
    private final Command command;

    public StopwatchControllerProxy(final Command command) {
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(command.getClass().isAnnotationPresent(StopWatch.class)) {
            long startTime = System.currentTimeMillis();
            String view = command.execute(req, resp);
            long endTime = System.currentTimeMillis();
            long result = endTime - startTime;
            log.debug("result: {}", result);

            return view;
        }


        return command.execute(req, resp);
    }

}
