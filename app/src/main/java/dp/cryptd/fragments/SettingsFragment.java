package dp.cryptd.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import dp.cryptd.R;
import dp.cryptd.activities.BaseActivity;
import dp.cryptd.activities.MainActivity;

/**
 * Settings fragment with options: change name, import picture from Google account, language, theme,
 * prayer notification switch and time.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preference profile_picture = findPreference("profile_picture");
        profile_picture.setOnPreferenceClickListener(preference -> {
            MainActivity.getInstance().getImageUtils().deleteBoth();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(MainActivity.HAS_LOADED_URL, false).commit();
            getActivity().recreate();
            return true;
        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EditTextPreference name = getPreferenceManager().findPreference(BaseActivity.PROFILE_NAME);
        name.setOnPreferenceChangeListener(getRecreateListener());

        ListPreference languages = getPreferenceManager().findPreference(BaseActivity.LANGUAGE);
        languages.setOnPreferenceChangeListener(getRecreateListener());

        ListPreference themes = getPreferenceManager().findPreference(BaseActivity.THEME);
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            themes.setEnabled(false);
        } else {
            themes.setOnPreferenceChangeListener((preference, newValue) -> {
                String value = (String) newValue;
                BaseActivity.setTheme(value);
                return true;
            });
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Preference.OnPreferenceChangeListener getRecreateListener() {
        return (preference, newValue) -> {
            SettingsFragment.this.getActivity().recreate();
            return true;
        };
    }
}