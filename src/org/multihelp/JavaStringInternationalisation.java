/**
 * Created by Daniel McEnnis on 7/10/2018
 * <p>
 * Copyright Daniel McEnnis 2015
 */

package org.multihelp;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Default Description Google Interview Project
 */
public class JavaStringInternationalisation {

    public static Locale nativeLocale = Locale.ENGLISH;

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

//    Translation translate = null;

    protected boolean autoTranslate;

    public String internationalize(String source){
        return internationalize(nativeLocale,source,Locale.getDefault());
    }

    public String internationalize(Locale sourceLocale, String source){
        return internationalize(sourceLocale,source,Locale.getDefault());
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
//        if(translate == null){
//            translate = TranslateOptions.getDefaultInstance().getService();
//        }
//
//        return translate.translate(source,sourceLocale,destinationLocale);
        return "";
    }
}
