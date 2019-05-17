package pl.edu.pollub.laboratorygroupregister.samples

import pl.edu.pollub.laboratorygroupregister.Grade
import pl.edu.pollub.laboratorygroupregister.GradeValue

import java.time.LocalDate

class SampleGrade {

    static Grade sampleGrade(customProperties = [:]) {
        def properties = [
                value: GradeValue.V2,
                addedAt: LocalDate.of(2019, 2, 1)
        ] + customProperties
        return new Grade(
                (GradeValue) properties.value,
                (LocalDate) properties.addedAt
        )
    }

}
