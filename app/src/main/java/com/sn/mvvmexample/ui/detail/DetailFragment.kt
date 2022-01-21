package com.sn.mvvmexample.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sn.mvvmexample.R
import com.sn.mvvmexample.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private val binding by lazy {
        DetailFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvId.text = args.argsProjectName.id.toString()
        binding.tvTitle.text = args.argsProjectName.title

        val image =
            if (args.argsProjectName.completed == true) R.drawable.ic_true else R.drawable.ic_false

        binding.ivCompleted.setImageResource(image)

    }

}