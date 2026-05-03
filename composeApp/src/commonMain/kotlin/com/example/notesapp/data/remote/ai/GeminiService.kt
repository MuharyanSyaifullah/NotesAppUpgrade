package com.example.notesapp.data.remote.ai

import com.example.notesapp.data.remote.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class GeminiService(
    private val httpClient: HttpClient,
    private val apiKey: String
) {
    private val baseUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent"

    suspend fun generateContent(prompt: String, systemPrompt: String): Result<String> {
        return try {
            val request = GeminiRequest(
                contents = listOf(
                    Content(parts = listOf(Part(text = "System: $systemPrompt"))),
                    Content(parts = listOf(Part(text = "User: $prompt")))
                )
            )

            val response: GeminiResponse = httpClient.post("$baseUrl?key=$apiKey") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            val text = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (text != null) {
                Result.success(text)
            } else {
                Result.failure(Exception("Empty response from AI"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
