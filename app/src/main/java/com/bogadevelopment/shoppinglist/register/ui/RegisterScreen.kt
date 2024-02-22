package com.bogadevelopment.shoppinglist.register.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bogadevelopment.shoppinglist.Routes


@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navigationController: NavHostController){
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary
                    )
                )
            )

    ){
        Header(
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
        Body(Modifier.padding(top = 230.dp),registerViewModel, navigationController)
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
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun Body(
    modifier: Modifier,
    registerViewModel: RegisterViewModel,
    navigationController: NavHostController
){
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
                .fillMaxHeight(0.3f),
            navigationController,
            registerViewModel,
            email,
            password
        )

    }
}

@Composable
fun Name(name: String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.background
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
            value = name,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = "Name", color = MaterialTheme.colors.onPrimary) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary,

                )
        )
    }

}

@Composable
fun LastName(lastName : String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.background
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = lastName,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = "Last Name", color = MaterialTheme.colors.onPrimary) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
            )
        )
    }
}

@Composable
fun Email(email : String, onTextChanged: (String) -> Unit){

    val selectColors  = TextSelectionColors(
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.background
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = email,
            onValueChange = { onTextChanged(it) },
            label = { Text(text = "Email", color = MaterialTheme.colors.onPrimary) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
            )
        )
    }
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit ){
    var passwordVisibility by remember { mutableStateOf(false) }
    val selectColors  = TextSelectionColors(
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.background
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            value = password,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            label = { Text(text = "Password", color = MaterialTheme.colors.onPrimary) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
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
        handleColor = MaterialTheme.colors.secondary,
        backgroundColor = MaterialTheme.colors.background
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectColors) {
        OutlinedTextField(
            value = password,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            label = { Text(text = "Repeat Password", color = MaterialTheme.colors.onPrimary) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.onPrimary,
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
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
fun RegisterButton(
    registerEnable: Boolean,
    modifier: Modifier,
    navigationController: NavHostController,
    registerViewModel: RegisterViewModel,
    email: String,
    password: String
){
    OutlinedButton(
        onClick = { registerViewModel.createUsersWithEmailAndPassword(email, password
        ) { navigationController.navigate(Routes.LoginScreen.route) }
        },
        enabled = registerEnable,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.primaryVariant,
            disabledContentColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(
            text = "REGISTER",
            fontSize = 20.sp,
        )
    }
}