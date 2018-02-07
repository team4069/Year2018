package frc.team4069.robot.commands.arm

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class TallyWrapper {
    val tally = mutableListOf<Int>()

    init {
        launch {
            delay(5000)
            println(tally.average())
        }
    }
}
