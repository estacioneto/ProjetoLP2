package projeto.util.reflexao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacao que deve ser usada em campos que precisem de conversao 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Conversao {

	Class<?> formato();
	String conversor() default "";
}
