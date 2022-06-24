package cell.fles.xsklk.fragments

import androidx.core.text.HtmlCompat
import cell.fles.xsklk.R
import cell.fles.xsklk.base.BaseFragment
import cell.fles.xsklk.binding.viewBinding
import cell.fles.xsklk.data.DataModel
import cell.fles.xsklk.databinding.FragmentDetailsBinding

class DetailsFragment(
    private val content: DataModel
): BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    override fun setupViews() {
        with(binding){
            contentTitle.text = content.title
            desciptionText.text = HtmlCompat.fromHtml(content.content,HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun viewModelObservers() {
    }
}