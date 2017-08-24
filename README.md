# TinkerDemo-master
注意gradle文件的
ext {
    tinkerEnabled = true
    tinkerOldApkPath = "${bakPath}/app-release-0824-15-32-27.apk"
    //proguard mapping file to build patch apk
    tinkerApplyMappingPath = "${bakPath}/"
    //resource R.txt to build patch apk, must input if there is resource changed
    tinkerApplyResourcePath = "${bakPath}/app-release-0824-15-32-27-R.txt"
}

是原版本文件，所以每次发包可以备份

https://github.com/sea2/TinkerDemo-master/blob/master/raw/QQ%E6%88%AA%E5%9B%BE20170824162308.png
