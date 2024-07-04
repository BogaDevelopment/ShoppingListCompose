package com.bogadevelopment.shoppinglist.shoppingCart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShoppingCartScreen() {
    Scaffold(
        content = { Content() },
        topBar = { ToolBar()},
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End
    )

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
fun ToolBar() {
    TopAppBar(
        title = {
            Text(text = "ShoppingList", color = MaterialTheme.colors.onPrimary);
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