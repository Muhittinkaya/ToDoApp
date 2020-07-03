package com.example.todoapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfNotes = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dummy data
        listOfNotes.add(Note(1,"Kotlin Çalış", "dsfghjhgbfhdbgscdfgvbhnjmköldcxsdcfghjkogfdsdrftyuolpıuytrdesdrf"))
        listOfNotes.add(Note(2,"Security", "dsfghjhgbfhdgdhfghklhjghfdbgscdfgvbhnjmköldcxsdcfghjkogfdsdrftyuolpıuytrdesdrf"))
        listOfNotes.add(Note(3,"Genom", "dsfghfdsghjkgyfthdgfsaafsgdhtyklıkyujhgbfhdbgscdfgvbhnjmköldcxsdcfghjkogfdsdrftyuolpıuytrdesdrf"))

        //Adapter ile listview i kullanılabilir yapmak.
        var myNotesAdapter = MyAdapter(listOfNotes)
        listViewNotes.adapter = myNotesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu) // this is for to menu items inflating

        //Now code for searching
        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                //Todo search db
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       if(item != null){
           when(item.itemId){
               R.id.addNote ->{
                   var intent = Intent(this,AddNotes::class.java)
                   startActivity(intent)
               }
           }
       }

        return super.onOptionsItemSelected(item)
    }


    //ticketı layoutta göstermek için kullanılan adapter
    inner class MyAdapter:BaseAdapter{
        var listOfAdapters = ArrayList<Note>()
        constructor(listOfAdapters: ArrayList<Note>):super(){
            this.listOfAdapters = listOfAdapters
        }

        //Asıl
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket,null) // ticket.xml e erişme
            var myNote = listOfAdapters[position]   //yazdıgımız notu adaptera yükleme

            myView.tvTitle.text = myNote.noteTitle
            myView.tvTodo.text = myNote.noteDecscription

            return myView
       }

        override fun getItem(position: Int): Any {
            return listOfAdapters[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAdapters.size
        }
    }
}
