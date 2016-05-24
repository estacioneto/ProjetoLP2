package projeto.util.reflexao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacao usada em campos que precisem de validacao 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validacao {
	
	String metodo() default "";
	String erro() default "";
	boolean get() default false;
	boolean cadastro() default true;
	boolean atualizacao() default true;
}
