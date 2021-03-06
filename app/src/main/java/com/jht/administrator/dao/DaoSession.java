package com.jht.administrator.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.jht.administrator.bean.User;
import com.jht.administrator.bean.Note;
import com.jht.administrator.bean.Score;

import com.jht.administrator.dao.UserDao;
import com.jht.administrator.dao.NoteDao;
import com.jht.administrator.dao.ScoreDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig noteDaoConfig;
    private final DaoConfig scoreDaoConfig;

    private final UserDao userDao;
    private final NoteDao noteDao;
    private final ScoreDao scoreDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
        noteDaoConfig.initIdentityScope(type);

        scoreDaoConfig = daoConfigMap.get(ScoreDao.class).clone();
        scoreDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        noteDao = new NoteDao(noteDaoConfig, this);
        scoreDao = new ScoreDao(scoreDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Note.class, noteDao);
        registerDao(Score.class, scoreDao);
    }
    
    public void clear() {
        userDaoConfig.getIdentityScope().clear();
        noteDaoConfig.getIdentityScope().clear();
        scoreDaoConfig.getIdentityScope().clear();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public ScoreDao getScoreDao() {
        return scoreDao;
    }

}
