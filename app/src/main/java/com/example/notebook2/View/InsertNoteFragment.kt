package com.example.notebook2.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notebook2.databinding.FragmentInsertNoteBinding

class InsertNoteFragment : Fragment() {

    private lateinit var binding: FragmentInsertNoteBinding

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
}