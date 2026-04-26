
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

class ExpenseService {

    val categoryMap = mapOf(
        "1" to Categories.FOOD,
        "2" to Categories.TRANSPORTATION,
        "3" to Categories.BILLS,
        "4" to Categories.SUPPLIES,
        "5" to Categories.OTHER
    )

    private fun formatExpenseHeader() {
        val separator = "-".repeat(5) + "+" + "-".repeat(12) +
                "+" + "-".repeat(17) + "+" + "-".repeat(14) + "+" +
                "-".repeat(20)
        val header = "ID".padEnd(4) + " | " +
                "AMOUNT".padEnd(10) + " | " +
                "CATEGORY".padEnd(15) + " | " +
                "DATE".padEnd(12) + " | " +
                "DESCRIPTION".padEnd(15)
        println("\n" + header + "\n" + separator)
    }

    private fun formatExpenseRow(item: Expense): String {
        return item.id.toString().padEnd(4) + " | " +
                item.amount.toString().padEnd(10) + " | " +
                item.category.toString().padEnd(15) + " | " +
                item.date.toString().padEnd(12) + " | " +
                item.description?.padEnd(15)
    }

    fun addExpenseRecord(repo: ExpenseRepository) {

        val expenseList = repo.loadExpenses()
        val id: Long
        if (expenseList.isEmpty()) {
            id = 1
        } else {
            val maxId = expenseList.maxOf { it.id }
            id = maxId.plus(1)
        }
        val allExpenses = expenseList.toMutableList()

        println("    _      _    _   ___                              ___                   _ \n" +
                "   /_\\  __| |__| | | __|_ ___ __  ___ _ _  ___ ___  | _ \\___ __ ___ _ _ __| |\n" +
                "  / _ \\/ _` / _` | | _|\\ \\ / '_ \\/ -_) ' \\(_-</ -_) |   / -_) _/ _ \\ '_/ _` |\n" +
                " /_/ \\_\\__,_\\__,_| |___/_\\_\\ .__/\\___|_||_/__/\\___| |_|_\\___\\__\\___/_| \\__,_|\n" +
                "                           |_|                                               ")

        println("\nInput the following expense details.")
        println("Use the following for categories: 1=FOOD, 2=TRANSPORTATION, 3=BILLS, 4=SUPPLIES, 5=OTHER \n")

        var amountSelector = true
        var amount = ""
        while (amountSelector) {
            print("Amount (PHP): ")
            val amountInput = readln()
            if (amountInput.toBigDecimalOrNull() != null) {
                amount = amountInput.toBigDecimalOrNull()?.setScale(2, RoundingMode.DOWN).toString()
                amountSelector = false
            } else {
                println("Enter a valid amount. \n")
            }
        }

        var categorySelector = true
        var category: Categories? = null
        while (categorySelector) {
            print("Category: ")
            val categoryInput = readln()
            val categoryOptions = listOf("1", "2", "3", "4", "5")

            if (categoryInput in categoryOptions) {
                category = categoryMap[categoryInput]
                categorySelector = false
            } else {
                println("Enter a valid category.")
            }
        }

        print("Description: ")
        val description = readln()
        allExpenses.add(
            Expense(
                BigDecimal(amount),
                category,
                LocalDate.now(),
                id,
                description
            )
        )
        repo.saveExpenses(allExpenses)
        println("\n Your expense record has been stored successfully!")
    }

    fun removeExpenseRecord(repo: ExpenseRepository) {

        val expenseList = repo.loadExpenses()

        println("  ___                         ___                              ___                   _ \n" +
                " | _ \\___ _ __  _____ _____  | __|_ ___ __  ___ _ _  ___ ___  | _ \\___ __ ___ _ _ __| |\n" +
                " |   / -_) '  \\/ _ \\ V / -_) | _|\\ \\ / '_ \\/ -_) ' \\(_-</ -_) |   / -_) _/ _ \\ '_/ _` |\n" +
                " |_|_\\___|_|_|_\\___/\\_/\\___| |___/_\\_\\ .__/\\___|_||_/__/\\___| |_|_\\___\\__\\___/_| \\__,_|\n" +
                "                                     |_|                                               ")

        var idSelector = true

        while (idSelector) {
            print("Enter the ID: ")
            val idToRemove = readln()
            if (idToRemove.toLongOrNull() != null) {
                val checkIdExists = expenseList.any { it.id == idToRemove.toLong() }
                if (checkIdExists) {
                    repo.deleteExpense(idToRemove.toLong())
                    println("The expense record has been deleted successfully!")
                } else {
                    println("The ID you entered does not exist, refer to the expenses list on Option 3.")
                }
                idSelector = false
            } else {
                println("Please enter a valid ID.")
            }
        }
    }

