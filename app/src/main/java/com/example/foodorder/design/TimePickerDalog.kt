package com.example.foodorder.design


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material3.Surface
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.lightColors
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(

    onDismissButtonClicked: () -> Unit,

    ) {
    var isAm by remember { mutableStateOf(true) }

    var hourSelectedValue by remember { mutableStateOf(6) }
    var minSelectedValue by remember { mutableStateOf(30) }

    AlertDialog(
        onDismissRequest = { onDismissButtonClicked() },
        modifier = Modifier
            .size(350.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .size(500.dp)
                .background(Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 10.dp, end = 7.dp)
            ) {
                Text(
                    text = "Schedule cooking time",
                    fontSize = 18.sp,
                    color = Color(0xFF2253A5),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.width(25.dp))
                Surface(
                    shadowElevation = 8.dp,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    IconButton(
                        onClick = { onDismissButtonClicked() },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(start = 30.dp)
            ) {

                Card(
                    modifier = Modifier
                        .size(height = 135.dp, width = 200.dp)
                ) {
                    //add content to set time
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()

                            .padding(start = 40.dp, top = 20.dp),
                    ) {

                        ScrollableNumberPicker(
                            initialValue = hourSelectedValue,
                            minValue = 1,
                            maxValue = 12,
                            onValueChange = { newValue ->
                                hourSelectedValue = newValue
                            }
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = ":",
                            fontSize = 40.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        ScrollableNumberPicker(
                            initialValue = minSelectedValue,
                            minValue = 1,
                            maxValue = 60,
                            onValueChange = { newValue ->
                                minSelectedValue = newValue
                            }
                        )

                    }

                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .padding(top = 25.dp)
                ) {
                    AmPmField(
                        selected = isAm,
                        text = "AM",
                        onClick = { isAm = true }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    AmPmField(
                        selected = !isAm,
                        text = "PM",
                        onClick = {
                            isAm = false
                        })


                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            val outlineColor = Color(0xFFDD4F22)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier

                ) {
                    Text(
                        text = "Re-schedule",
                        fontSize = 15.sp,
                        color = Color(0xFFDD4F22),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "Cook Now",
                        fontSize = 15.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier
                    )

                }

            }
        }
    }
}

@Composable
fun AmPmField(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    selectedColor: Color = Color(0xFF2253A5),
    selectedContentColor: Color = Color.White,
    unSelectedColor: Color = Color(0xFFE1E9F5),
    unSelectedContentColor: Color = Color(0xFF2253A5)
) {
    val contentColor = if (selected) selectedContentColor else unSelectedContentColor
    val containerColor = if (selected) selectedColor else unSelectedColor

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .size(width = 40.dp, height = 30.dp)

    ) {
        Text(
            text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxSize()

        )
    }

}

@Composable
fun ScrollableNumberPicker(
    initialValue: Int,
    minValue: Int,
    maxValue: Int,
    onValueChange: (Int) -> Unit
) {
    val range = minValue..maxValue
    val items = range.toList()
    val lazyListState = rememberLazyListState()
    val initialIndex = initialValue - minValue + 1
    var initialScrollDone by remember { mutableStateOf(false) }

    //get initial value on relaunch
    LaunchedEffect(initialValue) {
        if (!initialScrollDone) {
            lazyListState.scrollToItem(initialIndex)
            initialScrollDone = true
        }
    }

    val selectedIndex = (lazyListState.firstVisibleItemIndex - 1).coerceAtLeast(0)

    LaunchedEffect(selectedIndex) {
        if (selectedIndex in items.indices) {
            onValueChange(items[selectedIndex])
        }
    }

    Box(
        modifier = Modifier
            //.padding(start = 50.dp)
            .size(height = 90.dp, width = 50.dp)
    ) {
        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(vertical = 22.dp)
        ) {

            items(items.size + 2) { index ->
                if (index == 0 || index == items.size + 1) {

                    Spacer(Modifier.height(3.dp))
                } else {
                    val number = items[index - 1]
                    Text(
                        text = number.toString(),
                        fontSize = if (index - 1 == selectedIndex) 34.sp else 30.sp,
                        fontWeight = if (index - 1 == selectedIndex) FontWeight.Bold else FontWeight.Normal,
                        color = if (index - 1 == selectedIndex) Color(0xFF2253A5) else Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically)

                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialog(
        onDismissButtonClicked = {}
        )
}