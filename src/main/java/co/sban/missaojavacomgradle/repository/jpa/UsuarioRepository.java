package co.sban.missaojavacomgradle.repository.jpa;

import co.sban.missaojavacomgradle.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}