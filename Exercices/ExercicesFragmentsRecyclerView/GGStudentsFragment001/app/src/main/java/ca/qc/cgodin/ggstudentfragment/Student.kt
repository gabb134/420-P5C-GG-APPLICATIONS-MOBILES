package ca.qc.cgodin.ggstudentfragment

import java.util.*

data class Student(var title: String = "",
                   var isGoodStudent: Boolean = false,
                        var firstName: String="" ,
                        var lastName: String=""
                 ){
    private lateinit var id: UUID
    var birthDate: Date = Date()

    constructor(id: UUID = UUID.randomUUID(), title:String, isGoodStudent: Boolean,firstName: String,lastName: String) : this(title = title, birthDate = Date(), isGoodStudent = isGoodStudent,firstName = firstName,lastName=lastName){
        this.id = id
    }

    constructor(title: String, birthDate:Date, isGoodStudent: Boolean,firstName: String,lastName: String):this(title, isGoodStudent,firstName,lastName){
        this.birthDate = birthDate
    }

}