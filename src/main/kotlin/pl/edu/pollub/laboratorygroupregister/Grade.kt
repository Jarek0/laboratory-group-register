package pl.edu.pollub.laboratorygroupregister

import pl.edu.pollub.laboratorygroupregister.GradeValue.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDate.*

data class Grade(val value: GradeValue = V2, val addedAt: LocalDate = now()): Serializable {

    fun value() = value.value

}

enum class GradeValue(val value: Double): Serializable {
    V2(2.0),
    V3(3.0),
    V3_5(3.5),
    V4(4.0),
    V4_5(4.5),
    V5(5.0)
}
