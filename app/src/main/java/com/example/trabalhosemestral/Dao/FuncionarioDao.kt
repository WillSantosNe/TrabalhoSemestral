package com.example.trabalhosemestral.dao

// Importa as classes necessárias para trabalhar com o Room e a entidade Funcionario
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trabalhosemestral.entity.Funcionario

// Anotação @Dao indica que esta interface é um DAO (Data Access Object)
// O DAO é responsável por fornecer os métodos de acesso ao banco de dados
@Dao
interface FuncionarioDao {

    // Anotação @Insert indica que este método é usado para inserir dados na tabela
    // A função `inserirFuncionario` recebe um objeto da classe Funcionario e insere ele no banco de dados
    @Insert
    suspend fun inserirFuncionario(funcionario: Funcionario)

    // Anotação @Query indica que este método irá executar uma consulta SQL personalizada
    // A função `listarFuncionariosPorTipo` retorna uma lista de objetos Funcionario
    // Ela busca todos os funcionários da tabela `funcionarios` com um tipo específico, passado como parâmetro
    // O parâmetro `tipo` é usado para filtrar os resultados
    @Query("SELECT * FROM funcionarios WHERE tipo = :tipo")
    suspend fun listarFuncionariosPorTipo(tipo: String): List<Funcionario>
}
