package com.example.todoapp
 open class Note{

    var noteID:Int?=null
    var noteTitle:String?=null
    var noteDecscription:String?=null

    constructor(noteID:Int,noteTitle:String,noteDecscription:String){
        this.noteID = noteID
        this.noteTitle = noteTitle
        this. noteDecscription = noteDecscription
    }
}