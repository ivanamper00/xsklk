package cell.fles.xsklk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB>(@LayoutRes private val layout: Int): Fragment() {

    protected abstract val binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelObservers()
        setupViews()
    }

    abstract fun setupViews()

    abstract fun viewModelObservers()

    open fun onBackPressed(){
        requireActivity().onBackPressed()
    }

    open fun snackToast(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

}
