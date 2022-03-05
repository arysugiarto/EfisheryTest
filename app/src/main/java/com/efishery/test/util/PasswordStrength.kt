package com.efishery.test.util

import android.graphics.Color
import com.efishery.test.R

enum class PasswordStrength private constructor(
    internal var resId: Int,
    color: Int
) {

    WEAK(R.string.register_password_weak, Color.RED),
    MEDIUM(R.string.register_password_medium, Color.parseColor("#FF9644")),
    STRONG(R.string.register_password_strong, Color.parseColor("#5DB9FF")),
    VERY_STRONG(R.string.register_password_very_strong, Color.parseColor("#4275AD"));

    var color: Int = 0
        internal set

    init {
        this.color = color
    }

    companion object {
        internal var REQUIRED_LENGTH = 6

        internal var MAXIMUM_LENGTH = 30

        internal var REQUIRE_SPECIAL_CHARACTERS = false

        internal var REQUIRE_DIGITS = true

        internal var REQUIRE_LOWER_CASE = true

        internal var REQUIRE_UPPER_CASE = false
    }
}

fun calculatePasswordStrength(password: String): PasswordStrength {
    var currentScore = 0

    var upperCase = false
    var lowerCase = false
    var digitCase = false
    var specialCase = false

    for (char in password) {
        // Special Character Point
        if (!specialCase && !Character.isLetterOrDigit(char)) {
            currentScore += 1
            specialCase = true
        }
        // Alphabet Or Digit
        else {
            // Digit Point
            if (!digitCase && Character.isDigit(char)) {
                digitCase = true
            }
            // Alphabet
            else {
                if (!upperCase || !lowerCase) {
                    // UpperCase Alphabet
                    if (Character.isUpperCase(char)) upperCase = true
                    // LowerCase Alphabet
                    else lowerCase = true
                }
            }
        }
    }

    if (password.length > PasswordStrength.REQUIRED_LENGTH) {
        if (PasswordStrength.REQUIRE_SPECIAL_CHARACTERS && !specialCase
            || PasswordStrength.REQUIRE_UPPER_CASE && !upperCase
            || PasswordStrength.REQUIRE_LOWER_CASE && !lowerCase
            || PasswordStrength.REQUIRE_DIGITS && !digitCase) {
            currentScore = 1
        } else {
            if (specialCase && digitCase) {
                if (password.length >= PasswordStrength.MAXIMUM_LENGTH) currentScore = 3
                currentScore = 2
            } else currentScore = 1
        }
    }

    when (currentScore) {
        0 -> return PasswordStrength.WEAK
        1 -> return PasswordStrength.MEDIUM
        2 -> return PasswordStrength.STRONG
        3 -> return PasswordStrength.VERY_STRONG
    }

    return PasswordStrength.VERY_STRONG
}