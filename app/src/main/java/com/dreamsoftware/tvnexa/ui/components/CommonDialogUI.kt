package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R

// Layout
@Composable
fun CommonDialogUI(
    modifier: Modifier = Modifier,
    onDismissPressed: () -> Unit,
    onExitPressed: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
    ) {
        Column(modifier.background(MaterialTheme.colorScheme.surface)) {
            // .......................................................................
            Image(
                painter = painterResource(id = R.drawable.tvnexa_logo),
                contentDescription = null, // decorative
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Confirmation",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Do you really want to exit app ?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            // .......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colorScheme.background.copy(0.2F)),
            ) {
                TextButton(onClick = onDismissPressed, modifier = Modifier.weight(1F), shape = RoundedCornerShape(0.dp)) {
                    Text(
                        "No",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }

                Divider(
                    Modifier
                        .height(50.dp)
                        .width(1.dp),
                )

                TextButton(onClick = onExitPressed, modifier = Modifier.weight(1F), shape = RoundedCornerShape(0.dp)) {
                    Text(
                        "Yes",
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}