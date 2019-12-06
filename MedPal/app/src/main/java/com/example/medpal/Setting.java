package com.example.medpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner languages_select;
    String result;
    String prefsFile;
    TextView settings_Text;
    TextView text_Size_Text;
    TextView colour_Theme_Text;
    TextView change_Avatar_Text;
    TextView user_Email_Text;
    TextView allow_Automatic_Updates;
    TextView app_Name;
    TextView app_Version_Text;
    TextView languages_Text;
    TextView sign_out_Text;
    TextView medpal_Text;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Create shared preferences object
        this.sharedPrefs = getApplicationContext().getSharedPreferences(prefsFile, MODE_PRIVATE);
        //Get preferences themeInteger themePref = 1;​
        //Load in saved theme, if none is selected default to default

        /* Set strings for translation */
        this.languages_select = findViewById(R.id.languagesSpinner);
        this.settings_Text = findViewById(R.id.settingsTitle);
        this.text_Size_Text = findViewById(R.id.textSizeText);
        this.colour_Theme_Text = findViewById(R.id.colourThemeText);
        this.change_Avatar_Text = findViewById(R.id.changeAvatar);
        this.user_Email_Text = findViewById(R.id.userEmail);
        this.allow_Automatic_Updates = findViewById(R.id.allowUpdatesText);
        this.app_Version_Text = findViewById(R.id.appVersionText);
        this.languages_Text = findViewById(R.id.languagesText);
        this.sign_out_Text = findViewById(R.id.signOutButton);

        //Sets the language of the page from the user's preferences
        this.changeLanguage(this.sharedPrefs.getString("language",null));

        //Languages Spinner
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);        //Text Size Spinner

        //Languages Adapter
        languages_select.setAdapter(adapter);
        languages_select.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String language = (String) adapterView.getItemAtPosition(position);
        changeLanguage(language);
    }

    private void changeLanguage(String language) {
        SharedPreferences.Editor myEdit = sharedPrefs.edit();
        myEdit.putString("language", language);
        myEdit.commit();

        switch (language){
            case "English":
                setEnglish();
                break;
            case "French":
                setFrench();
                break;
            case "Polish":
                setPolish();
                break;
            case "Spanish":
                setSpanish();
                break;
            case "Chinese":
                setChinese();
                break;
                default: break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        
    }

    public void setEnglish() {
        this.settings_Text.setText(R.string.settings);
        this.text_Size_Text.setText(R.string.text_size);
        this.colour_Theme_Text.setText(R.string.colour_theme);
        this.change_Avatar_Text.setText(R.string.change_avatar);
        this.user_Email_Text.setText(R.string.user_email);
        this.allow_Automatic_Updates.setText(R.string.allowUpdatesText);
        this.app_Version_Text.setText(R.string.app_version);
        this.languages_Text.setText(R.string.languages);
        this.sign_out_Text.setText(R.string.sign_out);
    }
    public void setSpanish(){
        this.settings_Text.setText(R.string.settings_spanish);
        this.text_Size_Text.setText(R.string.text_size_spanish);
        this.colour_Theme_Text.setText(R.string.colour_theme_spanish);
        this.change_Avatar_Text.setText(R.string.change_avatar_spanish);
        this.user_Email_Text.setText(R.string.user_email_spanish);
        this.allow_Automatic_Updates.setText(R.string.allow_automatic_updates_spanish);
        this.app_Version_Text.setText(R.string.app_version_spanish);
        this.languages_Text.setText(R.string.languages_spanish);
        this.sign_out_Text.setText(R.string.sign_out_spanish);
    }
    public void setFrench() {
        this.settings_Text.setText(R.string.settings_french);
        this.text_Size_Text.setText(R.string.text_size_french);
        this.colour_Theme_Text.setText(R.string.colour_theme_french);
        this.change_Avatar_Text.setText(R.string.change_avatar_french);
        this.user_Email_Text.setText(R.string.user_email_french);
        this.allow_Automatic_Updates.setText(R.string.allow_automatic_updates_french);
        this.app_Version_Text.setText(R.string.app_version_french);
        this.languages_Text.setText(R.string.languages_french);
        this.sign_out_Text.setText(R.string.sign_out_french);
    }
    public void setPolish(){
        this.settings_Text.setText(R.string.settings_polish);
        this.text_Size_Text.setText(R.string.text_size_polish);
        this.colour_Theme_Text.setText(R.string.colour_theme_polish);
        this.change_Avatar_Text.setText(R.string.change_avatar_polish);
        this.user_Email_Text.setText(R.string.user_email_polish);
        this.allow_Automatic_Updates.setText(R.string.allow_automatic_updates_polish);
        this.app_Version_Text.setText(R.string.app_version_polish);
        this.languages_Text.setText(R.string.languages_polish);
        this.sign_out_Text.setText(R.string.sign_out_polish);
    }


    public void setChinese() {
        this.settings_Text.setText(R.string.settings_chinese);
        this.text_Size_Text.setText(R.string.text_size_chinese);
        this.colour_Theme_Text.setText(R.string.colour_theme_chinese);
        this.change_Avatar_Text.setText(R.string.change_avatar_chinese);
        this.user_Email_Text.setText(R.string.user_email_chinese);
        this.allow_Automatic_Updates.setText(R.string.allow_automatic_updates_chinese);
        this.app_Version_Text.setText(R.string.app_version_chinese);
        this.languages_Text.setText(R.string.languages_chinese);
        this.sign_out_Text.setText(R.string.sign_out_chinese);
    }

}

