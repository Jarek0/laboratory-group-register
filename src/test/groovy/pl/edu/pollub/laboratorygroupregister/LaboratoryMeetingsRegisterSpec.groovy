package pl.edu.pollub.laboratorygroupregister

import spock.lang.Specification
import spock.lang.Subject

import static pl.edu.pollub.laboratorygroupregister.samples.SampleLaboratoryGroupMeeting.sampleLaboratoryGroupMeeting
import static pl.edu.pollub.laboratorygroupregister.samples.SampleStudent.sampleStudent

class LaboratoryMeetingsRegisterSpec extends Specification {

    @Subject
    private def laboratoryMeetingsRegister

    def setup() {
        laboratoryMeetingsRegister = new LaboratoryMeetingsRegister()
    }

    def "should add meeting with present students"() {
        given:
            def student1 = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def student2 = sampleStudent(indexNumber: "81662", name: "Adam", lastName: "Nowak")
            def meeting = sampleLaboratoryGroupMeeting()
            meeting.addStudentPresence(student1.indexNumber)
            meeting.addStudentPresence(student2.indexNumber)
        when:
            laboratoryMeetingsRegister.add(meeting)
        then:
            def foundMeeting = laboratoryMeetingsRegister.findById(meeting.id)
            foundMeeting.studentsPresences.size() == 2
            foundMeeting.studentsPresences.containsAll([student1.indexNumber, student2.indexNumber])
    }

    def "should throw exception when student attend to more than 15 meetings"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            for(meetingNumber in 1..15) {
                def meeting = sampleLaboratoryGroupMeeting()
                meeting.addStudentPresence(student.indexNumber)
                laboratoryMeetingsRegister.add(meeting)
            }
            def meeting16th = sampleLaboratoryGroupMeeting()
            meeting16th.addStudentPresence(student.indexNumber)
        when:
            laboratoryMeetingsRegister.add(meeting16th)
        then:
            thrown(IllegalPresenceCountNumberException)
    }

    def "should add 15 student attendances"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            for(meetingNumber in 1..14) {
                def meeting = sampleLaboratoryGroupMeeting()
                meeting.addStudentPresence(student.indexNumber)
                laboratoryMeetingsRegister.add(meeting)
            }
            def meeting15th = sampleLaboratoryGroupMeeting()
            meeting15th.addStudentPresence(student.indexNumber)
        when:
            laboratoryMeetingsRegister.add(meeting15th)
        then:
            notThrown(IllegalPresenceCountNumberException)
    }

    def "should calculate student presences on meetings count"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def meeting1 = sampleLaboratoryGroupMeeting(studentsPresences: [student.indexNumber])
            def meeting2 = sampleLaboratoryGroupMeeting(studentsPresences: [student.indexNumber])
            laboratoryMeetingsRegister.add(meeting1)
            laboratoryMeetingsRegister.add(meeting2)
        when:
            def presencesCount = laboratoryMeetingsRegister.presencesCount(student.indexNumber)
        then:
            presencesCount == 2
    }

    def "should calculate student absences on meetings count"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def meeting1 = sampleLaboratoryGroupMeeting(studentsPresences: [student.indexNumber])
            def meeting2 = sampleLaboratoryGroupMeeting()
            def meeting3 = sampleLaboratoryGroupMeeting()
            laboratoryMeetingsRegister.add(meeting1)
            laboratoryMeetingsRegister.add(meeting2)
            laboratoryMeetingsRegister.add(meeting3)
        when:
            def presencesCount = laboratoryMeetingsRegister.absencesCount(student.indexNumber)
        then:
            presencesCount == 2
    }
}