    fun viewAllExpenses(repo: ExpenseRepository){

        val expenseList = repo.loadExpenses()

        println("  ___                                 _    _    _   \n" +
                " | __|_ ___ __  ___ _ _  ___ ___ ___ | |  (_)__| |_ \n" +
                " | _|\\ \\ / '_ \\/ -_) ' \\(_-</ -_|_-< | |__| (_-<  _|\n" +
                " |___/_\\_\\ .__/\\___|_||_/__/\\___/__/ |____|_/__/\\__|\n" +
                "         |_|                                        ")

        if (expenseList.isEmpty()) {
            println("\nYou have no expenses record yet.")
        } else {
            formatExpenseHeader()
            for (item in expenseList) {
                println(formatExpenseRow(item))
            }
        }
    }

    fun viewExpenseByCategory(repo: ExpenseRepository){

        val expenseList = repo.loadExpenses()

        println("  ___                                 _            ___      _                         \n" +
                " | __|_ ___ __  ___ _ _  ___ ___ ___ | |__ _  _   / __|__ _| |_ ___ __ _ ___ _ _ _  _ \n" +
                " | _|\\ \\ / '_ \\/ -_) ' \\(_-</ -_|_-< | '_ \\ || | | (__/ _` |  _/ -_) _` / _ \\ '_| || |\n" +
                " |___/_\\_\\ .__/\\___|_||_/__/\\___/__/ |_.__/\\_, |  \\___\\__,_|\\__\\___\\__, \\___/_|  \\_, |\n" +
                "         |_|                               |__/                    |___/         |__/ ")

        if (expenseList.isEmpty()) {
            println("You have no expenses record yet.")
        } else {
            var categorySelector = true
            var category: Categories? = null

            while (categorySelector) {
                println("\nUse the following for categories: 1=FOOD, 2=TRANSPORTATION, 3=BILLS, 4=SUPPLIES, 5=OTHER")
                print("Choose a category: ")
                val categoryInput = readln()
                val categoryOptions = listOf("1", "2", "3", "4", "5")

                if (categoryInput in categoryOptions) {
                    category = categoryMap[categoryInput]
                    categorySelector = false
                }
            }

            formatExpenseHeader()
            val filtered = expenseList
                .filter { it.category == category }
            for (item in filtered) {
                println(formatExpenseRow(item))
            }
        }
    }

    fun viewSpendingSummary(repo: ExpenseRepository){

        val expenseList = repo.loadExpenses()

        println("  ___                   _ _             ___                                \n" +
                " / __|_ __  ___ _ _  __| (_)_ _  __ _  / __|_  _ _ __  _ __  __ _ _ _ _  _ \n" +
                " \\__ \\ '_ \\/ -_) ' \\/ _` | | ' \\/ _` | \\__ \\ || | '  \\| '  \\/ _` | '_| || |\n" +
                " |___/ .__/\\___|_||_\\__,_|_|_||_\\__, | |___/\\_,_|_|_|_|_|_|_\\__,_|_|  \\_, |\n" +
                "     |_|                        |___/                                 |__/ ")

        val totalExpenses = expenseList.sumOf { it.amount }
        val byCategory = expenseList
            .groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount }}
        val totalFoodExpenses = byCategory[Categories.FOOD] ?: "0.00"
        val totalTransportationExpenses = byCategory[Categories.TRANSPORTATION] ?: "0.00"
        val totalBillsExpenses = byCategory[Categories.BILLS] ?: "0.00"
        val totalSuppliesExpenses = byCategory[Categories.SUPPLIES] ?: "0.00"
        val totalOtherExpenses = byCategory[Categories.OTHER] ?: "0.00"

        val maxSpendingCategory = byCategory.maxByOrNull { it.value }
        val numberOfExpenses = expenseList.size.toBigDecimal()
        val averageExpense = (totalExpenses / numberOfExpenses)
        val maxAmount = expenseList.maxByOrNull { it.amount }
        val minAmount = expenseList.minByOrNull { it.amount }

        println("\nTotal Expenses: $totalExpenses")
        println("\nBy Category:")
        println("FOOD: $totalFoodExpenses")
        println("TRANSPORTATION: $totalTransportationExpenses")
        println("BILLS: $totalBillsExpenses")
        println("SUPPLIES: $totalSuppliesExpenses")
        println("OTHER: $totalOtherExpenses")

        println("  ___                   _ _             ___         _      _   _      \n" +
                " / __|_ __  ___ _ _  __| (_)_ _  __ _  |_ _|_ _  __(_)__ _| |_| |_ ___\n" +
                " \\__ \\ '_ \\/ -_) ' \\/ _` | | ' \\/ _` |  | || ' \\(_-< / _` | ' \\  _(_-<\n" +
                " |___/ .__/\\___|_||_\\__,_|_|_||_\\__, | |___|_||_/__/_\\__, |_||_\\__/__/\n" +
                "     |_|                        |___/                |___/            ")

        println("\nHighest Spending Category: ${maxSpendingCategory?.key} (${maxSpendingCategory?.value})")
        println("Lowest Spending Category: ${minAmount?.category} (${minAmount?.amount})")
        println("Most Expensive Record: ${maxAmount?.description} (${maxAmount?.amount})")
        println("Average Expense: $averageExpense")
    }
}