package com.yannick.cosmo.library.testutils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TestCollector<T>(
    scope: CoroutineScope,
    flow: Flow<T>,
) {
    private val values = mutableListOf<T>()
    private val job: Job = scope.launch {
        flow.collect { value ->
            values.add(value)
        }
    }

    operator fun get(index: Int): T =
        values[index]

    fun finish() {
        job.cancel()
    }
}

fun <T> Flow<T>.test(scope: CoroutineScope): TestCollector<T> {
    return TestCollector(scope, this)
}
