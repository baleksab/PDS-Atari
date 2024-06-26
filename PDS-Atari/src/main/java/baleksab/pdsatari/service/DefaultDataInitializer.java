package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.RegisterBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;


import java.lang.reflect.InvocationTargetException;

@Singleton
@Startup
public class DefaultDataInitializer {

    @Inject
    private UserService userService;

    @PostConstruct
    public void contextInitialized() {
        if (userService.getUserByEmail("admin@gmail.com") == null) {
            RegisterBean registerBean = new RegisterBean();
            registerBean.setEmail("admin@gmail.com");
            registerBean.setPassword("admin123");
            registerBean.setConfirmPassword("admin123");
            registerBean.setAdmin(true);

            try {
                userService.registerUser(registerBean);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
