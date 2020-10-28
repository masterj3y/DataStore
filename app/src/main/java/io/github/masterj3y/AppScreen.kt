package io.github.masterj3y

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview
import io.github.masterj3y.ui.DataStoreTheme

@Composable
fun AppScreen(
    textLiveData: LiveData<String>,
    onSaveClicked: (String) -> Unit
) {
    val text = textLiveData.observeAsState("not specified yet!") as MutableState<String>
    DataStoreTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text.value,
                    onValueChange = { text.value = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text.value
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSaveClicked(text.value)
                    }
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppScreenPreview() {
    val textLiveData = MutableLiveData("Preview Text")
    AppScreen(textLiveData = textLiveData, onSaveClicked = {})
}