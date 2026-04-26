# NotesApp - Week 8 Platform Features

**Nama:** Muharyan Syaifullah  
**NIM:** 123140045  
**Mata Kuliah:** Pengembangan Aplikasi Mobile  

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
- Notes App dari tugas sebelumnya tetap berjalan
- Koin DI untuk pengelolaan dependency
- `DeviceInfo` platform-specific menggunakan `expect/actual`
- `NetworkMonitor` platform-specific menggunakan `expect/actual`
- Device Info ditampilkan di halaman settings
- Network indicator ditampilkan di halaman utama
- Status jaringan dapat berubah saat internet on/off
- Struktur kode dipisahkan dengan pendekatan yang lebih rapi dan modular

## Implementasi
### 1. Dependency Injection
Aplikasi menggunakan **Koin** untuk mengatur dependency:
- repository
- viewmodel
- platform services
- monitor jaringan
- device info

### 2. expect/actual Pattern
Project ini menggunakan pola `expect/actual` untuk memisahkan deklarasi di shared/common code dan implementasi spesifik di platform:
- `DeviceInfo`
- `NetworkMonitor`

### 3. Device Info
Informasi device yang ditampilkan pada **Settings Screen** meliputi:
- nama perangkat
- versi sistem operasi
- informasi tambahan device/app sesuai implementasi

### 4. Network Status Indicator
Aplikasi menampilkan indikator status jaringan pada **Main Screen**:
- saat internet aktif
- saat internet mati / offline

## Arsitektur Singkat
Aplikasi menggunakan pendekatan:
- **UI Layer**
- **ViewModel Layer**
- **Dependency Injection Layer**
- **Platform-Specific Layer**

Dependency dikelola melalui Koin dan service platform-specific diakses menggunakan `expect/actual`.

## Struktur Folder
```text
com.example.notesapp
├─ di
├─ platform
├─ ui
│  ├─ screen
│  └─ component
├─ viewmodel
├─ data
└─ MainActivity.kt
```

## Cara Menjalankan Project
1. Clone repository ini
2. Buka project di Android Studio
3. Tunggu proses Gradle Sync selesai
4. Jalankan aplikasi pada emulator atau device
5. Buka Settings Screen untuk melihat Device Info
6. Matikan dan nyalakan internet untuk menguji Network Status Indicator
