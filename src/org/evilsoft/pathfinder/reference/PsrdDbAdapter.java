package org.evilsoft.pathfinder.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PsrdDbAdapter {
	private Context context;
	public SQLiteDatabase database;
	private PsrdDbHelper dbHelper;
	private static final String TAG = "PsrdDbAdapter";

	public PsrdDbAdapter(Context context) {
		this.context = context;
	}

	public PsrdDbAdapter open() throws SQLException {
		dbHelper = new PsrdDbHelper(context);
		database = dbHelper.openDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public Cursor fetchSectionByType(String sectionType) {
		return fetchSectionByType(sectionType, null);
	}

	public String[] toStringArray(List<String> input) {
		String[] retarr = new String[input.size()];
		for (int i = 0; i < input.size(); i++) {
			retarr[i] = input.get(i);
		}
		return retarr;
	}

	public Cursor fetchSectionByType(String sectionType, String sectionSubType) {
		List<String> args = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT section_id, name");
		sb.append(" FROM sections");
		String where = " WHERE";
		if (sectionType != null) {
			sb.append(where);
			sb.append(" type = ?");
			where = "  AND";
			args.add(sectionType);
		}
		if (sectionSubType != null) {
			sb.append(where);
			sb.append(" subtype = ?");
			args.add(sectionSubType);
		}
		sb.append(" ORDER BY name");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public ArrayList<HashMap<String, String>> getPath(String sectionId) {
		ArrayList<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();
		Cursor curs = fetchSection(sectionId);
		Log.e(TAG, sectionId);
		boolean has_rows = curs.moveToFirst();
		Log.e(TAG, ((Boolean) has_rows).toString());
		if (has_rows) {
			String parentId = curs.getString(1);
			HashMap<String, String> element = new HashMap<String, String>();
			element.put("id", curs.getString(0));
			element.put("name", curs.getString(2));
			path.add(element);
			while (parentId != null) {
				Cursor curs2 = fetchSection(parentId);
				curs2.moveToFirst();
				parentId = curs2.getString(1);
				element = new HashMap<String, String>();
				element.put("id", curs2.getString(0));
				element.put("name", curs2.getString(2));
				path.add(element);
			}
		}
		return path;
	}

	public Cursor fetchSection(String sectionId) {
		List<String> args = new ArrayList<String>();
		args.add(sectionId);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT section_id, parent_id, name");
		sb.append(" FROM sections");
		sb.append(" WHERE section_id = ?");
		sb.append(" LIMIT 1");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public Cursor fetchSectionByParentId(String parentId) {
		List<String> args = new ArrayList<String>();
		args.add(parentId);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT section_id, name");
		sb.append(" FROM sections");
		sb.append(" WHERE parent_id = ?");
		sb.append(" ORDER BY name");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public Cursor fetchFullSection(String section_id) {
		List<String> args = new ArrayList<String>();
		args.add(section_id);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT node.section_id, node.lft, node.rgt, node.parent_id, node.type, node.subtype, node.name, node.abbrev, node.source, node.description, node.body");
		sb.append(" FROM sections AS node, sections AS parent");
		sb.append(" WHERE node.lft BETWEEN parent.lft AND parent.rgt");
		sb.append("  AND parent.section_id = ?");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public Cursor getAbilityTypes(String sectionId) {
		List<String> args = new ArrayList<String>();
		args.add(sectionId);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ability_type");
		sb.append(" FROM ability_types");
		sb.append(" WHERE section_id = ?");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public Cursor getAfflictionDetails(String section_id) {
		List<String> args = new ArrayList<String>();
		args.add(section_id);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT contracted, save, onset, frequency, effect, initial_effect, secondary_effect, cure");
		sb.append(" FROM affliction_details");
		sb.append(" WHERE section_id = ?");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}

	public Cursor getAnimalCompanionDetails(String section_id) {
		List<String> args = new ArrayList<String>();
		args.add(section_id);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ac, attack, ability_scores, special_qualities, special_attacks, size, speed, level");
		sb.append(" FROM animal_companion_details");
		sb.append(" WHERE section_id = ?");
		String sql = sb.toString();
		return database.rawQuery(sql, toStringArray(args));
	}
}