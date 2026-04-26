import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class Expense(
    @Contextual val amount: BigDecimal,
    val category: Categories?,
    @Contextual val date: LocalDate,
    val id: Long,
    val description: String?
)