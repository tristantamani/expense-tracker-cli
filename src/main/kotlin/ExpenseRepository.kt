
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.io.File

class ExpenseRepository(
    private val filePath: String
) {

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(BigDecimalSerializer)
            contextual(LocalDateSerializer)
        }
    }

    fun saveExpenses(expenses: List<Expense>) {
        val file = File(filePath)
        val jsonString = json.encodeToString(expenses)
        file.writeText(jsonString)
    }

    fun loadExpenses(): List<Expense> {
        val file = File(filePath)

        if (!file.exists()) return emptyList()
        val jsonString = file.readText()
        return json.decodeFromString(jsonString)
    }

    fun deleteExpense(id: Long) {

        val expenseList = loadExpenses()
        val updatedExpenseList = expenseList.filter { it.id != id}
        saveExpenses(updatedExpenseList)
    }
}