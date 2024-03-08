package com.ultivic.savenotes.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ultivic.savenotes.domain.model.NoteDetailsModel
import com.ultivic.savenotes.presentation.viewmodel.MyViewModel
import com.ultivic.savenotes.ui.theme.SaveNotesTheme
import com.ultivic.savenotes.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaveNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    private fun MyApp() {
        val navController = rememberNavController()
        val viewModel = viewModel<MyViewModel>()
        val selectedItem = remember { mutableStateOf<NoteDetailsModel?>(null) }

        NavHost(
            navController = navController,
            startDestination = Constants.HOME_SCREEN,
            builder = {

                // route : Home
                composable(Constants.HOME_SCREEN) {
                    HomeScreen(navController, viewModel)
                }

                // route : search
                composable(Constants.EDIT_SCREEN) {
                    EditScreen(navController, viewModel)
                }
                composable(Constants.UPDATE_SCREEN) {
                    UpdateDetail(
                        navController = navController,
                        viewModel = viewModel,
                        selectedItem = selectedItem.value
                    )
                    Log.e("vvcc", selectedItem.toString())
                }
            })
    }
}

