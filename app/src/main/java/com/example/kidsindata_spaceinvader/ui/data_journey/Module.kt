package com.example.kidsindata_spaceinvader.ui.data_journey

data class Module(

        var moduleId: Int,

        var moduleName: String,

        var moduleDescription: String,

        var interactive: Int,

        var moduleCompletedFlag: Boolean,
) {
    companion object {
        val MODULE_NAME = arrayOf(
                "What is data?",
                "How to work with data?",
                "Table",
                "Bar chart",
                "Line chart",
        )

        val MODULE_DESCRIPTION = arrayOf(
                "Learn about what data means and the different types of data.",
                "Learn how to collect and explore data.",
                "Learn all about tables and how to use a table to explore data.",
                "Explore and learn all about the bar charts.",
                "Explore and learn all about the line charts.",
        )

        val INTERACTIVE = arrayOf(
                0,
                0,
                1,
                1,
                1,
        )

        val MODULE_COMPLETED_FLAG = arrayOf(
                false,
                true,
                false,
                false,
                true,
        )
    }
}


