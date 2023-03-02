package com.example.notebook2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.Util.MyDate
import com.example.notebook2.ViewModel.UpdateViewModel
import com.example.notebook2.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(UpdateViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNote()

        binding.updateBtn.setOnClickListener {
            update()
        }
    }

    private fun getNote(){

        viewModel.getNote(getID())

        viewModel.noteModel.observe(viewLifecycleOwner,{
            binding.noteEdit.setText(it.notes)
            binding.titleEdit.setText(it.title)
        })
    }

    private fun update() {
        val title = binding.titleEdit.text.toString().trim()
        val note = binding.noteEdit.text.toString().trim()

        if (!title.equals("") && !note.equals("")) {
            viewModel.update(NotesModel(getID(), title, note, MyDate.myDate(requireContext())))
            goMain()
        } else {
            Toast.makeText(requireContext(), "Fill in the blanks", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goMain(){
        val direction = UpdateFragmentDirections.actionUpdateFragmentToMainFragment()
        Navigation.findNavController(requireView()).navigate(direction)
    }

    private fun getID() : Int{
        val bundle = arguments
        val args = UpdateFragmentArgs.fromBundle(bundle!!)
        return args.id
    }
}