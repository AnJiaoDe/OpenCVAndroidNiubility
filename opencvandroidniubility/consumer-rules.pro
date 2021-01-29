-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
    #这行不能加，加了就会失败
#    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class androidx.** {*;}

#表示不混淆任何包含native方法的类的类名以及native方法名，这个和我们刚才验证的结果是一致
-keepclasseswithmembernames class * {
    native <methods>;
}