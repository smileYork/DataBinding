package com.jht.administrator.service;

import java.util.List;


import android.content.Context;
import android.util.Log;
import com.jht.administrator.application.BaseApplication;
import com.jht.administrator.bean.User;
import com.jht.administrator.dao.DaoSession;
import com.jht.administrator.dao.UserDao;

public class UserService {

	private static final String TAG = "GREENDAO";

	private static UserService instance;

	private static Context appContext;

	private DaoSession mDaoSession;

	private UserDao userDao;

	public static UserService getInstance(Context context) {

		if (instance == null) {
			instance = new UserService();
			if (appContext == null) {
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = BaseApplication.getDaoSession(context);
			instance.userDao = instance.mDaoSession.getUserDao();
		}

		return instance;
	}

	public User loadUser(long id) {
		return userDao.load(id);
	}

	public List<User> loadAllUser() {
		return userDao.loadAll();
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

	// String... 表示传入的参数不确定
	public List<User> queryUser(String where, String... params) {
		return userDao.queryRaw(where, params);
	}

	/**
	 * insert or update note
	 * 
	 * @param
	 * @return insert or update note id
	 */
	public long saveUser(User user) {
		return userDao.insertOrReplace(user);
	}

	/**
	 * insert or update noteList use transaction
	 * 
	 * @param list
	 */
	public void saveUserLists(final List<User> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		userDao.getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < list.size(); i++) {
					User user = list.get(i);
					userDao.insertOrReplace(user);
				}
			}
		});

	}

	/**
	 * delete all note
	 */
	public void deleteAllUser() {
		userDao.deleteAll();
	}

	/**
	 * delete note by id
	 * 
	 * @param id
	 */
	public void deleteUser(long id) {
		userDao.deleteByKey(id);
		Log.i(TAG, "delete");
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

}
