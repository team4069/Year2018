package frc.team4069.robot.util

import java.util.*

data class LowPassFilter(val rc: Int = 100) {
    var lastValue: Double = 0.0
    var lastTime: Long = -1

    fun reset() {
        lastValue = 0.0
    }

    fun calculate(value: Double): Double {
        if(lastTime > 0) {
            val time = Date().time
            var a = (time - lastTime).toDouble()

            a /= (a + rc)

            lastTime = time
            lastValue = a * value + (1 - a) * lastValue
        }else {
            lastTime = Date().time
        }

        return lastValue
    }
}
