package com.bogadevelopment.shoppinglist.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bogadevelopment.shoppinglist.R
import com.bogadevelopment.shoppinglist.Routes
import com.bogadevelopment.shoppinglist.ui.theme.*


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navigationController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Gradient_background_dark,
                        Gradient_background_light
                    )
                )
            )
    ) {
        Header(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp))
        Body(Modifier.align(Alignment.Center), loginViewModel, navigationController)
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
fun Body(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    navigationController: NavHostController
) {
    val email : String by loginViewModel.email.observeAsState(initial = "")  // It's subscribed to ViewModel
    val password : String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable : Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Card(modifier = modifier.fillMaxWidth(),
        elevation = 7.dp,
        shape = RoundedCornerShape(30.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Gradient_background_light,
                        Gradient_background_dark
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
            Email(email) {
                loginViewModel.onLoginChanged(it, password = password)
            }
            Spacer(modifier = Modifier.size(14.dp))
            Password(password) {
                loginViewModel.onLoginChanged(email = email, password = it)
            }
            Spacer(modifier = Modifier.size(4.dp))
            ForgotPassword(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.size(20.dp))
            LoginButton(
                loginViewModel,
                isLoginEnable,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(0.08f),
                email,
                password
            )
            Spacer(modifier = Modifier.size(40.dp))
            Register(modifier = Modifier.align(Alignment.CenterHorizontally), navigationController)
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

    val selectColors  = TextSelectionColors(
        handleColor = Accent_Color,
        backgroundColor = Accent_Color_Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
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
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {

    var passwordVisibility by remember { mutableStateOf(false) }
    val selectColors  = TextSelectionColors(
        handleColor = Accent_Color,
        backgroundColor = Accent_Color_Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
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
            trailingIcon = {                    // Change the Icon if Password is visible
                val image =
                    if (passwordVisibility) {
                        Icons.Filled.VisibilityOff
                    } else {
                        Icons.Filled.Visibility
                    }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = image, contentDescription = "Show Password")
                }
            },
            visualTransformation = if (passwordVisibility) {      // Logic to hide the password
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}

@Composable
fun LoginButton(loginViewModel: LoginViewModel, loginEnable: Boolean, modifier: Modifier, email: String, password: String) {
    OutlinedButton(
        onClick = { loginViewModel.signInWithEmailAndPassword(email, password) },
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
fun Register(modifier: Modifier, navigationController: NavHostController){
    Row(
        modifier = modifier
    ){
        Text(text = "Don't have a account? ", fontSize = 16.sp, color = Text_Color)
        Text(
            text = "Register",
            fontSize = 16.sp,
            color = Accent_Color,
            modifier = Modifier.clickable { navigationController.navigate(Routes.RegisterScreen.route) })
    }
}

@Composable
fun WhaterMark(modifier: Modifier){

    Text(
        modifier = modifier,
        text = "By BogaDevelopment",
        color = WhaterMark
    )
}

