package pl.edu.pollub.laboratorygroupregister

import java.io.Serializable

class Student(val indexNumber: IndexNumber, var name: String, var lastName: String): Serializable {

    private val grades = mutableListOf<Grade>()

    fun indexNumberValue() = indexNumber.value

    fun changeName(name: String) {
        this.name = name
    }

    fun changeLastName(lastName: String) {
        this.lastName = lastName
    }

    fun addGrade(grade: Grade) = grades.add(grade)

    fun addGrades(grades: Collection<Grade>) = this.grades.addAll(grades)

    fun removeGrade(grade: Grade) = grades.remove(grade)

    fun calculateGradesAverage() = grades.map { it.value() }.average()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (indexNumber != other.indexNumber) return false

        return true
    }

    override fun hashCode(): Int {
        return indexNumber.hashCode()
    }

}

data class IndexNumber(val value: String): Serializable