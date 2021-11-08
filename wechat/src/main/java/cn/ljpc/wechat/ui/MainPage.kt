package cn.ljpc.wechat.ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import cn.ljpc.wechat.R

@Composable
fun MainPage(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("微信", "通讯录", "发现", "我")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(items[selectedItem])
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigation {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = {
                                when (index) {
                                    0 -> Icon(
                                        painter = painterResource(id = R.drawable.ic_chat_outlined),
                                        contentDescription = null
                                    )
                                    1 -> Icon(
                                        painter = painterResource(id = R.drawable.ic_contacts_outlined),
                                        contentDescription = null
                                    )
                                    2 -> Icon(
                                        painter = painterResource(id = R.drawable.ic_discover_outlined),
                                        contentDescription = null
                                    )
                                    else -> Icon(
                                        painter = painterResource(id = R.drawable.ic_me_outlined),
                                        contentDescription = null
                                    )
                                }
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                        )
                    }
                }
            }
        },
        drawerContent = {
        }
    ) {
        when (selectedItem) {
            0 -> {
                Chat()
            }
            1 -> {
                Contract()
            }
            2 -> {
                Discover()
            }
            else -> {
                Me()
            }
        }
    }
}

@Composable
fun Me() {
}

@Composable
fun Discover() {
}

@Composable
fun Contract() {
}

@Composable
fun Chat() {
}