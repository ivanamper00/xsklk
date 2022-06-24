package cell.fles.xsklk.fragments

import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.get
import cell.fles.xsklk.R
import cell.fles.xsklk.base.BaseFragment
import cell.fles.xsklk.binding.viewBinding
import cell.fles.xsklk.data.Data
import cell.fles.xsklk.databinding.FragmentMainBinding
import com.google.android.material.card.MaterialCardView

class MainFragment(
    private val listener: Listener
): BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private lateinit var buttonList: List<MaterialCardView>

    override fun setupViews() {

        with(binding){
            buttonList = listOf(
                button1,
                button2,
                button3,
                button4,
                button5
            )
            buttonList.forEachIndexed { i, item ->
                val content = Data.contents[i]
                item.strokeColor = ContextCompat.getColor(requireContext(), content.colorResource)
                val textView = item[0] as TextView
                textView.text = content.title
                item.setOnClickListener {
                    listener.onPageClick(i + 1)
                }
            }

            desciptionText.text = HtmlCompat.fromHtml(Data.contents[0].content, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun viewModelObservers() {
    }

    interface Listener {
        fun onPageClick(position: Int)
    }


}