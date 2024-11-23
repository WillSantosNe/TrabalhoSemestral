import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material")
data class Material(
    @PrimaryKey(autoGenerate = true)  // Gera automaticamente um ID único para cada usuário
    val id: Int = 0,
    val nome: String,
    val marca: String,
    val quantidade: String,
)
