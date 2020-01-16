package tz.co.asoft.camera.viewmodel

import kotlinx.coroutines.channels.Channel
import tz.co.asoft.rx.lifecycle.LiveData
import java.io.File

class CaptureViewModel {
    val ui = Channel<State>()

    sealed class State {
        object Capturing : State()
        class Previewing(val image: File) : State()
    }

    sealed class Intent {
        object Capture : Intent()
        class Preview(val image: File) : Intent()
    }

    suspend fun post(i: Intent) = when (i) {
        Intent.Capture -> ui.send(State.Capturing)
        is Intent.Preview -> ui.send(State.Previewing(i.image))
    }
}