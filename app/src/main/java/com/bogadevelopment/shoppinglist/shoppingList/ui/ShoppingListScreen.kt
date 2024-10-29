package com.bogadevelopment.shoppinglist.shoppingList.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShoppingListScreen(navigationController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { Content() },
        topBar = { ToolBar(scope, scaffoldState) },
        scaffoldState = scaffoldState,
        drawerContent = {  },
        floatingActionButton = { },
    )

}


@Composable
fun Content() {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        List(
            Modifier
                .fillMaxWidth()
                .weight(0.85f))
        Summary(
            Modifier
                .fillMaxWidth()
                .weight(0.15f))
    }
}

@Composable
fun List(modifier : Modifier){
    LazyRow(
        modifier = modifier.background(MaterialTheme.colors.primary)
    ){

    }
}


@Composable
fun Summary(modifier : Modifier){
    Card(
        modifier = modifier.padding(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 2.dp,
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
        ){
            Counter("Cantidad", Modifier.weight(0.5f), false)
            Counter("Precio", Modifier.weight(0.5f), true)
        }
    }
}

@Composable
fun Counter(title : String, modifier: Modifier, decimals : Boolean){
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(vertical = 5.dp))
        Divider(
            Modifier
                .fillMaxWidth(0.8f)
                .background(MaterialTheme.colors.onPrimary))
        if(decimals)
            Text(text = "0.00", fontSize = 20.sp, modifier = Modifier.padding(vertical = 5.dp))
        else
            Text(text = "0", fontSize = 20.sp, modifier = Modifier.padding(vertical = 5.dp))
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
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

