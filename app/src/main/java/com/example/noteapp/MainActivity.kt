package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.ViewModel.NoteRepository
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.View.NoteDetailScreen
import com.example.noteapp.View.NoteListScreen
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo Database và ViewModel
        val database = NoteDatabase.getDatabase(this)
        val repository = NoteRepository(database.noteDao())
        val noteViewModel: NoteViewModel by viewModels { NoteViewModelFactory(repository) }

        setContent {
            NoteAppTheme {
                NoteApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NoteApp(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "note_list",
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            composable("note_list") {
                NoteListScreen(navController, viewModel)
            }

            // Màn hình thêm ghi chú
            composable("add_note") {
                NoteDetailScreen(navController, viewModel, noteId = null)
            }

            // Màn hình chỉnh sửa ghi chú
            composable(
                "edit_note/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId")
                NoteDetailScreen(navController, viewModel, noteId)
            }
        }
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}