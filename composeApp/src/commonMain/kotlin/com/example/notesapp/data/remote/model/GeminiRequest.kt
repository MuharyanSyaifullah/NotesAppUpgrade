package com.example.notesapp.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig? = null
)

@Serializable
data class Content(
    val parts: List<Part>
)

@Serializable
data class Part(
    val text: String
)

@Serializable
data class GenerationConfig(
    val temperature: Double? = null,
    val topK: Int? = null,
    val topP: Double? = null,
    val maxOutputTokens: Int? = null,
    val stopSequences: List<String>? = null
)
