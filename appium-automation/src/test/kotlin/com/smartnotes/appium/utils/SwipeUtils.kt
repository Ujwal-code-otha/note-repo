package com.smartnotes.appium.utils

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.Sequence
import java.time.Duration

object SwipeUtils {
    fun swipeUp(driver: AndroidDriver) {
        val size = driver.manage().window().size
        val startX = size.width / 2
        val startY = (size.height * 0.8).toInt()
        val endY = (size.height * 0.2).toInt()

        val finger = PointerInput(PointerInput.Kind.TOUCH, "finger")
        val sequence = Sequence(finger, 1)
            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

        driver.perform(listOf(sequence))
    }
}
