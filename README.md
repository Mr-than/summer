# GithubApp
 红岩暑假考核

>2022/7/26
>
>经过了十几天的暴肝，才把这份暑假考核呈现出来，可能效果不是那么好，但是我也确实倾注了大量的心血 ~~*和头发*~~ ，这个过程，比最后的结果更加的重要

## app主要界面

 <img src="https://raw.githubusercontent.com/Mr-than/SummerAssessment/main/imgScreenshot_20220726_171343_com.example.summerasse.jpg" style="zoom:25%;" />

<img src="https://raw.githubusercontent.com/Mr-than/SummerAssessment/main/imgScreenshot_20220726_171351_com.example.summerasse.jpg" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171411_com.example.summerasse.jpg" alt="Screenshot_20220726_171411_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171416_com.example.summerasse.jpg" alt="Screenshot_20220726_171416_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171422_com.example.summerasse.jpg" alt="Screenshot_20220726_171422_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171449_com.example.summerasse.jpg" alt="Screenshot_20220726_171449_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171458_com.example.summerasse.jpg" alt="Screenshot_20220726_171458_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171503_com.example.summerasse.jpg" alt="Screenshot_20220726_171503_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171507_com.example.summerasse.jpg" alt="Screenshot_20220726_171507_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171525_com.example.summerasse.jpg" alt="Screenshot_20220726_171525_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171531_com.example.summerasse.jpg" alt="Screenshot_20220726_171531_com.example.summerasse" style="zoom:25%;" />

<img src="D:\Android's project\SummerAssessment\img\Screenshot_20220726_171559_com.example.summerasse.jpg" alt="Screenshot_20220726_171559_com.example.summerasse" style="zoom:25%;" />

## 用到的技术

### rxjava

用到了rxjava配合retrofit来进行网络请求，retrofit进行网络请求，rxjava流动数据，将数据从repo层流动到vm层，再通过livedata通知到ui，ui进行刷新，这里没有本地数据，本来可以省略repo层，但为了实现一个完整的mvvm架构，所以没有将其省略

### jetpack

官方的jetpack库，主要是用来实现mvvm架构用

### **[DKVideoPlayer](https://github.com/Doikki/DKVideoPlayer)**

这是一个第三方的视频播放器框架，得到学长允许后使用，主要用来播放视频

## 心得

本次考核为期10天，我也是注入了大量的心血，尽管最后的app还有一些小bug，但是我自己还是比较满意的，这次考核让我明白了过程比结果更加的重要，结果会不会如意我们不应该去过度的关心，而是应该把眼光和精力放在过程上，放在这期间的每一行代码，每一个字母上，当自己足够努力，最后的结果也显得没那么重要
