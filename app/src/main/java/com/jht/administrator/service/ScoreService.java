package com.jht.administrator.service;

import java.util.List;

import android.content.Context;
import android.util.Log;
import com.jht.administrator.application.BaseApplication;
import com.jht.administrator.bean.Score;
import com.jht.administrator.dao.DaoSession;
import com.jht.administrator.dao.ScoreDao;

public class ScoreService {

	private static final String TAG = "GREENDAO";

	private static ScoreService instance;

	private static Context appContext;

	private DaoSession mDaoSession;

	private ScoreDao scoreDao;

	public static ScoreService getInstance(Context context) {

		if (instance == null) {
			instance = new ScoreService();
			if (appContext == null) {
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = BaseApplication.getDaoSession(context);
			instance.scoreDao = instance.mDaoSession.getScoreDao();
		}

		return instance;
	}

	public Score loadScore(long id) {
		return scoreDao.load(id);
	}

	public List<Score> loadAllScore() {
		return scoreDao.loadAll();
	}

	/**
	 * query list with where clause ex: begin_date_time >= ? AND end_date_time
	 * <= ?
	 * 
	 * @param where
	 *            where clause, include 'where' word
	 * @param params
	 *            query parameters
	 * @return
	 */

	public List<Score> queryScore(String where, String params) {
		return scoreDao.queryRaw(where, params);
	}

	/**
	 * insert or update note
	 * 
	 * @param
	 * @return insert or update note id
	 */
	public long saveScore(Score score) {
		return scoreDao.insertOrReplace(score);
	}

	/**
	 * insert or update noteList use transaction
	 * 
	 * @param list
	 */
	public void saveScoreLists(final List<Score> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		scoreDao.getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < list.size(); i++) {
					Score score = list.get(i);
					scoreDao.insertOrReplace(score);
				}
			}
		});

	}

	/**
	 * delete all note
	 */
	public void deleteAllScore() {
		scoreDao.deleteAll();
	}

	/**
	 * delete note by id
	 * 
	 * @param id
	 */
	public void deleteScore(long id) {
		scoreDao.deleteByKey(id);
		Log.i(TAG, "delete");
	}

	public void deleteScore(Score score) {
		scoreDao.delete(score);
	}

}
