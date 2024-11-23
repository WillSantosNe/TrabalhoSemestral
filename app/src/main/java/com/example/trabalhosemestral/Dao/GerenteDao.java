import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GerenteDao {
    @Insert
    suspend fun Adicionar(material: Material)

    @Query("SELECT * FROM mateial")
    suspend fun buscarTodosr(): List<Material>
}
