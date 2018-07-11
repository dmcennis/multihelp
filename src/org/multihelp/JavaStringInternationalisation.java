/**
 * Created by Daniel McEnnis on 7/10/2018
 * <p>
 * Copyright Daniel McEnnis 2015
 */

package org.multihelp;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Default Description Google Interview Project
 */
public class JavaStringInternationalisation {

    public static Locale defaultDocumenationLanguage = Locale.ENGLISH;

    public static Locale defaultDisplayLanguage = Locale.getDefault();

    public static class OnlineTranslation extends java.util.ResourceBundle.Control{
        @Override
        public Locale getFallbackLocale(String baseName, Locale locale) {
            return super.getFallbackLocale(baseName, locale);
        }
    }

    // implements the proxy pattern
    public static class TranslateAPIPool {

    }

    /**
     * Default constructor for JavaStringInternationalisation
     */
    public JavaStringInternationalisation() {

    }

    ResourceBundle helpResourceBundle = null;

    Translate translate = null;

    protected boolean autoTranslate;

    public String internationalize(String source){
        return internationalize(defaultDocumenationLanguage,source,defaultDisplayLanguage);
    }

    public String internationalize(Locale sourceLocale, String source){
        return internationalize(sourceLocale,source,defaultDisplayLanguage);
    }

    public String internationalize(Locale sourceLocale, String source, Locale destinationLocale){
        if(sourceLocale == destinationLocale){
            return source;
        }
        if (helpResourceBundle == null){
            helpResourceBundle = ResourceBundle.getBundle("MultiHelpInternationalizations.txt",destinationLocale);
        }
        if(helpResourceBundle.containsKey(source)){
            return helpResourceBundle.getString(source);
        }
        if(autoTranslate) {
            if (translate == null) {
                TranslateOptions.Builder builder = TranslateOptions.newBuilder();
                builder.setTargetLanguage(destinationLocale.getDisplayLanguage());
                defaultDisplayLanguage = destinationLocale;
                translate = (new TranslateOptions.DefaultTranslateFactory()).create(builder.build());
            }
            return (translate.translate(source)).getTranslatedText();
        }else{
            return source;
        }
    }
}
