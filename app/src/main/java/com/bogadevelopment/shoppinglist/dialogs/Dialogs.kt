package com.bogadevelopment.shoppinglist.dialogs

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun NewShoppingCartDialog(show: Boolean, onDismiss:() -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties()
        ) {
            Column(
                Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Tittle("New Shopping Cart", 20);
                CustomTextField("Tittle")
                Buttons({onDismiss()})
            }
        }
    }
}

@Composable
fun Tittle(text: String, fontSize: Int) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = fontSize.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.padding(bottom = 12.dp)

    )
}

@Composable
fun CustomButton(text: String, modifier: Modifier, onClick:() -> Unit) {
    Button(onClick = {  onClick() }, modifier = modifier) {
        Text(text = text)
    }
}

@Composable
fun Buttons(onCancel:() -> Unit){
    Row (
        Modifier
            .fillMaxWidth()
    ){
        CustomButton(text = "Cancel",
            Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 5.dp, end = 5.dp)

        ) { onCancel() }
        Spacer(modifier = Modifier.size(5.dp))
        CustomButton(text = "Add",
            Modifier
                .fillMaxWidth(1f)
                .padding(start = 5.dp, end = 5.dp)
        ) { /*TODO*/ }
    }
}

@Composable
fun CustomTextField(label: String) {
    TextField(
        value = "", onValueChange = {}, label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.secondary,
            backgroundColor = MaterialTheme.colors.primaryVariant,
            textColor = MaterialTheme.colors.onPrimary,
            cursorColor = MaterialTheme.colors.onPrimary,
        ),
        modifier = Modifier.padding(top = 5.dp, bottom = 25.dp)
    )
}


