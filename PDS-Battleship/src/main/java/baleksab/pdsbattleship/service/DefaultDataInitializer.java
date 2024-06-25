package baleksab.pdsbattleship.service;

import baleksab.pdsbattleship.bean.RegisterBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.Provider;


import java.lang.reflect.InvocationTargetException;

@Singleton
@Startup
public class DefaultDataInitializer {

    @Inject
    private PlayerService playerService;

    @PostConstruct
    public void contextInitialized() {
        if (playerService.getPlayerByUsername("admin") == null) {
            RegisterBean registerBean = new RegisterBean();
            registerBean.setUsername("admin");
            registerBean.setPassword("admin123");
            registerBean.setConfirmPassword("admin123");
            registerBean.setAdmin(true);

            try {
                playerService.registerPlayer(registerBean);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
