    package com.example.JournalEntry.Service;

    import com.example.JournalEntry.Entity.User;
    import com.example.JournalEntry.Repository.UserRepo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Component;

    @Component
    public class UserDetailServiceIMPL implements UserDetailsService {

        @Autowired
        private UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User byUsername = userRepo.findByUsername(username);
            if (byUsername != null) {
                UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(byUsername.getUsername()).password(byUsername.getPassword()).roles(byUsername.getRoles().toArray(new String[0])).build();
                return userDetails;
            }
            throw new UsernameNotFoundException("User not found exception" + username);

        }
    }
