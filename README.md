# NotesApp - Week 8 Platform Features

**Nama:** Muharyan Syaifullah  
**NIM:** 123140045  
**Mata Kuliah:** Pemrograman Aplikasi Mobile  

## Deskripsi
Project ini merupakan pengembangan dari **Notes App** pada tugas sebelumnya dengan menambahkan **Platform-Specific Features** pada aplikasi.

Pada tugas ini, aplikasi di-upgrade dengan:
- **Koin Dependency Injection** untuk seluruh app
- implementasi **DeviceInfo** dengan `expect/actual`
- implementasi **NetworkMonitor** dengan `expect/actual`
- menampilkan **Device Info** pada **Settings Screen**
- menampilkan **Network Status Indicator** pada **Main Screen**
- seluruh dependency di-inject melalui **Koin**

## Fitur Utama
- Notes App dengan sistem CRUD
- Koin DI untuk pengelolaan dependency
- `DeviceInfo` & `NetworkMonitor` menggunakan `expect/actual`
- **AI Note Summarization** menggunakan Gemini API
- Status jaringan real-time dengan Network indicator

## Fitur AI: Ringkas Note dengan AI
Aplikasi ini memiliki fitur "Ringkas Note dengan AI" yang membantu pengguna mendapatkan poin-poin utama dari sebuah catatan secara otomatis.

### Implementasi AI:
- **Provider:** Google Gemini API (Model: gemini-1.5-flash)
- **Layering:**
  - `GeminiService`: Menangani komunikasi langsung ke API Google.
  - `AiRepository`: Menyediakan abstraksi dan mengelola *System Prompt*.
  - `AiViewModel`: Mengatur state UI (Idle, Loading, Success, Error).
  - `MainScreen`: Menampilkan tombol ringkas (✨) dan dialog hasil ringkasan.
- **System Prompt:** "Anda adalah asisten AI yang ahli dalam meringkas catatan. Tugas Anda adalah membuat ringkasan yang singkat, padat, dan jelas dari teks yang diberikan. Gunakan poin-poin jika perlu. Pastikan poin utama tetap terjaga. Berikan jawaban dalam Bahasa Indonesia."
- **Handling:**
  - **Loading State:** Menampilkan `CircularProgressIndicator` saat proses berlangsung.
  - **Error Handling:** Menampilkan pesan error melalui `Snackbar` jika terjadi gangguan jaringan atau API gagal.

### Cara Konfigurasi API Key:
1. Dapatkan API Key dari [Google AI Studio](https://aistudio.google.com/).
2. Buka file `composeApp/src/commonMain/kotlin/com/example/notesapp/di/AppModule.kt`.
3. Cari baris `single { GeminiService(get(), "YOUR_API_KEY_HERE") }` dan ganti dengan API Key Anda.

## Struktur Folder
```text
com.example.notesapp
├─ data
│  ├─ local
│  ├─ remote (AI Service & Models)
│  └─ repository
├─ di
├─ platform
├─ ui
│  ├─ screen
│  └─ component
├─ viewmodel
└─ MainActivity.kt
```

## Cara Menjalankan Project
1. Clone repository ini
2. Buka project di Android Studio
3. Tunggu proses Gradle Sync selesai
4. Jalankan aplikasi pada emulator atau device
5. Buka Settings Screen untuk melihat Device Info
6. Matikan dan nyalakan internet untuk menguji Network Status Indicator
