package com.jht.administrator.service;

import android.content.Context;
import android.util.Log;
import com.jht.administrator.application.BaseApplication;
import com.jht.administrator.bean.Note;
import com.jht.administrator.dao.DaoSession;
import com.jht.administrator.dao.NoteDao;
import java.util.List;

public class NoteService {

	private static final String TAG = "GREENDAO";

	private static NoteService instance;

	private static Context appContext;

	private DaoSession mDaoSession;

	private NoteDao noteDao;

	private NoteService() {

	}

	public static NoteService getInstance(Context context) {

		if (instance == null) {
			instance = new NoteService();
			if (appContext == null) {
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = BaseApplication.getDaoSession(context);
			instance.noteDao = instance.mDaoSession.getNoteDao();
		}

		return instance;
	}

	public Note loadNote(long id) {
		return noteDao.load(id);
	}

	public List<Note> loadAllNote() {
		return noteDao.loadAll();
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

	public List<Note> queryNote(String where, String... params) {
		return noteDao.queryRaw(where, params);
	}

	/**
	 * insert or update note
	 * 
	 * @param note
	 * @return insert or update note id
	 */
	public long saveNote(Note note) {
		return noteDao.insertOrReplace(note);
	}

	/**
	 * insert or update noteList use transaction
	 * 
	 * @param list
	 */
	public void saveNoteLists(final List<Note> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		noteDao.getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < list.size(); i++) {
					Note note = list.get(i);
					noteDao.insertOrReplace(note);
				}
			}
		});

	}

	/**
	 * delete all note
	 */
	public void deleteAllNote() {
		noteDao.deleteAll();
	}

	/**
	 * delete note by id
	 * 
	 * @param id
	 */
	public void deleteNote(long id) {
		noteDao.deleteByKey(id);
		Log.i(TAG, "delete");
	}

	public void deleteNote(Note note) {
		noteDao.delete(note);
	}

}
