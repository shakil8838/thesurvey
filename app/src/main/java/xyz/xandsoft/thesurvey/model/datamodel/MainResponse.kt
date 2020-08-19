package xyz.xandsoft.thesurvey.model.datamodel

data class MainResponse(
    val question: String?,
    val type: String?,
    val options: String?,
    val required: Boolean?
)