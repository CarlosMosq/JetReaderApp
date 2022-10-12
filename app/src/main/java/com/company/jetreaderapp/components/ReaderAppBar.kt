package com.company.jetreaderapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.company.jetreaderapp.navigation.ScreensList
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderAppBar(
    title: String,
    icon: ImageVector? = null,
    showProfile: Boolean = true,
    navController: NavController,
    onBackArrowPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(showProfile && icon == null) {
                    Icon(imageVector = Icons.Default.ThumbUp,
                        contentDescription = "main app icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f)
                    )
                }

                if(icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Arrow back",
                        tint = Color.Red.copy(alpha = 0.7f),
                        modifier = Modifier
                            .width(100.dp)
                            .clickable { onBackArrowPressed.invoke() }
                    )
                }

                Text(
                    text = title,
                    color = Color.Red.copy(0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )

                Spacer(modifier = Modifier.width(150.dp))


            }
        },
        actions = {
            IconButton(onClick = { FirebaseAuth.getInstance().signOut().run {
                navController.navigate(ScreensList.ReaderLoginScreen.name)
            }}) {
                Icon(imageVector = Icons.Filled.Logout,
                    contentDescription = "Log out icon",
                    tint = Color.Green.copy(0.4f)
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}