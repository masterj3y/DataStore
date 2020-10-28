package io.github.masterj3y

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

typealias LiveStore<T> = DataStore<T>

fun Context.createLiveStore(name: String) = createDataStore(name = name)

@InternalCoroutinesApi
fun <T> LiveStore<Preferences>.liveData(key: Preferences.Key<T>, defaultValue: T): LiveData<T> =
    data.map {
        it[key] ?: defaultValue
    }.asLiveData(IO)

fun <T> LiveStore<Preferences>.saveData(data: Pair<Preferences.Key<T>, T>) = GlobalScope.launch {
    edit {
        it[data.first] = data.second
    }
}