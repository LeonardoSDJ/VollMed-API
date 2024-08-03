package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long idMedico, @NotNull Long idPaciente, @Null @Future LocalDateTime data) {

}
