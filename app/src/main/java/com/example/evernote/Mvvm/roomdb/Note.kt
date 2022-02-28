package com.example.evernote.Mvvm.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*data class Note(
    @PrimaryKey val id: Int,
    val noteTitle : String,
    val noteDescription :String,
    val timeStamp :String
)*/
@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name = "title")val noteTitle :String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
