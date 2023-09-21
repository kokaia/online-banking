package ge.softlab.lessons.onlinebanking.test;

import ge.softlab.lessons.onlinebanking.entities.UserDomain;
import ge.softlab.lessons.onlinebanking.models.SuperAnotaion;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class ReflectTest {

    @PostConstruct
    public void test() throws IllegalAccessException, InvocationTargetException {
        var user = new UserDomain();
        user.setId(5);
        user.setUsername("giga");
        inspect(user);

    }

    private void inspect(Object o) throws IllegalAccessException, InvocationTargetException {
        if (o == null) {
            return;
        }
        System.out.println(o.getClass().getName());

        for (java.lang.reflect.Field field : o.getClass().getDeclaredFields()) {
            if (field.getName().equals("id")) {
                field.setAccessible(true);
                System.out.println(field.get(o));
                field.set(o, 45);
            }
            if (field.getName().equals("str")) {
                field.setAccessible(true);
                field.set(o, "giga");
            }
            for (var a: field.getAnnotations()){
                System.out.println(a);
            }

            var anotacia = field.getDeclaredAnnotation(SuperAnotaion.class);
            if (anotacia != null){
                field.setAccessible(true);
                if (field.get(o) == null) {
                    if (field.getType().getName().equals("java.lang.String")){
                        field.set(o, anotacia.value());
                    }
                }
            }
        }

        for(java.lang.reflect.Method m : o.getClass().getDeclaredMethods()) {
            System.out.println(m);
            SuperAnotaion anotaion = m.getDeclaredAnnotation(SuperAnotaion.class);
            if (anotaion != null) {
                if (m.getParameterCount() == 0) {
                    m.invoke(o);
                } else if (m.getParameterCount() == 1) {
                    m.invoke(o, "\n\n\n\ncall from reflect;\n\n\n");
                }

            }
        }

        System.out.println(o);

    }

}
