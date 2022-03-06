package com.beansoft.intellij.lang;

import com.intellij.lang.*;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class LanguageUtilEx {
    public static Icon getIcon(Language language) {
        LanguageFileType associatedLanguage = language.getAssociatedFileType();
        return associatedLanguage != null ? associatedLanguage.getIcon() : null;
    }

    public static String getExtension(Language language) {
        LanguageFileType fileType = language.getAssociatedFileType();
        return fileType == null ? "txt" : fileType.getDefaultExtension();
    }

//    public static @NotNull
//    List<Language> getFileLanguagesWithUnknown() {
//        List<Language> languages =  LanguageUtil.getFileLanguages();
//
//        List<String> langsInDB = FavoriteDataService.getInstance().queryDistinctLanguages();
//
//        // 添加数据库中不能识别的文件类型
//        for (String ext :
//                langsInDB) {
//            if (languages.stream().noneMatch(language -> language.getAssociatedFileType() != null &&
//                    ext.equals(language.getAssociatedFileType().getDefaultExtension()))) {
//                languages.add(new FileTypeHolderLanguage((ext)));
//            }
//        }
//
//        return languages;
//    }


    public static List<FileType> getRegisteredTextLanguageFileTypes() {
        List<FileType> types = ContainerUtil.filter(getRegisteredFilesTypes(),
                fileType -> fileType instanceof LanguageFileType && !fileType.isReadOnly());
        types.sort((o1, o2) -> o1.getDescription().compareToIgnoreCase(o2.getDescription()));
        return types;
    }

    public static LanguageFileType getLanguageFileTypeByName(String name) {
        List<FileType> types = ContainerUtil.filter(getRegisteredTextLanguageFileTypes(),
                fileType -> fileType instanceof LanguageFileType && fileType.getName().equals(name));
        return (LanguageFileType) ContainerUtil.getFirstItem(types);
    }

    @NotNull
    private static Set<FileType> getRegisteredFilesTypes() {
        return ContainerUtil.set(FileTypeManager.getInstance().getRegisteredFileTypes());
    }

    /**
     * 包含所有 Language, 扩展名为空的也允许, 例如 Dockerfile.
     * @see LanguageUtil#isFileLanguage(Language)
     * @param language
     * @return
     */
    public static boolean isFileLanguage(@NotNull Language language) {
        if (language instanceof DependentLanguage || language instanceof InjectableLanguage) return false;
        if (LanguageParserDefinitions.INSTANCE.forLanguage(language) == null) return false;
        LanguageFileType type = language.getAssociatedFileType();
        if( type != null && StringUtil.isEmpty(type.getDefaultExtension())) {
            System.out.println("Empty extension language:" + language.getDisplayName() + " file type =" + type.getName());
        }
//        if (type == null) return false;
        return type != null;
//        return StringUtil.isNotEmpty(type.getDefaultExtension());
    }

    public static @NotNull List<Language> getFileLanguages() {
//        return LanguageUtil.getLanguages((lang) -> isFileLanguage(lang));
        return getLanguages((lang) -> isFileLanguage(lang));
    }


//    public static @NotNull List<Language> getLanguages(Function<? super Language, Boolean> filter) {
//        List<Language> result = new ArrayList<>();
//        for (Language language : Language.getRegisteredLanguages()) {
//            if (!filter.apply(language)) continue;
//            result.add(language);
//        }
//        result.sort(LanguageUtil.LANGUAGE_COMPARATOR);
//        return result;
//    }

    /**
     * Port from 2022.1 to old IDEA.
     * This method is changed since 2022.1, to a new signature:
     * public static @NotNull List<Language> getLanguages(@NotNull Predicate<? super Language> filter) {
     * @param filter
     * @see LanguageUtil#getLanguages(Function)
     * @return
     */
    public static @NotNull List<Language> getLanguages(@NotNull Predicate<? super Language> filter) {
        List<Language> result = new ArrayList<>();
        for (Language language : Language.getRegisteredLanguages()) {
            if (filter.test(language)) {
                result.add(language);
            }
        }
        result.sort(LanguageUtil.LANGUAGE_COMPARATOR);
        return result;
    }
}
