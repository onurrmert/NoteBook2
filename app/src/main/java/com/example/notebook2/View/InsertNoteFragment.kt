package com.example.notebook2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.Util.MyDate.Companion.myDate
import com.example.notebook2.ViewModel.InsertViewModel
import com.example.notebook2.databinding.FragmentInsertNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNoteFragment : Fragment() {

    private lateinit var binding: FragmentInsertNoteBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(InsertViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backPressed()

        binding.insertBtn.setOnClickListener {
            getNote()
        }
    }

    private fun getNote(){
        val title = binding.titleEdit.text.toString().trim()
        val note = binding.noteEdit.text.toString().trim()

        if (!title.equals("") && !note.equals("")){
            viewModel.insert(NotesModel(title, note, myDate(requireContext())))
            goMain()
        }else{
            Toast.makeText(requireContext(), "Fill in the blanks", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goMain(){
        val direction = InsertNoteFragmentDirections.actionInsertNoteFragmentToMainFragment()
        Navigation.findNavController(requireView()).navigate(direction)
    }
    private fun backPressed(){
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    goMain()
                }
            })
    }
}