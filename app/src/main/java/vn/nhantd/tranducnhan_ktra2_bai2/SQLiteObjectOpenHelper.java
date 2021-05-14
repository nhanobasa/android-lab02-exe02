package vn.nhantd.tranducnhan_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import vn.nhantd.tranducnhan_ktra2_bai2.model.QuyenGop;

/**
 * Created by Tran Duc Nhan on 2021-05-14
 */
public class SQLiteObjectOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuyenGop.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteObjectOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table quyengop(id integer primary key autoincrement, name text, city text, date text, money real)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addQuyenGop(QuyenGop quyenGop) {
        ContentValues values = new ContentValues();
        values.put("name", quyenGop.getName());
        values.put("city", quyenGop.getCity());
        values.put("date", quyenGop.getDate());
        values.put("money", quyenGop.getMoney());

        SQLiteDatabase st = getWritableDatabase();
        return st.insert("quyengop", null, values);
    }

    public List<QuyenGop> getAll() {
        List<QuyenGop> quyenGopList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("quyengop", null, null, null, null, null, null);
        while ((cursor.moveToNext())) {
            quyenGopList.add(new QuyenGop(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));

        }
        return quyenGopList;
    }

    public int deleteQuyenGop(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("quyengop", whereClause, whereArgs);
    }

    public int update(QuyenGop quyengop) {
        ContentValues values = new ContentValues();
        values.put("name", quyengop.getName());
        values.put("city", quyengop.getCity());
        values.put("date", quyengop.getDate());
        values.put("money", quyengop.getMoney());

        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(quyengop.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("quyengop",
                values, whereClause, whereArgs);
    }

    public List<QuyenGop> search(String key) {
        List<QuyenGop> quyenGopList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereclause = "name LIKE '%" + key + "%'";

        Cursor cursor = sqLiteDatabase.query("quyengop", null, whereclause, null, null, null, null);
        while (cursor.moveToNext()) {
            quyenGopList.add(
                    new QuyenGop(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)
                            , cursor.getDouble(4)));

        }
        return quyenGopList;
    }
}
