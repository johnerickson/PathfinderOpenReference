package org.evilsoft.pathfinder.reference.api.contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 *   Copyright 2013 Devon D. Jones <devon.jones@gmail.com>
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 *   Obviously the application Pathfinder Open Reference is licensed under
 *   the GPLv3, and when distributed as a part of that application, the
 *   GPLv3 must be obeyed.  However, when distributed on it's own, or in
 *   another application, it is the opinion of the author that usage of
 *   this file and accessing the ContentProvider it is for via the android
 *   API does not constitute a breach of the GPLv3, regardless of the license
 *   of the application doing so.
 */

public class SpellContract {
	/** The authority for the contacts provider */
	public static final String AUTHORITY = "org.evilsoft.pathfinder.reference.api.spell";

	/** A content:// style uri to the authority for this table */
	public static final Uri SPELL_LIST_URI = Uri.parse("content://" + AUTHORITY
			+ "/spells");

	public static final Uri getSpellHtmlUri(String spellId) {
		return Uri.parse("content://" + AUTHORITY + "/spells/" + spellId
				+ ".html");
	}

	public static final Uri getSpellJsonUri(String spellId) {
		return Uri.parse("content://" + AUTHORITY + "/spells/" + spellId
				+ ".json");
	}

	public static final Uri getClassSpellList(String classId) {
		return Uri.parse("content://" + AUTHORITY + "/classes/" + classId
				+ "/spells");
	}

	/**
	 * The MIME type of {@link #SPELL_LIST_URI} providing a list of spells
	 */
	public static final String SPELL_LIST_CONTENT_TYPE = "vnd.android.cursor.dir/org.evilsoft.pathfinder.reference.api.class.list";

	/**
	 * The MIME type of {@link #getClassSpellList} providing a list of spells
	 */
	public static final String CLASS_SPELL_LIST_CONTENT_TYPE = "vnd.android.cursor.dir/org.evilsoft.pathfinder.reference.api.class.spell.list";

	private SpellContract() {
	}

	public final class SpellListColumns implements BaseColumns {
		private SpellListColumns() {
		}

		/**
		 * Source book name
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SOURCE = "source";

		/**
		 * Section type 'spell'
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String TYPE = "type";

		/**
		 * Section subtype
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SUBTYPE = "subtype";

		/**
		 * Class name
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String NAME = "name";

		/**
		 * class short description, probably null
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String DESCRIPTION = "description";

		/**
		 * PathfinderOpenReference unique pfsrd:// url for class
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String CONTENT_URL = "content_url";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SCHOOL = "school";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SUBSCHOOL = "subschool";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String DESCRIPTOR = "descriptor";
	}

	public final class ClassSpellListColumns implements BaseColumns {
		private ClassSpellListColumns() {
		}

		/**
		 * Source book name
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SOURCE = "source";

		/**
		 * Section type 'class'
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String TYPE = "type";

		/**
		 * Section subtype: 'core', 'base', 'npc', 'prestige'
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SUBTYPE = "subtype";

		/**
		 * Class name
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String NAME = "name";

		/**
		 * class short description, probably null
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String DESCRIPTION = "description";

		/**
		 * PathfinderOpenReference unique pfsrd:// url for class
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String CONTENT_URL = "content_url";

		/**
		 * Class with spell on spell list
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String CLASS = "class";

		/**
		 * Level of the spell for this class
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String LEVEL = "level";

		/**
		 * 'ARCANE' or 'DIVINE'
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String MAGIC_TYPE = "magic_type";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SCHOOL = "school";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String SUBSCHOOL = "subschool";

		/**
		 * School of the spell
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String DESCRIPTOR = "descriptor";
	}
}
