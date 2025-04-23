package com.nhnacademy.day04.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE, ElementType.METHOD}) // TYPE : 클래스 위에 쓸 수 있음
@Retention(RetentionPolicy.RUNTIME) // RUNTIME : 구동 시 실행
public @interface StopWatch {

}
