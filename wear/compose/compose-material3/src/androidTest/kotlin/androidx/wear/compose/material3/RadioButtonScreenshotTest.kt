/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.wear.compose.material3

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.testutils.assertAgainstGolden
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SdkSuppress
import androidx.test.screenshot.AndroidXScreenshotTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class RadioButtonScreenshotTest {
    @get:Rule val rule = createComposeRule()

    @get:Rule val screenshotRule = AndroidXScreenshotTestRule(SCREENSHOT_GOLDEN_PATH)

    @get:Rule val testName = TestName()

    @Test
    fun radio_button_selected() = verifyScreenshot {
        sampleRadioButton(
            selected = true,
        )
    }

    @Test
    fun radio_button_unselected() = verifyScreenshot {
        sampleRadioButton(
            selected = false,
        )
    }

    @Test
    fun radio_button_selected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleRadioButton(
                selected = true,
            )
        }

    @Test
    fun radio_button_unselected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleRadioButton(
                selected = false,
            )
        }

    @Test
    fun disabled_radio_button_selected() = verifyScreenshot {
        sampleRadioButton(
            selected = true,
            enabled = false,
        )
    }

    @Test
    fun disabled_radio_button_unselected() = verifyScreenshot {
        sampleRadioButton(
            selected = false,
            enabled = false,
        )
    }

    @Test
    fun disabled_radio_button_selected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleRadioButton(
                selected = true,
                enabled = false,
            )
        }

    @Test
    fun disabled_radio_button_unselected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleRadioButton(
                selected = false,
                enabled = false,
            )
        }

    @Test
    fun split_radio_button_selected() = verifyScreenshot {
        sampleSplitRadioButton(
            selected = true,
        )
    }

    @Test
    fun split_radio_button_unselected() = verifyScreenshot {
        sampleSplitRadioButton(
            selected = false,
        )
    }

    @Test
    fun split_radio_button_selected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleSplitRadioButton(
                selected = true,
            )
        }

    @Test
    fun split_radio_button_unselected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleSplitRadioButton(
                selected = false,
            )
        }

    @Test
    fun disabled_split_radio_button_selected() = verifyScreenshot {
        sampleSplitRadioButton(
            selected = true,
            enabled = false,
        )
    }

    @Test
    fun disabled_split_radio_button_unselected() = verifyScreenshot {
        sampleSplitRadioButton(
            selected = false,
            enabled = false,
        )
    }

    @Test
    fun disabled_split_radio_button_selected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleSplitRadioButton(
                selected = true,
                enabled = false,
            )
        }

    @Test
    fun disabled_split_radio_button_unselected_rtl() =
        verifyScreenshot(layoutDirection = LayoutDirection.Rtl) {
            sampleSplitRadioButton(
                selected = false,
                enabled = false,
            )
        }

    @Test
    fun split_radio_button_customized_padding_6dp() = verifyScreenshot {
        sampleSplitRadioButton(contentPadding = PaddingValues(6.dp))
    }

    @Test
    fun split_radio_button_customized_horizontal_padding_24dp() = verifyScreenshot {
        sampleSplitRadioButton(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp))
    }

    @Composable
    private fun sampleRadioButton(
        enabled: Boolean = true,
        selected: Boolean = true,
    ) {
        RadioButton(
            icon = { TestIcon() },
            label = { Text("RadioButton") },
            secondaryLabel = { Text("Secondary label") },
            selected = selected,
            enabled = enabled,
            onSelect = {},
            modifier = Modifier.testTag(TEST_TAG),
        )
    }

    @Composable
    private fun sampleSplitRadioButton(
        selected: Boolean = true,
        enabled: Boolean = true,
        contentPadding: PaddingValues = RadioButtonDefaults.ContentPadding,
    ) {
        SplitRadioButton(
            label = { Text("SplitRadioButton") },
            secondaryLabel = { Text("Secondary label") },
            selected = selected,
            enabled = enabled,
            onSelectionClick = {},
            onContainerClick = {},
            selectionContentDescription = null,
            modifier = Modifier.testTag(TEST_TAG),
            contentPadding = contentPadding,
        )
    }

    private fun verifyScreenshot(
        layoutDirection: LayoutDirection = LayoutDirection.Ltr,
        content: @Composable () -> Unit
    ) {
        rule.setContentWithTheme {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                Box(modifier = Modifier.background(Color.Black)) { content() }
            }
        }

        rule
            .onNodeWithTag(TEST_TAG)
            .captureToImage()
            .assertAgainstGolden(screenshotRule, testName.methodName)
    }
}
