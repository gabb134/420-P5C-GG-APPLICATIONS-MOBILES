package ca.qc.cgodin.database

class Student(var firstName: String, var lastName:String, var email: String, var phoneNumber: String, var username: String, var password: String) {
    private var id: Int = 0

    constructor(id: Int, firstName: String, lastName: String, email: String, phoneNumber: String, username: String, password: String)
            :this(firstName, lastName, email, phoneNumber,  username, password)

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Nom de l'étudiant = $username\nPassword de l'étudiant = $password")
        return sb.toString()
    }
}