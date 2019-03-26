package com.diyankomitov.digitalpasswallet.models.room;

import android.content.Context;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.PassModel;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class PassDaoTest {
    
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    
    private PassModel passModel;
    
    private PassDatabase database;
    private PassDao passDao;
    
    @Before
    public void setUp() {
        
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        database = Room.inMemoryDatabaseBuilder(targetContext, PassDatabase.class)
                       .allowMainThreadQueries()
                       .build();
        passDao = database.passDao();
        
        passModel = new PassModel(PassType.GENERIC);
    }
    
    @After
    public void tearDown() {
        
        database.close();
    }
    
    @Test
    public void testGetAllPassModelsEmptyDefault() throws InterruptedException {
        
        LiveData<List<PassModel>> allPassModels = passDao.getAllPassModels();
        
        assertThat(LiveDataTestUtil.getValue(allPassModels)).isEmpty();
    }
    
    @Test
    public void testInsertInserts() throws InterruptedException {
        
        passDao.insert(passModel);
        
        LiveData<List<PassModel>> allPassModels = passDao.getAllPassModels();
        
        assertThat(LiveDataTestUtil.getValue(allPassModels)).hasSize(1);
    }
    
    @Test
    public void testUpdateUpdates() throws InterruptedException {
        
        LiveData<List<PassModel>> allPassModels = passDao.getAllPassModels();
        passDao.insert(passModel);
        
        PassModel passModel = LiveDataTestUtil.getValue(allPassModels).get(0);
        passModel.setLogoText("logoText");
        passDao.update(passModel);
        
        assertThat(LiveDataTestUtil.getValue(allPassModels).get(0).getLogoText()).matches(
                "logoText");
    }
    
    @Test
    public void testDeleteDeletes() throws InterruptedException {
        
        LiveData<List<PassModel>> allPassModels = passDao.getAllPassModels();
        
        passDao.insert(passModel);
        
        PassModel passModel = LiveDataTestUtil.getValue(allPassModels).get(0);
        passDao.delete(passModel);
        
        assertThat(LiveDataTestUtil.getValue(allPassModels)).isEmpty();
    }
    
    @Test
    public void testDeleteByIdDeletes() throws InterruptedException {
        
        LiveData<List<PassModel>> allPassModels = passDao.getAllPassModels();
        
        passDao.insert(passModel);
        
        PassModel passModel = LiveDataTestUtil.getValue(allPassModels).get(0);
        passDao.deleteById(passModel.getId());
        
        assertThat(LiveDataTestUtil.getValue(allPassModels)).isEmpty();
    }
    
    @Test
    public void testGetAllPassesEmptyDefault() throws InterruptedException {
        
        LiveData<List<Pass>> allPasses = passDao.getAllPasses();
        
        assertThat(LiveDataTestUtil.getValue(allPasses)).isEmpty();
    }
    
    @Test
    public void testInsertPassInserts() throws InterruptedException {
        
        LiveData<List<Pass>> allPasses = passDao.getAllPasses();
        
        Pass pass = passModel;
        
        passDao.insert(pass);
        
        assertThat(LiveDataTestUtil.getValue(allPasses)).hasSize(1);
    }
    
    @Test
    public void testUpdatePassUpdates() throws InterruptedException {
        
        LiveData<List<Pass>> allPasses = passDao.getAllPasses();
        Pass pass = passModel;
        
        passDao.insert(pass);
        
        pass = LiveDataTestUtil.getValue(allPasses).get(0);
        pass.setLogoText("logoText");
        passDao.update(pass);
        
        assertThat(LiveDataTestUtil.getValue(allPasses).get(0).getLogoText()).matches("logoText");
    }
    
    @Test
    public void testDeletePassDeletes() throws InterruptedException {
        
        LiveData<List<Pass>> allPasses = passDao.getAllPasses();
        Pass pass = passModel;
        
        passDao.insert(pass);
        
        pass = LiveDataTestUtil.getValue(allPasses).get(0);
        passDao.delete(pass);
        
        assertThat(LiveDataTestUtil.getValue(allPasses)).isEmpty();
    }
    
    @Test
    public void testDeletePassByIdDeletes() throws InterruptedException {
        
        LiveData<List<Pass>> allPasses = passDao.getAllPasses();
        Pass pass = passModel;
        
        passDao.insert(pass);
        
        pass = LiveDataTestUtil.getValue(allPasses).get(0);
        passDao.deleteById(pass.getId());
        
        assertThat(LiveDataTestUtil.getValue(allPasses)).isEmpty();
    }
}