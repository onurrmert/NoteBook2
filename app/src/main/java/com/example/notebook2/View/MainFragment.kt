package com.example.notebook2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebook2.Adapter.IOnItemClickListener
import com.example.notebook2.Adapter.MainAdapter
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.R
import com.example.notebook2.ViewModel.MainViewModel
import com.example.notebook2.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllNotes()

        binding.addBtn.setOnClickListener {
            goInsert()
        }
    }

    private fun getAllNotes(){

        viewModel.getAllNote()

        viewModel.notesList.observe(viewLifecycleOwner, {
            if (it.size > 0) initRecycler(it)
            else Toast.makeText(requireContext(), "Note not found", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecycler(noteList : List<NotesModel>){
        val noteList2 = ArrayList<NotesModel>(noteList.sortedBy { it.history })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = MainAdapter(
            noteList2,
            object : IOnItemClickListener{
                override fun itemClick(notesModel: NotesModel, view: View) {
                    popUpMenu(notesModel, view)
                }
            }
        )
    }

    private fun popUpMenu(notesModel: NotesModel, view: View){
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.apply {
            this.inflate(R.menu.popup_menu)
            this.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.deletePopup -> println("silindi")

                    R.id.updatePopup ->{
                        goUpdate(notesModel.id)
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }.show()
    }

    private fun goUpdate(id : Int){
        val direction = MainFragmentDirections.actionMainFragmentToUpdateFragment(id)
        Navigation.findNavController(requireView()).navigate(direction)
    }

    private fun goInsert(){
        val direction = MainFragmentDirections.actionMainFragmentToInsertNoteFragment()
        Navigation.findNavController(requireView()).navigate(direction)
    }
}