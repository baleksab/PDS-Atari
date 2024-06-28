package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.LoginBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.bean.RegisterBean;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.UserRepository;
import baleksab.pdsatari.util.BeanValidator;
import baleksab.pdsatari.util.PasswordEncoder;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private BeanValidator beanValidator;

    @Inject
    private PasswordEncoder passwordEncoder;

    public void addUser(User player) {
        userRepository.add(player);
    }

    public User getByUserId(int id) {
        return userRepository.getById(id);
    }

    public UserBean loginUser(LoginBean loginBean) throws InvocationTargetException, IllegalAccessException {
        Set<String> violations = beanValidator.validate(loginBean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed logging user, validation violation!", violations);
        }

        User user = getUserByEmail(loginBean.getEmail());

        if (user == null) {
            violations.add("User with given email does not exist!");
            throw new ValidationException("Failed logging user, user with given email doesn't exist!", violations);
        }

        if (!passwordEncoder.checkPassword(loginBean.getPassword(), user.getPassword())) {
            violations.add("Wrong password!");
            throw new ValidationException("Failed logging user, wrong password!", violations);
        }

        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userBean, user);

        return userBean;
    }

    public void registerUser(RegisterBean registerBean) throws InvocationTargetException, IllegalAccessException {
        Set<String> violations = beanValidator.validate(registerBean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed registering user, validation violation!", violations);
        }

        if (getUserByEmail(registerBean.getEmail()) != null) {
            violations.add("Email is already taken!");
            throw new ValidationException("Failed registering user, email already taken!", violations);
        }

        if (!registerBean.getPassword().equals(registerBean.getConfirmPassword())) {
            violations.add("Passwords must match!");
            throw new ValidationException("Failed registering user, passwords must match!", violations);
        }

        User user = new User();
        BeanUtils.copyProperties(user, registerBean);

        user.setPassword(passwordEncoder.hashPassword(user.getPassword()));

        addUser(user);
    }

    private User getUserByEmail(String username) {
        return userRepository.getByEmail(username);
    }

    public List<UserBean> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<UserBean> userBeans = new ArrayList<>();

        for (User user : users) {
            UserBean userBean = new UserBean();

            try {
                BeanUtils.copyProperties(userBean, user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            userBeans.add(userBean);
        }

        return userBeans;
    }

    public boolean updateUserByBean(UserBean userBean) {
        Set<String> violations = beanValidator.validate(userBean);

        if (!violations.isEmpty()) {
            return false;
        }

        User emailCheck = getUserByEmail(userBean.getEmail());

        if (emailCheck != null && emailCheck.getId() != userBean.getId()) {
            return false;
        }

        User user = getByUserId(userBean.getId());
        user.setEmail(userBean.getEmail());
        user.setBudget(userBean.getBudget());
        user.setFirstName(userBean.getFirstName());
        user.setLastName(userBean.getLastName());
        user.setAdmin(userBean.isAdmin());

        updateUser(user);

        return true;
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
