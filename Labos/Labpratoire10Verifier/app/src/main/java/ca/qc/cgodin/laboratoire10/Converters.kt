package ca.qc.cgodin.laboratoire10

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }

}
