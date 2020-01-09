package ua.turskyi.viewholderexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), GreenAdapter.ListItemClickListener {
    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private var mAdapter: GreenAdapter? = null
    private var mNumbersList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */mNumbersList = findViewById<View>(R.id.rv_numbers) as RecyclerView
        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         */
        val layoutManager = LinearLayoutManager(this)
        mNumbersList!!.layoutManager = layoutManager
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */mNumbersList!!.setHasFixedSize(true)
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)
        mNumbersList!!.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.action_refresh -> {
                mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)
                mNumbersList!!.adapter = mAdapter
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * This is where we receive our callback from
     * [GreenAdapter.ListItemClickListener]
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    override fun onListItemClick(clickedItemIndex: Int) {
        val mToast = Toast.makeText(
            this@MainActivity,
            "Item #$clickedItemIndex clicked.",
            Toast.LENGTH_SHORT
        )
        mToast?.let {
            mToast.cancel()
        }
        mToast.show()
    }

    companion object {
        private const val NUM_LIST_ITEMS = 100
    }
}
