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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //gets the logo in the action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo1_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Create shared preferences object
        SharedPreferences sharedPrefs = getSharedPreferences(prefsFile, MODE_PRIVATE);
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

        //Languages Spinner
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);        //Text Size Spinner

        //Languages Adapter
        languages_select.setAdapter(adapter);
        languages_select.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String language = (String) adapterView.getItemAtPosition(position);
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
        this.medpal_Text.setText(R.string.medpal);
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
        app_Name.setText(R.string.app_name_spanish);
        allow_Automatic_Updates.setText(R.string.allow_automatic_updates_spanish);
        app_Version_Text.setText(R.string.app_version_spanish);
        change_Avatar_Text.setText(R.string.change_avatar_spanish);
        colour_Theme_Text.setText(R.string.colour_theme_spanish);
        languages_Text.setText(R.string.languages_spanish);
        medpal_Text.setText(R.string.medpal_spanish);
        settings_Text.setText(R.string.settings_spanish);
        sign_out_Text.setText(R.string.sign_out_spanish);
        user_Email_Text.setText(R.string.user_email_spanish);
        text_Size_Text.setText(R.string.text_size_spanish);
    }
    public void setFrench() {
        app_Name.setText(R.string.app_name_french);
        allow_Automatic_Updates.setText(R.string.allow_automatic_updates_french);
        app_Version_Text.setText(R.string.app_version_french);
        change_Avatar_Text.setText(R.string.change_avatar_french);
        colour_Theme_Text.setText(R.string.colour_theme_french);
        languages_Text.setText(R.string.languages_french);
        medpal_Text.setText(R.string.medpal_french);
        settings_Text.setText(R.string.settings_french);
        sign_out_Text.setText(R.string.sign_out_french);
        user_Email_Text.setText(R.string.user_email_french);
        text_Size_Text.setText(R.string.text_size_french);
    }
    public void setPolish(){
        app_Name.setText(R.string.app_name_polish);
        allow_Automatic_Updates.setText(R.string.allow_automatic_updates_polish);
        app_Version_Text.setText(R.string.app_version_polish);
        change_Avatar_Text.setText(R.string.change_avatar_polish);
        colour_Theme_Text.setText(R.string.colour_theme_polish);
        languages_Text.setText(R.string.languages_polish);
        medpal_Text.setText(R.string.medpal_polish);
        settings_Text.setText(R.string.settings_polish);
        sign_out_Text.setText(R.string.sign_out_polish);
        user_Email_Text.setText(R.string.user_email_polish);
        text_Size_Text.setText(R.string.text_size_polish);
    }


    public void setChinese() {
        app_Name.setText(R.string.app_name_chinese);
        allow_Automatic_Updates.setText(R.string.allow_automatic_updates_chinese);
        app_Version_Text.setText(R.string.app_version_chinese);
        change_Avatar_Text.setText(R.string.change_avatar_chinese);
        colour_Theme_Text.setText(R.string.colour_theme_chinese);
        languages_Text.setText(R.string.languages_chinese);
        medpal_Text.setText(R.string.medpal_chinese);
        settings_Text.setText(R.string.settings_chinese);
        sign_out_Text.setText(R.string.sign_out_chinese);
        user_Email_Text.setText(R.string.user_email_chinese);
        text_Size_Text.setText(R.string.text_size_chinese);
    }

}

