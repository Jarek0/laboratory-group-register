package pl.edu.pollub.laboratorygroupregister.samples

import pl.edu.pollub.laboratorygroupregister.IndexNumber
import pl.edu.pollub.laboratorygroupregister.Student

class SampleStudent {

    static Student sampleStudent(customProperties = [:]) {
        def properties = [
                indexNumber: "",
                name: "",
                lastName: ""
        ] + customProperties
        return new Student(
                new IndexNumber(properties.indexNumber),
                (String) properties.name,
                (String) properties.lastName
        )
    }

}
