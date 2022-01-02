package de.com.fdm.db.services;

import de.com.fdm.bot.access.UserLevel;
import de.com.fdm.db.data.User;
import de.com.fdm.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserForUserId(String userId) {
        return this.userRepository.findByUserId(userId);
    }

    public void save(String userId, UserLevel level) {
        User user = new User();
        user.setUserId(userId);
        user.setLevel(level);
        this.userRepository.save(user);
    }

    public void delete(String userId) {
        User user = getUserForUserId(userId);
        if (user == null) {
            return;
        }

        userRepository.delete(user);
    }
}
