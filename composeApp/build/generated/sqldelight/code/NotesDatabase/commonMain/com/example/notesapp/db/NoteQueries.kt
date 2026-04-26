package com.example.notesapp.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class NoteQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAllByNewest(mapper: (
    id: Long,
    title: String,
    content: String,
    created_at: Long,
    updated_at: Long,
  ) -> T): Query<T> = Query(1_133_918_147, arrayOf("Note"), driver, "Note.sq", "selectAllByNewest",
      """
  |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
  |ORDER BY updated_at DESC
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun selectAllByNewest(): Query<Note> = selectAllByNewest { id, title, content, created_at,
      updated_at ->
    Note(
      id,
      title,
      content,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectAllByOldest(mapper: (
    id: Long,
    title: String,
    content: String,
    created_at: Long,
    updated_at: Long,
  ) -> T): Query<T> = Query(1_168_445_916, arrayOf("Note"), driver, "Note.sq", "selectAllByOldest",
      """
  |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
  |ORDER BY updated_at ASC
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun selectAllByOldest(): Query<Note> = selectAllByOldest { id, title, content, created_at,
      updated_at ->
    Note(
      id,
      title,
      content,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectAllByTitle(mapper: (
    id: Long,
    title: String,
    content: String,
    created_at: Long,
    updated_at: Long,
  ) -> T): Query<T> = Query(42_235_611, arrayOf("Note"), driver, "Note.sq", "selectAllByTitle", """
  |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
  |ORDER BY title COLLATE NOCASE ASC
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun selectAllByTitle(): Query<Note> = selectAllByTitle { id, title, content, created_at,
      updated_at ->
    Note(
      id,
      title,
      content,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> selectById(id: Long, mapper: (
    id: Long,
    title: String,
    content: String,
    created_at: Long,
    updated_at: Long,
  ) -> T): Query<T> = SelectByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun selectById(id: Long): Query<Note> = selectById(id) { id_, title, content, created_at,
      updated_at ->
    Note(
      id_,
      title,
      content,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> searchByNewest(
    title: String,
    content: String,
    mapper: (
      id: Long,
      title: String,
      content: String,
      created_at: Long,
      updated_at: Long,
    ) -> T,
  ): Query<T> = SearchByNewestQuery(title, content) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun searchByNewest(title: String, content: String): Query<Note> = searchByNewest(title,
      content) { id, title_, content_, created_at, updated_at ->
    Note(
      id,
      title_,
      content_,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> searchByOldest(
    title: String,
    content: String,
    mapper: (
      id: Long,
      title: String,
      content: String,
      created_at: Long,
      updated_at: Long,
    ) -> T,
  ): Query<T> = SearchByOldestQuery(title, content) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun searchByOldest(title: String, content: String): Query<Note> = searchByOldest(title,
      content) { id, title_, content_, created_at, updated_at ->
    Note(
      id,
      title_,
      content_,
      created_at,
      updated_at
    )
  }

  public fun <T : Any> searchByTitle(
    title: String,
    content: String,
    mapper: (
      id: Long,
      title: String,
      content: String,
      created_at: Long,
      updated_at: Long,
    ) -> T,
  ): Query<T> = SearchByTitleQuery(title, content) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  public fun searchByTitle(title: String, content: String): Query<Note> = searchByTitle(title,
      content) { id, title_, content_, created_at, updated_at ->
    Note(
      id,
      title_,
      content_,
      created_at,
      updated_at
    )
  }

  /**
   * @return The number of rows updated.
   */
  public fun insertNote(
    title: String,
    content: String,
    created_at: Long,
    updated_at: Long,
  ): QueryResult<Long> {
    val result = driver.execute(1_514_081_002, """
        |INSERT INTO Note(title, content, created_at, updated_at)
        |VALUES (?, ?, ?, ?)
        """.trimMargin(), 4) {
          bindString(0, title)
          bindString(1, content)
          bindLong(2, created_at)
          bindLong(3, updated_at)
        }
    notifyQueries(1_514_081_002) { emit ->
      emit("Note")
    }
    return result
  }

  /**
   * @return The number of rows updated.
   */
  public fun updateNote(
    title: String,
    content: String,
    updated_at: Long,
    id: Long,
  ): QueryResult<Long> {
    val result = driver.execute(251_984_122, """
        |UPDATE Note
        |SET title = ?, content = ?, updated_at = ?
        |WHERE id = ?
        """.trimMargin(), 4) {
          bindString(0, title)
          bindString(1, content)
          bindLong(2, updated_at)
          bindLong(3, id)
        }
    notifyQueries(251_984_122) { emit ->
      emit("Note")
    }
    return result
  }

  /**
   * @return The number of rows updated.
   */
  public fun deleteNote(id: Long): QueryResult<Long> {
    val result = driver.execute(-1_982_462_756, """
        |DELETE FROM Note
        |WHERE id = ?
        """.trimMargin(), 1) {
          bindLong(0, id)
        }
    notifyQueries(-1_982_462_756) { emit ->
      emit("Note")
    }
    return result
  }

  private inner class SelectByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Note", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Note", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_108_390_003, """
    |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
    |WHERE id = ?
    """.trimMargin(), mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "Note.sq:selectById"
  }

  private inner class SearchByNewestQuery<out T : Any>(
    public val title: String,
    public val content: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Note", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Note", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-536_446_460, """
    |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
    |WHERE title LIKE ? OR content LIKE ?
    |ORDER BY updated_at DESC
    """.trimMargin(), mapper, 2) {
      bindString(0, title)
      bindString(1, content)
    }

    override fun toString(): String = "Note.sq:searchByNewest"
  }

  private inner class SearchByOldestQuery<out T : Any>(
    public val title: String,
    public val content: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Note", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Note", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-501_918_691, """
    |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
    |WHERE title LIKE ? OR content LIKE ?
    |ORDER BY updated_at ASC
    """.trimMargin(), mapper, 2) {
      bindString(0, title)
      bindString(1, content)
    }

    override fun toString(): String = "Note.sq:searchByOldest"
  }

  private inner class SearchByTitleQuery<out T : Any>(
    public val title: String,
    public val content: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("Note", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("Note", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(265_447_546, """
    |SELECT Note.id, Note.title, Note.content, Note.created_at, Note.updated_at FROM Note
    |WHERE title LIKE ? OR content LIKE ?
    |ORDER BY title COLLATE NOCASE ASC
    """.trimMargin(), mapper, 2) {
      bindString(0, title)
      bindString(1, content)
    }

    override fun toString(): String = "Note.sq:searchByTitle"
  }
}
