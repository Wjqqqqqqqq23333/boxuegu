# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/student/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5 #压缩级别
-dontusemixedcaseclassnames #大小写混合
-dontpreverify   #预校验
-verbose #混淆日志

#混淆的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.BroadcastReceiver
-keep public class * extends android.app.ContentProvider
-keep public class * extends android.app.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.iLicensingService
-keepclasseswithmembernames class *{
      native <methods>;
}
-keepclasseswithmembers class *{
    public <init>(android.content.Context,android.util.AtributeSet, int);
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
 }
-keepclassmembers enum * {
    public static **[] values();
    public static ** valuesof(java.lang.String);
 }
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
 }


# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
