package pl.edu.pollub.laboratorygroupregister

import java.io.Serializable
import java.lang.IllegalStateException

class LaboratoryGroup: Serializable {

    private val students = mutableSetOf<Student>()

    fun add(student: Student) {
        if(students.contains(student)) throw StudentAlreadyExistsException(student.indexNumber)
        students.add(student)
    }

    fun update(student: Student) {
        if(students.notContains(student)) throw StudentDoesNotExistException(student.indexNumber)
        students.remove(student)
        students.add(student)
    }

    fun findStudentByIndexNumber(indexNumber: IndexNumber) = students.firstOrNull { it.indexNumber == indexNumber }

    fun remove(student: Student) = students.remove(student)

    fun calculateGroupAverage() = students.map { it.calculateGradesAverage() }.average()

    private fun Set<Student>.notContains(student: Student) = !contains(student)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LaboratoryGroup

        if (students != other.students) return false

        return true
    }

    override fun hashCode(): Int {
        return students.hashCode()
    }


}

class StudentAlreadyExistsException(val indexNumber: IndexNumber):
        IllegalStateException("Student with index number: $indexNumber already exists in laboratory group")

class StudentDoesNotExistException(val indexNumber: IndexNumber):
        IllegalStateException("Student with index number: $indexNumber does not exist in laboratory group")