package com.diyankomitov.digitalpasswallet.models.room;

import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.PassModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * The DAO for Pass
 */
@Dao
public abstract class PassDao {
    
    private static final String TAG = "PassDao";
    
    /**
     * The Insert method implemented by Room.
     *
     * @param pass the pass. This is a PassModel as Interfaces can't be entities
     */
    @Insert
    protected abstract void insert(PassModel pass);
    
    /**
     * The Update method implemented by Room.
     *
     * @param pass the pass. This is a PassModel as Interfaces can't be entities
     */
    @Update
    protected abstract void update(PassModel pass);
    
    /**
     * The Delete method implemented by Room.
     *
     * @param pass the pass. This is a PassModel as Interfaces can't be entities
     */
    @Delete
    protected abstract void delete(PassModel pass);
    
    /**
     * This method queries the database using the pass id and deletes the matching pass.
     *
     * @param passId the pass id
     */
    @Query("DELETE FROM PassModel WHERE id = :passId")
    protected abstract void deleteById(int passId);
    
    /**
     * This method queries the database and returns a LiveData of List of PassModels
     *
     * @return the LiveData of the List of all PassModels
     */
    @Query("SELECT * FROM PassModel ORDER BY id DESC")
    protected abstract LiveData<List<PassModel>> getAllPassModels();
    
    /**
     * This is the public facing Insert method that takes in a Pass interface and casts it to a
     * PassModel.
     *
     * @param pass the pass
     */
    public void insert(Pass pass) {
        
        insert((PassModel) pass);
    }
    
    /**
     * This is the public facing Update method that takes in a Pass interface and casts it to a PassModel.
     *
     * @param pass the pass
     */
    public void update(Pass pass) {
        
        update((PassModel) pass);
    }
    
    /**
     * This is the public facing Delete method that takes in a Pass interface and casts it to a PassModel.
     *
     * @param pass the pass
     */
    public void delete(Pass pass) {
        
        delete((PassModel) pass);
    }
    
    /**
     * This is the public facing Delete by ID method.
     *
     * @param passId the pass id
     */
    public void delete(int passId) {
        
        Log.d(TAG, "delete() called with: passId = [" + passId + "]");
        deleteById(passId);
    }
    
    /**
     * This is the public facing get all passes method that returns a LiveData of List of Passes
     *
     * @return the LiveData of the List of all Passes
     */
    public LiveData<List<Pass>> getAllPasses() {
        
        LiveData<List<PassModel>> allPassModelsLiveData = getAllPassModels();
        
        return Transformations.map(allPassModelsLiveData, ArrayList<Pass>::new);
    }
}
