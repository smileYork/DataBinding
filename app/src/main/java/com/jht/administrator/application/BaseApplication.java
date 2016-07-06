package com.jht.administrator.application;


import android.app.Application;
import android.content.Context;
import com.jht.administrator.dao.DaoMaster;
import com.jht.administrator.dao.DaoSession;
import com.jht.administrator.global.Contants;

public class BaseApplication extends Application {

	private static BaseApplication myApplication;

	private static DaoMaster daoMaster;

	private static DaoSession daoSession;

	@Override
	public void onCreate() {

		super.onCreate();

		if (myApplication == null) {

			myApplication = this;

		}
	}

	/**
	 * 取得DaoMaster
	 * 
	 * @param context
	 * @return
	 */
	public static DaoMaster getDaoMaster(Context context) {

		if (daoMaster == null) {
			// 此处生成数据库名称
			DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, Contants.DATABASE, null);

			daoMaster = new DaoMaster(helper.getWritableDatabase());

		}

		return daoMaster;

	}

	/**
	 * 取得DaoSession
	 * 
	 * @param context
	 * @return
	 */

	public static DaoSession getDaoSession(Context context) {

		if (daoSession == null) {

			if (daoMaster == null) {

				daoMaster = getDaoMaster(context);

			}

			daoSession = daoMaster.newSession();
		}

		return daoSession;
	}

}
