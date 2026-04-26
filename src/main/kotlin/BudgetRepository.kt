import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.io.File

class BudgetRepository(
    private val filePath: String
) {

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(BigDecimalSerializer)
            contextual(YearMonthSerializer)
        }
    }

    fun saveBudget(budget: Budget) {
        val file = File(filePath)
        val jsonString = json.encodeToString(budget)
        file.writeText(jsonString)
    }

    fun loadBudget(): Budget? {
        val file = File(filePath)

        if (!file.exists()) return null
        val jsonString = file.readText()
        return json.decodeFromString(jsonString)
    }
}