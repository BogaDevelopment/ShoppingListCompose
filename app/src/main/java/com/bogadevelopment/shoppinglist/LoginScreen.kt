package com.bogadevelopment.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bogadevelopment.shoppinglist.ui.theme.*


@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Gradient_background_start,
                        Gradient_background_end
                    )
                )
            )
    ) {
        Header(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp))
        Body(Modifier.align(Alignment.Center))
        WhaterMark(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 12.dp))
    }

}

@Composable
fun Header(modifier: Modifier){
    Tittle(modifier)
}

@Composable
fun Tittle(modifier: Modifier) {
    Text(
        text = "WELCOME",
        fontSize = 42.sp,
        modifier = modifier,
        color = Text_Color
    )
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }

    Card(modifier = modifier.fillMaxWidth(),
        elevation = 7.dp,
        shape = RoundedCornerShape(30.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Gradient_card_start,
                        Gradient_card_end
                    )
                )
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            UserIcon(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(60.dp))
            Spacer(modifier = Modifier.size(18.dp))
            Email(email) { email = it }
            Spacer(modifier = Modifier.size(14.dp))
            Password(password) { password = it }
            Spacer(modifier = Modifier.size(4.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.size(20.dp))
            LoginButton(
                isLoginEnable,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(0.08f)
            )
            Spacer(modifier = Modifier.size(40.dp))
            Register(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@Composable
fun UserIcon(modifier: Modifier){
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_user) ,
        contentDescription = "UserIcon"
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        label = { Text(text = "Email", color = Text_Color) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Accent_Color,
            cursorColor = Text_Color,
            unfocusedBorderColor = Text_Color
        )

    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {

    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        label = { Text(text = "Password", color = Text_Color) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Accent_Color,
            cursorColor = Text_Color,
            unfocusedBorderColor = Text_Color
        ),
        trailingIcon = {
            val image =
                if(passwordVisibility){
                    Icons.Filled.VisibilityOff
                }else{
                    Icons.Filled.Visibility
                }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show Password")
            }
        }
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, modifier: Modifier) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        enabled = loginEnable,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Button_enable_color,
            disabledBackgroundColor = Button_disabled_color,
            disabledContentColor = Content_Disable,
            contentColor = Text_Color
        )
    ) {
        Text(
            text = "LOGIN",
            fontSize = 20.sp,
        )
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 14.sp,
        modifier = modifier.padding(horizontal = 15.dp),
        textDecoration = TextDecoration.Underline,
        color = Text_Color
    )
}

@Composable
fun Register(modifier: Modifier){
    Row(
        modifier = modifier
    ){
        Text(text = "Don't have a account? ", fontSize = 16.sp, color = Text_Color)
        Text(text = "Register", fontSize = 16.sp, color = Accent_Color)
    }
}

@Composable
fun WhaterMark(modifier: Modifier){

    Text(
        modifier = modifier,
        text = "By BogaDevelopment",
        color = com.bogadevelopment.shoppinglist.ui.theme.WhaterMark
    )
}

