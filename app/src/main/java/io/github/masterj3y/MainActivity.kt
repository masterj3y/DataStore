package io.github.masterj3y

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.LiveData
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {

    private val dataStore by lazy { createDataStore(name = "ds") }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textLiveData: LiveData<String> = dataStore.liveData(TEXT_KEY, "null")

        setContent {
            AppScreen(
                textLiveData = textLiveData,
                onSaveClicked = { saveText(it) }
            )
        }
    }

    private fun saveText(value: String) = dataStore.saveData(TEXT_KEY to value)

    companion object {
        private val TEXT_KEY = preferencesKey<String>("text")
    }
}