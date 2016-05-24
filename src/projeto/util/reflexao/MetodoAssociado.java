package projeto.util.reflexao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacao usada em campos que devem executar um metodo
 * ao tentar acesso a seu valor 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetodoAssociado {

	String get() default "";
	String set() default "";
}
