package pl.edu.pollub.laboratorygroupregister

import java.io.Serializable
import java.lang.IllegalStateException

class LaboratoryMeetingsRegister: Serializable{

    private val classMeetings = mutableSetOf<LaboratoryGroupMeeting>()

    fun add(laboratoryGroupMeeting: LaboratoryGroupMeeting) {
        if(classMeetings.count() == MAX_ATTENDANCE_COUNT) throw IllegalPresenceCountNumberException()
        classMeetings.add(laboratoryGroupMeeting)
    }

    fun findById(id: ClassMeetingId) = classMeetings.find { it.id == id }

    fun presencesCount(indexNumber: IndexNumber) = classMeetings
            .filter { it.studentsPresences.contains(indexNumber) }
            .count()

    fun absencesCount(indexNumber: IndexNumber) = classMeetings.count() - presencesCount(indexNumber)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LaboratoryMeetingsRegister

        if (classMeetings != other.classMeetings) return false

        return true
    }

    override fun hashCode(): Int {
        return classMeetings.hashCode()
    }

}


const val MAX_ATTENDANCE_COUNT = 15

class IllegalPresenceCountNumberException: IllegalStateException("Class meetings count cannot be greater than $MAX_ATTENDANCE_COUNT")