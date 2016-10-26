package chat.test.com.ping.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * A sharedPreferences implementation to facilitate easy use of SharedPreferences with utility methods to save, get and delete data
 * @author Devesh Shetty
 */
public class PreferencesStorage
{
    //the key associated with a user's username
    public static final String USERNAME = "USERNAME";

    //the key associated with a user's id
    public static final String USER_ID = "USER_ID";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    /**
     * Sets the value mapped by key
     * @param ctx  Context associated with the preferences
     * @param key
     * @param value  the value to be stored
     */
    public static void setValueForKey(Context ctx, String key, String value)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Gets the value mapped by the given key
     * @param ctx  Context associated with the preferences
     * @param key
     * @return value associated with the given key
     */
    public static String getValueForKey(Context ctx, String key)
    {
        return getSharedPreferences(ctx).getString(key, null);
    }

    /**
     * Clears all the stored data
     * @param ctx Context associated with the preferences
     */
    public static void clearData(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }


}