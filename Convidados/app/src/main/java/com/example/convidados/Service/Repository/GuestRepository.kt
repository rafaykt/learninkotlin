import android.content.ContentValues
import android.content.Context
import com.example.convidados.Service.Constants.DataBaseConstants
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.Service.Repository.GuestDataBase
import java.util.ArrayList

//classe antes de ser singleton
//class GuestRepository {
class GuestRepository(context: Context) {

    // Acesso ao banco de dados
    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    /**
     * Carrega convidado
     */
    fun get(id: Int): GuestModel {
        return mDataBase.load(id)
    }

    /**
     * Insere convidado
     */
    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    /**
     * Faz a listagem de todos os convidados
     */
    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    /**
     * Faz a listagem de todos os convidados presentes
     */
    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }

    /**
     * Atualiza convidado
     */
    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    /**
     * Remove convidado
     */
    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }

}