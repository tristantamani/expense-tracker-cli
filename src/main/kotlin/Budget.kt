import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.YearMonth

@Serializable
data class Budget(
    @Contextual val monthlyAllowance: BigDecimal,
    @Contextual val monthYear: YearMonth
)
