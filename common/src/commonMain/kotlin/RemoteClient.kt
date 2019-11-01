package org.common

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json.Companion.parse

// expect - some kind of interface for multiplatform projects
// you have to implement it for each target
internal expect val ApplicationDispatcher: CoroutineDispatcher

@KtorExperimentalAPI
class RemoteClient {

    private val client: HttpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @UnstableDefault
    fun getMessage(callback: (Event) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val response = client.get<HttpResponse>(
                        host = "localhost",
                        scheme = "http",
                        port = 8080,
                        path = "/api/randomjoke"
                )

                val result = response.readText()
                val event = parse(
                        deserializer = Event.serializer(),
                        string = result
                )

                callback(event)
            }
        }
    }
}

@Serializable
data class Event(val status: String, val message: String)