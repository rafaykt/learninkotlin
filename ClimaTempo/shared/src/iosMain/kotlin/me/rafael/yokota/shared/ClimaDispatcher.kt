import kotlinx.coroutines.CoroutineDispatcher

internal actual val ClimaDispatcher: CoroutineDispatcher =
    NsQueueDispatcher(
        dispatch_get_main_queue()
    )