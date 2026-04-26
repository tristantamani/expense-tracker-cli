import kotlin.system.exitProcess

fun main() {

    val expenseService = ExpenseService()
    val repo = ExpenseRepository("expense.json")

    println("  ___                              _____            _           \n" +
            " | __|_ ___ __  ___ _ _  ___ ___  |_   _| _ __ _ __| |_____ _ _ \n" +
            " | _|\\ \\ / '_ \\/ -_) ' \\(_-</ -_)   | || '_/ _` / _| / / -_) '_|\n" +
            " |___/_\\_\\ .__/\\___|_||_/__/\\___|   |_||_| \\__,_\\__|_\\_\\___|_|  \n" +
            "         |_|                                                    ")

    var choice = ""
    while (choice != "6") {
        println("\n1. Add expense record")
        println("2. Remove expense record")
        println("3. View all expenses")
        println("4. View expenses by category")
        println("5. View total spending summary and insights")
        println("6. Exit")

        print("\n Enter your choice: ")
        val menuChoice = readln()

        when(menuChoice) {
            "1" -> expenseService.addExpenseRecord(repo)
            "2" -> expenseService.removeExpenseRecord(repo)
            "3" -> expenseService.viewAllExpenses(repo)
            "4" -> expenseService.viewExpenseByCategory(repo)
            "5" -> expenseService.viewSpendingSummary(repo)
            "6" -> exitProcess(0)
            else -> print("You entered an invalid choice, pick from 1-5 only.")
        }
    }
}

