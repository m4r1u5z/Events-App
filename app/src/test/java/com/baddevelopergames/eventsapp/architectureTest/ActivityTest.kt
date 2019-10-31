package com.baddevelopergames.eventsapp.architectureTest

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.Test

class ActivityTest {

    @Test
    fun `all activity class should be public`() {
        val importedClasses = ClassFileImporter().importPackages("com.baddevelopergames.eventsapp")

        val rule = classes().that().haveNameMatching(".*Activity")
            .should().bePublic()

        rule.check(importedClasses)
    }
}