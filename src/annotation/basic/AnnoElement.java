package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention annotation 의 lifecycle 지정
//@Target(ElementType.Type, ElementType.Method, ElementType.Field)  지정할 영역
//@Documented java API 문서 생성시
//@Inherited

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    String value();
    int count() default 0;
    String[] tags() default {};

    //MyLogger datra(); 다른 타입은 적용 안됨.
    Class<? extends MyLogger> logger() default MyLogger.class;
}
