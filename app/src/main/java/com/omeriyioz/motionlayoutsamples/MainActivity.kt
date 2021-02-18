package com.omeriyioz.motionlayoutsamples

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

data class Example(
    val title: String,
    val activity: KClass<out Activity>
)

private val data = listOf(
    Example(
        "Example 1",
        Activity1::class
    ),
    Example(
        "Example 2",
        Activity2::class
    ),
    Example(
        "Example 3",
        Activity3::class
    ),
    Example(
        "Example 4",
        Activity4::class
    )
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = MainAdapter(data)
    }

}

class MainAdapter(val data: List<Example>) : RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])

    }

}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.textView)

    fun bind(example: Example) {
        textView.text = example.title

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, example.activity.java)
            itemView.context.startActivity(intent)
        }
    }

}