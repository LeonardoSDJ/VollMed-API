package med.voll.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class FlywayMigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public void testPrimaryKeyAndForeignKeyConformity() {
        // Query para buscar todas as tabelas exceto as de histórico
        String sqlTabelas = "SELECT table_schema, table_name FROM information_schema.tables " +
                "WHERE table_type = 'BASE TABLE' AND table_name NOT LIKE 'historico_%'";

        List<Map<String, Object>> tabelas = jdbcTemplate.queryForList(sqlTabelas);

        for (Map<String, Object> tabela : tabelas) {
            String esquema = (String) tabela.get("table_schema");
            String nomeDaTabela = (String) tabela.get("table_name");

            // Validação da PK
            String sqlPk = "SELECT COUNT(*) FROM information_schema.table_constraints " +
                    "WHERE constraint_type = 'PRIMARY KEY' AND table_schema = ? AND table_name = ?";
            int pkCount = jdbcTemplate.queryForObject(sqlPk, new Object[]{esquema, nomeDaTabela}, Integer.class);

            Assertions.assertTrue(pkCount > 0,
                    "Tabela " + esquema + "." + nomeDaTabela + " não possui uma PK.");

            // Contagem dos campos com prefixo id_
            String sqlIdCampos = "SELECT COUNT(*) FROM information_schema.columns " +
                    "WHERE table_name = ? AND table_schema = ? AND column_name LIKE 'id_%'";
            int idCamposCount = jdbcTemplate.queryForObject(sqlIdCampos, new Object[]{nomeDaTabela, esquema}, Integer.class);

            // Contagem das FKs
            String sqlFk = "SELECT COUNT(*) FROM information_schema.table_constraints tc " +
                    "JOIN information_schema.key_column_usage kcu ON tc.constraint_name = kcu.constraint_name " +
                    "WHERE tc.constraint_type = 'FOREIGN KEY' AND tc.table_schema = ? AND tc.table_name = ?";
            int fkCount = jdbcTemplate.queryForObject(sqlFk, new Object[]{esquema, nomeDaTabela}, Integer.class);

            Assertions.assertEquals(idCamposCount - 1, fkCount,
                    "Tabela " + esquema + "." + nomeDaTabela +
                            " possui " + idCamposCount + " campos com prefixo id_, mas " + fkCount + " FKs. " +
                            "O número esperado de FKs é " + (idCamposCount - 1) + ".");
        }
    }
}
