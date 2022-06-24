package cell.fles.xsklk

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import cell.fles.xsklk.binding.viewBinding
import cell.fles.xsklk.data.Data
import cell.fles.xsklk.databinding.ActivityMainBinding
import cell.fles.xsklk.fragments.DetailsFragment
import cell.fles.xsklk.fragments.MainFragment
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity(),
MainFragment.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var fragments: List<Fragment>

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private lateinit var buttonList: List<MaterialCardView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        fragments = listOf(
            MainFragment(this),
            DetailsFragment(Data.contents[0]),
            DetailsFragment(Data.contents[1]),
            DetailsFragment(Data.contents[2]),
            DetailsFragment(Data.contents[3]),
            DetailsFragment(Data.contents[4])
        )
        viewPagerAdapter = ViewPagerAdapter(this, fragments)

        with(binding.viewPager){
            adapter = viewPagerAdapter
            offscreenPageLimit = 5
            isUserInputEnabled = false

            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    showBottomMenu(position != 0)
                }
            })
        }

        with(binding.customNav){
            buttonList = listOf(
                button1,
                button2,
                button3,
                button4,
                button5
            )

            buttonList.forEachIndexed { i, item ->
                val content = Data.contents[i]
                item.strokeColor = ContextCompat.getColor(this@MainActivity, content.colorResource)
                val textView = item[0] as TextView
                textView.text = content.title
                item.setOnClickListener {
                    onPageClick(i + 1)
                }
            }
        }

    }

    private fun showBottomMenu(b: Boolean) {
        binding.bottomNav.visibility = if(b) View.VISIBLE else View.GONE
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onPageClick(position: Int) {
        binding.viewPager.currentItem = position
    }

    override fun onBackPressed() {
        if(binding.viewPager.currentItem > 0){
            binding.viewPager.currentItem = 0
        }else AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }
}