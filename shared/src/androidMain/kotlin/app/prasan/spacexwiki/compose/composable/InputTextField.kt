package app.prasan.spacexwiki.compose.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    text: String,
    hint: String,
    errorText: String = "",
    textInputMode: TextFieldInputMode = TextFieldInputMode.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onTextChange: (String) -> Unit
) {

    //Column {
        TextField(
            modifier = modifier,
            value = text,
            onValueChange = onTextChange,
            label = {
                Text(
                    text = errorText.ifEmpty { hint },
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            },
            trailingIcon = when (textInputMode) {
                is TextFieldInputMode.Password -> {
                    @Composable {
                        val image =
                            if (textInputMode.isPasswordShowing) Icons.Filled.Close else Icons.Filled.Add
                        Icon(
                            modifier = Modifier
                                .width(18.dp)
                                .height(16.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    textInputMode.onVisibilityClick(!textInputMode.isPasswordShowing)
                                },
                            contentDescription = "",
                            imageVector = image, tint = Color.Gray
                        )
                    }
                }
                else -> null
            },
            isError = errorText.isNotEmpty(),
            maxLines = 1,
            singleLine = true,
            visualTransformation = if (textInputMode is TextFieldInputMode.Password && !textInputMode.isPasswordShowing) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = MaterialTheme.typography.body1,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isDarkMode) Color.White else Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.LightGray,
                errorLabelColor = Color.Red,
                errorCursorColor = Color.Red,
                textColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray,
                cursorColor = Color.DarkGray
            ),
            keyboardOptions = keyboardOptions.copy(
                keyboardType = when (textInputMode) {
                    TextFieldInputMode.Email -> KeyboardType.Email
                    is TextFieldInputMode.Password -> KeyboardType.Password
                    else -> KeyboardType.Text
                },
                capitalization = when (textInputMode) {
                    is TextFieldInputMode.Name -> KeyboardCapitalization.Words
                    is TextFieldInputMode.Sentence -> KeyboardCapitalization.Sentences
                    else -> KeyboardCapitalization.None
                }
            ),
            keyboardActions = keyboardActions
        )

        /*Text(
            text = errorText,
            modifier = modifier.then(Modifier.padding(start = 16.dp)),
            color = Color.Red,
            fontSize = 10.sp,
            style = MaterialTheme.typography.overline
        )
    }*/
}

sealed class TextFieldInputMode {
    object Text : TextFieldInputMode()
    object Name : TextFieldInputMode()
    object Email : TextFieldInputMode()
    object Sentence : TextFieldInputMode()
    class Password(
        val isPasswordShowing: Boolean,
        val onVisibilityClick: (Boolean) -> Unit
    ) : TextFieldInputMode()
}