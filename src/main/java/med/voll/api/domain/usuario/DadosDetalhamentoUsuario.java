package med.voll.api.domain.usuario;



public record DadosDetalhamentoUsuario(String login, String senha) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getLogin(), usuario.getSenha());
    }
}
