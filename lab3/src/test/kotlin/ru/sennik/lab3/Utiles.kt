package ru.sennik.lab3

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

// функция, которая запускает тесты параллельно
fun runTest(testFun: (RemoteWebDriver) -> Unit, driverList: List<RemoteWebDriver>){
    val executor = Executors.newFixedThreadPool(driverList.size)
    val results = driverList.map { d ->
        executor.submit{
            testFun(d)
        }
    }
    executor.shutdown()
//        executor.awaitTermination(15, TimeUnit.SECONDS)
    results.forEach{
        try{
            it.get()
        }
        catch (e : ExecutionException){
            println("ExecutionException: ${e.message}")
            Assertions.fail<Any>()
        }
        catch (e : InterruptedException){
            println("InterruptedException: ${e.message}")
            Assertions.fail<Any>()
        }
    }
}