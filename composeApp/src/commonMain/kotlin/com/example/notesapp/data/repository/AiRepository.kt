package com.example.notesapp.data.repository

import com.example.notesapp.data.remote.ai.GeminiService

class AiRepository(private val geminiService: GeminiService) {
    
    private val summarizationSystemPrompt = """
        Anda adalah asisten AI yang ahli dalam meringkas catatan. 
        Tugas Anda adalah membuat ringkasan yang singkat, padat, dan jelas dari teks yang diberikan.
        Gunakan poin-poin jika perlu. 
        Pastikan poin utama tetap terjaga.
        Berikan jawaban dalam Bahasa Indonesia.
    """.trimIndent()

    suspend fun summarize(text: String): Result<String> {
        if (text.isBlank()) return Result.failure(Exception("Teks tidak boleh kosong"))
        return geminiService.generateContent(text, summarizationSystemPrompt)
    }
}
