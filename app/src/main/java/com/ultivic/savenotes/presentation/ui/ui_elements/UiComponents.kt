package com.ultivic.savenotes.presentation.ui.ui_elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeTextField(textString: String = "", fontSize: Int, title: String = ""): String {
    var title by rememberSaveable { mutableStateOf(title) }
    TextField(value = title, onValueChange = { value ->
        title = value
    }, modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(text = textString, style = TextStyle(fontSize = fontSize.sp))
        }, textStyle = TextStyle(fontSize = fontSize.sp)
    )
    return title
}

@Composable
fun ComposeButton(onClick: () -> Unit, buttonText: String = "Save") {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp)
        )

    }
}