package bojankosta.cyrillic.service;

import bojankosta.cyrillic.entity.MyUserDetails;
import bojankosta.cyrillic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user == null) {
            user = userService.getUserByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
        }
        return new MyUserDetails(user);
    }

}
