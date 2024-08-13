package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = (Usuario) repository.findByLogin(username);
        if (usuario == null) {
            System.out.println("Usuário não encontrado: " + username);
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        System.out.println("Usuário encontrado: " + username);
        return usuario;
    }
}