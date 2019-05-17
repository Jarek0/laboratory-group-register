package pl.edu.pollub.laboratorygroupregister

import spock.lang.Specification

import static pl.edu.pollub.laboratorygroupregister.samples.SampleLaboratoryGroupMeeting.sampleLaboratoryGroupMeeting
import static pl.edu.pollub.laboratorygroupregister.samples.SampleStudent.sampleStudent

class LaboratoryGroupMeetingSpec extends Specification {

    def "should add student presence to laboratory meeting"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def meeting = sampleLaboratoryGroupMeeting()
        when:
            meeting.addStudentPresence(student.indexNumber)
        then:
            meeting.studentsPresences.contains(student.indexNumber)
    }

    def "should cancel student presence in laboratory meeting"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def meeting = sampleLaboratoryGroupMeeting()
            meeting.addStudentPresence(student.indexNumber)
        when:
            meeting.cancelStudentPresence(student.indexNumber)
        then:
            !meeting.studentsPresences.contains(student.indexNumber)
    }
}
