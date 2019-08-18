package com.testdevlab.tdl_chocolateboxapp.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.testdevlab.tdl_chocolateboxapp.R
import com.testdevlab.tdl_chocolateboxapp.apdapters.ChocolateAdapter
import com.testdevlab.tdl_chocolateboxapp.interfaces.ChocolateClickListener
import com.testdevlab.tdl_chocolateboxapp.models.Chocolate
import com.testdevlab.tdl_chocolateboxapp.utils.CacheHandler
import kotlinx.android.synthetic.main.activity_all.*

class AllActivity : AppCompatActivity(), ChocolateClickListener {

    private val chocolates = ArrayList<Chocolate>()
    private val adapter = ChocolateAdapter(chocolates, this)
    private lateinit var cacheHandler: CacheHandler



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        cacheHandler= CacheHandler(this)

        val cache = cacheHandler.getChocolates()
        if (cache != null){
            chocolates.clear()
            chocolates.addAll(cache)
        }

        chocolate_list.layoutManager = LinearLayoutManager(this)
        chocolate_list.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onChocolateLongClicked(index: Int) {
        chocolates.removeAt(index)
        adapter.notifyItemRemoved(index)
        cacheHandler.saveChocolates(chocolates)

        if (chocolates.isEmpty()) {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}