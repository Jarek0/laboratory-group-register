package pl.edu.pollub.laboratorygroupregister

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDate.*
import java.util.*

class LaboratoryGroupMeeting(private val date: LocalDate = now(), val studentsPresences: MutableSet<IndexNumber> = mutableSetOf()): Serializable {

    val id = ClassMeetingId()

    fun addStudentPresence(presentStudentIndexNumber: IndexNumber) = studentsPresences.add(presentStudentIndexNumber)

    fun cancelStudentPresence(presentStudentIndexNumber: IndexNumber) = studentsPresences.remove(presentStudentIndexNumber)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LaboratoryGroupMeeting

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}

data class ClassMeetingId(private val value: String = UUID.randomUUID().toString()): Serializable