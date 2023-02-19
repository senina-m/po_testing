import java.lang.RuntimeException

fun cos(x: Double, accuracy: Double): Double {
    if(accuracy >= 1 || accuracy <= 0){
        throw  RuntimeException("Accuracy has to be from 0 to 1!")
    }
    var cosx = 1.0
    var f = 1
    var curx = x*x;
    var n = 2;
    while(curx / f > accuracy) {
        if (n % 2 == 0) cosx -= curx/f;
        else cosx += curx/f;
        f *= n*(n + 1);
        curx *= x*x;
    }
    return cosx
}