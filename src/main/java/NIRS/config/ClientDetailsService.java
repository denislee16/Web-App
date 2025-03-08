package NIRS.config;

import NIRS.entity.Clients;
import NIRS.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {

    @Autowired
    private ClientsService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Clients client = clientService.readOne(email);

        if (client == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new ClientDetails(client);
    }
}