//import android.content.ContentValues
//import android.content.Context
//import com.example.convidados.Service.Constants.DataBaseConstants
//import com.example.convidados.Service.Model.GuestModel
//import com.example.convidados.Service.Repository.GuestDataBaseHelper
//import java.util.ArrayList
//
////classe antes de ser singleton
////class GuestRepository {
//class GuestRepository_old private constructor(context: Context) {
//
//    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)
//
//    companion object {
//        private lateinit var repository: GuestRepository_old
//
//        fun getInstance(context: Context): GuestRepository_old {
//            if (!::repository.isInitialized) {
//                repository = GuestRepository_old(context)
//            }
//            return repository
//        }
//    }
//
//    fun get(id: Int): GuestModel? {
//        var guest: GuestModel? = null
//        return try {
//            val db = mGuestDataBaseHelper.readableDatabase
//
//
//            val cursor = db.rawQuery("select * from Guest where id = $id", null)
//            if (cursor != null && cursor.count > 0) {
//                cursor.moveToFirst()
//                val name =
//                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                val presence =
//                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
//                guest = GuestModel(id, name, presence)
//            }
//            cursor?.close()
//            guest
//        } catch (e: Exception) {
//            guest
//        }
//
//    }
//
//    fun getAll(): List<GuestModel> {
//        val list: MutableList<GuestModel> = ArrayList()
//        return try {
//            val db = mGuestDataBaseHelper.readableDatabase
//            val cursor = db.rawQuery("select * from Guest", null)
//            if (cursor != null && cursor.count > 0) {
//
//                while (cursor.moveToNext()) {
//                    val id =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
//                    val name =
//                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence =
//                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
//                    val guest = GuestModel(id, name, presence)
//                    list.add(guest)
//                }
//            }
//            cursor?.close()
//            list
//        } catch (e: Exception) {
//            list
//        }
//    }
//
//    fun getPresent(): List<GuestModel> {
//        val list: MutableList<GuestModel> = ArrayList()
//        return try {
//            val db = mGuestDataBaseHelper.readableDatabase
//            val cursor = db.rawQuery("SELECT * FROM Guest WHERE presence = 1", null)
//            if (cursor != null && cursor.count > 0) {
//
//                while (cursor.moveToNext()) {
//                    val id =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
//                    val name =
//                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence =
//                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
//                    val guest = GuestModel(id, name, presence)
//                    list.add(guest)
//                }
//            }
//            cursor?.close()
//            list
//        } catch (e: Exception) {
//            list
//        }
//    }
//
//    fun getAbsent(): List<GuestModel> {
//        val list: MutableList<GuestModel> = ArrayList()
//        return try {
//            val db = mGuestDataBaseHelper.readableDatabase
//            val cursor = db.rawQuery("SELECT * FROM Guest WHERE presence = 0", null)
//            if (cursor != null && cursor.count > 0) {
//
//                while (cursor.moveToNext()) {
//                    val id =
//                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
//                    val name =
//                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
//                    val presence =
//                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
//                    val guest = GuestModel(id, name, presence)
//                    list.add(guest)
//                }
//            }
//            cursor?.close()
//            list
//        } catch (e: Exception) {
//            list
//        }
//    }
//
//    fun save(guest: GuestModel): Boolean {
//        return try {
//            val db = mGuestDataBaseHelper.writableDatabase
//
//            val contentValues = ContentValues()
//            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
//            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
//            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    fun update(guest: GuestModel): Boolean {
//        return try {
//            val db = mGuestDataBaseHelper.writableDatabase
//            val contentValues = ContentValues()
//            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
//            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
//
//            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
//            val args = arrayOf(guest.id.toString())
//
//            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
//            true
//        } catch (e: Exception) {
//            false
//        }
//
//
//    }
//
//    fun delete(id: Int): Boolean {
//
//
//        return try {
//            val db = mGuestDataBaseHelper.writableDatabase
//
//            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
//            val args = arrayOf(id.toString())
//            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
//            true
//        } catch (e: Exception) {
//            false
//        }
//
//
//    }
//
//
//}