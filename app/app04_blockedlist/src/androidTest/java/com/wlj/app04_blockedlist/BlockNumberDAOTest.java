package com.wlj.app04_blockedlist;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BlockNumberDAOTest {
    @Test
    public void testAdd(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        BlockedNumberDAO dao = new BlockedNumberDAO(appContext);
        //dao.add(new BlockedNumber(-1, "5145326589"));
        dao.add(new BlockedNumber(-1, "5148883030"));
        dao.add(new BlockedNumber(-1, "5148886666"));
        dao.add(new BlockedNumber(-1, "5148887777"));
    }

    @Test
    public void testGetAll(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        BlockedNumberDAO dao = new BlockedNumberDAO(appContext);
        List<BlockedNumber> list = dao.getAll();
        Log.i("TAG", list.toString());
    }

    @Test
    public void testUpdate(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        BlockedNumberDAO dao = new BlockedNumberDAO(appContext);
        dao.update(new BlockedNumber(1, "5145558888"));

    }

    @Test
    public void testDelete(){
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        BlockedNumberDAO dao = new BlockedNumberDAO(appContext);
        dao.deleteById(2);

    }

}
