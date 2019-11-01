package tech.michalik

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.*
import io.ktor.http.cio.websocket.Frame
import io.ktor.response.cacheControl
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.response.respondTextWriter
import io.ktor.routing.*
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay

@ExperimentalCoroutinesApi
fun Application.main() {

    install(DefaultHeaders)
    install(CallLogging)
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Delete)
        header(HttpHeaders.XForwardedProto)
        header(HttpHeaders.Authorization)
        anyHost()
        host("localhost:3000")
        allowCredentials = true
        allowNonSimpleContentTypes = true
        maxAge = java.time.Duration.ofDays(1)
    }
    install(ContentNegotiation) {
        gson()
    }

    routing {
        get("/api/stream") {
            val events = produce {
                // this: ProducerScope<SseEvent> ->
                while (true) {
                    send(SseEvent(nextRandomJoke()))
                    delay(10000)
                }
            }.broadcast().openSubscription()
            try {
                call.respondSse(events)
            } finally {
                events.cancel()
            }
        }

        get("/") {
            call.respondText(
                    """
                        <html>
                            <head></head>
                            <body>
                                <h1>Lame Halloween Jokes</h1>
                                <ul id="events">
                                </ul>
                                <script type="text/javascript">
                                    var source = new EventSource('/api/stream');
                                    var eventsUl = document.getElementById('events');

                                    function logEvent(text) {
                                        var li = document.createElement('li')
                                        li.innerText = text;
                                        eventsUl.appendChild(li);
                                    }

                                    source.addEventListener('message', function(e) {
                                        logEvent(e.data);
                                    }, false);

                                    source.addEventListener('open', function(e) {
                                        logEvent('open');
                                    }, false);

                                    source.addEventListener('error', function(e) {
                                        if (e.readyState == EventSource.CLOSED) {
                                            logEvent('closed');
                                        } else {
                                            logEvent('error');
                                            console.log(e);
                                        }
                                    }, false);
                                </script>
                            </body>
                        </html>
                    """.trimIndent(),
                    contentType = ContentType.Text.Html
            )
        }

        get(("/api/randomjoke")) {
            call.respond(
                    HttpStatusCode.OK,
                    mapOf(
                            "status" to "success",
                            "message" to nextRandomJoke())
            )
        }
    }
}

fun Routing.socket() {
    webSocket("/websocket") {
        produce {
            while (true) {
                send(Frame.Text(nextRandomJoke()))
                delay(5000)
            }
        }
    }
}

data class SseEvent(val data: String, val event: String? = null, val id: String? = null)

suspend fun ApplicationCall.respondSse(events: ReceiveChannel<SseEvent>) {
    response.cacheControl(CacheControl.NoCache(null))
    respondTextWriter(contentType = ContentType.Text.EventStream) {
        for (event in events) {
            if (event.id != null) {
                write("id: ${event.id}\n")
            }
            if (event.event != null) {
                write("event: ${event.event}\n")
            }
            for (dataLine in event.data.lines()) {
                write("data: $dataLine\n")
            }
            write("\n")
            flush()
        }
    }
}