package com.testdevlab.tdl_chocolateboxapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.testdevlab.tdl_chocolateboxapp.utils.CacheHandler
import com.testdevlab.tdl_chocolateboxapp.R
import com.testdevlab.tdl_chocolateboxapp.models.Chocolate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val chocolates = ArrayList<Chocolate>()
    private var clicked = false
    private lateinit var cacheHandler: CacheHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cacheHandler = CacheHandler(this)

        val cache = cacheHandler.getChocolates()
        if (cache != null) {
            chocolates.addAll(cache)
        }


        save.setOnClickListener {

            //Take text from input field
            val text: String = input.text.toString()

            if (text.isNotBlank()) {
                //Clear inputfield
                input.text.clear()

                //Cleate new object with text value in constructor
                val note = Chocolate(text)

                //Add text
                chocolates.add(note)

                cacheHandler.saveChocolates(chocolates)
                Toast.makeText(this, "New chocolate has been added", Toast.LENGTH_SHORT).show()

                updateButtonState()
            } else {
                Toast.makeText(this, "Enter chocolate name first", Toast.LENGTH_SHORT).show()

            }
        }

        show.setOnClickListener {

            if (!chocolates.isEmpty()) {
                val intent = Intent(this, AllActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
            } else {
                Toast.makeText(this, "Box of chocolates is empty!", Toast.LENGTH_SHORT).show()
            }
            updateButtonState()
        }

        delete.setOnClickListener {

            if (!chocolates.isEmpty()) {
                chocolates.removeAt(chocolates.size - 1)
                Toast.makeText(this, "Chocolate has been deleted", Toast.LENGTH_SHORT).show()
                cacheHandler.saveChocolates(chocolates)
                updateButtonState()
            } else {
                Toast.makeText(this, "There is nothing to delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        clicked = false

        cacheHandler = CacheHandler(this)

        val cache = cacheHandler.getChocolates()

        if (cache != null) {
            chocolates.clear()
            chocolates.addAll(cache)
        }

        updateButtonState()
    }

    private fun updateButtonState() {
        if (chocolates.isEmpty()) {
            delete.setBackgroundColor(getColor(R.color.button_background_disabled))
            show.setBackgroundColor(getColor(R.color.button_background_disabled))
        } else {
            delete.setBackgroundColor(getColor(R.color.button_background))
            show.setBackgroundColor(getColor(R.color.button_background))
        }
    }

}
