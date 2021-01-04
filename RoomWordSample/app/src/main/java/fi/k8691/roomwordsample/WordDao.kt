package fi.k8691.roomwordsample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Entity(tableName = "word_table")
    class Word(@PrimaryKey @ColumnInfo(name="word") val word: String)

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}