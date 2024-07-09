package com.bogadevelopment.shoppinglist.shoppingCart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShoppingCartScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { Content() },
        topBar = { ToolBar(scope, scaffoldState)},
        scaffoldState = scaffoldState,
        drawerContent = {Drawer()},
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End
    )

}

@Composable
fun Drawer(){
    val menu_items = listOf(
        ""
    )
    Column(){
        menu_items.forEach{item -> 
            TextButton(onClick = { /*TODO*/ }) {
                Text(item,
                    modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun Content() {
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
    ) {

    }
}

@Composable
fun ToolBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Text(text = "ShoppingList", color = MaterialTheme.colors.onPrimary)
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch{
                    scaffoldState.drawerState.open()
                }
            }){
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun FAB() {
    FloatingActionButton(
        modifier = Modifier.size(60.dp),
        onClick = { /*TODO*/ },
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "AddIcon"
        )
    }
}