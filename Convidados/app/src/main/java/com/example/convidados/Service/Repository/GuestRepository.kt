
import android.content.Context
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.Service.Repository.GuestDataBaseHelper
import java.util.ArrayList

//classe antes de ser singleton
//class GuestRepository {
class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel) {

    }

    fun update( guest: GuestModel){

    }

    fun delete (guest: GuestModel){

    }


}