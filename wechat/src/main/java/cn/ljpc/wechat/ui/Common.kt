package cn.ljpc.wechat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import cn.ljpc.wechat.R
import androidx.compose.material.Text as Text

@Composable
fun Header(heading: String) {
    Row {
        Text(
            text = heading,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(vertical = 32.dp)
                .weight(1f),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Input(
    modifier: Modifier = Modifier,
    label: String,
    isSecure: Boolean = false,
    value: String,
    valueChange: (String) -> Unit
) {
    Column(modifier) {
        Text(
            text = label,
            color = MaterialTheme.colors.onBackground,
            fontSize = 14.sp
        )
        //是否显示密码
        val passwordHidden = remember {
            mutableStateOf(isSecure)
        }
        OutlinedTextField(
            textStyle = TextStyle(fontSize = 16.sp),
            value = value,
            onValueChange = valueChange,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colors.onBackground),
            visualTransformation = if (passwordHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isSecure) Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .padding(10.dp)
                        .clickable {
                            passwordHidden.value = !passwordHidden.value
                        },
                    tint = MaterialTheme.colors.onBackground
                )
            }
        )
    }
}

@Composable
fun TipDialog(
    title: String,
    contextMsg: String = "",
    isShow: Boolean = false,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.h6
                )
            },
            text = {
                Text(
                    text = contextMsg,
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                ) {
                    Text(
                        "确认",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.button
                    )
                }
            },
        )
    }
}

@Composable
fun LoadingDialog(content: String, isShow: Boolean = false) {
    if (isShow) {
        val dialogProperties =
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        AlertDialog(
            onDismissRequest = {
            },
            buttons = {
            },
            text = {
                CircularProgressIndicator(modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp))
                Text(text = content)
            },
            properties = dialogProperties,
            modifier = Modifier.alpha(0.5f)
        )
    }
}