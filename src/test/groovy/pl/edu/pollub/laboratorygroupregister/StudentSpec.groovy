package pl.edu.pollub.laboratorygroupregister

import spock.lang.Specification

import java.time.LocalDate

import static pl.edu.pollub.laboratorygroupregister.samples.SampleStudent.sampleStudent

class StudentSpec extends Specification {

    def "should change student name"() {
        given:
            def indexNumber = "81663"
            def student = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
        when:
            student.changeName("Adam")
        then:
            student.name == "Adam"
    }

    def "should change student last name"() {
        given:
            def indexNumber = "81663"
            def student = sampleStudent(indexNumber: indexNumber, name: "Jarosław", lastName: "Bielec")
        when:
            student.changeLastName("Nowak")
        then:
            student.lastName == "Nowak"
    }

    def "should add some grade for student"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grade = new Grade(GradeValue.V5, LocalDate.now())
        when:
            student.addGrade(grade)
        then:
            def grades = student.grades
            grades.size() == 1
            grades.first() == grade
    }

    def "should add some grades for student"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grades = [
                    new Grade(GradeValue.V5, LocalDate.now()),
                    new Grade(GradeValue.V5, LocalDate.now())
            ]
        when:
            student.addGrades(grades)
        then:
            def studentGrades = student.grades
            studentGrades.size() == grades.size()
            studentGrades == grades
    }

    def "should remove grade for student"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grade = new Grade(GradeValue.V5, LocalDate.now())
            student.addGrade(grade)
        when:
            student.removeGrade(grade)
        then:
            def grades = student.grades
            grades.size() == 0
    }

    def "should calculate grade average for one student"() {
        given:
            def student = sampleStudent(indexNumber: "81663", name: "Jarosław", lastName: "Bielec")
            def grade1 = new Grade(GradeValue.V5, LocalDate.now())
            def grade2 = new Grade(GradeValue.V4, LocalDate.now())
            student.addGrades([grade1, grade2])
        when:
            def average = student.calculateGradesAverage()
        then:
            new BigDecimal(average) == 4.5
    }
}
