package me.web.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)  
public @interface DataSource {
	public me.web.common.enums.DataSources value() default me.web.common.enums.DataSources.DATA_SOURCE_ONE;
}
