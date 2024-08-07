package med.voll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank
                                   String usuario,
                                   @NotBlank
                                   String senha
                                   ) {
}
