package pl.edu.pollub.laboratorygroupregister

import java.io.*

class FileParser {

    fun serializeLaboratoryMeetingsRegister(path: String, laboratoryMeetingsRegister: LaboratoryMeetingsRegister) {
        val fileOutputStream = FileOutputStream(File(path))
        val objectOutputStream = ObjectOutputStream(fileOutputStream)

        objectOutputStream.writeObject(laboratoryMeetingsRegister)

        objectOutputStream.close()
        fileOutputStream.close()
    }

    fun deserializeLaboratoryMeetingsRegister(path: String): LaboratoryMeetingsRegister {
        val fileInputStream = FileInputStream(File(path))
        val objectInputStream = ObjectInputStream(fileInputStream)

        val register = objectInputStream.readObject() as LaboratoryMeetingsRegister

        objectInputStream.close()
        fileInputStream.close()

        return register
    }

    fun serializeLaboratoryGroup(path: String, laboratoryGroup: LaboratoryGroup) {
        val fileOutputStream = FileOutputStream(File(path))
        val objectOutputStream = ObjectOutputStream(fileOutputStream)

        objectOutputStream.writeObject(laboratoryGroup)

        objectOutputStream.close()
        fileOutputStream.close()
    }

    fun deserializeLaboratoryGroup(path: String): LaboratoryGroup {
        val fileInputStream = FileInputStream(File(path))
        val objectInputStream = ObjectInputStream(fileInputStream)

        val group = objectInputStream.readObject() as LaboratoryGroup

        objectInputStream.close()
        fileInputStream.close()

        return group
    }
}