package com.ultivic.savenotes.presentation.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.ultivic.savenotes.presentation.ui.ui_elements.ComposeButton
import com.ultivic.savenotes.presentation.ui.ui_elements.ComposeTextField
import com.ultivic.savenotes.presentation.viewmodel.MyViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(navController: NavHostController, viewModel: MyViewModel) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var titleSnackbarVisible by remember { mutableStateOf(false) }
    var descriptionSnackbarVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.add_new_note),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            background = Color.Transparent,
                        )
                    )
                },
                backgroundColor = Color(0xFF6650a4),
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 20.dp)
        ) {
            Row(

                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                title = ComposeTextField(
                    textString = stringResource(id = R.string.title),
                    fontSize = 18
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                description =
                    ComposeTextField(
                        textString = stringResource(id = R.string.write_your_description_here),
                        fontSize = 16
                    )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                ComposeButton(onClick = {

                    if (title.isEmpty()) {
                        titleSnackbarVisible = true
                        Toast.makeText(context, "Please Enter Title", Toast.LENGTH_SHORT).show()
                    } else if (description.isEmpty()) {
                        descriptionSnackbarVisible = true
                        Toast.makeText(context, "Please Enter Description", Toast.LENGTH_SHORT)
                            .show()

                    } else {
                        val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                        val currentDateAndTime: String = sdf.format(Date())
                        viewModel.addNotee(NoteDetailsModel(title, description, currentDateAndTime))
                        navController.popBackStack()
                    }

                }, buttonText = stringResource(id = R.string.save))
            }
        }
    }

    // Display Snackbar for empty title
    if (titleSnackbarVisible) {
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Gray),
            action = {
                TextButton(onClick = { titleSnackbarVisible = false }) {
                    Text(stringResource(id = R.string.ok), color = Color.White)
                }
            }
        ) {
            Text(stringResource(id = R.string.enter_title), color = Color.White)
        }
    }


    // Display Snackbar for empty description
    if (descriptionSnackbarVisible) {
        Snackbar(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Gray),
            action = {
                TextButton(onClick = { descriptionSnackbarVisible = false }) {
                    Text(stringResource(id = R.string.ok), color = Color.White)
                }
            }
        ) {
            Text(stringResource(id = R.string.enter_descriptions), color = Color.White)
        }
    }
}