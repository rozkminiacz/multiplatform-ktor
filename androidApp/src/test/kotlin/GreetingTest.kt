package org.konan.multiplatform

import org.common.Greeting
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GreetingTest {

    @Test
    fun `should print hello android from android mpp`() {
        assertEquals(Greeting().common(), "Hello, Android")
    }
}

// Note that common tests for calculator (i.e. `CalculatorTest`) can be run from `common`
// with `test` Gradle task.