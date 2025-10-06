package com.unsoed.informatikamobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.informatikamobile.data.model.BookDoc
import com.unsoed.informatikamobile.databinding.ActivityDaftarBukuBinding
import com.unsoed.informatikamobile.ui.adapter.BookAdapter
import com.unsoed.informatikamobile.ui.adapter.OnBookClickListener
import com.unsoed.informatikamobile.ui.fragment.BookDetailFragment
import com.unsoed.informatikamobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reciclerView.layoutManager = LinearLayoutManager(this)
        binding.reciclerView.adapter = adapter

        viewModel.books.observe(this) {
            adapter.setData(it)
        }

        viewModel.fetchBooks("Kotlin Programming")
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b ->
            BookDetailFragment(
                b.title ?: "-",
                b.authorName?.joinToString(", ") ?: "Unknown Author",
                "${b.firstPublishYear}",
                b.coverId?:0
            ).show(supportFragmentManager,BookDetailFragment::class.java.simpleName)
        }
    }

}