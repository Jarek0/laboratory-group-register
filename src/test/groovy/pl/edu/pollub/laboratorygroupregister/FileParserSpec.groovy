package pl.edu.pollub.laboratorygroupregister

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.time.LocalDate

import static pl.edu.pollub.laboratorygroupregister.samples.SampleLaboratoryGroupMeeting.sampleLaboratoryGroupMeeting
import static pl.edu.pollub.laboratorygroupregister.samples.SampleStudent.sampleStudent

class FileParserSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()

    def fileParser = new FileParser()

    def "should serialize laboratory meetings register to file"() {
        given:
            def file = temporaryFolder.newFile("laboratoryMeetingsRegister.txt")
            def registerToSerialize = new LaboratoryMeetingsRegister()
            def student1 = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def student2 = sampleStudent(indexNumber: "81662", name: "Adam", lastName: "Nowak")
            def meeting1 = sampleLaboratoryGroupMeeting(studentsPresences: [student1.indexNumber, student2.indexNumber])
            def meeting2 = sampleLaboratoryGroupMeeting(studentsPresences: [student1.indexNumber])
            registerToSerialize.add(meeting1)
            registerToSerialize.add(meeting2)
        when:
            fileParser.serializeLaboratoryMeetingsRegister(file.path, registerToSerialize)
        then:
            def deserializedRegister = fileParser.deserializeLaboratoryMeetingsRegister(file.path)
            deserializedRegister == registerToSerialize
    }

    def "should serialize laboratory group"() {
        given:
            def file = temporaryFolder.newFile("laboratoryGroup.txt")
            def student1 = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grade1 = new Grade(GradeValue.V3, LocalDate.now())
            def grade2 = new Grade(GradeValue.V4, LocalDate.now())
            student1.addGrades([grade1, grade2])

            def student2 = sampleStudent(indexNumber: "81662", name: "Adam", lastName: "Nowak")
            def grade3 = new Grade(GradeValue.V5, LocalDate.now())
            def grade4 = new Grade(GradeValue.V4, LocalDate.now())
            student2.addGrades([grade3, grade4])

            def laboratoryGroupToSerialize = new LaboratoryGroup()
            laboratoryGroupToSerialize.add(student1)
            laboratoryGroupToSerialize.add(student2)
        when:
            fileParser.serializeLaboratoryGroup(file.path, laboratoryGroupToSerialize)
        then:
            def deserializedLaboratoryGroup = fileParser.deserializeLaboratoryGroup(file.path)
            deserializedLaboratoryGroup == laboratoryGroupToSerialize
    }
}
