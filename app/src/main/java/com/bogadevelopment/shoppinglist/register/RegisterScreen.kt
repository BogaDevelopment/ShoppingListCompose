package com.bogadevelopment.shoppinglist.register

import androidx.compose.foundation.background
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bogadevelopment.shoppinglist.ui.theme.*


@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel){
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

    ){
        Header(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
        Body(Modifier.padding(top = 230.dp),registerViewModel)
    }
}

@Composable
fun Header(modifier: Modifier){
    Tittle(modifier)
}

@Composable
fun Tittle(modifier : Modifier){
    Text(
        text = "SIGN UP",
        fontSize = 42.sp,
        modifier = modifier,
        color = Text_Color
    )
}

@Composable
fun Body(modifier: Modifier, registerViewModel: RegisterViewModel){
    val name : String by registerViewModel.name.observeAsState(initial = "")  // It's subscribed to ViewModel
    val lastName : String by registerViewModel.lastName.observeAsState(initial = "")
    val email : String by registerViewModel.email.observeAsState(initial = "")
    val password : String by registerViewModel.password.observeAsState(initial = "")
    val repeatPassword : String by registerViewModel.repeatPassword.observeAsState(initial = "")
    val isRegisterEnable : Boolean by registerViewModel.isRegisterEnable.observeAsState(initial = false)


    Column(modifier = modifier
        .fillMaxWidth()
    ){
        Name(name){
            registerViewModel.onRegisterChanged(
                name = it,
                lastName = lastName,
                email = email,
                password = password,
                repeatPassword = repeatPassword
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        LastName(lastName){
            registerViewModel.onRegisterChanged(
                name = name,
                lastName = it,
                email = email,
                password = password,
                repeatPassword = repeatPassword
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        Email(email){
            registerViewModel.onRegisterChanged(
                name = name,
                lastName = lastName,
                email = it,
                password = password,
                repeatPassword = repeatPassword
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        Password(password){
            registerViewModel.onRegisterChanged(
                name = name,
                lastName = lastName,
                email = email,
                password = it,
                repeatPassword = repeatPassword
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        RepeatPassword(repeatPassword){
            registerViewModel.onRegisterChanged(
                name = name,
                lastName = lastName,
                email = email,
                password = password,
                repeatPassword = it
            )
        }
        Spacer(modifier = Modifier.size(50.dp))
        RegisterButton(
            isRegisterEnable,
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.35f)
                .fillMaxHeight(0.3f)
        )

    }
}

@Composable
fun Name(name: String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = Accent_Color,
        backgroundColor = Accent_Color_Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
            value = name,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = "Name", color = Text_Color) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Accent_Color,
                cursorColor = Text_Color,
                unfocusedBorderColor = Text_Color,

                )
        )
    }

}

@Composable
fun LastName(lastName : String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = Accent_Color,
        backgroundColor = Accent_Color_Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = lastName,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = "Last Name", color = Text_Color) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
fun Email(email : String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = Accent_Color,
        backgroundColor = Accent_Color_Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = email,
            onValueChange = { onTextChanged(it) },
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
fun Password(password: String, onTextChanged: (String) -> Unit ){
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
fun RepeatPassword(password: String, onTextChanged: (String) -> Unit ){
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
            label = { Text(text = "Repeat Password", color = Text_Color) },
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
fun RegisterButton(registerEnable: Boolean, modifier: Modifier){
    OutlinedButton(
        onClick = { /*TODO*/ },
        enabled = registerEnable,
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
            text = "REGISTER",
            fontSize = 20.sp,
        )
    }
}


