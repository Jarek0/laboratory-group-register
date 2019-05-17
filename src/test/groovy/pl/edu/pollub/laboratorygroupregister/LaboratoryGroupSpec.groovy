package pl.edu.pollub.laboratorygroupregister

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

import static pl.edu.pollub.laboratorygroupregister.samples.SampleStudent.sampleStudent

class LaboratoryGroupSpec extends Specification {

    @Subject
    private def laboratoryGroup

    def setup() {
        laboratoryGroup = new LaboratoryGroup()
    }

    def "should add student to laboratory group"() {
        given:
            def indexNumber = "81663"
            def student = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
        when:
            laboratoryGroup.add(student)
        then:
            def foundStudent = laboratoryGroup.findStudentByIndexNumber(new IndexNumber(indexNumber))
            foundStudent.indexNumber == student.indexNumber
            foundStudent.name == student.name
            foundStudent.lastName == student.lastName
    }

    def "should throw exception when add student with already existing index number to laboratory group"() {
        given:
            def indexNumber = "81663"
            def student1 = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
            def student2 = sampleStudent(indexNumber: indexNumber, name: "Adam", lastName: "Nowak")
        when:
            laboratoryGroup.add(student1)
            laboratoryGroup.add(student2)
        then:
            def e = thrown(StudentAlreadyExistsException)
            e.indexNumber.value == indexNumber
    }

    def "should update student in laboratory group"() {
        given:
            def indexNumber = "81663"
            def student = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
            laboratoryGroup.add(student)
            def updatedStudent = sampleStudent(indexNumber: indexNumber, name: "Andrzej", lastName: "Kowalski")
        when:
            laboratoryGroup.update(updatedStudent)
        then:
            def foundStudent = laboratoryGroup.findStudentByIndexNumber(new IndexNumber(indexNumber))
            foundStudent.indexNumber == updatedStudent.indexNumber
            foundStudent.name == updatedStudent.name
            foundStudent.lastName == updatedStudent.lastName
    }

    def "should throw exception when try update student with not existing index number in laboratory group"() {
        given:
            def indexNumber = "81663"
            def student1 = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
        when:
            laboratoryGroup.update(student1)
        then:
            def e = thrown(StudentDoesNotExistException)
            e.indexNumber.value == indexNumber
    }

    def "should remove student from laboratory group"() {
        given:
            def indexNumber = "81663"
            def student = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
            laboratoryGroup.add(student)
        when:
            laboratoryGroup.remove(student)
        then:
            def nonExist = laboratoryGroup.findStudentByIndexNumber(new IndexNumber(indexNumber))
            nonExist == null
    }

    def "should calculate grade average for laboratory group"() {
        given:
            def student1 = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grade1 = new Grade(GradeValue.V3, LocalDate.now())
            def grade2 = new Grade(GradeValue.V4, LocalDate.now())
            student1.addGrades([grade1, grade2])

            def student2 = sampleStudent(indexNumber: "81662", name: "Adam", lastName: "Nowak")
            def grade3 = new Grade(GradeValue.V5, LocalDate.now())
            def grade4 = new Grade(GradeValue.V4, LocalDate.now())
            student2.addGrades([grade3, grade4])

            laboratoryGroup.add(student1)
            laboratoryGroup.add(student2)
        when:
            def average = laboratoryGroup.calculateGroupAverage()
        then:
            average == 4
    }

}
