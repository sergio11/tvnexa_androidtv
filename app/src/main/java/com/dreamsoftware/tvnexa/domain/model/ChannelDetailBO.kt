package com.dreamsoftware.tvnexa.domain.model

data class ChannelDetailBO(
    val channelId: String,
    val name: String?,
    val network: String?,
    val country: CountryBO,
    val subdivision: SubdivisionBO?,
    val city: String?,
    val isNsfw: Boolean?,
    val website: String?,
    val logo: String?,
    val launched: String?,
    val closed: String?,
    val replacedBy: ChannelDetailBO?,
    val streamUrl: String?,
    val guides: List<ChannelGuideBO>,
    val languages: List<LanguageBO>,
    val categories: List<CategoryBO>,
    val altNames: List<String>,
    val owners: List<String>,
    val broadcastAreas: List<String>,
    val isBlocked: Boolean = false
)
