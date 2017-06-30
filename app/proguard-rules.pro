# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\qsmaxmin\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
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

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/qsmaxmin/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

###################################  基本混淆  #############################################
-flattenpackagehierarchy
-allowaccessmodification
-printmapping map.txt
-dontskipnonpubliclibraryclassmembers
-ignorewarnings
-optimizationpasses 5                                                           # 指定代码的压缩级别
-dontusemixedcaseclassnames                                                     # 是否使用大小写混合
-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar
-dontpreverify                                                                  # 混淆时是否做预校验
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-keep public class * extends android.app.Activity                               # 保持哪些类不被混淆
-keep public class * extends android.app.Application                            # 保持哪些类不被混淆
-keep public class * extends android.app.Service                                # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider                    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference                      # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService              # 保持哪些类不被混淆
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.**
-keep public class com.google.vending.licensing.ILicensingService
-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {                                               # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);     # 保持自定义控件类不被混淆
}
-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
   public void *(android.view.View);
}
-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}
-keep class MyClass                                                             # 保持自己定义的类不被混淆

-dontshrink
-dontoptimize
-keepattributes Exceptions,InnerClasses,Signature,SourceFile,LineNumberTable
-keepattributes *Annotation*
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
-keepclassmembers class fqcn.of.javascript.interface.for.webview {public *;}
-keepclassmembers class **.R$* {public static <fields>;}
-keepclassmembers class * implements java.io.Serializable {*;}


###################################  FounderBase框架  ############################################
-keep class j2w.team.** { *; }
-dontwarn j2w.team.*
#View注入
-keep class * extends java.lang.annotation.Annotation { *; }
#picasso
-dontwarn com.squareup.okhttp.**

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#okio
-dontwarn okio.
-dontwarn in.srain.cube.
-keep class in.srain.cube.**
#EventBus
-keep class de.greenrobot.event.** {*;}
-keepclassmembers class ** {
     public void onEvent*(**);
        void onEvent*(**);
}
-keepclassmembers class * extends j2w.team.common.event.J2WEvent {*;}
-keepclassmembers class $ extends j2w.team.common.event.J2WEvent {*;}

#GSON
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model. { *; }
#AVAX
-dontwarn javax.annotation.
-keep class javax.annotation.
-dontwarn javax.inject.
-keep class javax.inject.

#model防止混淆
-keepclassmembers class * extends j2w.team.mvp.model.J2WModel {*;}
-keepclassmembers class $ extends j2w.team.mvp.model.J2WModel {*;}
-keepclassmembers class * extends com.sugar.fontstore.common.model.BaseModel {*;}
-keepclassmembers class * extends com.sugar.fontstore.common.model.BaseModelReq {*;}
-keepclassmembers class * extends com.sugar.fontstore.common.model.** {
   *;
   public static class *;
}
-keepclassmembers class com.sugar.fontstore.common.greendao.model.**{
 *;
   public static class *;
}

#############################  友盟统计混淆  ####################################
-keepclassmembers class * {
    public <init> (org.json.JSONObject);
}
-keep public class com.sugar.fontstore.R$*{
    public static final int *;
}

################################ greenDao #####################################
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties




################################## aliPay 混淆 ########################################
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

