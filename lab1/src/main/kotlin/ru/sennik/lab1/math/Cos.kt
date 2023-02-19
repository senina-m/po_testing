package ru.sennik.lab1.math
import java.lang.Math.PI
import java.lang.RuntimeException
import java.math.BigDecimal

fun cos(x: Double, accuracy: Double): Double {
    if(accuracy >= 1 || accuracy <= 0) {
        throw RuntimeException("Accuracy has to be from 0 to 1!")
    }
    val xb = BigDecimal(x)
    val acc = BigDecimal(accuracy*0.1)
    var cosX = BigDecimal(1.0)
    var f =  BigDecimal(1)
    var crx = BigDecimal(1.0)
    var n = BigDecimal(1)
    var i = 1
    while((crx / f) >= acc) {
        if (i  > 20) {
            break
        }
        f *= n*(n + BigDecimal(1))
        crx *= xb*xb
        n += BigDecimal(2)
        i += 1
        if (i % 2 == 0){
            cosX -= crx/f
            print("${-crx/f}\n")

        }else{
            cosX += crx/f
            print("${crx/f}\n")
        }
        print("$cosX\n")
    }

    return cosX.toDouble()
}

fun main(){
    print("result ${cos(2.0, 0.001)} \n")
    print("cos act = ${kotlin.math.cos(2.0)}")
}