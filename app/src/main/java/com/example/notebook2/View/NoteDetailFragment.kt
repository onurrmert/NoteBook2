package com.example.notebook2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notebook2.ViewModel.NoteDetailViewModel
import com.example.notebook2.databinding.FragmentNoteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(NoteDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNote()
    }

    private fun getNote(){

        viewModel.getNote(getID())

        viewModel.noteModel.observe(viewLifecycleOwner, {
            binding.noteText.setText(it.notes)
            binding.titleText.setText(it.title)
        })
    }

    private fun getID() : Int{
        val bundle = arguments
        val args = NoteDetailFragmentArgs.fromBundle(bundle!!)
        return args.id
    }
}