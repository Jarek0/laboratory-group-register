package pl.edu.pollub.laboratorygroupregister.samples

import pl.edu.pollub.laboratorygroupregister.LaboratoryGroupMeeting

import java.time.LocalDate

class SampleLaboratoryGroupMeeting {

    static LaboratoryGroupMeeting sampleLaboratoryGroupMeeting(customProperties = [:]) {
        def properties = [
                date: LocalDate.now(),
                studentsPresences: []
        ] + customProperties
        return new LaboratoryGroupMeeting(
                (LocalDate) properties.date,
                (Set) properties.studentsPresences
        )
    }

}
