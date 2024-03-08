package com.ultivic.savenotes.presentation.ui.screens


import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ultivic.savenotes.R
import com.ultivic.savenotes.domain.model.NoteDetailsModel
import com.ultivic.savenotes.presentation.viewmodel.MyViewModel
import com.ultivic.savenotes.utils.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MyViewModel) {
    val noteList by viewModel.noteListObservee.observeAsState(ArrayList())
    val selectedItem = remember { mutableStateOf<NoteDetailsModel?>(null) } // Track selected item
    val searchQuery = remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.notes),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            background = Color.Transparent,
                        )
                    )
                },
                backgroundColor = Color(0xFF6650a4),
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    // Add a menu to the app bar
                    IconButton(onClick = {
                        menuExpanded = !menuExpanded
                    }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(text = {
                            Text(
                                text = stringResource(id = R.string.sign_out), style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black // Set the color of the text
                                )
                            )
                        }, onClick = {
                            Toast.makeText(
                                context,
                                R.string.coming_soon,
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    }
                }
            )

        },
        floatingActionButton = {
            ComposeFab {
                navController.navigate(Constants.EDIT_SCREEN)
            }
        }
    ) {
        Column(modifier = Modifier.background(Color.Black)) {

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text(stringResource(id = R.string.search_here), style = TextStyle(color = Color.Green)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Green,
                    cursorColor = Color.Blue,
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Yellow,
                    disabledLabelColor = Color.Gray,
                    errorLabelColor = Color.Red,
                    focusedLabelColor = Color.Magenta,
                    unfocusedLabelColor = Color.Yellow
                ),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                })

            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .background(Color.Black)
            ) {

                val filteredNotes = noteList.filter {
                    it.titleName.contains(searchQuery.value, ignoreCase = true) ||
                            it.titleDescription.contains(searchQuery.value, ignoreCase = true)
                }
                viewModel.getAllNotes()
                ComposeNoteList(listData = filteredNotes, viewModel, navController, selectedItem)
            }
        }
    }

}

@Composable
fun ComposeFab(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick.invoke() }) {
        Icon(Icons.Default.Add, "Floating Action Button")
    }
}

@Composable
fun ComposeNoteList(
    listData: List<NoteDetailsModel>,
    viewModel: MyViewModel,
    navController: NavHostController,
    selectedItem: MutableState<NoteDetailsModel?>
) {
    var isLoading by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(listData) { item ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(color = Color.Cyan, shape = RoundedCornerShape(20.dp))
                        .clickable {
                            selectedItem.value = item
                            Log.e("vvvvv", selectedItem.value!!.titleName)
                            viewModel.handleSelectedItem(item)
                            navController.navigate(Constants.UPDATE_SCREEN)

                        }
                ) {
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                    ) {
                        Text(
                            text = item.titleName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.titleDescription,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.timeStamp!!,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            maxLines = 2
                        )
                    }
                    IconButton(onClick = {
                        viewModel.delete(item)
                        isLoading = true
                        GlobalScope.launch {
                            delay(1000)
                            viewModel.getAllNotes()
                            isLoading = false

                        }
                    }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }

        if (isLoading) {
            Log.e("sss", isLoading.toString())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}
